package com.example.tiagocardoso.eventool.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tiagocardoso.eventool.Adapter.MensagemAdapter;
import com.example.tiagocardoso.eventool.Config.ConfigFirebase;
import com.example.tiagocardoso.eventool.Helper.Base64Custom;
import com.example.tiagocardoso.eventool.Helper.Preferencias;
import com.example.tiagocardoso.eventool.R;
import com.example.tiagocardoso.eventool.model.Conversa;
import com.example.tiagocardoso.eventool.model.Mensagem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ConversasActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText editMensagem;
    private ImageButton btnMensagem;
    private DatabaseReference firebase;
    private ListView listView;
    private ArrayList<Mensagem> mensagens;
    private ArrayAdapter<Mensagem> adapter;
    private ValueEventListener valueEventListenerMensagem;


    //Dados do destinatario
    private String nomeUsuarioDestinatario;
    private String idUsuarioDestinatario;
    private String sobrenomeUsuarioDestinatario;

    //Dados remetente
    private String idUsuarioRemetente;
    private String nomeUsuarioRemetente;
    private String sobrenomeUsuarioRemetente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversas);

        toolbar = (Toolbar) findViewById(R.id.toolbar_conversa);
        editMensagem = (EditText) findViewById(R.id.editText_mensagem);
        btnMensagem = (ImageButton) findViewById(R.id.btn_enviarMSG);
        listView = (ListView) findViewById(R.id.lv_conversas);

        //dados do usuario logado
        Preferencias preferencias = new Preferencias(ConversasActivity.this);
        idUsuarioRemetente = preferencias.getIdentificador();
        nomeUsuarioRemetente = preferencias.getNome();
        sobrenomeUsuarioRemetente = preferencias.getSobrenome();


        //recuperando os dasdos do ContatosFragment
        Bundle extra = getIntent().getExtras();

        if (extra != null){
            nomeUsuarioDestinatario = extra.getString("nome");
            String emailDestinatario = extra.getString("email");
            idUsuarioDestinatario = Base64Custom.encodeBase64(emailDestinatario);
        }

        toolbar.setTitle(nomeUsuarioDestinatario); //mostra no nome do contato na toolbar

        mensagens = new ArrayList<>();

        adapter = new MensagemAdapter(ConversasActivity.this, mensagens);

        listView.setAdapter(adapter);

        //recuperar mensagens do firebase
        firebase = ConfigFirebase.getFirebase().child("mensagens").child(idUsuarioRemetente).child(idUsuarioDestinatario);

        //Listener para mensagens
        valueEventListenerMensagem = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mensagens.clear();


                //percorre pegando as mensagens nos filhos
                for (DataSnapshot dados : dataSnapshot.getChildren()){
                    Mensagem mensagem = dados.getValue(Mensagem.class);
                    mensagens.add(mensagem);
                }


                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        firebase.addValueEventListener(valueEventListenerMensagem);


        //evia mensagen
        btnMensagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textoMensagem = editMensagem.getText().toString();

                if (textoMensagem.isEmpty()){
                    Toast.makeText(ConversasActivity.this, "Digite sua mensagem", Toast.LENGTH_SHORT).show();
                }else{
                    Mensagem mensagem = new Mensagem();
                    mensagem.setIdUsuario(idUsuarioRemetente);
                    mensagem.setMensagem(textoMensagem);


                    Boolean retornoMensagemRemetente = salvarMensagem(idUsuarioRemetente, idUsuarioDestinatario, mensagem);

                    if (!retornoMensagemRemetente){
                        Toast.makeText(ConversasActivity.this, "Erroa ao salvar a mensagem, tente novamente", Toast.LENGTH_SHORT).show();
                    }else {
                        Boolean retornoMensagemDestinatario = salvarMensagem(idUsuarioDestinatario, idUsuarioRemetente, mensagem);
                        if (!retornoMensagemDestinatario) {
                            Toast.makeText(ConversasActivity.this, "Erroa ao salvar a mensagem, tente novamente", Toast.LENGTH_SHORT).show();
                        }

                    }

                    //salvando conversa para remetente
                    Conversa conversa = new Conversa();
                    conversa.setIdUsuario(idUsuarioDestinatario);
                    conversa.setNome(nomeUsuarioDestinatario);
                    conversa.setMensagem(textoMensagem);

                    Boolean retornoConversaRemetente = salvarConversa(idUsuarioRemetente,idUsuarioDestinatario,conversa);

                    if (!retornoConversaRemetente){
                        Toast.makeText(ConversasActivity.this, "Erro ao salvar a conversa, tente novamente!", Toast.LENGTH_SHORT).show();
                    }else{

                        //salvando conversa para o destinatario
                        conversa = new Conversa();
                        conversa.setIdUsuario(idUsuarioRemetente);
                        conversa.setNome(nomeUsuarioRemetente);
                        conversa.setMensagem(textoMensagem);

                        Boolean retornoConversaDestinatario = salvarConversa(idUsuarioDestinatario,idUsuarioRemetente,conversa);

                        if (!retornoConversaDestinatario) {
                            Toast.makeText(ConversasActivity.this, "Erroa ao salvar a mensagem, tente novamente", Toast.LENGTH_SHORT).show();
                        }

                    }

                    editMensagem.setText("");
                }
            }
        });

    }
    //metodo para salvar no firebase
    private boolean salvarMensagem(String idRemetente, String idDestinatario, Mensagem mensagem){
        try{
            firebase = ConfigFirebase.getFirebase().child("mensagens");


            firebase.child(idRemetente).child(idDestinatario).push().setValue(mensagem);

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private boolean salvarConversa(String idRemetente, String idDestinatario, Conversa conversa){
        try{
            firebase = ConfigFirebase.getFirebase().child("conversas");
            firebase.child(idUsuarioRemetente).child(idUsuarioDestinatario).push().setValue(conversa);

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebase.removeEventListener(valueEventListenerMensagem);
    }
}
