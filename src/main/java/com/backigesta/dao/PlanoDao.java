package com.backigesta.dao;

import com.backigesta.conexao.Conexao;
import com.backigesta.model.Condenas;
import com.backigesta.model.Planos;

import java.sql.*;
import java.util.ArrayList;

public class PlanoDao {

    //==========ATRIBUTOS==========\\
    private Connection conn;
    private Conexao conexao = new Conexao();

    //==========MÉTODOS DA CLASSE==========\\
    public ArrayList<Planos> buscarPlanos() {
        conn = conexao.conectar();
        ArrayList<Planos> listas = new ArrayList<>();
        try {
            Statement pstmt = conn.createStatement();
            ResultSet rs = pstmt.executeQuery("select * from planos order by nome,mensalidade");
            while (rs.next()) {
                listas.add(new Planos(rs.getInt(1),rs.getString(2), rs.getDouble(3), rs.getInt(4)));
            }
        } catch (SQLException sql) {
            return null;
        } finally {
            conexao.desconectar(conn);
        }
        return listas;
    } // Método que busca todos planos do banco de dados

    public ArrayList<Planos> buscarPlanosNome(String procura) {
        conn = conexao.conectar();
        ArrayList<Planos> listas = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement("select * from planos where lower(nome) like ? order by nome,mensalidade");
            pstmt.setString(1,procura+"%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                listas.add(new Planos(rs.getInt(1),rs.getString(2), rs.getDouble(3), rs.getInt(4)));
            }
        } catch (SQLException sql) {
            return null;
        } finally {
            conexao.desconectar(conn);
        }
        return listas;
    } // Método que busca as condenas por seu nome no banco de dados

    public boolean deletarPlano(int id) {
        conn = conexao.conectar();
        ArrayList<Integer> listaId = new ArrayList<>();
        try {
            conn.setAutoCommit(false);


            PreparedStatement pstmt = conn.prepareStatement("select t.id from planos p join empresas e on e.id_planos = p.id join funcionarios f on f.id_empresa = e.id join telefones t on f.id = t.id_funcionario where p.id = ?");
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                listaId.add(rs.getInt(1));
            }
            pstmt.close();
            for (int i = 0; i < listaId.size(); i++) {
                pstmt = conn.prepareStatement("delete from telefones where id = ?");
                pstmt.setInt(1,listaId.get(i));
                pstmt.execute();
                pstmt.close();
            }


            pstmt = conn.prepareStatement("select qc.id from planos p join empresas e on e.id_planos = p.id join funcionarios f on f.id_empresa = e.id join medicoes m on f.id = m.cod_gestor join quantidadecondenas qc on m.id = qc.cod_medicao where p.id = ?");
            pstmt.setInt(1,id);
            rs = pstmt.executeQuery();
            listaId = new ArrayList<>();
            while (rs.next()) {
                listaId.add(rs.getInt(1));
            }
            pstmt.close();
            for (int i = 0;i < listaId.size();i++) {
                pstmt = conn.prepareStatement("delete from quantidadecondenas where id = ?");
                pstmt.setInt(1,listaId.get(i));
                pstmt.execute();
                pstmt.close();
            }


            pstmt = conn.prepareStatement("select m.id from planos p join empresas e on e.id_planos = p.id join funcionarios f on f.id_empresa = e.id join medicoes m on f.id = m.cod_gestor where p.id = ?");
            pstmt.setInt(1,id);
            rs = pstmt.executeQuery();
            listaId = new ArrayList<>();
            while (rs.next()) {
                listaId.add(rs.getInt(1));
            }
            pstmt.close();
            for (int i = 0;i < listaId.size();i++) {
                pstmt = conn.prepareStatement("delete from medicoes where id = ?");
                pstmt.setInt(1,listaId.get(i));
                pstmt.execute();
                pstmt.close();
            }


            pstmt = conn.prepareStatement("select f.id from planos p join empresas e on e.id_planos = p.id join funcionarios f on f.id_empresa = e.id where p.id = ?");
            pstmt.setInt(1,id);
            rs = pstmt.executeQuery();
            listaId = new ArrayList<>();
            while (rs.next()) {
                listaId.add(rs.getInt(1));
            }
            pstmt.close();
            for (int i = 0;i < listaId.size();i++) {
                pstmt = conn.prepareStatement("delete from funcionarios where id = ?");
                pstmt.setInt(1,listaId.get(i));
                pstmt.execute();
                pstmt.close();
            }

            pstmt = conn.prepareStatement("delete from empresas where id_planos = ?");
            pstmt.setInt(1,id);
            pstmt.execute();
            pstmt.close();


            pstmt = conn.prepareStatement("delete from planos where id = ?");
            pstmt.setInt(1,id);
            if (pstmt.executeUpdate() > 0) {
                pstmt.close();
                conn.commit();
                return true;
            }
            pstmt.close();
            return false;
        } catch (SQLException sql) {
            try {
                conn.rollback();
            } catch (SQLException sql1) {
                sql1.printStackTrace();
            }
            sql.printStackTrace();
            return false;
        } finally {
            try {
                conn.setAutoCommit(true);
                conexao.desconectar(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    } // Método que deleta um registro de plano por seu id no banco de dados

    public boolean alterarPlano(Planos plano) {
        conn = conexao.conectar();
        System.out.println(plano);
        try {
            PreparedStatement pstmt = conn.prepareStatement("update planos set nome = ?, mensalidade = ?, armazenamento = ? where id = ?");
            pstmt.setString(1,plano.getNome());
            pstmt.setDouble(2,plano.getMensalidade());
            pstmt.setInt(3,plano.getArmazenamento());
            pstmt.setInt(4,plano.getId());
            if (pstmt.executeUpdate() > 0) {
                return true;
            }
            return false;
        } catch (SQLException sql) {
            return false;
        } finally {
            conexao.desconectar(conn);
        }
    } // Método de alterar um registro de plano por seu id no banco de dados

    public boolean adicionarPlano(Planos planos) {
        conn = conexao.conectar();
        try {
            PreparedStatement pstmt = conn.prepareStatement("insert into planos(nome,mensalidade,armazenamento) values(?,?,?)");
            pstmt.setString(1,planos.getNome());
            pstmt.setDouble(2,planos.getMensalidade());
            pstmt.setInt(3,planos.getArmazenamento());
            if (pstmt.executeUpdate() > 0) {
                return true;
            }
            return false;
        } catch (SQLException sql) {
            sql.printStackTrace();
            return false;
        } finally {
            conexao.desconectar(conn);
        }
    } // Método de adicionar um plano no banco de dados
}
