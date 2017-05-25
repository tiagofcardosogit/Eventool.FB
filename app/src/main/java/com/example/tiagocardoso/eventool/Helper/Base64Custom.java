package com.example.tiagocardoso.eventool.Helper;

import android.util.Base64;

/**
 * Created by tiagocardoso on 25/05/17.
 */

public class Base64Custom {

    //metodo para criptografar os textos (email) que será salvo no Firebase
    public static String encodeBase64(String texto){
       return Base64.encodeToString(texto.getBytes(), Base64.DEFAULT).replaceAll("(\\n|\\r)", "");
    }

    //metodo para descriptografar os textos (email) que seráo retornados do Firebase
    public static String decodeBase64(String textoCodificado){
        return new String(Base64.decode(textoCodificado, Base64.DEFAULT));
    }


}
