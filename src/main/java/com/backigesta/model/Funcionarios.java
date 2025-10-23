package com.backigesta.model;

import java.time.LocalTime;

//Classe criada por Artur ;)
public class Funcionarios extends Usuarios{
//=======================ATRIBUTOS=======================\\
    private LocalTime turno;
    private String cpf;
    private String nomeEmpresa;
    private String nomeCargo;
    private int idPermissoes;
//=======================MÉTODOS DA CLASSE=======================\\

    //nenhum aqui tbm :P

//=======================CONSTRUTORES=======================\\

    //Vazio
    public Funcionarios() {
    }

    public Funcionarios(int id,String nome) {
        super(id,nome);
    }

    //Completo
    public Funcionarios(int id ,String nome,String email, String cpf, String senha, String nomeEmpresa, String nomeCargo, int idPermissoes, LocalTime turno, byte[] foto) {
        super(id, nome, email, senha, foto);
        this.turno = turno;
        this.cpf = cpf;
        this.nomeEmpresa = nomeEmpresa;
        this.nomeCargo = nomeCargo;
        this.idPermissoes = idPermissoes;
    }

    //Sem Id
    public Funcionarios(String nome, String email, String cpf, String senha, String nomeEmpresa, String nomeCargo, int idPermissoes, LocalTime turno, byte[] foto) {
        super(nome, email, senha, foto);
        this.turno = turno;
        this.cpf = cpf;
        this.nomeEmpresa = nomeEmpresa;
        this.nomeCargo = nomeCargo;
        this.idPermissoes = idPermissoes;
    }

    //Sem foto
    public Funcionarios(int id ,String nome, String email, String cpf, String senha, String nomeEmpresa, String nomeCargo, int idPermissoes, LocalTime turno) {
        super(id, nome, email, senha);
        this.turno = turno;
        this.cpf = cpf;
        this.nomeEmpresa = nomeEmpresa;
        this.nomeCargo = nomeCargo;
        this.idPermissoes = idPermissoes;
    }

    //Sem Foto nem Id
    public Funcionarios(String nome, String email, String cpf, String senha, String nomeEmpresa, String nomeCargo, int idPermissoes, LocalTime turno) {
        super(nome, email, senha);
        this.turno = turno;
        this.cpf = cpf;
        this.nomeEmpresa = nomeEmpresa;
        this.nomeCargo = nomeCargo;
        this.idPermissoes = idPermissoes;
    }

//=======================MÉTODOS GET=======================\\

    public LocalTime getTurno() {
        return turno;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public String getNomeCargo() {
        return nomeCargo;
    }

    public int getIdPermissoes() {
        return idPermissoes;
    }

//=======================MÉTODOS SET=======================\\


    public void setTurno(LocalTime turno) {
        this.turno = turno;
    }

    public void setNomeCargo(String nomeCargo) {
        this.nomeCargo = nomeCargo;
    }

    public void setIdPermissoes(int idPermissoes) {
        this.idPermissoes = idPermissoes;
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
                " | nomeEmpresa:" + nomeEmpresa +
                " | nomeCargo:" + nomeCargo +
                " | idPermissoes:" + idPermissoes +
                " | Tem foto: " + (super.getFoto()!=null ? "sim":"não") +
                " }";
    }
}
