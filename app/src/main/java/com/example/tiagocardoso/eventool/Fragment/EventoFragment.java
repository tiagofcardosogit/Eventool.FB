package com.example.tiagocardoso.eventool.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tiagocardoso.eventool.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventoFragment extends Fragment {

    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<String> eventos;


    public EventoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        eventos = new ArrayList<>();
        eventos.add("Festa no ape");
        eventos.add("Festa no ape");
        eventos.add("Festa no ape");
        eventos.add("Festa no ape");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_evento, container, false);

        //monta o listview adapter
        listView = (ListView) view.findViewById(R.id.listViewEventoFragID);
        adapter = new ArrayAdapter(
          getActivity(),
                android.R.layout.simple_expandable_list_item_1,
                eventos

        );
        listView.setAdapter(adapter);
        return view;
    }

}
