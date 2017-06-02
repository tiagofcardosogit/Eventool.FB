package com.example.tiagocardoso.eventool.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tiagocardoso.eventool.R;
import com.example.tiagocardoso.eventool.model.Evento;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListaEventosActivity extends Activity {

    private ListView listViewEventos;
    ArrayList<Evento> eventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventos_activity_listview);



        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference firebase = databaseReference;


        firebase.child("Eventos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dadosEventos : dataSnapshot.getChildren()) {

                    Evento evento = dadosEventos.getValue(Evento.class);
                    eventos.add(evento);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {



            }
        });


        listViewEventos = (ListView) findViewById(R.id.listViewEventoID);

        ArrayAdapter<Evento> adapter = new ArrayAdapter<Evento>(
                getApplicationContext(),
                android.R.layout.simple_expandable_list_item_1,
                android.R.id.text1,
                eventos
        );

    }


}
