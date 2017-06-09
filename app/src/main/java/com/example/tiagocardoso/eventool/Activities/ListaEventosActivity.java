package com.example.tiagocardoso.eventool.Activities;

import android.app.Activity;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tiagocardoso.eventool.Adapter.AdapterListaEvento;
import com.example.tiagocardoso.eventool.R;
import com.example.tiagocardoso.eventool.model.Evento;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.maps.MapView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListaEventosActivity extends Activity {

    private ImageView btnImageShare;
    private TextView nomeEvento;
    private TextView detalhesEvento;
    private TextView horaEvento;
    private TextView dataEvento;
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventos_activity_listview);

        btnImageShare = (ImageButton) findViewById(R.id.btnImageShareID);
        nomeEvento = (TextView) findViewById(R.id.textViewNomeEventoID);
        detalhesEvento = (TextView) findViewById(R.id.textViewDetalhesID);
        horaEvento = (TextView) findViewById(R.id.textViewHoraID);
        dataEvento = (TextView) findViewById(R.id.texViewtDataID);



        //recuperando os dasdos do ContatosFragment
        Bundle extra = getIntent().getExtras();

        if (extra != null) {
            String nomeEventoPassado = extra.getString("nomeEvento");
            nomeEvento.setText(nomeEventoPassado);
            String detalhePassado = extra.getString("detalheEvento");
            detalhesEvento.setText(detalhePassado);
            String dataPassada = extra.getString("dataEvento");
            dataEvento.setText(dataPassada);
            String horaPassada = extra.getString("horaEvento");
            horaEvento.setText(horaPassada);

        }


    }
}
