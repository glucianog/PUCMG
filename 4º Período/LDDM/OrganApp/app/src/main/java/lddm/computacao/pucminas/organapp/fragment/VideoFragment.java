package lddm.computacao.pucminas.organapp.fragment;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;
import java.util.List;

import lddm.computacao.pucminas.organapp.R;
import lddm.computacao.pucminas.organapp.arquivos.Arquivo;

/**
 * Created by gabri on 15/10/2017.
 */

public class VideoFragment  extends Fragment {

    private static final String ARG_PARAM = "";
    final public String ARQUIVO_TAG = "InserirFragment";
    public ArrayList<Arquivo> arquivosList;
    public ArrayList<String> videosNome;
    ArrayAdapter<String> adapter;
    SwipeMenuListView lview;


    public static VideoFragment newInstance() {
        VideoFragment fragment = new VideoFragment();
        return fragment;
    }

    public ArrayList<Arquivo> getArquivos() {
        return arquivosList;
    }

    public void setArquivos(ArrayList<Arquivo> arquivos) {
        this.arquivosList = arquivos;
    }

    public static VideoFragment newInstance(String param) {
        VideoFragment fragment = new VideoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM,param);
        fragment.setArguments(args);
        return fragment;
    }

    String paramText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            paramText = getArguments().getString(ARG_PARAM);
        }
        videosNome = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.video_fragment, container, false);

        inciaListas();


        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,
                videosNome);

        lview = (SwipeMenuListView) view.findViewById(R.id.lista_arquivos);
        lview.setAdapter(adapter);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(170);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);

                // create "openVideo" item
                SwipeMenuItem openVideo = new SwipeMenuItem(
                        getContext());
                // set item background
                openVideo.setBackground(new ColorDrawable(Color.rgb(0x00,
                        0x00, 0xBB)));
                // set item width
                openVideo.setWidth(170);
                // set a icon
                openVideo.setIcon(R.drawable.ic_play);
                // add to menu
                menu.addMenuItem(openVideo);
            }
        };
        lview.setMenuCreator(creator);

        lview.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        String item = videosNome.get(position);
                        //remover
                        adapter.remove(item);
                        remover(item);

                        //atualizar
                        adapter.notifyDataSetChanged();
                        //Notificação
                        Toast.makeText(getContext(),"Conteúdo removido.",
                                Toast.LENGTH_SHORT).show();
                        break;

                    case 1:
                        YoutubeFragment fragment = YoutubeFragment.newInstance(videosNome.get(position));
                        getFragmentManager().beginTransaction().replace(R.id.main_content ,fragment)
                                .commit();


                    break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });


        FloatingActionButton fButton = (FloatingActionButton) view.findViewById(R.id.adcionarBTN);
        fButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment fragment = InserirFragment.newInstance();
                Bundle bundle = new Bundle();
                bundle.putString("key","video");
                fragment.setArguments(bundle);
                fragment.show(getFragmentManager(), ARQUIVO_TAG);
            }
        });

        return view;
    }

    private void remover(String valor) {
        for(int i = 0; i< arquivosList.size(); i++){
            if(arquivosList.get(i).getLink().equals(valor)) {
                arquivosList.remove(i);
                i = arquivosList.size();
            }
        }
    }

    private void inciaListas() {
        if(!arquivosList.isEmpty()) {
            for(int i = 0; i< arquivosList.size(); i ++ ) {
                videosNome.add(arquivosList.get(i).getLink());
            }

        }

    }
}
