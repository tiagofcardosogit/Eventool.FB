package com.example.tiagocardoso.eventool.Helper;

/**
 * Created by tiagocardoso on 09/04/17.
 */
public class Usuario {
    private long ID;
    private String nome;
    private String sobrenome;
    private String email;
    private int telefone;

    public Usuario() {
    }

    public Usuario(long ID, String nome, String sobrenome, String email, int telefone) {
        this.ID = ID;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.email = email;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone() {
        this.telefone = telefone;
    }



}
