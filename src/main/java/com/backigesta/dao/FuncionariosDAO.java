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
            PreparedStatement ps = conn.prepareStatement("select id from empresas where nome = ?");
            ps.setString(1,func.getNomeEmpresa());
            ResultSet rs = ps.executeQuery();
            int empresaId = -1;
            while (rs.next()) {
                empresaId = rs.getInt(1);
            }
            ps.close();
            rs.close();


            ps = conn.prepareStatement("select id from cargo where nome = ?");
            ps.setString(1,func.getNomeCargo());
            rs = ps.executeQuery();
            int cargoId = -1;
            while (rs.next()) {
                cargoId = rs.getInt(1);
            }
            ps.close();
            rs.close();


            ps = conn.prepareStatement("INSERT INTO funcionarios(cpf, nome, email, senha, id_empresa, turno, id_cargo, id_permissao, foto) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            Time turno = new Time(func.getTurno().getHour(), func.getTurno().getMinute(), func.getTurno().getSecond());
            ps.setString(1, func.getCpf());
            ps.setString(2, func.getNome());
            ps.setString(3, func.getEmail());
            ps.setString(4, func.getSenha());
            ps.setInt(5, empresaId);
            ps.setTime(6, turno);
            ps.setInt(7, cargoId);
            ps.setInt(8, func.getIdPermissoes());
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
            PreparedStatement ps = conn.prepareStatement("select f.id, f.nome, f.email, f.cpf, f.senha, e.nome, c.nome, f.id_permissao, f.turno, f.foto from funcionarios f join empresas e on f.id = e.id join cargos c on f.id_cargo = c.id where f.id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                retorno = new Funcionarios(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getTime(9).toLocalTime(),
                        rs.getBytes(10)
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

    public List<Funcionarios> selecionarTodos(){
        Connection conn = banco.conectar();
        List<Funcionarios> funcionarios = new ArrayList<Funcionarios>();
        try{
            PreparedStatement ps = conn.prepareStatement("select f.id, f.nome, f.email, f.cpf, f.senha, e.nome, c.nome, f.id_permissao, f.turno, f.foto from funcionarios f join empresas e on f.id = e.id join cargos c on f.id_cargo = c.id");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                funcionarios.add(new Funcionarios(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getTime(9).toLocalTime(),
                        rs.getBytes(10)
                ));
            }
            conn.close();
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar FuncionariosDAO.selecionarPorNome(String)");
            sqle.printStackTrace();
        }
        finally {
            return funcionarios;
        }
    }

    public byte[] selecionarFotoPorId(int id){
        Connection conn = banco.conectar();
        byte[] foto = null;
        try{
            String sql = "SELECT foto FROM funcionarios WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
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

    public ArrayList<String> buscarApenasNome() {
        Connection conn = banco.conectar();
        ArrayList<String> lista = new ArrayList<>();
        try{
            PreparedStatement ps = conn.prepareStatement("select nome from funcionarios");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                lista.add(rs.getString(1));
            }
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar FuncionariosDAO.selecionarPorId(int)!!");
            sqle.printStackTrace();
        }
        finally{
            return lista;
        }
    }

    //=======================MÉTODOS UPDATE=======================\\
    public boolean atualizar(Usuarios user){
        Funcionarios func = (Funcionarios) user;
        boolean retorno = false;
        Connection conn = banco.conectar();
        try{
            PreparedStatement ps = conn.prepareStatement("select id from empresas where nome = ?");
            ps.setString(1,func.getNomeEmpresa());
            ResultSet rs = ps.executeQuery();
            int empresaId = -1;
            while (rs.next()) {
                empresaId = rs.getInt(1);
            }
            ps.close();
            rs.close();


            ps = conn.prepareStatement("select id from cargo where nome = ?");
            ps.setString(1,func.getNomeCargo());
            rs = ps.executeQuery();
            int cargoId = -1;
            while (rs.next()) {
                cargoId = rs.getInt(1);
            }
            ps.close();
            rs.close();


            ps = conn.prepareStatement("UPDATE funcionarios SET nome=?, cpf=?, email=?, senha=?, id_empresa=?, id_cargo=?, id_permissao=?, turno=?, foto=? where id=?");

            ps.setString(1, func.getNome());
            ps.setString(2, func.getCpf());
            ps.setString(3, func.getEmail());
            ps.setString(4, func.getSenha());
            ps.setInt(5, empresaId);
            ps.setInt(6, cargoId);
            ps.setInt(7, func.getIdPermissoes());
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
