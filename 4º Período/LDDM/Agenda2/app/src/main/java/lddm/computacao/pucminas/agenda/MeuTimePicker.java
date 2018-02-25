package lddm.computacao.pucminas.agenda;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
/**
 * Created by 1031486 on 12/09/2017.
 */

public class MeuTimePicker implements View.OnClickListener, TimePickerDialog.OnTimeSetListener {
    EditText _label;
    EditText _label2 = null;
    private int hora;
    private int minuto;
    private Context context;


    public MeuTimePicker(Context context, int _label) {
        Activity actv = (Activity)context;
        this._label =  (EditText)actv.findViewById(_label);
        this._label.setOnClickListener(this);
        this.context = context;
    }
    public MeuTimePicker(Context context, int _label,int _label2) {
        Activity actv = (Activity)context;
        this._label =  (EditText)actv.findViewById(_label);
        this._label.setOnClickListener(this);
        this.context = context;
        this._label2 = (EditText)actv.findViewById(_label2);
    }

    @Override
    public void onClick(View v){
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        TimePickerDialog dialog = new TimePickerDialog(context, this, cal.get(Calendar.HOUR),cal.get(Calendar.MINUTE), true);
        dialog.show();
        if(this._label2 != null) validar();

    }
    @Override
    public void onTimeSet(TimePicker view, int hour, int minute) {
        this.hora = hour;
        this.minuto = minute;
        mostrarHora();
    }

    private void mostrarHora(){
        if(this._label2 != null) validar();
        DateFormat df = new SimpleDateFormat( "HH:mm" );
        String hora = this.hora+ ":" + this.minuto;
        Date date = null;

        try{
            date = df.parse(hora);
        } catch(ParseException e){
            e.printStackTrace();
        }

        this._label.setText(df.format(date));
    }

    public void validar(){
        int hinicio = Integer.parseInt(this._label2.toString());
        int hfim = this.hora + this.minuto;
        if(hinicio <= hfim){

        }
    }

}
