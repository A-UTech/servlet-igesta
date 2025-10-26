package com.backigesta.dao;

import com.backigesta.conexao.Conexao;
import com.backigesta.model.Telefone;

import java.sql.*;
import java.util.ArrayList;

public class TelefoneDao {

    private Connection conn;
    private final Conexao conexao = new Conexao();

//=======================MÉTODOS CREATE=======================\\

    public boolean inserir(Telefone telefone) {
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
    } // Método que inserir um novo Telefone no banco

//=======================MÉTODOS READ=======================\\

    public ArrayList<Telefone> selecionarPorIdFuncionario(int id) {
        ArrayList<Telefone> telefones = new ArrayList<>();
        try {
            conn = conexao.conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM telefone WHERE id_funcionario = ?");
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
    } // Método para selecionar os telefones por o IdFuncionario

//=======================MÉTODOS UPDATE=======================\\

    public boolean atualizar(Telefone telefone) {
        boolean retorno = false;
        try{
            conn = conexao.conectar();
            PreparedStatement ps = conn.prepareStatement("UPDATE telefone SET telefone = ? WHERE id = ?");
            ps.setString(1,telefone.getTelefone());
            ps.setInt(2,telefone.getId());

            retorno = ps.executeUpdate() == 1;
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar FuncionariosDAO.atualizar(Funcionarios)!!");
            sqle.printStackTrace();
        }
        finally {
            conexao.desconectar(conn);
            return retorno;
        }
    } // Método para atualizar dados do telefone

//=======================MÉTODOS DELETE=======================\\

    public boolean deletar(int id) {
        boolean retorno = false;
        try{
            conn = conexao.conectar();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM telefone WHERE id = ?");
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
    } // Método para deletar um registro de telefone por id
}
