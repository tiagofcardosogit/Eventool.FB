package com.example.tiagocardoso.eventool.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tiagocardoso.eventool.Adapter.TabAdapter;
import com.example.tiagocardoso.eventool.Config.ConfigFirebase;
import com.example.tiagocardoso.eventool.Helper.Base64Custom;
import com.example.tiagocardoso.eventool.Helper.Preferencias;
import com.example.tiagocardoso.eventool.Helper.SlidingTabLayout;
import com.example.tiagocardoso.eventool.R;
import com.example.tiagocardoso.eventool.model.Contato;
import com.example.tiagocardoso.eventool.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class NavigationLayout extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth autenticacao;
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;
    private String idContato;
    private DatabaseReference firebase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        autenticacao = ConfigFirebase.getFirebaseAutenticacao();

        //instanciando as tabs
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.stl_tabs);
        viewPager = (ViewPager) findViewById(R.id.vp_pagina);

        //Configurando Adapter
        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabAdapter);

        slidingTabLayout.setViewPager(viewPager);
        slidingTabLayout.setDistributeEvenly(true);//para alinhar as tabs no layout
        slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this,R.color.colorAccent));


        //floating button para criar o evento
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent intent = new Intent(NavigationLayout.this, CriarEventoActivity.class);
                startActivity(intent);

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; metodo usado para inflar/adicionar os menus de opcoes na toolbar. Parte de cima do layout
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {

            case R.id.action_sair:
                delogarUsuario();
                return true;
            case R.id.Pesquisar:
                return true;
            case R.id.maps:
                Intent troca = new Intent(NavigationLayout.this, MapsActivity.class);
                startActivity(troca);
            case R.id.add_pessoa:
                adicionarNovoContato();
            default:
            return super.onOptionsItemSelected(item);
        }
    }

    public void delogarUsuario(){
        autenticacao = ConfigFirebase.getFirebaseAutenticacao();
        autenticacao.signOut();
        Intent intent = new Intent(NavigationLayout.this, MainActivity.class);
        startActivity(intent);
        //finish();

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    public void criarEventoScreen(View view){
        Intent intent = new Intent(NavigationLayout.this, CriarEventoActivity.class);
        startActivity(intent);
    }

    private void adicionarNovoContato(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(NavigationLayout.this);
        alertDialog.setTitle("Novo Contato");
        alertDialog.setMessage("Email do Contato");
        alertDialog.setCancelable(false);

        final EditText editText = new EditText(NavigationLayout.this);
        alertDialog.setView(editText);


        alertDialog.setPositiveButton("Cadastrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String emailContato = editText.getText().toString();

                if (emailContato.isEmpty()){
                    Toast.makeText(NavigationLayout.this, "Digite o email", Toast.LENGTH_SHORT).show();
                }else{
                    //verifica se usuario ja esta cadastrado na base
                    idContato = Base64Custom.encodeBase64(emailContato);


                    firebase = ConfigFirebase.getFirebase().child("usuarios").child(idContato);
                    firebase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            if (dataSnapshot.getValue() != null){

                                //recuperar dados do usuario a ser cadastrado
                                Usuario usuarioContato = dataSnapshot.getValue(Usuario.class);


                                //recuperar identificador do usuario logado
                                Preferencias preferencias = new Preferencias(NavigationLayout.this);
                                String identificadorUsuarioLogado = preferencias.getIdentificador();

                                firebase = ConfigFirebase.getFirebase();
                                firebase = firebase.child("contatos").child(identificadorUsuarioLogado).child(idContato);

                                Contato contato = new Contato();
                                contato.setIdentificadorUsuario(idContato);
                                contato.setEmail(usuarioContato.getEmail());
                                contato.setNome(usuarioContato.getNome());
                                firebase.setValue(contato);

                            }else {
                                Toast.makeText(NavigationLayout.this, "Usuario nao Existe", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });



                }


            }
        });
        alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertDialog.create();
        alertDialog.show();
    }


/*    public void selectMainOpetion(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btnInserir:
                intent = new Intent(this, Criar_usuarioActivity.class);
                break;
            case R.id.btnListar:
                intent = new Intent(this, Listar_usuarioActivity.class);
                break;
            case R.id.btnListarEvento:
                intent = new Intent(this, Listar_evento2Activity.class);
        }
        if (intent != null) {
            startActivity(intent);
        }*/
    }