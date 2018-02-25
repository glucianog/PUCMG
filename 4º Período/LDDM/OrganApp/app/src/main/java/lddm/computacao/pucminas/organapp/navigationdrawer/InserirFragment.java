package lddm.computacao.pucminas.organapp.navigationdrawer;

/**
 * Created by gabri on 19/10/2017.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import lddm.computacao.pucminas.organapp.R;


/**
 * Created by gabri on 23/09/2017.
 */

public class InserirFragment extends DialogFragment {


    //EditTexts
    private EditText conteudoEditText;
    private EditText nomeconteudoEditText;


    public static InserirFragment newInstance() {
        InserirFragment fragment = new InserirFragment(); // Manter apenas construtor vazio
        // Setar dados que precisar com métodos getter. Ex: fragment.setEssaCaralha(essaCaralha);
        // Na Activity: nó método onClick, que abre a caixa de dialógo para criar uma pessoa
        // fazer:
        // DialogFragment fragment = InserirFragment.newInstance(argumentos);
        // fragment.show(getFragmentManager() ou getSupportedFragmentManager(), "Tag");
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Create the view to show
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //Get the layout inflater

        LayoutInflater inflater = getActivity().getLayoutInflater();


        //// Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        final View view = inflater.inflate(R.layout.dialog_inserir,null);
        builder.setView(view);
        conteudoEditText     = (EditText) view.findViewById(R.id.conteudo);

        builder.setView(view)
                // Add Action Buttons
                .setPositiveButton(R.string.inserir , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nome  = (conteudoEditText.getText().toString());

                        if( !nome.isEmpty() ) {
                            Toast.makeText(getActivity(),R.string.conteudo_inserido_sucesso,
                                    Toast.LENGTH_SHORT).show();

                            try{

                            }catch(Exception e){
                                e.printStackTrace();
                            }
                        }else {
                            Toast.makeText(getActivity(),"Valores Inseridos Inválidos.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        InserirFragment.this.getDialog().cancel();
                        Toast.makeText(getActivity(),R.string.insercao_cancelada,
                                Toast.LENGTH_SHORT).show();
                    }
                });
        return builder.create();    }



    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host

        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }





}