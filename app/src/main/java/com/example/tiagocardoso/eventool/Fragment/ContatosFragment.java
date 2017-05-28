package com.example.tiagocardoso.eventool.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.example.tiagocardoso.eventool.Activities.ConversasActivity;
import com.example.tiagocardoso.eventool.Config.ConfigFirebase;
import com.example.tiagocardoso.eventool.Helper.Preferencias;
import com.example.tiagocardoso.eventool.R;
import com.example.tiagocardoso.eventool.model.Contato;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.example.tiagocardoso.eventool.Adapter.ContatoAdapter;

import java.util.ArrayList;

public class ContatosFragment extends Fragment {

    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<Contato> contatos;
    private DatabaseReference firebase;
    private ValueEventListener valueEventListenerContatos;

    public ContatosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        firebase.addValueEventListener( valueEventListenerContatos );
        Log.i("ValueEventListener", "onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        firebase.removeEventListener( valueEventListenerContatos );
        Log.i("ValueEventListener", "onStop");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        //Instânciar objetos
        contatos = new ArrayList<>();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contatos, container, false);

        //Monta listview e adapter
        listView = (ListView) view.findViewById(R.id.lv_contatos);
        /*adapter = new ArrayAdapter(
                getActivity(),
                R.layout.lista_contato,
                contatos
        );*/
        adapter = new ContatoAdapter(getActivity(),contatos);
        listView.setAdapter( adapter );

        //Recuperar contatos do firebase
        Preferencias preferencias = new Preferencias(getActivity());
        String identificadorUsuarioLogado = preferencias.getIdentificador();

        firebase = ConfigFirebase.getFirebase()
                .child("contatos")
                .child( identificadorUsuarioLogado );

        //Listener para recuperar contatos
        valueEventListenerContatos = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Limpar lista
                contatos.clear();

                //Listar contatos
                for (DataSnapshot dados: dataSnapshot.getChildren() ){

                    Contato contato = dados.getValue( Contato.class );
                    contatos.add( contato );


                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ConversasActivity.class);


                //recupera os dados a serem passados
                Contato contato = contatos.get(position);

                //passando as mensagens para conversa activity
                intent.putExtra("nome", contato.getNome());
                intent.putExtra("email", contato.getEmail());


                startActivity(intent);
            }
        });


        return view;

    }

}








/*


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tiagocardoso.eventool.Adapter.ContatoAdpter;
import com.example.tiagocardoso.eventool.Config.ConfigFirebase;
import com.example.tiagocardoso.eventool.Helper.Preferencias;
import com.example.tiagocardoso.eventool.R;
import com.example.tiagocardoso.eventool.model.Contato;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

*/
/**
 * A simple {@link Fragment} subclass.
 *//*

public class ContatosFragment extends Fragment {

    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<Contato> contatos;
    private DatabaseReference firebase;
    private ValueEventListener valueEventListenerContatos;

    public ContatosFragment() {
        // Required empty public constructor
    }

    //otimizacao do listner, para nao ficar carregando toda hora. Somente será chamado quando o o Fragment for chamado, assim economiza recurso
    @Override
    public void onStart() {
        super.onStart();
        firebase.addValueEventListener(valueEventListenerContatos);

    }

    //para o listner quando sair do fragmento
    @Override
    public void onStop() {
        super.onStop();
        firebase.removeEventListener(valueEventListenerContatos);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Array de contatos
        contatos = new ArrayList<>();


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contatos, container, false);


        adapter = new ContatoAdpter(getActivity(), contatos);
        listView.setAdapter(adapter);

        //listando contatos do Firebase
        Preferencias preferencias = new Preferencias(getActivity());
        String identificadorUsuarioLogado = preferencias.getIdentificador();

        firebase = ConfigFirebase.getFirebase()
                .child("contatos")
                .child(identificadorUsuarioLogado);


        //Listner para recuperar contatos adicionados no Firebase, sempre que atualizar
        valueEventListenerContatos = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //limpa array de contatos
                contatos.clear();

                //listar os dados percorrento todos os contatos

                for (DataSnapshot dados : dataSnapshot.getChildren()){
                    Contato contato = dados.getValue(Contato.class);
                    contatos.add(contato);


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
*/
