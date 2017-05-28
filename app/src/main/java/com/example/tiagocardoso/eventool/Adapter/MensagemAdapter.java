package com.example.tiagocardoso.eventool.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tiagocardoso.eventool.Helper.Preferencias;
import com.example.tiagocardoso.eventool.R;
import com.example.tiagocardoso.eventool.model.Mensagem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tiagocardoso on 28/05/17.
 */

public class MensagemAdapter extends ArrayAdapter<Mensagem> {

    private Context context;
    private ArrayList<Mensagem> mensagens;


    public MensagemAdapter(Context c, ArrayList<Mensagem> objects) {
        super(c,0, objects);
        this.context = c;
        this.mensagens = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = null;

        if (mensagens != null){

            //Recupera dados do remetente
            Preferencias preferencias = new Preferencias(context);
            String idUsuarioRemetente = preferencias.getIdentificador();

            //inicializa objeto para montar o layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            //recupera mensagem
            Mensagem mensagem = mensagens.get(position);


            //troca de mensagem esquerda e direita
            if (idUsuarioRemetente.equals(mensagem.getIdUsuario())){
                view = inflater.inflate(R.layout.item_mensagem_direita, parent, false);

            }else{
                view = inflater.inflate(R.layout.item_mensagem_esquerda, parent, false);
            }




            //Recupera elemtno para exibir
            TextView textoMensagem = (TextView) view.findViewById(R.id.tv_mensagem);
            textoMensagem.setText(mensagem.getMensagem());


        }

        return view;
    }
}
