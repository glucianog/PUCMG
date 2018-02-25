package lddm.computacao.pucminas.organapp.navigationdrawer;

import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import lddm.computacao.pucminas.organapp.R;


/**
 * Created by miki on 7/7/17.
 */

public class SubTitleViewHolder extends ChildViewHolder {
    private TextView subTitleTextView;

    public SubTitleViewHolder(View itemView) {
        super(itemView);
        subTitleTextView = (TextView) itemView.findViewById(R.id.main_nav_submenu_item_title);
    }

    public void setSubTitletName(String name) {
        subTitleTextView.setText(name);
    }
}