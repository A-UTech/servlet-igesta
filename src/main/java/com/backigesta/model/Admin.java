package com.backigesta.model;

public class Admin extends Usuario {
//=======================MÉTODOS CONSTRUTORES=======================\\

    public Admin() {
    } // Construtor vazio

    public Admin(int id, String nome, String email, String senha, byte[] foto) {
        super(id, nome, email, senha, foto);
    } // Construtor completo

    //Sem Foto
    public Admin(int id, String nome, String email, String senha) {
        super(id, nome, email, senha);
    } // Construtor com id, nome, email e senha

    //Sem ID
    public Admin(String nome, String email, String senha, byte[] foto) {
        super(nome, email, senha, foto);
    } // Construtor com nome, email, senha e foto

    //Sem ID nem Foto
    public Admin(String nome, String email, String senha) {
        super(nome, email, senha);
    } // Construtor com nome, email e senha

//=======================TO STRING=======================\\

    public String toString() {
        return "Admin{ id: "+super.getId()+" | nome: "+super.getNome()+"  | email: "+super.getEmail()+" | senha: "+super.getSenha()+
                " | Tem foto: " + (super.getFoto()!=null ? "sim":"não") +
                " }";
    }

}
