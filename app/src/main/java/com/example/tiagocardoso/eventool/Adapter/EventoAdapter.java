package com.example.tiagocardoso.eventool.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tiagocardoso.eventool.R;

import com.example.tiagocardoso.eventool.model.Evento;

import java.util.ArrayList;

/**
 * Created by tiagocardoso on 28/05/17.
 */

public class EventoAdapter extends ArrayAdapter<Evento>{

    private ArrayList<Evento> eventos;
    private Context context;

    public EventoAdapter(Context c, ArrayList<Evento> objects) {
        super(c, 0, objects);
        this.context = c;
        this.eventos = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;

        // Verifica se a lista está preenchida
        if( eventos != null ){

            // inicializar objeto para montagem da view
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            // Monta view a partir do xml
            view = inflater.inflate(R.layout.listview_eventos, parent, false);

            // recupera elemento para exibição
            TextView nomeEvento = (TextView) view.findViewById(R.id.tv_nomeEvento);
            TextView dataEvento = (TextView) view.findViewById(R.id.tv_dataEvento);
            //TextView horaEvento = (TextView) view.findViewById(R.id.tv_horaEvento);

            Evento evento = eventos.get(position);
            nomeEvento.setText( evento.getNomeEvento() );
            //horaEvento.setText(evento.getHoraEvento());
            dataEvento.setText(evento.getDataEvento());



        }

        return view;
    }
}
