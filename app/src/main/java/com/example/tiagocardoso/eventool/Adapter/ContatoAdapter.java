package com.example.tiagocardoso.eventool.Adapter;

/**
 * Created by tiagocardoso on 27/05/17.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tiagocardoso.eventool.R;
import com.example.tiagocardoso.eventool.model.Contato;

import java.util.ArrayList;


public class ContatoAdapter extends ArrayAdapter<Contato> {

    private ArrayList<Contato> contatos;
    private Context context;

    public ContatoAdapter(Context c, ArrayList<Contato> objects) {
        super(c, 0, objects);
        this.contatos = objects;
        this.context = c;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;

        // Verifica se a lista está vazia
        if( contatos != null ){

            // inicializar objeto para montagem da view
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            // Monta view a partir do xml
            view = inflater.inflate(R.layout.listview_customizado, parent, false);

            // recupera elemento para exibição
            TextView nomeContato = (TextView) view.findViewById(R.id.tv_nome);
            TextView emailContato = (TextView) view.findViewById(R.id.tv_email);

            Contato contato = contatos.get( position );
            nomeContato.setText( contato.getNome());
            emailContato.setText( contato.getEmail() );

        }

        return view;

    }
}





/*















public class ContatoAdpter extends ArrayAdapter<Contato>{

    private ArrayList<Contato> contatos;
    private Context context;

    public ContatoAdpter( Context c, ArrayList<Contato> objects) {
        super(c, 0, objects);
        this.contatos = objects;
        this.context = c;
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

        View view = null;

        if (contatos != null){
            //inicializa o objeto para mostrar na view
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            // monta a view via xlml
            view = inflater.inflate(R.layout.listview_customizado,parent,false);

            TextView nomeContato = (TextView) view.findViewById(R.id.tv_nome);
            TextView emailContato = (TextView) view.findViewById(R.id.tv_email);

            Contato contato = contatos.get(position);
            nomeContato.setText(contato.getNome());
            emailContato.setText(contato.getEmail());


        }
        return view;

    }
}
*/
