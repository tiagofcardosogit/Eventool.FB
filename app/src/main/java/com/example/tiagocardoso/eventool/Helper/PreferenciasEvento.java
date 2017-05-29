package com.example.tiagocardoso.eventool.Helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by tiagocardoso on 28/05/17.
 */

public class PreferenciasEvento {

    private SharedPreferences.Editor editorEvento;
    private Context contexto;
    private SharedPreferences preferences;
    private final String NOME_ARQUIVO = "eventool2.preferencias";
    private final int MODE = 0;

    //preferencias evento
    private static final String CHAVE_IDENFICADOREVENTO = "identificadorEvento";
    private static final String CHAVE_NOMEEVENTO = "nomeEvento";
    private static final String CHAVE_DATAEVENTO = "dataEvento";
    private static final String CHAVE_HORAEVENTO = "horaEvento";









    public void salvarDadosEventos( String identificadorEvento, String nomeEvento, String dataEvento, String horaEvento ){

        editorEvento.putString(CHAVE_IDENFICADOREVENTO, identificadorEvento);
        editorEvento.putString(CHAVE_NOMEEVENTO, nomeEvento);
        editorEvento.putString(CHAVE_DATAEVENTO, dataEvento);
        editorEvento.putString(CHAVE_HORAEVENTO, dataEvento);

        editorEvento.commit();

    }

    public String getIdentificadorEvento(){
        return preferences.getString(CHAVE_IDENFICADOREVENTO,null);
    }

    public String getNomeEvento(){
        return preferences.getString(CHAVE_NOMEEVENTO,null);
    }

    public String getHoraEvento(){
        return preferences.getString(CHAVE_HORAEVENTO,null);
    }

    public String getDataEvento(){
        return preferences.getString(CHAVE_DATAEVENTO,null);
    }
}

