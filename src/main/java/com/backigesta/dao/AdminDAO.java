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

    //=======================MÉTODOS CREATE=======================\\
    public boolean inserir(Admin adm){
        boolean retorno = false;
        Connection conn = banco.conectar();
        try{
            String sql = "INSERT INTO admin(nome, sobrenome, email, senha, foto) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, adm.getNome());
            ps.setString(2, adm.getSobrenome());
            ps.setString(3, adm.getEmail());
            ps.setString(4, adm.getSenha());
            ps.setBytes(5, adm.getFoto());

            retorno = ps.executeUpdate()==1;
            conn.close();
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar AdminDAO.inserir(Admin)!!");
            sqle.printStackTrace();
        }
        finally{
            return retorno;
        }
    }

    //=======================MÉTODOS READ=======================\\
    public Admin selecionarPorId(int id){
        Connection conn = banco.conectar();
        Admin adm = null;
        try{
            String sql = "SELECT * FROM admin WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                adm = new Admin(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("sobrenome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getBytes("foto")
                );
            }
            conn.close();
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar AdminDAO.selecionarPorId(int)!!");
            sqle.printStackTrace();
        }
        finally {
            return adm;
        }
    }

    public List<Admin> selecionarTodos(){
        Connection conn = banco.conectar();
        List<Admin> admins = new ArrayList<>();
        try{
            String sql = "SELECT * FROM admin";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                admins.add(new Admin(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("sobrenome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getBytes("foto")
                ));
            }

            conn.close();
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar AdminDAO.selecionarTodos()!!");
            sqle.printStackTrace();
        }
        finally {
            return admins;
        }
    }

    public List<Admin> selecionarPorNome(String nome){
        Connection conn = banco.conectar();
        List<Admin> admins = new ArrayList<>();
        try{
            String sql = "SELECT * FROM admin WHERE nome like ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                admins.add(new Admin(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("sobrenome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getBytes("foto")
                ));
            }

            conn.close();
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar AdminDAO.selecionarPorNome(String)");
            sqle.printStackTrace();
        }
        finally {
            return admins;
        }
    }

    public byte[] selecionarFotoPorId(int id){
        Connection conn = banco.conectar();
        byte[] foto = null;
        try{
            String sql = "SELECT foto FROM admin WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                foto = rs.getBytes(1);
            }
            conn.close();
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar AdminDAO.selecionarFotoPorId(int)!!");
            sqle.printStackTrace();
        }
        finally {
            return foto;
        }
    }

    //=======================MÉTODOS UPDATE=======================\\

    public boolean atualizar(Admin adm){
        boolean retorno = false;
        Connection conn = banco.conectar();
        try{
            String sql = "UPDATE admin SET nome=?, sobrenome=?, email=?, senha=?, foto=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, adm.getNome());
            ps.setString(2, adm.getSobrenome());
            ps.setString(3, adm.getEmail());
            ps.setString(4, adm.getSenha());
            ps.setBytes(5, adm.getFoto());
            ps.setInt(6, adm.getId());

            retorno = ps.executeUpdate()>=1;
            conn.close();
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar AdminDAO.atualizar(Admin)!!");
            sqle.printStackTrace();
        }
        finally {
            return retorno;
        }
    }

    public boolean atualizarFoto(int id, byte[] foto){
        boolean retorno = false;
        Connection conn = banco.conectar();
        try{
            String sql = "UPDATE admin SET foto=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBytes(1, foto);
            ps.setInt(2, id);

            retorno = ps.executeUpdate()>=1;
            conn.close();
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar AdminDAO.atualizarFoto(int, byte[])!!");
            sqle.printStackTrace();
        }
        finally {
            return retorno;
        }
    }

    public boolean verificaLoginAdm(String email, String senha) {
        boolean valido = false;
        try {
            Connection conn = banco.conectar();
            String sql = "SELECT * FROM admins WHERE email = ? AND senha = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rset = ps.executeQuery();

            if (rset.next()) {
                valido = true;
            }
        } catch (Exception e) {
            System.out.println("!!SQLException ao chamar AdminDAO.verificaLoginAdm(String email, String senha)!!");
            e.printStackTrace();
        }
        return valido;
    }

    //=======================MÉTODOS DELETE=======================\\
    public boolean deletar(int id){
        boolean retorno = false;
        Connection conn = banco.conectar();
        try{
            String sql = "DELETE FROM admin WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            retorno = ps.executeUpdate()>=1;
            conn.close();
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar AdminDAO.deletar(int)!!");
            sqle.printStackTrace();
        }
        finally {
            return retorno;
        }
    }
}
