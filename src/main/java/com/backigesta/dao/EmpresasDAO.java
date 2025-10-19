package com.backigesta.dao;

import com.backigesta.conexao.Conexao;
import com.backigesta.model.Admin;
import com.backigesta.model.Empresas;
import com.backigesta.model.Usuarios;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EmpresasDAO extends DAO{
    private final Conexao banco = new Conexao();
    //=======================MÉTODOS CREATE=======================\\
    public boolean inserir(Empresas empresas){
        boolean retorno = false;
        Connection conn = banco.conectar();
        try{
            String sql = "INSERT INTO empresas(cnpj, nome, email, senha, id_planos, foto, regiao, unidade) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, empresas.getCnpj());
            ps.setString(2, empresas.getNome());
            ps.setString(3, empresas.getEmail());
            ps.setString(4, empresas.getSenha());
            ps.setInt(5, empresas.getId_planos());
            ps.setBytes(6, empresas.getFoto());
            ps.setString(7, empresas.getRegiao());
            ps.setString(8, empresas.getUnidade());

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
        Connection conn = banco.conectar();
        Empresas empresas = null;
        try{
            String sql = "SELECT * FROM empresas WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                empresas = new Empresas(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("cnpj"),
                        rs.getString("senha"),
                        rs.getInt("id_planos"),
                        rs.getString("regiao"),
                        rs.getString("unidade"),
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
            return empresas;
        }
    }

    public HashMap<Empresas, String> selecionarPorRegiao(String regiao){
        Connection conn = banco.conectar();
        HashMap<Empresas, String> empresa = new HashMap<>();
        try{
            String sql = "SELECT e.*, p.nome AS plano FROM empresas e JOIN planos p ON p.id=e.id_planos where e.regiao like ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, regiao);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                empresa.put(new Empresas(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("cnpj"),
                        rs.getString("senha"),
                        rs.getInt("id_planos"),
                        rs.getString("regiao"),
                        rs.getString("unidade"),
                        rs.getBytes("foto")
                ), rs.getString("plano"));
            }
            conn.close();
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar EmpresasDAO.selecionarPorRegiao(String)!!");
            sqle.printStackTrace();
        }
        finally {
            return empresa;
        }
    }

    public HashMap<Empresas, String> selecionarPorNome(String nome){
        Connection conn = banco.conectar();
        HashMap<Empresas, String> empresa = new HashMap<>();
        try{
            String sql = "SELECT e.*, p.nome AS plano FROM empresas e JOIN planos p ON p.id=e.id_planos where e.nome ilike ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                empresa.put(new Empresas(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("cnpj"),
                        rs.getString("senha"),
                        rs.getInt("id_planos"),
                        rs.getString("regiao"),
                        rs.getString("unidade"),
                        rs.getBytes("foto")
                ), rs.getString("plano"));
            }
            conn.close();
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar EmpresasDAO.selecionarPorNome(String)!!");
            sqle.printStackTrace();
        }
        finally {
            return empresa;
        }
    }

    public HashMap<Empresas, String> selecionarTodos(){
        Connection conn = banco.conectar();
        HashMap<Empresas, String> empresa = new HashMap<>();
        try{
            String sql = "SELECT e.*, p.nome AS plano FROM empresas e JOIN planos p ON p.id=e.id_planos";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()) {
                empresa.put(new Empresas(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("cnpj"),
                        rs.getString("senha"),
                        rs.getInt("id_planos"),
                        rs.getString("regiao"),
                        rs.getString("unidade"),
                        rs.getBytes("foto")
                ), rs.getString("plano"));
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

    public Empresas selecionarPorEmail(String email){
        Connection conn = null;
        Empresas empresas = null;
        try{
            conn = banco.conectar();
            String sql = "SELECT * FROM admin WHERE email like ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                empresas = new Empresas(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("cnpj"),
                        rs.getString("senha"),
                        rs.getInt("id_planos"),
                        rs.getString("regiao"),
                        rs.getString("unidade"),
                        rs.getBytes("foto")
                );
            }
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar AdminDAO.selecionarPorNome(String)");
            sqle.printStackTrace();
        }
        finally {
            banco.desconectar(conn);
            return empresas;
        }
    }

    public byte[] selecionarFotoPorId(int id){
        Connection conn = banco.conectar();
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

    public boolean verificaLoginEmpresa(String email, String senha) {
        Connection conn = null;
        try {
            conn = banco.conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM empresas WHERE email = ? AND senha = ?");
            ps.setString(1,email);
            ps.setString(2,senha);

            ResultSet rset = ps.executeQuery();

            if (rset.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("!!SQLException ao chamar EmpresasDAO.verificaLoginEmp(String email, String senha)!!");
            e.printStackTrace();
        } finally {
            banco.desconectar(conn);
        }
        return false;
    }

    //=======================MÉTODOS UPDATE=======================\\
    public boolean atualizar(Usuarios user){
        Empresas empresas = (Empresas) user;
        boolean retorno = false;
        Connection conn = banco.conectar();
        try{
            String sql = "UPDATE empresas SET cnpj=?, nome=?, email=?, senha=?, id_planos=?, regiao=?, unidade=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, empresas.getCnpj());
            ps.setString(2, empresas.getNome());
            ps.setString(3, empresas.getEmail());
            ps.setString(4, empresas.getSenha());
            ps.setInt(5, empresas.getId_planos());
            ps.setString(6, empresas.getRegiao());
            ps.setString(7, empresas.getUnidade());
            ps.setInt(8, empresas.getId());

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
        Connection conn = banco.conectar();
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
        Connection conn = banco.conectar();
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

    //=======================MÉTODOS DELETE=======================\\
}
