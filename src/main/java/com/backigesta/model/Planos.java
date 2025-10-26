package com.backigesta.model;

public class Planos {
//==========ATRIBUTOS==========\\

    private int id;
    private String nome;
    private double mensalidade;
    private int armazenamento;

//==========CONSTRUTORES==========\\

    public Planos(){} // Construtor Vazio

    public Planos(int id, String nome, double mensalidade, int armazenamento){
        this.id = id;
        this.nome = nome;
        this.mensalidade = mensalidade;
        this.armazenamento = armazenamento;
    } // Construtor Completo

    public Planos(String nome, double mensalidade, int armazenamento){
        this.nome = nome;
        this.mensalidade = mensalidade;
        this.armazenamento = armazenamento;
    } // Construtor com nome, mensalidade e armazenamento

    public Planos(String nome, int mensalidade) {
        this.nome = nome;
        this.mensalidade = mensalidade;
    } // Construtor com nome e mensalidade

//==========MÉTODOS GET==========\\

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getMensalidade() {
        return mensalidade;
    }

    public int getArmazenamento() {
        return armazenamento;
    }

//==========MÉTODOS SET==========\\

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMensalidade(double mensalidade) {
        this.mensalidade = mensalidade;
    }

    public void setArmazenamento(int armazenamento) {
        this.armazenamento = armazenamento;
    }

//==========TO STRING==========\\

    public String toString(){
        return "NovaClasse{ id: " + id +
                " | nome: " + nome +
                " | mensalidade: " + mensalidade +
                " | armazenamento: " + armazenamento +
                "}";
    }
}
