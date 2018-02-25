package lddm.computacao.pucminas.organapp.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;

import lddm.computacao.pucminas.organapp.R;
import lddm.computacao.pucminas.organapp.arquivos.Arquivo;

/**
 * Created by gabri on 15/10/2017.
 */

public class LinkFragment  extends Fragment {

    private static final String ARG_PARAM = "";
    final public String ARQUIVO_TAG = "InserirFragment";
    SwipeMenuListView lview;
    ArrayList<String> linkListName;
    ArrayList<Arquivo> linkList;
    ArrayAdapter<String> listviewAdapter;

    public void setLinkList(ArrayList<Arquivo> linkList) {
        this.linkList = linkList;
    }

    public ArrayList<Arquivo> getLinkList() {
        return linkList;
    }

    public static LinkFragment newInstance() {
        LinkFragment fragment = new LinkFragment();
        return fragment;
    }

    public static LinkFragment newInstance(String param) {
        LinkFragment fragment = new LinkFragment();
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

        linkListName = new ArrayList<>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.link_fragment, container, false);

        inciaListas();

        lview = (SwipeMenuListView) view.findViewById(R.id.lista_arquivos);
        listviewAdapter = new ArrayAdapter<>(
                getActivity(), android.R.layout.simple_list_item_1, linkListName);
        lview.setAdapter(listviewAdapter);



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

                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xFF,
                        0xFF, 0xFF)));
                // set item width
                openItem.setWidth(170);
                // set a icon
                openItem.setIcon(R.drawable.ic_open);
                // add to menu
                menu.addMenuItem(openItem);
            }
        };
        lview.setMenuCreator(creator);
        lview.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        String item = linkListName.get(position);
                        //remover
                        listviewAdapter.remove(item);
                        remover(item);

                        //atualizar
                        listviewAdapter.notifyDataSetChanged();
                        //Notificação
                        Toast.makeText(getContext(),"Conteúdo removido.",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        String link = linkListName.get(position);
                        Intent intent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse(link));
                        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                            startActivity(intent);
                        }
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
                bundle.putString("key","link");
                fragment.setArguments(bundle);
                fragment.show(getFragmentManager(), ARQUIVO_TAG);
            }
        });

        listviewAdapter.notifyDataSetChanged();



        return view;
    }

    private void inciaListas() {

        if (!linkList.isEmpty()) {
            for(int i = 0; i< linkList.size(); i ++ ) {
                linkListName.add(linkList.get(i).getLink());
            }

        }

    }

    private void remover(String valor) {
        for(int i = 0; i< linkListName.size(); i++){
            if(linkListName.get(i).equals(valor)) {
                linkListName.remove(i);
                i = linkListName.size();
            }
        }
    }

}
