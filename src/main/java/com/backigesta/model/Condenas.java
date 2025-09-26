package com.backigesta.model;

public class Condenas {
    //==========ATRIBUTOS==========\\

    private int id;
    private String tipo_condena;
    private String nome;
    private String descricao;
    private int id_admin;

    //==========CONSTRUTORES==========\\

    public Condenas(){} // Vazio
    public Condenas(int id, String tipo_condena, String nome, String descricao, int id_admin){
        this.id = id;
        this.tipo_condena = tipo_condena;
        this.nome = nome;
        this.descricao = descricao;
        this.id_admin = id_admin;
    } //Completo

    public Condenas(String tipo_condena, String nome) {
        this.tipo_condena = tipo_condena;
        this.nome = nome;
    }     // Construtor apenas com tipo e nome


    //==========MÉTODOS GET==========\\

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getId_admin() {
        return id_admin;
    }
    //==========MÉTODOS SET==========\\

    public void setTipo_condena(String tipo_condena) {
        this.tipo_condena = tipo_condena;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }
    //==========TO STRING==========\\

    public String toString(){
        return "NovaClasse{ id: " + id +
                " | tipo de condena: " + tipo_condena +
                " | nome: " + nome +
                " | descrição: " + descricao +
                " | id adiministrador: " + id_admin +
                "}";
    }
}
