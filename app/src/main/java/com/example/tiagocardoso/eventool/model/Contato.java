package com.example.tiagocardoso.eventool.model;

/**
 * Created by tiagocardoso on 25/05/17.
 */

public class Contato {
    private String identificadorUsuario;
    private String nome;
    private String email;

    public Contato() {

    }

    public String getIdentificadorUsuario() {
        return identificadorUsuario;
    }

    public void setIdentificadorUsuario(String identificadorUsuario) {
        this.identificadorUsuario = identificadorUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}