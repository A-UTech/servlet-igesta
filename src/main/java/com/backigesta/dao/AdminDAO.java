package com.backigesta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.backigesta.conexao.Conexao;
import com.backigesta.model.Admin;

public class AdminDAO {
    private Conexao banco = new Conexao();
    private Connection conn;

//=======================MÉTODOS CREATE=======================\\
    public boolean inserir(Admin adm){
        boolean retorno = false;
        try{
            conn = banco.conectar();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO admin(nome, email, senha, foto) VALUES(?, ?, ?, ?)");

            ps.setString(1, adm.getNome());
            ps.setString(2, adm.getEmail());
            ps.setString(3, adm.getSenha());
            ps.setBytes(4, adm.getFoto());

            retorno = ps.executeUpdate() == 1;
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar AdminDAO.inserir(Admin)!!");
            sqle.printStackTrace();
        }
        finally{
            banco.desconectar(conn);
            return retorno;
        }
    } // Método que inseri um admin

//=======================MÉTODOS READ=======================\\

    public boolean verificaLoginAdmin(String email, String senha) {
        boolean retorno = false;
        try {
            conn = banco.conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM admin WHERE email LIKE ? AND senha LIKE ?");
            ps.setString(1,email);
            ps.setString(2,senha);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                retorno = true;
            }
        } catch (Exception e) {
            System.out.println("!!SQLException ao chamar AdminDAO.verificaLoginAdmin(email,senha)!!");
            e.printStackTrace();
        } finally {
            banco.desconectar(conn);
            return retorno;
        }
    } // Método que verifica se existe aquela conta de admin

    public List<Admin> selecionarTodos(){
        List<Admin> admins = new ArrayList<>();
        try{
            conn = banco.conectar();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM admin ORDER BY nome");

            while(rs.next()){
                admins.add(new Admin(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getBytes("foto")
                ));
            }
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar AdminDAO.selecionarTodos()!!");
            sqle.printStackTrace();
        }
        finally {
            banco.desconectar(conn);
            return admins;
        }
    } // Método que seleciona todos os admins

    public List<Admin> selecionarPorNome(String nome){
        List<Admin> admins = new ArrayList<>();
        try{
            conn = banco.conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM admin WHERE lower(nome) LIKE lower(?) ORDER BY nome");
            ps.setString(1, nome+"%");

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                admins.add(new Admin(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getBytes("foto")
                ));
            }
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar AdminDAO.selecionarPorNome(nome)");
            sqle.printStackTrace();
        }
        finally {
            banco.desconectar(conn);
            return admins;
        }
    } // Método que seleciona admins por nome

    public Admin selecionarPorEmail(String email){
        Admin admin = null;
        try{
            conn = banco.conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM admin WHERE email LIKE ? ORDER BY nome");
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                admin = new Admin(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getBytes("foto")
                );
            }
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar AdminDAO.selecionarPorEmail(email)");
            sqle.printStackTrace();
        }
        finally {
            banco.desconectar(conn);
            return admin;
        }
    } // Método que seleciona admins por email

    public byte[] selecionarFotoPorId(int id){
        byte[] foto = null;
        try{
            conn = banco.conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT foto FROM admin WHERE id = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                foto = rs.getBytes(1);
            }
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar AdminDAO.selecionarFotoPorId(id)!!");
            sqle.printStackTrace();
        }
        finally {
            banco.desconectar(conn);
            return foto;
        }
    } // Método que seleciona a foto de um admin por id

//=======================MÉTODOS UPDATE=======================\\

    public boolean atualizar(Admin admin){
        boolean retorno = false;
        try{
            conn = banco.conectar();
            PreparedStatement ps = conn.prepareStatement("UPDATE admin SET nome = ?, email = ?, senha = ? WHERE id = ?");
            ps.setString(1, admin.getNome());
            ps.setString(2, admin.getEmail());
            ps.setString(3, admin.getSenha());
            ps.setInt(4, admin.getId());

            retorno = ps.executeUpdate() >= 1;
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar AdminDAO.atualizar(admin)!!");
            sqle.printStackTrace();
        }
        finally {
            banco.desconectar(conn);
            return retorno;
        }
    } // Método que atualiza dados de um admin por id

    public boolean atualizarFoto(int id, byte[] foto){
        boolean retorno = false;
        try{
            conn = banco.conectar();
            String sql = "UPDATE admin SET foto = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBytes(1, foto);
            ps.setInt(2, id);

            retorno = ps.executeUpdate() >= 1;
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar AdminDAO.atualizarFoto(id, foto)!!");
            sqle.printStackTrace();
        }
        finally {
            banco.desconectar(conn);
            return retorno;
        }
    } // Método que atualiza a foto de um admin por id

//=======================MÉTODOS DELETE=======================\\
    public boolean deletar(int id){
        boolean retorno = false;
        try{
            conn = banco.conectar();
            conn.setAutoCommit(false);

            PreparedStatement ps = conn.prepareStatement("SELECT qc.id FROM admin a JOIN condena c ON c.cod_admin = a.id JOIN quantidadecondena qc ON qc.cod_condena = c.id where a.id = ?");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ps = conn.prepareStatement("DELETE FROM quantidadecondena where id = ?");
                ps.setInt(1,rs.getInt(1));
                ps.execute();
            }
            rs.close();
            ps.close();

            ps = conn.prepareStatement("SELECT c.id FROM admin a JOIN condena c ON c.cod_admin = a.id WHERE a.id = ?");
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while (rs.next()) {
                ps = conn.prepareStatement("DELETE FROM condena WHERE id = ?");
                ps.setInt(1,rs.getInt(1));
                ps.execute();
            }
            rs.close();
            ps.close();

            ps = conn.prepareStatement("DELETE FROM admin WHERE id=?");
            ps.setInt(1, id);

            retorno = ps.executeUpdate() >= 1;

            conn.commit();
        }
        catch(SQLException sqle){
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            System.out.println("!!SQLException ao chamar AdminDAO.deletar(id)!!");
            sqle.printStackTrace();
        }
        finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            banco.desconectar(conn);
            return retorno;
        }
    } // Método que deleta um admin por id
}
