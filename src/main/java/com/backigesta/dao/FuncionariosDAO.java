package com.backigesta.dao;

import com.backigesta.conexao.Conexao;
import com.backigesta.model.Funcionarios;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionariosDAO {
    private final Conexao banco = new Conexao();
    private Connection conn;

//=======================MÉTODOS CREATE=======================\\

    public boolean inserir(Funcionarios func, int idEmpresa){
        boolean retorno = false;
        try{
            conn = banco.conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT id FROM cargo WHERE nome LIKE ?");
            ps.setString(1,func.getNomeCargo()+"%");
            ResultSet rs = ps.executeQuery();
            int cargoId = -1;
            if (rs.next()) {
                cargoId = rs.getInt(1);
            }
            ps.close();
            rs.close();


            ps = conn.prepareStatement("INSERT INTO funcionario(cpf, nome, email, senha, id_empresa, turno, id_cargo, id_permissao) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
            Time turno = new Time(func.getTurno().getHour(), func.getTurno().getMinute(), func.getTurno().getSecond());
            ps.setString(1, func.getCpf());
            ps.setString(2, func.getNome());
            ps.setString(3, func.getEmail());
            ps.setString(4, func.getSenha());
            ps.setInt(5, idEmpresa);
            ps.setTime(6, turno);
            ps.setInt(7, cargoId);
            ps.setInt(8, func.getIdPermissoes());

            retorno = ps.executeUpdate() == 1;
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar FuncionariosDAO.inserir(Funcionario)!!");
            sqle.printStackTrace();
        }
        finally{
            banco.desconectar(conn);
            return retorno;
        }
    } // Método para inserir um funcionario no banco

//========================MÉTODOS READ========================\\

    public Funcionarios selecionarPorId(int id){
        Funcionarios retorno = null;
        try{
            conn = banco.conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT f.id, f.nome, f.email, f.cpf, f.senha, e.nome, c.nome, f.id_permissao, f.turno, f.foto FROM funcionario f JOIN empresa e ON f.id_empresa = e.id JOIN cargo c ON f.id_cargo = c.id WHERE f.id = ?");
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
            banco.desconectar(conn);
            return retorno;
        }
    } // Método para selecionar um funcionario por id

    public ArrayList<Funcionarios> selecionarTodosComTelefone() {
        ArrayList<Funcionarios> funcionarios = new ArrayList<>();
        try{
            conn = banco.conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT f.id, f.nome, f.email, f.cpf, f.senha, e.nome, c.nome, f.id_permissao, f.turno, f.foto FROM funcionario f JOIN empresa e ON f.id_empresa = e.id JOIN cargo c ON f.id_cargo = c.id JOIN telefone t ON t.id_funcionario = f.id");
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
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar FuncionariosDAO.selecionarTodos(String)");
            sqle.printStackTrace();
        }
        finally {
            banco.desconectar(conn);
            return funcionarios;
        }
    } // Método que seleciona todos os funcionarios que tem telefones

    public ArrayList<Funcionarios> selecionarTodos(int idEmpresa){
        ArrayList<Funcionarios> funcionarios = new ArrayList<>();
        try{
            conn = banco.conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT f.id, f.nome, f.email, f.cpf, f.senha, e.nome, c.nome, f.id_permissao, f.turno, f.foto FROM funcionario f JOIN empresa e ON f.id_empresa = e.id JOIN cargo c ON f.id_cargo = c.id WHERE id_empresa=?");
            ps.setInt(1,idEmpresa);
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
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar FuncionariosDAO.selecionarPorNome(String)");
            sqle.printStackTrace();
        }
        finally {
            banco.desconectar(conn);
            return funcionarios;
        }
    } // Método que seleciona todos os funcionarios do banco

    public ArrayList<Funcionarios> selecionarPorNomeComTelefone(String nome){
        ArrayList<Funcionarios> funcionarios = new ArrayList<>();
        try{
            conn = banco.conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT f.id, f.nome, f.email, f.cpf, f.senha, e.nome, c.nome, f.id_permissao, f.turno, f.foto FROM funcionario f JOIN empresa e ON f.id_empresa = e.id JOIN cargo c ON f.id_cargo = c.id JOIN telefone t ON t.id_funcionario = f.id WHERE lower(f.nome) LIKE lower(?)");
            ps.setString(1,nome + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
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
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar FuncionariosDAO.selecionarPorNome(String)");
            sqle.printStackTrace();
        }
        finally {
            banco.desconectar(conn);
            return funcionarios;
        }
    } // Método que seleciona funcionarios por nome que tem telefones

    public ArrayList<Funcionarios> selecionarPorEmailComTelefone(String email){
        ArrayList<Funcionarios> funcionarios = new ArrayList<>();
        try{
            conn = banco.conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT f.id, f.nome, f.email, f.cpf, f.senha, e.nome, c.nome, f.id_permissao, f.turno, f.foto FROM funcionario f JOIN empresa e ON f.id_empresa = e.id JOIN cargo c ON f.id_cargo = c.id JOIN telefone t ON t.id_funcionario = f.id WHERE lower(f.email) LIKE lower(?)");
            ps.setString(1,email + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
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
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar FuncionariosDAO.selecionarPorNome(String)");
            sqle.printStackTrace();
        }
        finally {
            banco.desconectar(conn);
            return funcionarios;
        }
    } // Método que seleciona funcionarios por email com telefones

    public List<Funcionarios> selecionarPorNome(String nome, int id_empresa){
        List<Funcionarios> funcionarios = new ArrayList<>();
        try{
            conn = banco.conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT f.id, f.nome, f.email, f.cpf, f.senha, e.nome, c.nome, f.id_permissao, f.turno, f.foto FROM funcionario f JOIN empresa e ON f.id_empresa = e.id JOIN cargo c ON f.id_cargo = c.id WHERE lower(f.nome) LIKE lower(?) and e.id = ?");
            ps.setString(1, "%" + nome + "%");
            ps.setInt(2, id_empresa);
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
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar FuncionariosDAO.selecionarTodos(String)");
            sqle.printStackTrace();
        }
        finally {
            banco.desconectar(conn);
            return funcionarios;
        }
    } // Método que seleciona funcionarios pelo nome

    public List<Funcionarios> selecionarPorCargo(String nomeCargo, int id_empresa){
        List<Funcionarios> funcionarios = new ArrayList<>();
        try{
            conn = banco.conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT f.id, f.nome, f.email, f.cpf, f.senha, e.nome, c.nome, f.id_permissao, f.turno, f.foto FROM funcionario f JOIN empresa e ON f.id_empresa  = e.id JOIN cargo c ON f.id_cargo = c.id WHERE f.id_empresa = ? and lower(c.nome) LIKE lower(?)");
            ps.setString(2, nomeCargo+'%');
            ps.setInt(1, id_empresa);
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
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar FuncionariosDAO.selecionarTodos(String)");
            sqle.printStackTrace();
        }
        finally {
            banco.desconectar(conn);
            return funcionarios;
        }
    } // Método que seleciona funcionarios por cargo

    public byte[] selecionarFotoPorId(int id){
        byte[] foto = null;
        try{
            conn = banco.conectar();
            String sql = "SELECT foto FROM funcionario WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                foto = rs.getBytes(1);
            }
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar funcionarioDAO.selecionarFotoPorId(int)!!");
            sqle.printStackTrace();
        }
        finally {
            banco.desconectar(conn);
            return foto;
        }
    } // Método que seleciona foto do funcionario por id

    public ArrayList<Funcionarios> selecionarNomeId() {
        ArrayList<Funcionarios> lista = new ArrayList<>();
        try{
            conn = banco.conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT id,nome FROM funcionario");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                lista.add(new Funcionarios(rs.getInt(1),rs.getString(2)));
            }
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar funcionarioDAO.selecionarPorId(int)!!");
            sqle.printStackTrace();
        }
        finally{
            banco.desconectar(conn);
            return lista;
        }
    } // Método que seleciona nome e id dos funcionarios

//=======================MÉTODOS UPDATE=======================\\

    public boolean atualizar(Funcionarios funcionario){
        boolean retorno = false;
        try{
            conn = banco.conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT id FROM empresa WHERE nome = ?");
            ps.setString(1,funcionario.getNomeEmpresa());
            ResultSet rs = ps.executeQuery();
            int empresaId = -1;
            while (rs.next()) {
                empresaId = rs.getInt(1);
            }
            ps.close();
            rs.close();


            ps = conn.prepareStatement("SELECT id FROM cargo WHERE lower(nome) like (lower(?)");
            System.out.println(funcionario.getNomeCargo());
            ps.setString(1,funcionario.getNomeCargo()+"%");
            rs = ps.executeQuery();
            int cargoId = -1;
            while (rs.next()) {
                cargoId = rs.getInt(1);
            }
            ps.close();
            rs.close();


            ps = conn.prepareStatement("UPDATE funcionario SET nome = ?, cpf = ?, email = ?, senha = ?, id_empresa = ?, id_cargo = ?, id_permissao = ?, turno = ?, foto = ? WHERE id = ?");

            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getCpf());
            ps.setString(3, funcionario.getEmail());
            ps.setString(4, funcionario.getSenha());
            ps.setInt(5, empresaId);
            ps.setInt(6, cargoId);
            ps.setInt(7, funcionario.getIdPermissoes());
            ps.setTime(8,  new Time(funcionario.getTurno().getHour(), funcionario.getTurno().getMinute(), funcionario.getTurno().getSecond()));
            ps.setBytes(9, funcionario.getFoto());
            ps.setInt(10, funcionario.getId());

            retorno = ps.executeUpdate() == 1;
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar FuncionariosDAO.atualizar(Funcionarios)!!");
            sqle.printStackTrace();
        }
        finally {
            banco.desconectar(conn);
            return retorno;
        }
    } // Método que atualiza dados do funcionario por id

    public boolean atualizar(Funcionarios funcionario, int empresaId){
        boolean retorno = false;
        try{
            conn = banco.conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT id FROM cargo WHERE lower(nome) LIKE lower(?)");
            ps.setString(1,funcionario.getNomeCargo()+"%");
            ResultSet rs = ps.executeQuery();
            int cargoId = -1;
            while (rs.next()) {
                cargoId = rs.getInt(1);
            }
            ps.close();
            rs.close();


            ps = conn.prepareStatement("UPDATE funcionario SET nome = ?, cpf = ?, email = ?, senha = ?, id_empresa = ?, id_cargo = ?, id_permissao = ?, turno = ?, foto = ? WHERE id = ?");

            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getCpf());
            ps.setString(3, funcionario.getEmail());
            ps.setString(4, funcionario.getSenha());
            ps.setInt(5, empresaId);
            ps.setInt(6, cargoId);
            ps.setInt(7, funcionario.getIdPermissoes());
            ps.setTime(8,  new Time(funcionario.getTurno().getHour(), funcionario.getTurno().getMinute(), funcionario.getTurno().getSecond()));
            ps.setBytes(9, funcionario.getFoto());
            ps.setInt(10, funcionario.getId());

            retorno = ps.executeUpdate() == 1;
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar FuncionariosDAO.atualizar(Funcionarios, int)!!");
            sqle.printStackTrace();
        }
        finally {
            banco.desconectar(conn);
            return retorno;
        }
    } // Método que atualiza dados do funcionario com o idEmpresa
    
//=======================MÉTODOS DELETE=======================\\

    public boolean deletar(int id){
        boolean retorno = false;
        try{
            conn = banco.conectar();
            conn.setAutoCommit(false);

            PreparedStatement ps = conn.prepareStatement("select qc.id from funcionario f join medicao m on m.cod_gestor = f.id join quantidadecondena qc on qc.cod_medicao = m.id where f.id = ?");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ps = conn.prepareStatement("delete from quantidadecondena where id = ?");
                ps.setInt(1,rs.getInt(1));
                ps.execute();
            }
            ps.close();
            rs.close();

            ps = conn.prepareStatement("select m.id from funcionario f join medicao m on m.cod_gestor = f.id where f.id = ?");
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while (rs.next()) {
                ps = conn.prepareStatement("delete from medicao where id = ?");
                ps.setInt(1,rs.getInt(1));
                ps.execute();
            }
            ps.close();
            rs.close();


            ps = conn.prepareStatement("DELETE FROM funcionario WHERE id = ?");
            ps.setInt(1, id);

            retorno = ps.executeUpdate() >= 1;

            conn.commit();
        }
        catch(SQLException sqle){
            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("Erro no método deletarAdmin()");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
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
    } // Método que deleta um funcionario por id
}