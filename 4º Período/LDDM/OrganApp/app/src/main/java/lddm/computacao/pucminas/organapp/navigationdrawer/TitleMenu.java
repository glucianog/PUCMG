package lddm.computacao.pucminas.organapp.navigationdrawer;

import java.util.List;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;


/**
 * Created by miki on 7/7/17.
 */

public class TitleMenu extends ExpandableGroup<SubTitle> {
    private int imageResource = 0;

    public TitleMenu(String title, List<SubTitle> items, int imageResource) {
        super(title, items);
        this.imageResource = imageResource;
    }

    public int getImageResource() {
        return imageResource;
    }
}