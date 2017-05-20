package com.example.tiagocardoso.eventool.model;


/**
 * Created by tiagocardoso on 15/04/17.
 */

public class Evento {

    private String id;
    private String id_usuario;
    private String nomeEvento;
    private String detalhesEvento;
    private String dataEvento;
    private String horaEvento;
    private String nomelocal;
    private boolean ativo;

    public Evento(){

    }


    public Evento(String id, String nomeEvento, String detalhesEvento, String nomelocal, String dataEvento, String horarioevento, String horaEvento) {
        this.id = id;
        this.nomeEvento = nomeEvento;
        this.detalhesEvento = detalhesEvento;
        this.nomelocal = nomelocal;
        this.dataEvento = dataEvento;
        this.horaEvento= horaEvento;

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
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

    public String getNomelocal() {
        return nomelocal;
    }

    public void setNomelocal(String nomelocal) {
        this.nomelocal = nomelocal;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}


