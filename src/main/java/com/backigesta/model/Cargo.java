package com.backigesta.model;

public class Cargo {
//=======================ATRIBUTOS=======================\\

    private int id;
    private String nome;
    private String descricao;

//=======================CONSTRUTORES=======================\\

    public Cargo() {
    } // Construtor vazio

    public Cargo(int id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    } // Construtor completo

    public Cargo(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    } // Construtor nome e descricao

    public Cargo(int id, String nome) {
        this.id = id;
        this.nome = nome;
    } // Construtor com id e nome

    public Cargo(String nome) {
        this.nome = nome;
    } // Construtor com nome

//=======================MÉTODOS GET=======================\\

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

//=======================MÉTODOS SET=======================\\

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

//=======================TO STRING=======================\\

    @Override
    public String toString() {
        return "Cargo{" +
                " id:" + id +
                "| nome:" + nome +
                "| descricao:" + descricao +
                '}';
    }
}
