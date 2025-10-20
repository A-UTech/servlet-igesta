package com.backigesta.dao;

import com.backigesta.conexao.Conexao;
import com.backigesta.model.Admin;
import com.backigesta.model.Empresas;
import com.backigesta.model.Funcionarios;
import com.backigesta.model.Usuarios;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class FuncionariosDAO extends DAO{
    private final Conexao banco = new Conexao();
    //=======================MÉTODOS CREATE=======================\\
    public boolean inserir(Funcionarios func){
        boolean retorno = false;
        Connection conn = banco.conectar();
        try{
            String sql = "INSERT INTO funcionarios(cpf, nome, email, senha, id_empresa, turno, id_cargo, id_permissao, foto) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            Time turno = new Time(func.getTurno().getHour(), func.getTurno().getMinute(), func.getTurno().getSecond());
            ps.setString(1, func.getCpf());
            ps.setString(2, func.getNome());
            ps.setString(3, func.getEmail());
            ps.setString(4, func.getSenha());
            ps.setInt(5, func.getId_empresa());
            ps.setTime(6, turno);
            ps.setInt(7, func.getId_cargo());
            ps.setInt(8, func.getId_permissoes());
            ps.setBytes(9, func.getFoto());

            retorno = ps.executeUpdate()==1;
            conn.close();
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar FuncionariosDAO.inserir(Funcionario)!!");
            sqle.printStackTrace();
        }
        finally{
            return retorno;
        }
    }

    //========================MÉTODOS READ========================\\
    public Funcionarios selecionarPorId(int id){
        Connection conn = banco.conectar();
        Funcionarios retorno = null;
        try{
            String sql = "SELECT f.*, c.nome AS cargo FROM funcionarios f JOIN cargos c ON c.id=f.id_cargo WHERE f.id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                retorno = new Funcionarios(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("cpf"),
                        rs.getString("senha"),
                        rs.getInt("id_empresa"),
                        rs.getInt("id_cargo"),
                        rs.getString("cargo"),
                        rs.getInt("id_permissao"),
                        rs.getTime("turno").toLocalTime(),
                        rs.getBytes("foto")
                );
            }
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar FuncionariosDAO.selecionarPorId(int)!!");
            sqle.printStackTrace();
        }
        finally{
            return retorno;
        }
    }

    public List<Funcionarios> selecionarTodos(int id_empresa){
        Connection conn = banco.conectar();
        List<Funcionarios> funcionarios = new ArrayList<>();
        try{
            String sql = "SELECT f.*, c.nome AS cargo FROM funcionarios f JOIN cargos c ON c.id=f.id_cargo WHERE id_empresa=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id_empresa);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                funcionarios.add(new Funcionarios(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("cpf"),
                        rs.getString("senha"),
                        rs.getInt("id_empresa"),
                        rs.getInt("id_cargo"),
                        rs.getString("cargo"),
                        rs.getInt("id_permissao"),
                        rs.getTime("turno").toLocalTime(),
                        rs.getBytes("foto")
                ));
            }
            conn.close();
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar FuncionariosDAO.selecionarTodos(String)");
            sqle.printStackTrace();
        }
        finally {
            return funcionarios;
        }
    }

    public List<Funcionarios> selecionarPorNome(String nome, int id_empresa){
        Connection conn = banco.conectar();
        List<Funcionarios> funcionarios = new ArrayList<>();
        try{
            String sql = "SELECT f.*, c.nome AS cargo FROM funcionarios f JOIN cargos c ON c.id=f.id_cargo WHERE f.nome ilike ? and id_empresa=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + nome + "%");
            ps.setInt(2, id_empresa);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                funcionarios.add(new Funcionarios(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("cpf"),
                        rs.getString("senha"),
                        rs.getInt("id_empresa"),
                        rs.getInt("id_cargo"),
                        rs.getString("cargo"),
                        rs.getInt("id_permissao"),
                        rs.getTime("turno").toLocalTime(),
                        rs.getBytes("foto")
                ));
            }
            conn.close();
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar FuncionariosDAO.selecionarTodos(String)");
            sqle.printStackTrace();
        }
        finally {
            return funcionarios;
        }
    }

    public List<Funcionarios> selecionarPorCargo(int id_cargo, int id_empresa){
        Connection conn = banco.conectar();
        List<Funcionarios> funcionarios = new ArrayList<>();
        try{
            String sql = "SELECT f.*, c.nome AS cargo FROM funcionarios f JOIN cargos c ON c.id=f.id_cargo WHERE f.id_cargo = ? and id_empresa=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id_cargo);
            ps.setInt(2, id_empresa);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                funcionarios.add(new Funcionarios(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("cpf"),
                        rs.getString("senha"),
                        rs.getInt("id_empresa"),
                        rs.getInt("id_cargo"),
                        rs.getString("cargo"),
                        rs.getInt("id_permissao"),
                        rs.getTime("turno").toLocalTime(),
                        rs.getBytes("foto")
                ));
            }
            conn.close();
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar FuncionariosDAO.selecionarTodos(String)");
            sqle.printStackTrace();
        }
        finally {
            return funcionarios;
        }
    }



    public byte[] selecionarFotoPorId(int id_empresa){
        Connection conn = banco.conectar();
        byte[] foto = null;
        try{
            String sql = "SELECT foto FROM funcionarios WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id_empresa);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                foto = rs.getBytes(1);
            }
            conn.close();
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar FuncionariosDAO.selecionarFotoPorId(int)!!");
            sqle.printStackTrace();
        }
        finally {
            return foto;
        }
    }

    //=======================MÉTODOS UPDATE=======================\\
    public boolean atualizar(Usuarios user){
        Funcionarios func = (Funcionarios) user;
        boolean retorno = false;
        Connection conn = banco.conectar();
        try{
            String sql = "UPDATE funcionarios SET nome=?, cpf=?, email=?, senha=?, id_empresa=?, id_cargo=?, id_permissao=?, turno=?, foto=? where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, func.getNome());
            ps.setString(2, func.getCpf());
            ps.setString(3, func.getEmail());
            ps.setString(4, func.getSenha());
            ps.setInt(5, func.getId_empresa());
            ps.setInt(6, func.getId_cargo());
            ps.setInt(7, func.getId_permissoes());
            ps.setTime(8,  new Time(func.getTurno().getHour(), func.getTurno().getMinute(), func.getTurno().getSecond()));
            ps.setBytes(9, func.getFoto());
            ps.setInt(10, func.getId());

            retorno = ps.executeUpdate()==1;
            conn.close();
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar FuncionariosDAO.atualizar(Funcionarios)!!");
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
            String sql = "UPDATE funcionarios SET foto=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBytes(1, foto);
            ps.setInt(2, id);

            retorno = ps.executeUpdate()>=1;
            conn.close();
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar FuncionariosDAO.atualizarFoto(int, byte[])!!");
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
            String sql = "DELETE FROM funcionarios WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            retorno = ps.executeUpdate()>=1;
            conn.close();
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar FuncionariosDAO.deletar(int)!!");
            sqle.printStackTrace();
        }
        finally {
            return retorno;
        }
    }
}
