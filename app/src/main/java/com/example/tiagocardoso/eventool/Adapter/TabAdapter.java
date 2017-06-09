package com.example.tiagocardoso.eventool.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.tiagocardoso.eventool.Fragment.ContatosFragment;
import com.example.tiagocardoso.eventool.Fragment.ConversasFragment;
import com.example.tiagocardoso.eventool.Fragment.EventosFragment;

/**
 * Created by tiagocardoso on 23/05/17.
 */

public class TabAdapter extends FragmentStatePagerAdapter {

    private String[] tituloAbas = {"Eventos","Conversas", "Contatos"};

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        //chama o fragment de acordo com a escolha da tab
        switch (position){
            case 0:
                fragment = new EventosFragment();
                break;
            case 1:
                fragment = new ConversasFragment();
                break;
            case 2:
                fragment = new ContatosFragment();
                break;
        }



        return fragment;
    }

    @Override
    public int getCount() {
        return tituloAbas.length;
    }

    public CharSequence getPageTitle(int position){
        return tituloAbas[position];
    }
}
