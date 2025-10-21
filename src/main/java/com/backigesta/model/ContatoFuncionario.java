package com.backigesta.model;

public class ContatoFuncionario {
    //=======================ATRIBUTOS=======================\\

    private int id;
    private String nomeEmpresa;
    private String nome;
    private String email;
    private String telefone;


//=======================CONSTRUTORES=======================\\


    //Vazio
    public ContatoFuncionario() {
    }

    //Completo
    public ContatoFuncionario(int id,String nomeEmpresa, String nome, String email, String telefone) {
        this.id = id;
        this.nomeEmpresa = nomeEmpresa;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public ContatoFuncionario(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public ContatoFuncionario(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    //=======================MÉTODOS GET=======================\\

    public int getId() {
        return id;
    }
    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

//=======================MÉTODOS SET=======================\\

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//=======================TO STRING=======================\\


    @Override
    public String toString() {
        return "Contato Funcionario {" +
                " Nome empresa:" + nomeEmpresa +
                "| nome:" + nome +
                "| email:" + email +
                "| telefone:" + telefone +
                '}';
    }
}
