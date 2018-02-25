package lddm.computacao.pucminas.agenda;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by 1031486 on 12/09/2017.
 */

public class MeuDatePicker implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    EditText _label;
    EditText _label2;
    private int ano;
    private int mes;
    private int dia;
    private Context context;
    String data;


    public MeuDatePicker(Context context, int _label) {
        Activity actv = (Activity)context;
        this._label =  (EditText)actv.findViewById(_label);
        this._label.setOnClickListener(this);
        this.context = context;
    }
    public MeuDatePicker(Context context, int _label, EditText _label2) {
        Activity actv = (Activity)context;
        this._label =  (EditText)actv.findViewById(_label);
        this._label.setOnClickListener(this);
        this.context = context;
        this._label2 = _label2;
    }

    @Override
    public void onClick(View v){
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());

        DatePickerDialog dialog = new DatePickerDialog(context,this,
                cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH));
        dialog.show();

    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        this.ano = year;
        this.mes = monthOfYear;
        this.dia = dayOfMonth;
        mostrarData();
    }


    private void mostrarData() {
        if(this._label2 != null ) validar();
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat dfsaida = new SimpleDateFormat("EEE dd, MMMM - yyyy");
        String data = this.dia + "-" + (this.mes + 1) + "-" + this.ano;
        Date date = null;
        try {
            date = df.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.data = data;
        this._label.setText(dfsaida.format((date)));
    }

    public String getData() {
        return this.data;
    }
    public boolean validar(){
        boolean ehvalido = true;

        return ehvalido;

    }

}


