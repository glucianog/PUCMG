package lddm.computacao.pucminas.organapp.fragment;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import lddm.computacao.pucminas.organapp.PdfFragment;
import lddm.computacao.pucminas.organapp.R;
import lddm.computacao.pucminas.organapp.arquivos.Arquivo;

import static android.app.Activity.RESULT_OK;

/**
 * Created by gabri on 15/10/2017.
 */

public class ArquivoFragment  extends Fragment{

    private static final String ARG_PARAM = "";
    private static final int PDF_CODE = 1;
    final public String ARQUIVO_TAG = "InserirFragment";

    int mStackLevel = 0;
    public static final int DIALOG_FRAGMENT = 1;

    ArrayList<String> nomeArquivos;
    ArrayList<Arquivo> arquivosList;
    ArrayAdapter<String> adapter;
    SwipeMenuListView lview;

    private Activity activity;


    public void setArquivosList(ArrayList<Arquivo> arquivosList) {
        this.arquivosList = arquivosList;
    }

    public ArrayList<Arquivo> getArquivosList() {
        return arquivosList;
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        this.activity = activity;
    }

    public static ArquivoFragment newInstance() {
        ArquivoFragment fragment = new ArquivoFragment();
        return fragment;
    }

    public static ArquivoFragment newInstance(String param) {
        ArquivoFragment fragment = new ArquivoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM,param);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ( savedInstanceState != null) {
            mStackLevel = savedInstanceState.getInt("level");
        }
        nomeArquivos = new ArrayList<>();



    }

    private void inciaListas() {
        if(!arquivosList.isEmpty()) {
            for(int i = 0; i< arquivosList.size(); i ++ ) {
                nomeArquivos.add(arquivosList.get(i).getLink().toString());
            }
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("level", mStackLevel);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        inciaListas();

        View view = inflater.inflate(R.layout.arquivo_fragment, container, false);


        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,
                nomeArquivos);

        lview = (SwipeMenuListView) view.findViewById(R.id.lista_arquivos);
        lview.setAdapter(adapter);

        FloatingActionButton fButton = (FloatingActionButton) view.findViewById(R.id.adcionarBTN);
        fButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivityForResult(intent, PDF_CODE);
                }

            }
        });

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
                openItem.setIcon(R.drawable.ic_pdf);
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
                        String item = nomeArquivos.get(position);
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
                        String path = nomeArquivos.get(position);
                        PdfFragment pdfFragment = PdfFragment.newInstance(path,nomeArquivos.get(position));
                        getFragmentManager().beginTransaction().replace(R.id.main_content,pdfFragment)
                                .commit();

                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PDF_CODE && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            String uriString = uri.toString();
            try {
                arquivosList.add(new Arquivo(uri.toString()));
            } catch (Exception e) {
                e.printStackTrace();
            }


            nomeArquivos.add(uriString);
            adapter.notifyDataSetChanged();

        }


    }

    private void remover(String valor) {
        for(int i = 0; i< arquivosList.size(); i++){
            if(arquivosList.get(i).getLink().equals(valor)) {
                arquivosList.remove(i);
                i = arquivosList.size();
            }
        }
    }


}





