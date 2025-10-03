package com.backigesta.dao;

import com.backigesta.conexao.Conexao;
import com.backigesta.model.Admin;
import com.backigesta.model.Empresas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpresasDAO {
    private final Conexao banco = new Conexao();
    //=======================MÉTODOS CREATE=======================\\
    public boolean inserir(Empresas empresa){
        boolean retorno = false;
        Connection conn = banco.conexao();
        try{
            String sql = "INSERT INTO empresas(cnpj, nome, email, senha, id_planos, foto) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, empresa.getCnpj());
            ps.setString(2, empresa.getNome());
            ps.setString(3, empresa.getEmail());
            ps.setString(4, empresa.getSenha());
            ps.setInt(5, empresa.getId_planos());
            ps.setBytes(6, empresa.getFoto());

            retorno = ps.executeUpdate()==1;
            conn.close();
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar EmpresasDAO.inserir(Empresa)");
            sqle.printStackTrace();
        }
        finally{
            return retorno;
        }
    }

    //=======================MÉTODOS READ=======================\\
    public Empresas selecionarPorId(int id){
        Connection conn = banco.conexao();
        Empresas empresa = null;
        try{
            String sql = "SELECT * FROM empresas WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                empresa = new Empresas(
                        rs.getInt("id"),
                        rs.getString("cnpj"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getInt("id_planos"),
                        rs.getBytes("foto")
                );
            }
            conn.close();
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar EmpresasDAO.selecionarPorId(int)!!");
            sqle.printStackTrace();
        }
        finally {
            return empresa;
        }
    }

    public List<Empresas> selecionarTodos(){
        Connection conn = banco.conexao();
        List<Empresas> empresa = new ArrayList<>();
        try{
            String sql = "SELECT * FROM empresas";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()) {
                empresa.add(new Empresas(
                        rs.getInt("id"),
                        rs.getString("cnpj"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getInt("id_planos"),
                        rs.getBytes("foto")
                ));
            }
            conn.close();
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar EmpresasDAO.selecionarTodos()!!");
            sqle.printStackTrace();
        }
        finally {
            return empresa;
        }
    }

    public byte[] selecionarFotoPorId(int id){
        Connection conn = banco.conexao();
        byte[] foto = null;
        try{
            String sql = "SELECT foto FROM empresas WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                foto = rs.getBytes(1);
            }
            conn.close();
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar EmpresasDAO.selecionarFotoPorId(int)!!");
            sqle.printStackTrace();
        }
        finally {
            return foto;
        }
    }

    //=======================MÉTODOS UPDATE=======================\\
    public boolean atualizar(Empresas empresas){
        boolean retorno = false;
        Connection conn = banco.conexao();
        try{
            String sql = "UPDATE empresas SET cnpj=?, nome=?, email=?, senha=?, id_planos=?,foto=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, empresas.getCnpj());
            ps.setString(2, empresas.getNome());
            ps.setString(3, empresas.getEmail());
            ps.setString(4, empresas.getSenha());
            ps.setInt(5, empresas.getId_planos());
            ps.setBytes(6, empresas.getFoto());
            ps.setInt(7, empresas.getId());

            retorno = ps.executeUpdate()>=1;
            conn.close();
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar EmpresasDAO.atualizar(Empresas)!!");
            sqle.printStackTrace();
        }
        finally {
            return retorno;
        }
    }

    public boolean atualizarFoto(int id, byte[] foto){
        boolean retorno = false;
        Connection conn = banco.conexao();
        try{
            String sql = "UPDATE empresas SET foto=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBytes(1, foto);
            ps.setInt(2, id);

            retorno = ps.executeUpdate()>=1;
            conn.close();
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar EmpresasDAO.atualizarFoto(int, byte[])!!");
            sqle.printStackTrace();
        }
        finally {
            return retorno;
        }
    }

    //=======================MÉTODOS DELETE=======================\\
    public boolean deletar(int id){
        boolean retorno = false;
        Connection conn = banco.conexao();
        try{
            String sql = "DELETE FROM empresas WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            retorno = ps.executeUpdate()>=1;
            conn.close();
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar EmpresasDAO.deletar(int)!!");
            sqle.printStackTrace();
        }
        finally {
            return retorno;
        }
    }
}
