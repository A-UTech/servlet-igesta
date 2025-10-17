package com.backigesta.dao;

import com.backigesta.conexao.Conexao;
import com.backigesta.model.Condenas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CondenasDao{

    //==========ATRIBUTOS==========\\
    private Connection conn;
    private Conexao conexao = new Conexao();

    //==========MÉTODOS DA CLASSE==========\\
    public ArrayList<Condenas> buscarCondenas() {
        conn = conexao.conectar();
        ArrayList<Condenas> listas = new ArrayList<>();
        try {
            Statement pstmt = conn.createStatement();
            ResultSet rs = pstmt.executeQuery("select c.id,c.nome,a.nome,c.descricao,c.tipo_condena from condenas c join admin a on a.id = c.cod_admin order by c.nome");
            while (rs.next()) {
                listas.add(new Condenas(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException sql) {
            return null;
        } finally {
            conexao.desconectar(conn);
        }
        return listas;
    } // Método que busca todas as condenas do banco de dados

    public ArrayList<Condenas> buscarCondenasTipo(String tipo_condena) {
        conn = conexao.conectar();
        ArrayList<Condenas> listas = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement("select c.id,c.nome,a.nome,c.descricao,c.tipo_condena from condenas c join admin a on a.id = c.cod_admin where lower(c.tipo_condena) = ? order by c.nome");
            pstmt.setString(1,tipo_condena);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                listas.add(new Condenas(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException sql) {
            return null;
        } finally {
            conexao.desconectar(conn);
        }
        return listas;
    } // Método que busca as condenas por seu tipo de condena no banco de daddos

    public ArrayList<Condenas> buscarCondenasNome(String procura) {
        conn = conexao.conectar();
        ArrayList<Condenas> listas = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement("select c.id,c.nome,a.nome,c.descricao,c.tipo_condena from condenas c join admin a on a.id = c.cod_admin where lower(c.nome) like ? order by c.nome");
            pstmt.setString(1,procura+"%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                listas.add(new Condenas(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException sql) {
            return null;
        } finally {
            conexao.desconectar(conn);
        }
        return listas;
    } // Método que busca as condenas por seu nome no banco de dados

    public boolean deletarCondena(int id) {
        conn = conexao.conectar();
        try {
            PreparedStatement pstmt1 = conn.prepareStatement("delete from quantidadecondenas where cod_condena = ?");
            pstmt1.setInt(1,id);
            pstmt1.execute();
            PreparedStatement pstmt2 = conn.prepareStatement("delete from condenas where id = ?");
            pstmt2.setInt(1,id);
            if (pstmt2.executeUpdate() > 0) {
                return true;
            }
            return false;
        } catch (SQLException sql) {
            sql.printStackTrace();
            return false;
        } finally {
            conexao.desconectar(conn);
        }
    } // Método que deleta um registro de condena por seu id no banco de dados

    public boolean alterarCondena(Condenas condena) {
        conn = conexao.conectar();
        try {
            PreparedStatement pstmt = conn.prepareStatement("update condenas set tipo_condena = ?, nome = ?, descricao = ? where id = ?");
            pstmt.setString(1,condena.getTipoCondena());
            pstmt.setString(2,condena.getNome());
            if (condena.getDescricao().equals("") || condena.getDescricao().toLowerCase().equals("sem descricao")) {
                pstmt.setNull(3,java.sql.Types.VARCHAR);
            } else {
                pstmt.setString(3, condena.getDescricao());
            }
            pstmt.setInt(4,condena.getId());
            if (pstmt.executeUpdate() > 0) {
                return true;
            }
            return false;
        } catch (SQLException sql) {
            return false;
        } finally {
            conexao.desconectar(conn);
        }
    } // Método de alterar um registro de condena por seu id no banco de dados

    public boolean adicionarCondena(Condenas condena) {
        conn = conexao.conectar();
        try {
            PreparedStatement pstmt1 = conn.prepareStatement("select id from admin where nome = ?");
            pstmt1.setString(1,condena.getNomeAdmin());
            ResultSet rs = pstmt1.executeQuery();
            int id = 0;
            while (rs.next()) {
                id = rs.getInt(1);
            }
            PreparedStatement pstmt2 = conn.prepareStatement("insert into condenas(nome,cod_admin,descricao,tipo_condena) values(?,?,?,?)");
            pstmt2.setString(1,condena.getNome());
            pstmt2.setInt(2,id);
            if (condena.getDescricao().equals("") || condena.getDescricao().toLowerCase().equals("sem descricao")) {
                pstmt2.setNull(3, java.sql.Types.VARCHAR);
            } else {
                pstmt2.setString(3, condena.getDescricao());
            }
            pstmt2.setString(4,condena.getTipoCondena());
            if (pstmt2.executeUpdate() > 0) {
                return true;
            }
            return false;
        } catch (SQLException sql) {
            sql.printStackTrace();
            return false;
        } finally {
            conexao.desconectar(conn);
        }
    } // Método de adicionar uma condena no banco de dados
}
