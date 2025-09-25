package com.example.model;

//Classe criada por Artur ;)
// A classe de Admin, servirá para confirmar o acesso dos Desenvolvedores na Área Restrita do website.
// Dessa forma somos capazes de criar um menu interativo para analisar, controlar ou extrair informações
// com mais facilidade.
public class Admin extends Usuarios{
//=======================ATRIBUTOS=======================\\

    private String sobrenome;

//=======================MÉTODOS DA CLASSE=======================\\

    //nenhum aqui tbm :P

//=======================MÉTODOS CONSTRUTORES=======================\\

    //Vazio
    public Admin() {
    }

    //Completo
    public Admin(int id, String nome, String sobrenome, String email, String senha, Byte[] foto) {
        super(id, nome, email, senha, foto);
        this.sobrenome = sobrenome;
    }

    //Sem Foto
    public Admin(int id, String nome, String sobrenome, String email, String senha) {
        super(id, nome, email, senha);
        this.sobrenome = sobrenome;
    }

    //Sem ID
    public Admin(String nome, String sobrenome, String email, String senha, Byte[] foto) {
        super(nome, email, senha, foto);
        this.sobrenome = sobrenome;
    }

    //Sem ID nem Foto
    public Admin(String nome, String sobrenome, String email, String senha) {
        super(nome, email, senha);
        this.sobrenome = sobrenome;
    }

//=======================MÉTODOS GET=======================\\

    public String getSobrenome() {
        return sobrenome;
    }

//=======================MÉTODOS SET=======================\\

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

//=======================TO STRING=======================\\

    public String toString() {
        return "Admin{ id: "+super.getId()+" | nome e sobrenome: "+super.getNome()+" "+
                sobrenome+" | email: "+super.getEmail()+" | senha: "+super.getSenha()+
                " }";
    }

}
