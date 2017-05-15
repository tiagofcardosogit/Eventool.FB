package com.example.tiagocardoso.eventool.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.tiagocardoso.eventool.R;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }

    public void abrirCriarUsuario(View view){
        Intent intent = new Intent(LoginActivity.this, Criar_usuarioActivity.class);
        startActivity(intent);

    }

}

