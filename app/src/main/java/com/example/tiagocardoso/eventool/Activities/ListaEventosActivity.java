package com.example.tiagocardoso.eventool.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tiagocardoso.eventool.Helper.EventoAdapter;
import com.example.tiagocardoso.eventool.Helper.Util;
import com.example.tiagocardoso.eventool.R;
import com.example.tiagocardoso.eventool.model.Evento;

import java.util.List;

public class ListaEventosActivity extends Activity {

    private ListView listViewEventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_eventos);

       /* listViewEventos = (ListView) findViewById(R.id.listViewEventoID);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_expandable_list_item_1,
                android.R.id.text1,
                listaEventos
        );*/
    }


}
