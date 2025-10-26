package com.backigesta.model;

public class Telefone {
//==========ATRIBUTOS==========\\

    private int id;
    private int idFuncionario;
    private String telefone;

//==========CONSTRUTORES==========\\

    public Telefone(){} // Construtor Vazio

    public Telefone(int id, int idFuncionario, String telefone){
        this.id = id;
        this.idFuncionario = idFuncionario;
        this.telefone = telefone;
    } // Construtor Completo

    public Telefone(String telefone,int idFuncionario) {
        this.idFuncionario = idFuncionario;
        this.telefone = telefone;
    } // Construtor com telefone e idFuncionario

    public Telefone(int id, String telefone) {
        this.id = id;
        this.telefone = telefone;
    } // Construtor com id e telefone

//==========MÉTODOS GET==========\\

    public int getId() {
        return id;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public String getTelefone() {
        return telefone;
    }

//==========MÉTODOS SET==========\\

    public void setIdFuncionaro(int idFuncionaro) {
        this.idFuncionario = idFuncionaro;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

//==========TO STRING==========\\

    public String toString(){
        return "NovaClasse{ id: " + this.id +
                " | Nome funcionário: " + this.idFuncionario +
                " | telefone: " + this.telefone + "}";
    }
}
