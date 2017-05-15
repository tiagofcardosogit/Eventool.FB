package com.example.tiagocardoso.eventool.model;


/**
 * Created by tiagocardoso on 15/04/17.
 */

public class Evento {

    private long id_evento;
    private long id_usuario;
    private String nome;
    private String descricao;
    private String dataevento;
    private String horarioevento;
    private String nomelocal;
    private String logradouro;
    private int numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;
    private boolean ativo;

    public Evento(){

    }


    public Evento(long ID, String nome, String descricao, String nomelocal, String dataevento, String horarioevento, String cidade, String estado) {
        this.id_evento = ID;
        this.nome = nome;
        this.descricao = descricao;
        this.nomelocal = nomelocal;
        this.dataevento = dataevento;
        this.horarioevento= horarioevento;
        this.cidade = cidade;
        this.estado = estado;
    }

    public long getId_evento() {
        return id_evento;
    }

    public void setId_evento(long id_evento) {
        this.id_evento = id_evento;
    }

    public long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataevento() {
        return dataevento;
    }

    public void setDataevento(String dataevento) {
        this.dataevento = dataevento;
    }

    public String getHorarioevento() {
        return horarioevento;
    }

    public void setHorarioevento(String horarioevento) {
        this.horarioevento = horarioevento;
    }

    public String getNomelocal() {
        return nomelocal;
    }

    public void setNomelocal(String nomelocal) {
        this.nomelocal = nomelocal;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
