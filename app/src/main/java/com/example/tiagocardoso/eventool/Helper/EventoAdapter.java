package com.example.tiagocardoso.eventool.Helper;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tiagocardoso.eventool.R;
import com.example.tiagocardoso.eventool.model.Evento;

import java.util.List;



public class EventoAdapter extends ArrayAdapter<Evento> {
    private List<Evento> eventos;
    private int layout;

    public EventoAdapter(Context context, int resource, List<Evento> eventos){
        super(context,resource,eventos);
        this.eventos = eventos;
        layout = resource;
    }

    @Override
    public View getView(int posicao, View contentView, ViewGroup parent){
        View localView = contentView;

        if(localView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            localView = inflater.inflate(layout,null);
        }

        Evento evento = eventos.get(posicao);

        if(evento != null){
            //TextView eventoID = (TextView) localView.findViewById(R.id.eventoID);
            TextView noveEvento = (TextView) localView.findViewById(R.id.nomeEvento);
          /*  TextView descricaoEvento = (TextView) localView.findViewById(R.id.descricaoEvento);
            TextView localEvento = (TextView) localView.findViewById(R.id.localEvento);
            TextView dataEvento = (TextView) localView.findViewById(R.id.dataEvento);*/
            //TextView horaEvento = (TextView) localView.findViewById(R.id.horaEvento);
            //TextView cidade = (TextView) localView.findViewById(R.id.cidade);
            //TextView estado = (TextView) localView.findViewById(R.id.estado);


           /* if(eventoID != null){
                eventoID.setText(String.valueOf(evento.getId_evento()));
            }*/
            if(noveEvento != null){
                noveEvento.setText(evento.getNomeEvento());
            }
      /*      if(descricaoEvento != null){
                descricaoEvento.setText(evento.getDetalhesEvento());
            }
            if (localEvento != null){
                localEvento.setText(evento.getNomelocal());
            }
            if(dataEvento != null){
                dataEvento.setText(String.valueOf(evento.getDataEvento()));
            }*/
            /*if(horaEvento != null){
                horaEvento.setText(String.valueOf(evento.getHorarioevento()));
            }
            if(cidade != null){
                cidade.setText(String.valueOf(evento.getCidade()));
            }
            if(estado != null){
                estado.setText(String.valueOf(evento.getEstado()));
            }*/
        }
        return localView;
    }
}
