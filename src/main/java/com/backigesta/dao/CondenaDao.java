package com.backigesta.dao;

import com.backigesta.conexao.Conexao;
import com.backigesta.model.Condena;

import java.sql.*;
import java.util.ArrayList;

public class CondenaDao {
    private Connection conn;
    private Conexao banco = new Conexao();

//=======================MÉTODOS CREATE=======================\\

    public boolean inserir(Condena condena) {
        boolean retorno = false;
        try {
            conn = banco.conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT id FROM admin WHERE lower(nome) = ?");
            ps.setString(1,condena.getNomeAdmin().toLowerCase());
            ResultSet rs = ps.executeQuery();
            int id = 0;
            while (rs.next()) {
                id = rs.getInt(1);
            }
            ps.close();

            ps = conn.prepareStatement("INSERT INTO condena(nome,cod_admin,descricao,tipo_condena) VALUES(?,?,?,?)");
            ps.setString(1,condena.getNome());
            ps.setInt(2,id);
            if (condena.getDescricao().equals("") || condena.getDescricao().toLowerCase().equals("sem descricao")) {
                ps.setNull(3, java.sql.Types.VARCHAR);
            } else {
                ps.setString(3, condena.getDescricao());
            }
            ps.setString(4,condena.getTipoCondena());

            retorno = ps.executeUpdate() == 0;
        } catch (SQLException sql) {
            System.out.println("!!SQLException ao chamar CondenaDAO.inserir(condena)!!");
            sql.printStackTrace();
        } finally {
            banco.desconectar(conn);
            return retorno;
        }
    } // Método que inseri uma condena

//=======================MÉTODOS READ=======================\\

    public ArrayList<Condena> selecionarTodos() {
        ArrayList<Condena> listas = new ArrayList<>();
        try {
            conn = banco.conectar();
            Statement ps = conn.createStatement();
            ResultSet rs = ps.executeQuery("SELECT c.id,c.nome,a.nome,c.descricao,c.tipo_condena FROM condena c JOIN admin a on a.id = c.cod_admin ORDER BY c.nome");
            while (rs.next()) {
                listas.add(new Condena(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException sql) {
            System.out.println("!!SQLException ao chamar CondenaDAO.selecionarTodos()!!");
            sql.printStackTrace();
        } finally {
            banco.desconectar(conn);
            return listas;
        }
    } // Método que seleciona todas as condenas

    public ArrayList<Condena> selecionarPorTipo(String tipoCondena) {
        ArrayList<Condena> listas = new ArrayList<>();
        try {
            conn = banco.conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT c.id,c.nome,a.nome,c.descricao,c.tipo_condena FROM condena c JOIN admin a on a.id = c.cod_admin WHERE lower(c.tipo_condena) = ? ORDER BY c.nome");
            ps.setString(1,tipoCondena);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listas.add(new Condena(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException sql) {
            System.out.println("!!SQLException ao chamar CondenaDAO.selecionarPorTipo(tipoCondena)!!");
            sql.printStackTrace();
        } finally {
            banco.desconectar(conn);
            return listas;
        }
    } // Método que seleciona condenas por tipoCondena

    public ArrayList<Condena> selecionarPorNome(String procura) {
        ArrayList<Condena> listas = new ArrayList<>();
        try {
            conn = banco.conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT c.id,c.nome,a.nome,c.descricao,c.tipo_condena FROM condena c JOIN admin a on a.id = c.cod_admin WHERE lower(c.nome) LIKE lower(?) ORDER BY c.nome");
            ps.setString(1,procura+"%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listas.add(new Condena(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException sql) {
            System.out.println("!!SQLException ao chamar CondenaDAO.selecionarPorNome(procura)!!");
            sql.printStackTrace();
        } finally {
            banco.desconectar(conn);
            return listas;
        }
    } // Método que seleciona condenas por nome

//=======================MÉTODOS UPDATE=======================\\

    public boolean atualizar(Condena condena) {
        boolean retorno = false;
        try {
            conn = banco.conectar();
            PreparedStatement ps = conn.prepareStatement("UPDATE condena SET tipo_condena = ?, nome = ?, descricao = ? WHERE id = ?");
            ps.setString(1,condena.getTipoCondena());
            ps.setString(2,condena.getNome());
            if (condena.getDescricao().equals("") || condena.getDescricao().toLowerCase().equals("sem descricao")) {
                ps.setNull(3,java.sql.Types.VARCHAR);
            } else {
                ps.setString(3, condena.getDescricao());
            }
            ps.setInt(4,condena.getId());

            retorno = ps.executeUpdate() == 0;
        } catch (SQLException sqle) {
            System.out.println("!!SQLException ao chamar CondenaDAO.atualizar(condena)!!");
            sqle.printStackTrace();
        } finally {
            banco.desconectar(conn);
            return retorno;
        }
    } // Método de atualiza dados de uma condena por id

//=======================MÉTODOS DELETE=======================\\

    public boolean deletar(int id) {
        boolean retorno = false;
        try {
            conn = banco.conectar();
            conn.setAutoCommit(false);

            PreparedStatement ps = conn.prepareStatement("DELETE FROM quantidadecondena WHERE cod_condena = ?");
            ps.setInt(1,id);
            ps.execute();
            ps.close();

            ps = conn.prepareStatement("DELETE FROM condena WHERE id = ?");
            ps.setInt(1,id);

            retorno = ps.executeUpdate() == 1;

            conn.commit();
        } catch (SQLException sqle) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            System.out.println("!!SQLException ao chamar CondenaDAO.deletar(id)!!");
            sqle.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            banco.desconectar(conn);
            return retorno;
        }
    } // Método que deleta um registro de condena por id
}
