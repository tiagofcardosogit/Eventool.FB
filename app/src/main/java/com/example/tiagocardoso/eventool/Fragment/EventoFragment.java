package com.example.tiagocardoso.eventool.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tiagocardoso.eventool.Adapter.EventoAdapter;
import com.example.tiagocardoso.eventool.Config.ConfigFirebase;
import com.example.tiagocardoso.eventool.Helper.Preferencias;
import com.example.tiagocardoso.eventool.Helper.PreferenciasEvento;
import com.example.tiagocardoso.eventool.R;
import com.example.tiagocardoso.eventool.model.Evento;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventoFragment extends Fragment {

/*
    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<String> eventos;

    public EventoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        eventos = new ArrayList<>();


        View view = inflater.inflate(R.layout.fragment_evento,container,false);

        listView = (ListView) view.findViewById(R.id.listViewEventoID);
        adapter = new ArrayAdapter(
                getActivity(),
                android.R.layout.simple_expandable_list_item_1,
                eventos
        );

        listView.setAdapter(adapter);


        return view;
    }


}

*/





























    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<Evento> eventos;
    private DatabaseReference firebase;
    private ValueEventListener valueEventListenerEento;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference eventoReferencia = databaseReference.child("Eventos");


    public EventoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_evento, container, false);

        eventos = new ArrayList<>();

        //monta o listview adapter
        listView = (ListView) view.findViewById(R.id.listViewEventoFragID);
        adapter = new EventoAdapter(getActivity(), eventos);
        listView.setAdapter(adapter);


        PreferenciasEvento preferencias = new PreferenciasEvento(getActivity());
        String idEvento = preferencias.getIdentificadorEvento();

        //firebase = ConfigFirebase.getFirebase().child("usuarios");


        valueEventListenerEento = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                eventos.clear();

                for (DataSnapshot dados : dataSnapshot.getChildren()){
                    Evento evento = dados.getValue(Evento.class);

                    eventos.add(evento);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };



        return view;
    }

}
