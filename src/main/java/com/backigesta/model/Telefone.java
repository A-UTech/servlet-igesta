package com.backigesta.model;

public class Telefone {
    //==========ATRIBUTOS==========\\

    private int id;
    private String nomeFuncionario;
    private String telefone;

    //==========CONSTRUTORES==========\\
    public Telefone(){} //Vazio
    public Telefone(int id, String nomeFuncionario, String telefone){
        this.id = id;
        this.nomeFuncionario = nomeFuncionario;
        this.telefone = telefone;
    } //Completo

    public Telefone(String nomeFuncionario, String telefone) {
        this.nomeFuncionario = nomeFuncionario;
        this.telefone = telefone;
    }     // Construtor com nomeFuncionario e telefone

    public Telefone(int id, String telefone) {
        this.id = id;
        this.telefone = telefone;
    }

    //==========MÉTODOS GET==========\\

    public int getId() {
        return id;
    }
    public String getNomeFuncionario() {
        return nomeFuncionario;
    }
    public String getTelefone() {
        return telefone;
    }
    //==========MÉTODOS SET==========\\

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    //==========TO STRING==========\\

    public String toString(){
        return "NovaClasse{ id: " + this.id +
                " | Nome funcionário: " + this.nomeFuncionario +
                " | telefone: " + this.telefone + "}";
    }
}
