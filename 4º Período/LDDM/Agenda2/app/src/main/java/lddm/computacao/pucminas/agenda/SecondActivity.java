package lddm.computacao.pucminas.agenda;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.SortedSet;

public class SecondActivity extends AppCompatActivity
                            implements InserirFragment.OnFragmentInteractionListener,
                                       View.OnClickListener{

    private static final String TAG = "SecondActivity";


    //Convidados
    SortedSet<LinearLayout> convidadoLinearLayout;

    LayoutInflater inflater;
    public String[] informacoes = new String[8];

    final public String CONVIDADO_TAG = "InserirFragment";
    SwipeMenuListView lview;
    ArrayList<String> convidados;
    ArrayList<Pessoa> contatos;
    ArrayAdapter<String> adapter;
    Button concluirBtn;
    Button voltarBtn;



    public void setConvidadosLinearLayout(SortedSet<LinearLayout> convidadoLinearLayout) {
        this.convidadoLinearLayout = convidadoLinearLayout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_convidados);

        //Pegar Paramentros das Informações do Evento
        Bundle bundle = getIntent().getExtras();
        if ( bundle != null) {
            informacoes = bundle.getStringArray("valor");
        }


        lview = (SwipeMenuListView) findViewById(R.id.lista_Convidados);
        convidados = new ArrayList<>();
        contatos = new ArrayList<>();
        adapter = new ArrayAdapter<>(SecondActivity.this, android.R.layout.simple_list_item_1,
                convidados);

        concluirBtn = (Button) findViewById(R.id.concluir_BTN);
        voltarBtn = (Button) findViewById(R.id.voltar_BTN);
        voltarBtn.setOnClickListener(this);
        concluirBtn.setOnClickListener(this);
        lview.setAdapter(adapter);
        inflater = SecondActivity.this.getLayoutInflater();
        FloatingActionButton inserir = (FloatingActionButton) findViewById(R.id.AdcionarConvidado);
        inserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment fragment = InserirFragment.newInstance();
                fragment.show(getSupportFragmentManager(), CONVIDADO_TAG);
            }
        });

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(170);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);

                // create "Agenda" item
                SwipeMenuItem addAgenda = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                addAgenda.setBackground(new ColorDrawable(Color.rgb(0x27, 0x77,
                        0xE6)));
                // set item width
                addAgenda.setWidth(170);
                // set a icon
                addAgenda.setIcon(R.drawable.ic_agenda);
                // add to menu
                menu.addMenuItem(addAgenda);
            }
        };

        lview.setMenuCreator(creator);

        lview.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        String item = convidados.get(position);
                        //remover
                        adapter.remove(item);
                        remover(item);

                        //atualizar
                        adapter.notifyDataSetChanged();
                        //Notificação
                        Toast.makeText(getApplicationContext(),"Convidado removido.",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        String convidado = convidados.get(position);
                        //Inserir na Agenda
                        try {
                            inserirContatoAgenda(convidado);
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }

                        //Notificação
                        Toast.makeText(getApplicationContext(),"Inserindo na Agenda.",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
    }


    private void remover(String valor) {
        for(int i = 0; i< contatos.size(); i++){
            if(contatos.get(i).getNome().equals(valor)) {
                contatos.remove(i);
                i = contatos.size();
            }
        }
    }

    private void inserirContatoAgenda(String valor) throws CloneNotSupportedException {
        for(int i = 0; i<contatos.size(); i++) {
            if(contatos.get(i).getNome().equals(valor)) {
                Pessoa p = contatos.get(i).clone();
                inserirContatoAgenda(p.getEmail(),p.getTelefone(),p.getNome());

            }
        }
    }


    public void setPersonData(Pessoa pessoa) {
            contatos.add(pessoa);
            convidados.add(pessoa.getNome());
            adapter.notifyDataSetChanged();
            InserirFragment convidadosFragment = (InserirFragment) getSupportFragmentManager()
                    .findFragmentByTag(CONVIDADO_TAG);
        }

    public void cadastrarEventoAgenda(View view) {
        String [] infosDataIni = informacoes[0].split("-");
        int diaIni = Integer.parseInt(infosDataIni[0]);
        int mesIni = Integer.parseInt(infosDataIni[1]);
        int anoIni = Integer.parseInt(infosDataIni[2]);

        String [] infosHoraIni = informacoes[2].split(":");
        int horaIni = Integer.parseInt(infosHoraIni[0]);
        int minutoIni = Integer.parseInt(infosHoraIni[1]);

        String [] infosDataFim = informacoes[1].split("-");
        int diaFim = Integer.parseInt(infosDataFim[0]);
        int mesFim = Integer.parseInt(infosDataFim[1]);
        int anoFim = Integer.parseInt(infosDataFim[2]);

        String [] infosHoraFim = informacoes[3].split(":");
        int horaFim = Integer.parseInt(infosHoraFim[0]);
        int minutoFim = Integer.parseInt(infosHoraFim[1]);


        Calendar calendarIni = Calendar.getInstance();
        calendarIni.set(anoIni,mesIni,diaIni,horaIni,minutoIni);

        Calendar calendarFim = Calendar.getInstance();
        calendarFim.set(anoFim,mesFim,diaFim,horaFim,minutoFim);

        Intent intent2 = new Intent(Intent.ACTION_INSERT);
        intent2.setData(CalendarContract.Events.CONTENT_URI);
        intent2.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,calendarIni.getTimeInMillis());
        intent2.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, calendarFim.getTimeInMillis());
        intent2.putExtra(CalendarContract.Events.TITLE,informacoes[7]);

        startActivity(intent2);

    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.concluir_BTN:
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("valor",informacoes);
                startActivity(intent);
                cadastrarEventoAgenda(v);
                break;

            case R.id.voltar_BTN:
                finish();
                break;

        }

    }
    public void inserirContatoAgenda(String email, String tel, String nome) {
        //Inserir Contato na Agenda
        Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
        intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
        intent
                .putExtra(ContactsContract.Intents.Insert.EMAIL,email)
                .putExtra(ContactsContract.Intents.Insert.EMAIL_TYPE,
                        ContactsContract.CommonDataKinds.Email.TYPE_WORK)
                .putExtra(ContactsContract.Intents.Insert.PHONE,tel)
                .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE,
                        ContactsContract.CommonDataKinds.Phone.TYPE_WORK)
                .putExtra(ContactsContract.Intents.Insert.NAME,nome);
        startActivity(intent);
    }
}
