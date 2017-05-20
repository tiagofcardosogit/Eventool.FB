package com.example.tiagocardoso.eventool.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.tiagocardoso.eventool.Config.ConfigFirebase;
import com.example.tiagocardoso.eventool.R;
import com.google.firebase.auth.FirebaseAuth;

public class NavigationLayout extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth autenticacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


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
                Intent intent = new Intent(NavigationLayout.this, MapsActivity.class);
                startActivity(intent);
            default:
            return super.onOptionsItemSelected(item);
        }
    }

    public void delogarUsuario(){
        autenticacao = ConfigFirebase.getFirebaseAutenticacao();
        autenticacao.signOut();
        Intent intent = new Intent(NavigationLayout.this, MainActivity.class);
        startActivity(intent);
        finish();

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