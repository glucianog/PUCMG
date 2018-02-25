package lddm.computacao.pucminas.agenda;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.facebook.share.model.ShareOpenGraphAction;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.ShareOpenGraphObject;
import com.facebook.share.widget.ShareDialog;


public class ThirdActivity extends AppCompatActivity {


    EditText emsg;
    ImageButton btnFb, btnWpp,btnLi;
    String[] informacoes = new String[8];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);



        //Pegar Paramentros das Informações do Evento
        Bundle bundle = getIntent().getExtras();
        if (bundle.containsKey("valor")) {
            informacoes = bundle.getStringArray("valor");
        }

        emsg   = (EditText) findViewById(R.id.msg_compartilhar);
        btnFb  = (ImageButton) findViewById(R.id.botao_face);
        btnWpp = (ImageButton) findViewById(R.id.botao_wpp);
        btnLi  = (ImageButton) findViewById(R.id.botao_linkedin);

        btnWpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarMsgWpp(descricao());
            }

        });
        btnFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareOpenGraphObject object = new ShareOpenGraphObject.Builder()
                        .putString("og:type","books.book")
                        .putString("og:title","Meu Convite")
                        .putString("og:description",descricao())
                        .build();

                ShareOpenGraphAction action = new ShareOpenGraphAction.Builder()
                        .setActionType("books.reads")
                        .putObject("book",object)
                        .build();

                ShareOpenGraphContent content = new ShareOpenGraphContent.Builder()
                        .setPreviewPropertyName("book")
                        .setAction(action)
                        .build();

                ShareDialog.show(ThirdActivity.this,content);
            }
        });

        btnLi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarLinkedIn(descricao());
            }
        });

    }

    public void enviarMsgWpp(String msg) {
        Intent wppIntent = new Intent(Intent.ACTION_SEND);
        wppIntent.setType("text/plain");
        wppIntent.putExtra(Intent.EXTRA_TEXT, msg);
        wppIntent.setPackage("com.whatsapp");
        startActivity(wppIntent);
    }

    public void enviarLinkedIn(String msg) {
        Intent liIntent = new Intent(Intent.ACTION_SEND);
        liIntent.setType("text/plain");
        liIntent.putExtra(Intent.EXTRA_TEXT, msg);
        liIntent.setPackage("com.linkedin.android");
        if(liIntent.resolveActivity(getPackageManager()) != null)
            startActivity(liIntent);

    }

    public String descricao() {
        String mensagem = "Evento: " + informacoes[7]+
                "\n Inicio: " + informacoes[0] + " às " + informacoes[2] +
                "\n Término: " + informacoes[1] + "às " + informacoes[3] +
                "\n Organizador: " + informacoes[4] +
                "\n E-mail: " + informacoes[5] +
                "\n Telefone: " + informacoes[6]  +
                "\n" + emsg.getText().toString() ;
        return mensagem;
    }
}
