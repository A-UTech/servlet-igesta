package com.backigesta.dao;

import com.backigesta.conexao.Conexao;
import com.backigesta.model.Condenas;
import com.backigesta.model.ContatoFuncionario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContatoFuncionarioDao {
    private Connection conn;
    private final Conexao conexao = new Conexao();

    public List<ContatoFuncionario> buscarContatoFuncionario() {
        conn = conexao.conectar();
        ArrayList<ContatoFuncionario> listas = new ArrayList<>();
        try {
            Statement pstmt = conn.createStatement();
            ResultSet rs = pstmt.executeQuery("select t.id, e.nome, f.nome, f.email, t.telefone from empresas e join funcionarios f on f.id_empresa = e.id join telefones t on f.id = t.id_funcionario order by f.nome");
            while (rs.next()) {
                listas.add(new ContatoFuncionario(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException sql) {
            return null;
        } finally {
            conexao.desconectar(conn);
        }
        return listas;
    }

    public List<ContatoFuncionario> buscarContatoFuncionarioPorNome(String nome) {
        conn = conexao.conectar();
        ArrayList<ContatoFuncionario> listas = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement("select t.id, e.nome, f.nome, f.email, t.telefone from empresas e join funcionarios f on f.id_empresa = e.id join telefones t on f.id = t.id_funcionario where lower(f.nome) like lower(?) order by f.nome");
            pstmt.setString(1,nome);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                listas.add(new ContatoFuncionario(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException sql) {
            return null;
        } finally {
            conexao.desconectar(conn);
        }
        return listas;
    }

    public List<ContatoFuncionario> buscarContatoFuncionarioPorTelefone(String telefone) {
        conn = conexao.conectar();
        ArrayList<ContatoFuncionario> listas = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement("select t.id, e.nome, f.nome, f.email, t.telefone from empresas e join funcionarios f on f.id_empresa = e.id join telefones t on f.id = t.id_funcionario where lower(t.telefone) like lower(?) order by f.nome");
            pstmt.setString(1,telefone);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                listas.add(new ContatoFuncionario(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException sql) {
            return null;
        } finally {
            conexao.desconectar(conn);
        }
        return listas;
    }
}
