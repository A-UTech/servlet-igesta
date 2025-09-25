package com.example.model;

//Classe criada por Artur ;)
//Classe empresas é responsável por guardar o login dos nossos clientes, no caso as fábricas.
//Nela será onde teremos as informações sobre o plano que essa empresa assinoy, e cnpj para
//Checarmos se ela de fato existe.
public class Empresas extends Usuarios{
//=======================ATRIBUTOS=======================\\

    private String cnpj;
    private int id_planos;

//=======================MÉTODOS DA CLASSE=======================\\

    //nenhum aqui tbm :P

//=======================CONSTRUTORES=======================\\
    //Vazio
    public Empresas() {
    }

    //Completo
    public Empresas(int id, String nome, String email, String cnpj, String senha, int id_planos,  Byte[] foto) {
        super(id, nome, email, senha, foto);
        this.cnpj = cnpj;
        this.id_planos = id_planos;
    }

    //Sem Id
    public Empresas(String nome, String email, String cnpj, String senha, int id_planos,  Byte[] foto) {
        super(nome, email, senha, foto);
        this.cnpj = cnpj;
        this.id_planos = id_planos;
    }

    //Sem foto
    public Empresas(int id, String nome, String email, String cnpj, String senha, int id_planos) {
        super(id, nome, email, senha);
        this.cnpj = cnpj;
        this.id_planos = id_planos;
    }

    //Sem id_planos
    public Empresas(int id, String nome, String email, String cnpj, String senha, Byte[] foto) {
        super(id, nome, email, senha, foto);
        this.cnpj = cnpj;
    }

    //Sem foto nem Id
    public Empresas(String nome, String email, String cnpj, String senha, int id_planos) {
        super(nome, email, senha);
        this.cnpj = cnpj;
        this.id_planos = id_planos;
    }

    //Sem id_planos nem Id
    public Empresas(String nome, String email, String cnpj, String senha, Byte[] foto) {
        super(nome, email, senha, foto);
        this.cnpj = cnpj;
    }

    //Sem foto nem id_planos
    public Empresas(int id, String nome, String email, String cnpj, String senha) {
        super(id, nome, email, senha);
        this.cnpj = cnpj;
    }





    //Sem foto, id_planos e Id
    public Empresas(String nome, String email, String cnpj, String senha) {
        super(nome, email, senha);
        this.cnpj = cnpj;
    }


//=======================MÉTODOS GET=======================\\

    public String getCnpj() {
        return cnpj;
    }

    public int getId_planos() {
        return id_planos;
    }

//=======================MÉTODOS SET=======================\\

    public void setId_planos(int id_planos) {
        this.id_planos = id_planos;
    }

//=======================TO STRING=======================\\

    @Override
    public String toString() {
        return "Empresas{ " +
                "id: " +super.getId()+
                " | cnpj: "+cnpj+
                " | id_planos: "+id_planos+
                " | nome: "+super.getNome()+
                " | email: "+super.getEmail()+
                " | senha: "+super.getSenha()+
                " }";
    }
}