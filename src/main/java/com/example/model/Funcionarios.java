package com.example.model;

import java.time.LocalTime;

//Classe criada por Artur ;)
public class Funcionarios extends Usuarios{
//=======================ATRIBUTOS=======================\\
    private LocalTime turno;
    private String cpf;
    private String sobrenome;
    private int id_empresa;
    private int id_cargo;
    private int id_permissoes;
//=======================MÉTODOS DA CLASSE=======================\\

    //nenhum aqui tbm :P

//=======================CONSTRUTORES=======================\\

    //Vazio
    public Funcionarios() {
    }

    //Completo
    public Funcionarios(int id ,String nome, String sobrenome, String email, String cpf, String senha, int id_empresa, int id_cargo, int id_permissoes, LocalTime turno ,Byte[] foto) {
        super(id, nome, email, senha, foto);
        this.turno = turno;
        this.cpf = cpf;
        this.sobrenome = sobrenome;
        this.id_empresa = id_empresa;
        this.id_cargo = id_cargo;
        this.id_permissoes = id_permissoes;
    }

    //Sem Id
    public Funcionarios(String nome, String sobrenome, String email, String cpf, String senha, int id_empresa, int id_cargo, int id_permissoes, LocalTime turno ,Byte[] foto) {
        super(nome, email, senha, foto);
        this.turno = turno;
        this.cpf = cpf;
        this.sobrenome = sobrenome;
        this.id_empresa = id_empresa;
        this.id_cargo = id_cargo;
        this.id_permissoes = id_permissoes;
    }

    //Sem foto
    public Funcionarios(int id ,String nome, String sobrenome, String email, String cpf, String senha, int id_empresa, int id_cargo, int id_permissoes, LocalTime turno) {
        super(id, nome, email, senha);
        this.turno = turno;
        this.cpf = cpf;
        this.sobrenome = sobrenome;
        this.id_empresa = id_empresa;
        this.id_cargo = id_cargo;
        this.id_permissoes = id_permissoes;
    }

    //Sem Foto nem Id
    public Funcionarios(String nome, String sobrenome, String email, String cpf, String senha, int id_empresa, int id_cargo, int id_permissoes, LocalTime turno) {
        super(nome, email, senha);
        this.turno = turno;
        this.cpf = cpf;
        this.sobrenome = sobrenome;
        this.id_empresa = id_empresa;
        this.id_cargo = id_cargo;
        this.id_permissoes = id_permissoes;
    }

//=======================MÉTODOS GET=======================\\

    public LocalTime getTurno() {
        return turno;
    }

    public String getCpf() {
        return cpf;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public int getId_cargo() {
        return id_cargo;
    }

    public int getId_permissoes() {
        return id_permissoes;
    }

//=======================MÉTODOS SET=======================\\


    public void setTurno(LocalTime turno) {
        this.turno = turno;
    }

    public void setId_cargo(int id_cargo) {
        this.id_cargo = id_cargo;
    }

    public void setId_permissoes(int id_permissoes) {
        this.id_permissoes = id_permissoes;
    }

//=======================TO STRING=======================\\


    @Override
    public String toString() {
        return "Funcionarios{" +
                " id: " + super.getId() +
                " | nome: "+super.getNome()+
                " | email: "+super.getEmail()+
                " | senha: "+super.getSenha()+
                " | turno:" + turno +
                " | cpf:" + cpf +
                " | sobrenome:" + sobrenome +
                " | id_empresa:" + id_empresa +
                " | id_cargo:" + id_cargo +
                " | id_permissoes:" + id_permissoes +
                " }";
    }
}
