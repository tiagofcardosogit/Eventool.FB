package com.example.tiagocardoso.eventool.Config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by tiagocardoso on 15/05/17.
 */

public final class ConfigFirebase {

    private static DatabaseReference referenceDatabase;
    private static FirebaseAuth autenticacao;


    public static DatabaseReference getFirebase(){

        if (referenceDatabase == null){
            referenceDatabase = FirebaseDatabase.getInstance().getReference();
        }

        return referenceDatabase;
    }

    public static FirebaseAuth getFirebaseAutenticacao(){
        if (autenticacao == null){
            autenticacao = FirebaseAuth.getInstance();
        }
        return autenticacao;
    }

}
