package lddm.computacao.pucminas.organapp;

/**
 * Created by gabri on 29/10/2017.
 */
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.barteksc.pdfviewer.PDFView;

import lddm.computacao.pucminas.organapp.fragment.InserirFragment;

public class PdfFragment extends Fragment{
    private String mPath;

    public PdfFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PdfFragment.
     */
    public static PdfFragment newInstance(String path, String title) {
        PdfFragment fragment = new PdfFragment();
        Bundle args = new Bundle();
        args.putString("path", path);
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPath = getArguments().getString("path");

            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getArguments()
                    .getString("title"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pdf, container, false);
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        PDFView pdfView = (PDFView) getActivity().findViewById(R.id.pdfView);
        pdfView.fromUri(Uri.parse(mPath))
                .load();
    }


}
