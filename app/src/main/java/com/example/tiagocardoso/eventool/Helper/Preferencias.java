package com.example.tiagocardoso.eventool.Helper;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class Preferencias {

    private Context contexto;
    private SharedPreferences preferences;
    private final String NOME_ARQUIVO = "whatsapp.preferencias";
    private final int MODE = 0;
    private SharedPreferences.Editor editor;

    private final String CHAVE_IDENFICADOR = "identificadorUsuario";


    public Preferencias( Context contextoParametro){

        contexto = contextoParametro;
        preferences = contexto.getSharedPreferences(NOME_ARQUIVO, MODE );
        editor = preferences.edit();

    }

    public void salvarDados( String identificadorUsuario ){

        editor.putString(CHAVE_IDENFICADOR, identificadorUsuario);

        editor.commit();

    }

    public String getIdentificador(){
        return preferences.getString(CHAVE_IDENFICADOR, null);
    }


}