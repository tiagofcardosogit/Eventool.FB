package com.example.tiagocardoso.eventool.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tiagocardoso.eventool.Config.ConfigFirebase;
import com.example.tiagocardoso.eventool.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.example.tiagocardoso.eventool.model.Usuario;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class Criar_usuarioActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private EditText nome;
    private EditText sobrenome;
    private EditText email;
    private EditText senha;
    private Button btnSalvar;
    private Usuario usuario;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_usuario);

 /*       firebaseAuth = FirebaseAuth.getInstance();

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
                });*/
     nome = (EditText) findViewById(R.id.usuarioNameID);
     sobrenome = (EditText) findViewById(R.id.usuarioSobrenomeID);
     email = (EditText) findViewById(R.id.emailID);
     senha = (EditText) findViewById(R.id.senhaID);
     btnSalvar = (Button) findViewById(R.id.btnSalvarID);



        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                usuario = new Usuario();
                usuario.setNome(nome.getText().toString());
                usuario.setSobrenome(sobrenome.getText().toString());
                usuario.setEmail(email.getText().toString());
                usuario.setSenha(senha.getText().toString());

                cadastrarUsuario();
            }
        });

    }

    private void cadastrarUsuario(){
        autenticacao = ConfigFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(Criar_usuarioActivity.this, new OnCompleteListener<AuthResult>() { //salva os dados do usuario na base
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                //verifica se os dados foram salvos com sucesso
                if (task.isSuccessful()){
                    Toast.makeText(Criar_usuarioActivity.this, "Cadastro Realizado com Sucesso!!", Toast.LENGTH_SHORT).show();


                    FirebaseUser usuarioFirebase = task.getResult().getUser();
                    usuario.setId(usuarioFirebase.getUid()); //grava o id gerado automaticamente pelo firebase
                    usuario.salvar();

                    Intent intent = new Intent(Criar_usuarioActivity.this, NavigationLayout.class);
                    startActivity(intent);

                }else{

                    String erroExcecao = "";

                    try{
                        throw task.getException();
                    } catch (FirebaseAuthUserCollisionException e) { //metodo para lancar excecao quando o usuario já existe na base
                        erroExcecao = "Email já cadastrado para outro usuario!";

                    }catch (FirebaseAuthInvalidCredentialsException e){ //metodo para lancar excecao quando e email é invalido
                        erroExcecao = "Email inválido, digite novamente!";
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    Toast.makeText(Criar_usuarioActivity.this, "Erro: " + erroExcecao, Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}
