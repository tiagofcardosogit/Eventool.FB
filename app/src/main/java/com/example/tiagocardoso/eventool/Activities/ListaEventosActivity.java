package com.example.tiagocardoso.eventool.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.tiagocardoso.eventool.R;

public class ListaEventosActivity extends Activity {

    private ListView listViewEventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventos_activity_listview);

       /* listViewEventos = (ListView) findViewById(R.id.listViewEventoID);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_expandable_list_item_1,
                android.R.id.text1,
                listaEventos
        );*/
    }


}
