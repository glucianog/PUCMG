package lddm.computacao.pucminas.organapp.arquivos;

import java.net.URL;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Created by gabri on 19/10/2017.
 */

public class Arquivo {
    private String link;


    public Arquivo(String link ) {
        this.link = link;
    }


    public String getLink() {
        return link;
    }


    @Override
    public Arquivo clone() throws CloneNotSupportedException {
        super.clone();
        return new Arquivo(link);
    }


    public int compareTo(@NonNull Arquivo arquivo) {
        return this.link.compareTo(arquivo.getLink());
    }


}
