package com.backigesta.model;

//Classe criada por Artur ;)
//Classe empresas é responsável por guardar o login dos nossos clientes, no caso as fábricas.
//Nela será onde teremos as informações sobre o plano que essa empresa assinoy, e cnpj para
//Checarmos se ela de fato existe.
public class Empresas extends Usuarios{
//=======================ATRIBUTOS=======================\\

    private String cnpj;
    private String nomePlano;
    private String regiao;
    private String unidade;

//=======================MÉTODOS DA CLASSE=======================\\

    //nenhum aqui tbm :P

//=======================CONSTRUTORES=======================\\
    //Vazio
    public Empresas() {
    }

    //Completo
    public Empresas(int id, String nome, String email, String cnpj, String senha, String nomePlano, String regiao, String unidade, byte[] foto) {
        super(id, nome, email, senha, foto);
        this.cnpj = cnpj;
        this.nomePlano = nomePlano;
        this.regiao = regiao;
        this.unidade = unidade;
    }

    public Empresas(String nome, String email, String cnpj, String nomePlano, String regiao, String unidade) {
        super(nome, email);
        this.cnpj = cnpj;
        this.nomePlano = nomePlano;
        this.regiao = regiao;
        this.unidade = unidade;
    }

    //Sem Id
    public Empresas(String nome, String email, String cnpj, String senha, String nomePlano, String regiao, String unidade, byte[] foto) {
        super(nome, email, senha, foto);
        this.cnpj = cnpj;
        this.nomePlano = nomePlano;
        this.regiao = regiao;
        this.unidade = unidade;
    }

    //Sem foto
    public Empresas(int id, String nome, String email, String cnpj, String senha, String nomePlano, String regiao, String unidade) {
        super(id, nome, email, senha);
        this.cnpj = cnpj;
        this.nomePlano = nomePlano;
        this.regiao = regiao;
        this.unidade = unidade;
    }

    //Sem id_planos
    public Empresas(int id, String nome, String email, String cnpj, String senha, String regiao, String unidade, byte[] foto) {
        super(id, nome, email, senha, foto);
        this.cnpj = cnpj;
        this.regiao = regiao;
        this.unidade = unidade;
    }

    //Sem foto nem Id
    public Empresas(String nome, String email, String cnpj, String senha, String nomePlano, String regiao, String unidade) {
        super(nome, email, senha);
        this.cnpj = cnpj;
        this.nomePlano = nomePlano;
        this.regiao = regiao;
        this.unidade = unidade;
    }

    //Sem id_planos nem Id
    public Empresas(String nome, String email, String cnpj, String senha, String regiao, String unidade, byte[] foto) {
        super(nome, email, senha, foto);
        this.cnpj = cnpj;
        this.regiao = regiao;
        this.unidade = unidade;
    }

    //Sem foto nem id_planos
    public Empresas(int id, String nome, String email, String cnpj, String senha, String regiao, String unidade) {
        super(id, nome, email, senha);
        this.cnpj = cnpj;
        this.regiao = regiao;
        this.unidade = unidade;
    }


//=======================MÉTODOS GET=======================\\

    public String getCnpj() {
        return cnpj;
    }

    public String getNomePlano() {
        return nomePlano;
    }

    public String getRegiao() {
        return regiao;
    }

    public String getUnidade() {
        return unidade;
    }

//=======================MÉTODOS SET=======================\\

    public void setNomePlano(String nomePlano) {
        this.nomePlano = nomePlano;
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
                " | regiao: "+regiao+
                " | unidade: "+unidade+
                " | Tem foto: " + (super.getFoto()!=null ? "sim":"não") +
                " }";
    }
}