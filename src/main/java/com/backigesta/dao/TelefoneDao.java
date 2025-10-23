package com.backigesta.dao;

import com.backigesta.conexao.Conexao;
import com.backigesta.model.Telefone;

import java.sql.*;
import java.util.ArrayList;

public class TelefoneDao {

    private Connection conn;
    private final Conexao conexao = new Conexao();

    public boolean inserirTelefone(Telefone telefone) {
        boolean retorno = false;
        try {
            conn = conexao.conectar();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO telefone(id_funcionario, telefone) VALUES(?, ?)");
            ps.setInt(1, telefone.getIdFuncionario());
            ps.setString(2, telefone.getTelefone());

            retorno = ps.executeUpdate() == 1;
        } catch (SQLException sqle) {
            System.out.println("!!SQLException ao chamar FuncionariosDAO.inserir(Funcionario)!!");
            sqle.printStackTrace();
        } finally {
            conexao.desconectar(conn);
            return retorno;
        }
    }

    public ArrayList<Telefone> buscarPorIdFuncionario(int id) {
        boolean retorno = false;
        ArrayList<Telefone> telefones = new ArrayList<>();
        try {
            conn = conexao.conectar();
            PreparedStatement ps = conn.prepareStatement("select * from telefone where id_funcionario = ?");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                telefones.add(new Telefone(rs.getInt(1),rs.getInt(2),rs.getString(3)));
            }

        } catch (SQLException sqle) {
            System.out.println("!!SQLException ao chamar FuncionariosDAO.inserir(Funcionario)!!");
            sqle.printStackTrace();
        } finally {
            conexao.desconectar(conn);
            return telefones;
        }
    }

    public boolean atualizarTelefone(Telefone telefone) {
        boolean retorno = false;
        try{
            conn = conexao.conectar();
            PreparedStatement ps = conn.prepareStatement("UPDATE telefone SET telefone=? where id = ?");
            ps.setString(1,telefone.getTelefone());
            ps.setInt(2,telefone.getId());

            retorno = ps.executeUpdate()==1;
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar FuncionariosDAO.atualizar(Funcionarios)!!");
            sqle.printStackTrace();
        }
        finally {
            conexao.desconectar(conn);
            return retorno;
        }
    }

    public boolean deletarTelefone(int id) {
        boolean retorno = false;
        try{
            conn = conexao.conectar();
            PreparedStatement ps = conn.prepareStatement("delete from telefone where id = ?");
            ps.setInt(1,id);

            retorno = ps.executeUpdate()==1;
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar FuncionariosDAO.atualizar(Funcionarios)!!");
            sqle.printStackTrace();
        }
        finally {
            conexao.desconectar(conn);
            return retorno;
        }
    }
}
