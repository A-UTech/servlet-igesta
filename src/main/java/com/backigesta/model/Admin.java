package com.backigesta.model;

//Classe criada por Artur ;)
// A classe de Admin, servirá para confirmar o acesso dos Desenvolvedores na Área Restrita do website.
// Dessa forma somos capazes de criar um menu interativo para analisar, controlar ou extrair informações
// com mais facilidade.
public class Admin extends Usuarios{
//=======================ATRIBUTOS=======================\\


//=======================MÉTODOS DA CLASSE=======================\\

    //nenhum aqui tbm :P

//=======================MÉTODOS CONSTRUTORES=======================\\

    //Vazio
    public Admin() {
    }

    //Completo
    public Admin(int id, String nome, String email, String senha, byte[] foto) {
        super(id, nome, email, senha, foto);
    }

    //Sem Foto
    public Admin(int id, String nome, String email, String senha) {
        super(id, nome, email, senha);
    }

    //Sem ID
    public Admin(String nome, String email, String senha, byte[] foto) {
        super(nome, email, senha, foto);
    }

    //Sem ID nem Foto
    public Admin(String nome, String sobrenome, String email, String senha) {
        super(nome, email, senha);
    }

//=======================MÉTODOS GET=======================\\



//=======================MÉTODOS SET=======================\\



//=======================TO STRING=======================\\

    public String toString() {
        return "Admin{ id: "+super.getId()+" | nome: "+super.getNome()+"  | email: "+super.getEmail()+" | senha: "+super.getSenha()+
                " | Tem foto: " + (super.getFoto()!=null ? "sim":"não") +
                " }";
    }

}
