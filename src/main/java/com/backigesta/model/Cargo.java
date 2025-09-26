package com.backigesta.model;

//Classe criada por Artur ;)
public class Cargo {
//=======================ATRIBUTOS=======================\\

    private int id;
    private String nome;
    private String descricao;

//=======================MÉTODOS DA CLASSE=======================\\

    //nenhum ainda... :P

//=======================CONSTRUTORES=======================\\


    //Vazio
    public Cargo() {
    }

    //Completo
    public Cargo(int id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    //Sem Id
    public Cargo(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    //Sem descricao
    public Cargo(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    //Sem Id nem Descricao
    public Cargo(String nome) {
        this.nome = nome;
    }

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
