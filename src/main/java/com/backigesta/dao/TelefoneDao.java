package com.backigesta.dao;

import com.backigesta.conexao.Conexao;
import com.backigesta.model.Telefone;

import java.sql.*;

public class TelefoneDao {

    private Connection conn;
    private final Conexao conexao = new Conexao();

    public boolean inserirTelefone(Telefone telefone) {
        boolean retorno = false;
        try {
            conn = conexao.conectar();
            PreparedStatement ps = conn.prepareStatement("select id from funcionarios where lower(nome) = lower(?)");
            ps.setString(1, telefone.getNomeFuncionario());
            ResultSet rs = ps.executeQuery();
            int idFuncionario = -1;
            while (rs.next()) {
                idFuncionario = rs.getInt(1);
            }

            ps = conn.prepareStatement("INSERT INTO telefones(id_funcionario, telefone) VALUES(?, ?)");
            ps.setInt(1, idFuncionario);
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

    public boolean atualizarTelefone(Telefone telefone) {
        boolean retorno = false;
        try{
            conn = conexao.conectar();
            PreparedStatement ps = conn.prepareStatement("UPDATE telefones SET telefone=? where id = ?");
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
            PreparedStatement ps = conn.prepareStatement("delete from telefones where id = ?");
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
