package com.example.tiagocardoso.eventool.Activities;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.example.tiagocardoso.eventool.Helper.Evento;
import com.example.tiagocardoso.eventool.Helper.EventoAdapter;
import com.example.tiagocardoso.eventool.Helper.Util;
import com.example.tiagocardoso.eventool.R;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Listar_EventoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_evento2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarEvt);
        setSupportActionBar(toolbar);

/*        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.actionBtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }
        });*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loadEventos();
    }

    public void loadEventos() {
        new DownloadFromMyAPI().execute();
    }

    private class DownloadFromMyAPI extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL("http://eventool.esy.es/selectEvento.php");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(15000);
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoInput(true); //envio de daddos
                urlConnection.setDoOutput(true);//coleta de dados
                /*urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.connect();*/

                int test = urlConnection.getResponseCode();

                String result = Util.webToString(urlConnection.getInputStream());

                return result;
            } catch (Exception e) {
                Log.e("Error", "Error ", e);
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            List<Evento> eventos = Util.convertJSONtoEvento(s);
            if (eventos != null) {
                ArrayAdapter<Evento> eventoAdapter = new EventoAdapter(Listar_EventoActivity.this, R.layout.evento_item, eventos);
                ListView listaEvento = (ListView) findViewById(R.id.listEventos);
                listaEvento.setAdapter(eventoAdapter);
            }
        }
    }

}