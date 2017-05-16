package com.example.tiagocardoso.eventool.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tiagocardoso.eventool.Config.ConfigFirebase;
import com.example.tiagocardoso.eventool.R;
import com.example.tiagocardoso.eventool.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText senha;
    private Button btnLogin;
    private Usuario usuario;
    private FirebaseAuth autenticacao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userIsLogged();


        email = (EditText) findViewById(R.id.emaiLoginID);
        senha = (EditText) findViewById(R.id.senhaLoginID);
        btnLogin = (Button) findViewById(R.id.btnLoginID);


        //momento em que é capturado email e senha ao clicar no botao 'BORA'
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = new Usuario();
                usuario.setEmail(email.getText().toString());
                usuario.setSenha(senha.getText().toString());
                validarLogin();
            }
        });

    }

    //valida o login se as credenciais estao corretas
    public void validarLogin(){

        autenticacao = ConfigFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Login com Sucesso", Toast.LENGTH_SHORT).show();
                    abrirTelaPrincipal();
                }else{

                    String erroExcecao = "";

                    try{
                        throw task.getException();
                    } catch (FirebaseAuthInvalidUserException e) { //metodo para lancar excecao quando o usuario esta errado
                        erroExcecao = "Falha ao Realizar o Login";

                    }catch (FirebaseAuthInvalidCredentialsException e){ //metodo para lancar excecao quando e email é invalido
                        erroExcecao = "Email inválido, digite novamente!";
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    Toast.makeText(LoginActivity.this, "Erro: " + erroExcecao, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void userIsLogged(){ // Vefica se o usuario já esta logado
        autenticacao = ConfigFirebase.getFirebaseAutenticacao();

        if (autenticacao.getCurrentUser() != null){
            abrirTelaPrincipal();
        }
    }

    private void abrirTelaPrincipal(){
        Intent intent = new Intent(LoginActivity.this, NavigationLayout.class);
        startActivity(intent);
    }

    public void abrirCriarUsuario(View view) {
        Intent intent = new Intent(LoginActivity.this, Criar_usuarioActivity.class);
        startActivity(intent);

    }


}



