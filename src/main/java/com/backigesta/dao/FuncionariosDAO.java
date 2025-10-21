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
<<<<<<< HEAD
            String sql = "INSERT INTO funcionarios(cpf, nome, email, senha, id_empresa, turno, id_cargo, id_permissao, foto) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
=======
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
>>>>>>> d850abced838118a24345f846131b597d8980f3f
            Time turno = new Time(func.getTurno().getHour(), func.getTurno().getMinute(), func.getTurno().getSecond());
            ps.setString(1, func.getCpf());
            ps.setString(2, func.getNome());
            ps.setString(3, func.getEmail());
            ps.setString(4, func.getSenha());
<<<<<<< HEAD
            ps.setInt(5, func.getId_empresa());
            ps.setTime(6, turno);
            ps.setInt(7, func.getId_cargo());
            ps.setInt(8, func.getId_permissoes());
=======
            ps.setInt(5, empresaId);
            ps.setTime(6, turno);
            ps.setInt(7, cargoId);
            ps.setInt(8, func.getIdPermissoes());
>>>>>>> d850abced838118a24345f846131b597d8980f3f
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
<<<<<<< HEAD
            String sql = "SELECT f.*, c.nome AS cargo FROM funcionarios f JOIN cargos c ON c.id=f.id_cargo WHERE f.id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
=======
            PreparedStatement ps = conn.prepareStatement("select f.id, f.nome, f.email, f.cpf, f.senha, e.nome, c.nome, f.id_permissao, f.turno, f.foto from funcionarios f join empresas e on f.id = e.id join cargos c on f.id_cargo = c.id where f.id = ?");
>>>>>>> d850abced838118a24345f846131b597d8980f3f
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                retorno = new Funcionarios(
<<<<<<< HEAD
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
=======
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
>>>>>>> d850abced838118a24345f846131b597d8980f3f
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
<<<<<<< HEAD
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
=======
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
>>>>>>> d850abced838118a24345f846131b597d8980f3f
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
<<<<<<< HEAD
            String sql = "UPDATE funcionarios SET nome=?, cpf=?, email=?, senha=?, id_empresa=?, id_cargo=?, id_permissao=?, turno=?, foto=? where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
=======
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
>>>>>>> d850abced838118a24345f846131b597d8980f3f

            ps.setString(1, func.getNome());
            ps.setString(2, func.getCpf());
            ps.setString(3, func.getEmail());
            ps.setString(4, func.getSenha());
<<<<<<< HEAD
            ps.setInt(5, func.getId_empresa());
            ps.setInt(6, func.getId_cargo());
            ps.setInt(7, func.getId_permissoes());
=======
            ps.setInt(5, empresaId);
            ps.setInt(6, cargoId);
            ps.setInt(7, func.getIdPermissoes());
>>>>>>> d850abced838118a24345f846131b597d8980f3f
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
