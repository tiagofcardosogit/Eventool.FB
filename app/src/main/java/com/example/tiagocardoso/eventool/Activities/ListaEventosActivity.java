package com.example.tiagocardoso.eventool.Activities;

import android.app.Activity;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tiagocardoso.eventool.Adapter.AdapterListaEvento;
import com.example.tiagocardoso.eventool.R;
import com.example.tiagocardoso.eventool.model.Evento;
import com.firebase.ui.database.FirebaseListAdapter;
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

    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<String> arrayList;

    final List<Evento> eventos = new  ArrayList<Evento>();


     @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.eventos_activity_listview);

       DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
       DatabaseReference firebase = databaseReference;

         ListView listview = (ListView) findViewById(R.id.listViewEventoID);

         ArrayAdapter<Evento> eventoAdapter = new ArrayAdapter<Evento>(
                 ListaEventosActivity.this,
                 android.R.layout.simple_expandable_list_item_1,
                 android.R.id.text1,
                 eventos
         );

        // listview.setAdapter(eventoAdapter);



         firebase.child("eventos").addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot dataSnapshot) {
                 Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                 for (DataSnapshot child: children) {
                     Evento evento = child.getValue(Evento.class);
                     eventos.add(evento);
                 }

                 adapter.notifyDataSetChanged();
             }

             @Override
             public void onCancelled(DatabaseError databaseError) {
                 Log.i("ERROR", databaseError.getMessage());

             }
         });





    }
}
