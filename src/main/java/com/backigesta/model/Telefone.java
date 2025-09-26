package com.backigesta.model;

public class Telefone {
    //==========ATRIBUTOS==========\\

    private int id;
    private int id_funcionario;
    private String telefone;

    //==========CONSTRUTORES==========\\
    public Telefone(){} //Vazio
    public Telefone(int id, int id_funcionario, String telefone){
        this.id = id;
        this.id_funcionario = id_funcionario;
        this.telefone = telefone;
    } //Completo

    public Telefone(int id_funcionario, String telefone) {
        this.id_funcionario = id_funcionario;
        this.telefone = telefone;
    }     // Construtor com id_funcionario e telefone

    //==========MÉTODOS GET==========\\

    public int getId() {
        return id;
    }
    public int getId_funcionario() {
        return id_funcionario;
    }
    public String getTelefone() {
        return telefone;
    }
    //==========MÉTODOS SET==========\\

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    //==========TO STRING==========\\

    public String toString(){
        return "NovaClasse{ id: " + this.id +
                " | id funcionário: " + this.id_funcionario +
                " | telefone: " + this.telefone + "}";
    }
}
