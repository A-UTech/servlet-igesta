package com.backigesta.dao;

import com.backigesta.conexao.Conexao;
import com.backigesta.model.Plano;

import java.sql.*;
import java.util.ArrayList;

public class PlanoDao {
    private Connection conn;
    private Conexao banco = new Conexao();

//=======================MÉTODOS CREATE=======================\\

    public boolean inserir(Plano plano) {
        boolean retorno = false;
        try {
            conn = banco.conectar();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO plano(nome,mensalidade,armazenamento) VALUES(?,?,?)");
            ps.setString(1,plano.getNome());
            ps.setDouble(2,plano.getMensalidade());
            ps.setInt(3,plano.getArmazenamento());
            retorno = ps.executeUpdate() == 1;
        } catch (SQLException sql) {
            System.out.println("!!SQLException ao chamar PlanoDAO.inserir(plano)!!");
            sql.printStackTrace();
        } finally {
            banco.desconectar(conn);
            return retorno;
        }
    } // Método para adicionar um plano

//=======================MÉTODOS READ=======================\\

    public ArrayList<Plano> selecionarTodos() {
        ArrayList<Plano> listas = new ArrayList<>();
        try {
            conn = banco.conectar();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM plano ORDER BY nome,mensalidade");
            while (rs.next()) {
                listas.add(new Plano(rs.getInt(1),rs.getString(2), rs.getDouble(3), rs.getInt(4)));
            }
        } catch (SQLException sql) {
            System.out.println("!!SQLException ao chamar FuncionariosDAO.selecionarTodos()!!");
            sql.printStackTrace();
        } finally {
            banco.desconectar(conn);
            return listas;
        }
    } // Método que seleciona todos planos

    public ArrayList<Plano> selecionarPorNome(String procura) {
        ArrayList<Plano> listas = new ArrayList<>();
        try {
            conn = banco.conectar();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM plano WHERE lower(nome) LIKE lower(?) ORDER BY nome,mensalidade");
            pstmt.setString(1,procura+"%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                listas.add(new Plano(rs.getInt(1),rs.getString(2), rs.getDouble(3), rs.getInt(4)));
            }
        } catch (SQLException sql) {
            System.out.println("!!SQLException ao chamar PlanoDAO.selecionarPorNome(procura)!!");
            sql.printStackTrace();
        } finally {
            banco.desconectar(conn);
            return listas;
        }
    } // Método que selecionar planos pelo seu nome

    public ArrayList<String> selecionarNomes() {
        ArrayList<String> listas = new ArrayList<>();
        try {
            conn = banco.conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT nome FROM plano");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listas.add(rs.getString(1));
            }
        } catch (SQLException sql) {
            System.out.println("!!SQLException ao chamar FuncionariosDAO.selecionarNomes()!!");
            sql.printStackTrace();
        } finally {
            banco.desconectar(conn);
            return listas;
        }
    } // Método para selecionar todos os nomes de planos que existem

//=======================MÉTODOS UPDATE=======================\\

    public boolean atualizar(Plano plano) {
        boolean retorno = false;
        try {
            conn = banco.conectar();
            PreparedStatement ps = conn.prepareStatement("UPDATE plano SET nome = ?, mensalidade = ?, armazenamento = ? WHERE id = ?");
            ps.setString(1,plano.getNome());
            ps.setDouble(2,plano.getMensalidade());
            ps.setInt(3,plano.getArmazenamento());
            ps.setInt(4,plano.getId());
            retorno = ps.executeUpdate() == 1;
        } catch (SQLException sql) {
            System.out.println("!!SQLException ao chamar PlanoDAO.atualizar(plano)!!");
            sql.printStackTrace();
        } finally {
            banco.desconectar(conn);
            return retorno;
        }
    } // Método atualiaza dados dos planos

//=======================MÉTODOS DELETE=======================\\

    public boolean deletar(int id) {
        boolean retorno = false;
        try {
            conn = banco.conectar();
            conn.setAutoCommit(false);

            PreparedStatement ps = conn.prepareStatement("SELECT t.id FROM plano p JOIN empresa e ON e.id_plano = p.id JOIN funcionario f ON f.id_empresa = e.id JOIN telefone t ON f.id = t.id_funcionario WHERE p.id = ?");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ps = conn.prepareStatement("DELETE FROM telefone WHERE id = ?");
                ps.setInt(1,rs.getInt(1));
                ps.execute();
            }
            ps.close();
            rs.close();


            ps = conn.prepareStatement("SELECT qc.id FROM plano p JOIN empresa e ON e.id_plano = p.id JOIN funcionario f ON f.id_empresa = e.id JOIN medicao m ON f.id = m.cod_gestor JOIN quantidadecondena qc ON m.id = qc.cod_medicao WHERE p.id = ?");
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while (rs.next()) {
                ps = conn.prepareStatement("DELETE FROM quantidadecondena WHERE id = ?");
                ps.setInt(1,rs.getInt(1));
                ps.execute();
            }
            ps.close();
            rs.close();


            ps = conn.prepareStatement("SELECT m.id FROM plano p JOIN empresa e ON e.id_plano = p.id JOIN funcionario f ON f.id_empresa = e.id JOIN medicao m ON f.id = m.cod_gestor WHERE p.id = ?");
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while (rs.next()) {
                ps = conn.prepareStatement("DELETE FROM medicao WHERE id = ?");
                ps.setInt(1,rs.getInt(1));
                ps.execute();
            }
            ps.close();


            ps = conn.prepareStatement("SELECT f.id FROM plano p JOIN empresa e ON e.id_plano = p.id JOIN funcionario f ON f.id_empresa = e.id WHERE p.id = ?");
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while (rs.next()) {
                ps = conn.prepareStatement("DELETE FROM funcionario WHERE id = ?");
                ps.setInt(1,rs.getInt(1));
                ps.execute();
            }
            ps.close();


            ps = conn.prepareStatement("DELETE FROM empresa WHERE id_plano = ?");
            ps.setInt(1,id);
            ps.execute();
            ps.close();


            ps = conn.prepareStatement("DELETE FROM plano WHERE id = ?");
            ps.setInt(1,id);
            retorno = ps.executeUpdate() == 1;
            ps.close();

            conn.commit();
        } catch (SQLException sql) {
            try {
                conn.rollback();
            } catch (SQLException sql1) {
                sql1.printStackTrace();
            }
            System.out.println("!!SQLException ao chamar PlanoDAO.deletar(id)!!");
            sql.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            banco.desconectar(conn);
            return retorno;
        }
    } // Método que deleta um registro de plano por id
}
