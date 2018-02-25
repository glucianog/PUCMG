package lddm.computacao.pucminas.organapp.data;

import java.util.ArrayList;

import lddm.computacao.pucminas.organapp.fragment.ArquivoFragment;
import lddm.computacao.pucminas.organapp.fragment.LinkFragment;
import lddm.computacao.pucminas.organapp.fragment.MainFragment;
import lddm.computacao.pucminas.organapp.fragment.VideoFragment;
import lddm.computacao.pucminas.organapp.R;
import lddm.computacao.pucminas.organapp.navigationdrawer.NavMenuModel;

/**
 * Created by gabri on 15/10/2017.
 */

public class Constant {

    public static ArrayList<NavMenuModel> getMenuNavigasi(){
        ArrayList<NavMenuModel> menu = new ArrayList<>();

        menu.add(new NavMenuModel("Home", R.drawable.ic_home, MainFragment.newInstance("Página Principal")));
        inserirNoMenu(menu,"1º Período");
        inserirNoMenu(menu,"2º Período");
        inserirNoMenu(menu,"3º Período");
        inserirNoMenu(menu,"4º Período");
        inserirNoMenu(menu,"5º Período");
        inserirNoMenu(menu,"6º Período");
        inserirNoMenu(menu,"7º Período");
        inserirNoMenu(menu,"8º Período");


        return menu;
    }


    public static void inserirNoMenu(ArrayList<NavMenuModel> menu, String str) {

        menu.add(new NavMenuModel(str, R.drawable.ic_book,
                new ArrayList<NavMenuModel.SubMenuModel>() {{
                    add(new NavMenuModel.SubMenuModel("Arquivos", ArquivoFragment.newInstance("Arquivo")));
                    add(new NavMenuModel.SubMenuModel("Links", LinkFragment.newInstance("Link")));
                    add(new NavMenuModel.SubMenuModel("Vídeos", VideoFragment.newInstance("Video")));
                }}));


    }
}
