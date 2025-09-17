package com.example.model;

//Classe criada por Artur ;)
//A Classe Abstrata Usuarios, é a base para as outras classes de contas, contendo métodos
// e atributos básicos para a abstração de uma pessoa.
public abstract class Usuarios {
//=======================ATRIBUTOS=======================\\

    private String nome;
    private String email;
    private String senha;
    private Byte[] foto;

//=======================MÉTODOS DA CLASSE=======================\\

    //nenhum ainda :P

//=======================CONSTRUTORES=======================\\

    //Vazio
    public Usuarios() {
    }

    //Completo
    public Usuarios(String nome, String email, String senha, Byte[] foto) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.foto = foto;
    }

    //Sem foto
    public Usuarios(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

//=======================METODOS GET=======================\\

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public Byte[] getFoto() {
        return foto;
    }

//=======================METODOS SET=======================\\

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setFoto(Byte[] foto) {
        this.foto = foto;
    }

//=======================TO STRING=======================\\

    public String toString() {
        return "Usuario{ " +
                "nome: "+nome+
                " | email: "+email+
                " | senha: "+senha+
                " }";
    }
}