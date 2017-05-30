package com.example.tiagocardoso.eventool.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiagocardoso.eventool.Config.ConfigFirebase;
import com.example.tiagocardoso.eventool.R;
import com.example.tiagocardoso.eventool.model.Evento;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CriarEventoActivity extends AppCompatActivity{

    private Button btn_criarEvento;
    private TextView nomeEvento;
    private TextView dataEvento;
    private TextView horaEvento;
    private TextView detalhesEvento;
    private Evento evento;
    private Boolean isSuccessful;
    private Spinner categoria;



    private DatabaseReference databaseReference = ConfigFirebase.getFirebase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_evento);


        btn_criarEvento = (Button) findViewById(R.id.btn_Criar_Evento);
        nomeEvento = (TextView) findViewById(R.id.evt_nomeID);
        dataEvento = (TextView) findViewById(R.id.dataEventoID);
        horaEvento = (TextView) findViewById(R.id.horaEventoID);
        detalhesEvento = (TextView) findViewById(R.id.detalheEventoID);
        categoria = (Spinner) findViewById(R.id.spinnerID);

        btn_criarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evento = new Evento();
                evento.setNomeEvento(nomeEvento.getText().toString());
                evento.setDataEvento(dataEvento.getText().toString());
                evento.setHoraEvento(horaEvento.getText().toString());
                evento.setDetalhesEvento(detalhesEvento.getText().toString());
                //evento.getCategoria(categoria.getTex().toString());

                criarEvento();

            }
        });


    }

    private void criarEvento(){

        databaseReference.child("eventos").push().setValue(evento);

            Toast.makeText(CriarEventoActivity.this, "Evento Criado!!\nHave Fun!!!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(CriarEventoActivity.this, NavigationLayout.class);
        startActivity(intent);
        finish();

    }

}
