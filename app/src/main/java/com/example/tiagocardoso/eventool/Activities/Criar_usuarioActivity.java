package com.example.tiagocardoso.eventool.Activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.tiagocardoso.eventool.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Criar_usuarioActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_usuario);

        firebaseAuth = FirebaseAuth.getInstance();

        //Cadastando usuario
        firebaseAuth.createUserWithEmailAndPassword("tiago.cardoso@opet.edu.br","tiago123") // preicisa alterar para recuperar o email e senha da tela
                .addOnCompleteListener(Criar_usuarioActivity.this, new OnCompleteListener<AuthResult>() {  //interface
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) { //testando se o cadastro foi com sucesso
                        if ( task.isSuccessful()) {
                            Log.i("createUser", "Sucesso ao cadastrar");
                        }else {
                            Log.i("createUser", "Falha ao cadastrar");
                        }
                    }
                });


    }
}
