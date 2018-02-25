package lddm.computacao.pucminas.organapp.navigationdrawer;

import java.util.ArrayList;
import java.util.List;
import android.support.v4.app.Fragment;
/**
 * Created by miki on 7/8/17.
 */

public class NavMenuModel {
    public String menuTitle;
    public int menuIconDrawable;
    public List<SubMenuModel> subMenu;
    public Fragment fragment;

    public NavMenuModel(String menuTitle, int menuIconDrawable, Fragment fragment) {
        this.menuTitle = menuTitle;
        this.menuIconDrawable = menuIconDrawable;
        this.fragment = fragment;
        this.subMenu = new ArrayList<>();
    }

    public NavMenuModel(String menuTitle, int menuIconDrawable, ArrayList<SubMenuModel> subMenu) {
        this.menuTitle = menuTitle;
        this.menuIconDrawable = menuIconDrawable;
        this.subMenu = new ArrayList<>();
        this.subMenu.addAll(subMenu);
    }

    public static class SubMenuModel {
        public String subMenuTitle;
        public int subMenuIconDrawable;
        public Fragment fragment;

        public SubMenuModel(String subMenuTitle, Fragment fragment) {
            this.subMenuTitle = subMenuTitle;
            this.fragment = fragment;
        }
        public SubMenuModel(String subMenuTitle,int subMenuIconDrawable, Fragment fragment) {
            this.subMenuTitle = subMenuTitle;
            this.subMenuIconDrawable = subMenuIconDrawable;
            this.fragment = fragment;
        }
    }
}