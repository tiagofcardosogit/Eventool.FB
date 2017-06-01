package com.example.tiagocardoso.eventool.model;


import com.example.tiagocardoso.eventool.Helper.Categoria;

/**
 * Created by tiagocardoso on 15/04/17.
 */

public class Evento {

    private String idEvento;
    private String nomeEvento;
    private String detalhesEvento;
    private String dataEvento;
    private String horaEvento;


    public Evento(){

    }


    public Evento(String id, String nomeEvento, String detalhesEvento,  String dataEvento, String horarioevento, String horaEvento) {
        this.idEvento = idEvento;
        this.nomeEvento = nomeEvento;
        this.detalhesEvento = detalhesEvento;
        this.dataEvento = dataEvento;
        this.horaEvento= horaEvento;

    }

    public String getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public String getDetalhesEvento() {
        return detalhesEvento;
    }

    public void setDetalhesEvento(String detalhesEvento) {
        this.detalhesEvento = detalhesEvento;
    }

    public String getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(String dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getHoraEvento() {
        return horaEvento;
    }

    public void setHoraEvento(String horaEvento) {
        this.horaEvento = horaEvento;
    }
}


