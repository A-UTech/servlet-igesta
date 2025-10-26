package com.backigesta.model;

public class Empresas extends Usuarios{
//=======================ATRIBUTOS=======================\\

    private String cnpj;
    private String nomePlano;
    private String estado;
    private String cidade;
    private String unidade;

//=======================CONSTRUTORES=======================\\

    public Empresas() {
    } // Construtor vazio

    public Empresas(int id, String nome, String email, String cnpj, String senha, String nomePlano, String estado,String cidade, String unidade, byte[] foto) {
        super(id, nome, email, senha, foto);
        this.cnpj = cnpj;
        this.nomePlano = nomePlano;
        this.estado = estado;
        this.cidade = cidade;
        this.unidade = unidade;
    } // Construtor completo

    public Empresas(String nome, String email, String cnpj, String nomePlano, String estado, String unidade) {
        super(nome, email);
        this.cnpj = cnpj;
        this.nomePlano = nomePlano;
        this.estado = estado;
        this.unidade = unidade;
    } // Construtor com nome, email, cnpf, nomePlano, estado e unidade

    public Empresas(String nome, String email, String cnpj, String senha, String nomePlano, String estado, String unidade, byte[] foto) {
        super(nome, email, senha, foto);
        this.cnpj = cnpj;
        this.nomePlano = nomePlano;
        this.estado = estado;
        this.unidade = unidade;
    } // Construtor com nome, email, cnpj, senha, nomePlano, estado, unidade e foto

    public Empresas(int id, String nome, String email, String senha, String nomePlano, String estado,String cidade, String unidade) {
        super(id, nome, email, senha);
        this.nomePlano = nomePlano;
        this.estado = estado;
        this.cidade = cidade;
        this.unidade = unidade;
    } // Construtor com id, nome, email, cnpj, senha, nomePlano, estado e unidade

    public Empresas(int id, String nome, String email, String cnpj, String senha, String estado, String unidade, byte[] foto) {
        super(id, nome, email, senha, foto);
        this.cnpj = cnpj;
        this.estado = estado;
        this.unidade = unidade;
    } // Construtor com id, nome, email, cnpj, senha, estado, unidade e foto

    public Empresas(String nome, String email, String cnpj, String senha, String nomePlano, String estado,String cidade, String unidade) {
        super(nome, email, senha);
        this.cnpj = cnpj;
        this.nomePlano = nomePlano;
        this.estado = estado;
        this.cidade = cidade;
        this.unidade = unidade;
    } // Construtor com nome, email, cnpj, senha, nomePlano, estado e unidade

    public Empresas(String nome, String email, String cnpj, String nomePlano, String estado,String cidade, String unidade) {
        super(nome, email);
        this.cnpj = cnpj;
        this.nomePlano = nomePlano;
        this.estado = estado;
        this.cidade = cidade;
        this.unidade = unidade;
    } // Construtor com nome, email, cnpj, senha, nomePlano, estado e unidade

    // nome,email,cnpj,plano,estado,cidade,unidade
    public Empresas(String nome, String email, String cnpj, String senha, String estado, String unidade, byte[] foto) {
        super(nome, email, senha, foto);
        this.cnpj = cnpj;
        this.estado = estado;
        this.unidade = unidade;
    } // Construtor com nome, email, cnpj, senha, estado, unidade e foto

    public Empresas(int id, String nome, String email, String cnpj, String senha, String estado, String unidade) {
        super(id, nome, email, senha);
        this.cnpj = cnpj;
        this.estado = estado;
        this.unidade = unidade;
    } // Construtor com id, nome, email, cnpj, senha, estado e unidade

//=======================MÉTODOS GET=======================\\

    public String getCnpj() {
        return cnpj;
    }

    public String getNomePlano() {
        return nomePlano;
    }

    public String getEstado() {
        return estado;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUnidade() {
        return unidade;
    }

//=======================MÉTODOS SET=======================\\

    public void setNomePlano(String nomePlano) {
        this.nomePlano = nomePlano;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

//=======================TO STRING=======================\\

    @Override
    public String toString() {
        return "Empresas{ " +
                "id: " +super.getId()+
                " | cnpj: "+cnpj+
                " | id_planos: "+nomePlano+
                " | nome: "+super.getNome()+
                " | email: "+super.getEmail()+
                " | senha: "+super.getSenha()+
                " | regiao: "+estado+
                " | unidade: "+unidade+
                " | cidade: "+cidade+
                " | Tem foto: " + (super.getFoto()!=null ? "sim":"não") +
                " }";
    }
}