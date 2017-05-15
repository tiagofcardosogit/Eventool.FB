package com.example.tiagocardoso.eventool.Helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tiagocardoso.eventool.R;
import com.example.tiagocardoso.eventool.model.Usuario;

import java.util.List;



public class UsuarioAdapter extends ArrayAdapter<Usuario> {
    private List<Usuario> usuarios;
    private int layout;

    public UsuarioAdapter(Context context, int resource, List<Usuario> usuarios){
        super(context,resource,usuarios);
        this.usuarios = usuarios;
        layout = resource;
    }

    @Override
    public View getView(int posicao, View contentView, ViewGroup parent){
        View localView = contentView;

        if(localView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            localView = inflater.inflate(layout,null);
        }

        Usuario usuario = usuarios.get(posicao);

        if(usuario != null){
            //TextView textID = (TextView) localView.findViewById(R.id.textID);
            TextView textNome = (TextView) localView.findViewById(R.id.textNome);
            TextView textSobrenome = (TextView) localView.findViewById(R.id.textSobrenome);
            TextView textTelefone = (TextView) localView.findViewById(R.id.textTelefone);

            /*if(textID != null){
                textID.setText(String.valueOf(usuario.getID()));
            }*/
            if(textNome != null){
                textNome.setText(usuario.getNome());
            }
            if(textSobrenome != null){
                textSobrenome.setText(usuario.getSobrenome());
            }
            if(textTelefone != null){
                textTelefone.setText(String.valueOf(usuario.getTelefone()));
            }
        }
        return localView;
    }
}
