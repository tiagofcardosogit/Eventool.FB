package com.example.tiagocardoso.eventool.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tiagocardoso.eventool.R;
import com.example.tiagocardoso.eventool.model.Evento;

import java.util.List;

/**
 * Created by tiagocardoso on 05/06/17.
 */

public class AdapterListaEvento extends  ArrayAdapter<Evento>{


    List<Evento> eventos;

    public AdapterListaEvento(Context context, int resource, List<Evento> objects) {
        super(context, resource, objects);
        eventos = objects;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent){
        View localView = contentView;

        if (localView==null){
            LayoutInflater inflate = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            localView = inflate.inflate(R.layout.evento_item, null);
        }

        Evento evento = eventos.get(position);

        if (evento==null){
            TextView nomeEvento = (TextView) localView.findViewById(R.id.nomeEvento);
            TextView detalhesEvento = (TextView) localView.findViewById(R.id.descricaoEvento);
            TextView horaEvento = (TextView) localView.findViewById(R.id.horaEvento);
            TextView dataEvento = (TextView) localView.findViewById(R.id.dataEvento);

            if (nomeEvento !=null){
                nomeEvento.setText(evento.getNomeEvento());
            }

            if (detalhesEvento != null){
                detalhesEvento.setText(String.valueOf(evento.getDetalhesEvento()));
            }

            if (horaEvento != null){
                horaEvento.setText(String.valueOf(evento.getHoraEvento()));
            }

            if (dataEvento != null){
                dataEvento.setText(String.valueOf(evento.getDataEvento()));
            }


        }

        return localView;
    }

}

