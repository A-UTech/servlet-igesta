package com.backigesta.model;

public class Condena {
//==========ATRIBUTOS==========\\

    private int id;
    private String nome;
    private String nomeAdmin;
    private String descricao;
    private String tipoCondena;

//==========CONSTRUTORES==========\\

    public Condena() {} // Construtor vazio

    public Condena(int id, String nome, String nomeAdmin, String descricao, String tipoCondena){
        this.id = id;
        this.nome = nome;
        this.nomeAdmin = nomeAdmin;
        this.descricao = descricao;
        this.tipoCondena = tipoCondena;
    } // Construtor completo

    public Condena(String nome, String nomeAdmin, String descricao, String tipoCondena) {
        this.nome = nome;
        this.nomeAdmin = nomeAdmin;
        this.descricao = descricao;
        this.tipoCondena = tipoCondena;
    } // Construtor com nome, nomeAdmin, descricao e tipoCondena

    public Condena(int id, String nome, String descricao, String tipoCondena) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.tipoCondena = tipoCondena;
    } // Construtor com id, nome, descricao e tipoCondena

//==========MÉTODOS GET==========\\

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTipoCondena() {
        return this.tipoCondena;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNomeAdmin() {
        return nomeAdmin;
    }

//==========MÉTODOS SET==========\\

    public void setTipoCondena(String tipoCondena) {
        this.tipoCondena = tipoCondena;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setNomeAdmin(String nome_admin) {
        this.nomeAdmin = nome_admin;
    }

//==========TO STRING==========\\

    public String toString(){
        return "NovaClasse{ id: " + id +
                " | tipo de condena: " + tipoCondena +
                " | nome: " + nome +
                " | descrição: " + descricao +
                " | id adiministrador: " + nomeAdmin +
                "}";
    }
}
