package lddm.computacao.pucminas.agenda;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TimeZone;
import java.util.TreeSet;

public class MainActivity extends AppCompatActivity
                                  implements View.OnClickListener{
    //ID Edit Texts

    private ArrayList<Pessoa> contatos;
    private int idDataInicio = R.id.textDataInicio;
    private int idDataFim    = R.id.textDataFim;
    private int idHoraInicio = R.id.textHoraInicio;
    private int idHoraFim    = R.id.textHoraFim;
    private int idNome       = R.id.Organizador;
    private int idEmail      = R.id.EmailOrganizador;
    private int idTelefone   = R.id.telOrganizador;
    private int country_code = R.string.country_code;
    private int btnProximo   = R.id.criarBTN;
    private int idnomeEvento = R.id.NomeEvento;
    String[] elementos       = new String[8];

    // Botão
    Button btnOk;

    // Edit Texts
    EditText edatainicio, edatafim,ehorainicio,ehorafim,enome,email,etelefone,enomeEvento;

    // DatePickers
    MeuDatePicker inicio,fim;

    // TimePickers
    MeuTimePicker comeco,termino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        atribuirData();
        btnOk.setOnClickListener(this);
        contatos = new ArrayList<>();
    }
    public void atribuirData() {
        edatainicio  = (EditText) findViewById(idDataInicio);
        edatafim     = (EditText) findViewById(idDataFim);
        ehorainicio  = (EditText) findViewById(idHoraInicio);
        ehorafim     = (EditText) findViewById(idHoraFim);
        enome        = (EditText) findViewById(idNome);
        email        = (EditText) findViewById(idEmail);
        etelefone    = (EditText) findViewById(idTelefone);
        enomeEvento  = (EditText) findViewById(idnomeEvento);
        btnOk        = (Button) findViewById(btnProximo);
    }

    public void onClick(View v) {
        //Pegar infos do evento
        elementos[0] = inicio.getData();
        elementos[1] = fim.getData();
        elementos[2] = ehorainicio.getText().toString();
        elementos[3] = ehorafim.getText().toString();
        elementos[4] = enome.getText().toString();
        elementos[5] = email.getText().toString();
        elementos[6] = etelefone.getText().toString();
        elementos[7] = enomeEvento.getText().toString();

        Intent it = new Intent(this, SecondActivity.class);
        it.putExtra("valor",elementos);
        if(isValid()) {
            startActivity(it);
        } else {
            Toast.makeText(this,"Valores Inseridos Inválidos",Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isValid(){
        return (!isEmpty(edatainicio) && !isEmpty(edatafim) && !isEmpty(ehorainicio)
                && !isEmpty(ehorafim) && !isEmpty(enome) && !isEmpty(email) && !isEmpty(etelefone));
    }
    public boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() == 0) return true;
        else return false;
    }
    @Override
    public void onStart() {
        super.onStart();
        inicio  = new MeuDatePicker(this , idDataInicio);
        fim     = new MeuDatePicker(this,idDataFim);
        comeco  = new MeuTimePicker(this,idHoraInicio);
        termino = new MeuTimePicker(this,idHoraFim);
        email.getText();
        enome.getText();
        etelefone.addTextChangedListener(new PhoneNumberFormattingTextWatcher(getResources()
                .getString(country_code)));


    }
}


