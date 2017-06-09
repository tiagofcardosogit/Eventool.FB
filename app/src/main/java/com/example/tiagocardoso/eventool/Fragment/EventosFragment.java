package com.example.tiagocardoso.eventool.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tiagocardoso.eventool.Activities.ListaEventosActivity;
import com.example.tiagocardoso.eventool.Adapter.ConversaAdapter;
import com.example.tiagocardoso.eventool.Adapter.EventoAdapter;
import com.example.tiagocardoso.eventool.Config.ConfigFirebase;
import com.example.tiagocardoso.eventool.Helper.Preferencias;
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
public class EventosFragment extends Fragment {

    private ArrayList<Evento> eventos;
    private ListView listView;
    private ArrayAdapter<Evento> adapter;
    private DatabaseReference firebase;
    private ValueEventListener valueEventListenerConversas;


    public EventosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference firebase = databaseReference;


        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_eventos, container, false);

        // Monta listview e adapter
        eventos = new ArrayList<>();
        listView = (ListView) view.findViewById(R.id.lv_eventos);
        adapter = new EventoAdapter(getActivity(), eventos );
        listView.setAdapter( adapter );

        // recuperar dados do usuário
       /* Preferencias preferencias = new Preferencias(getActivity());
        String idUsuarioLogado = preferencias.getIdentificador();
*/
        // Recuperar conversas do Firebase
        //firebase = ConfigFirebase.getFirebase();

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


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ListaEventosActivity.class);


                //recupera os dados a serem passados
                Evento evento = eventos.get(position);

                //passando as mensagens para conversa activity
                intent.putExtra("nomeEvento", evento.getNomeEvento());
                intent.putExtra("detalheEvento", evento.getDetalhesEvento());
                intent.putExtra("dataEvento", evento.getDataEvento());
                intent.putExtra("horaEvento", evento.getHoraEvento());
                startActivity(intent);
            }
        });

        return  view;





    }


}





