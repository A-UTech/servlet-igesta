package com.backigesta.dao;

import com.backigesta.conexao.Conexao;
import com.backigesta.model.Empresas;

import java.sql.*;
import java.util.ArrayList;

public class EmpresasDAO{
    private Connection conn;
    private final Conexao banco = new Conexao();
    
//=======================MÉTODOS CREATE=======================\\
    public boolean inserir(Empresas empresas){
        boolean retorno = false;
        try{
            conn = banco.conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT id FROM plano WHERE lower(nome) = lower(?)");
            ps.setString(1,empresas.getNomePlano());
            ResultSet rs = ps.executeQuery();
            int planoId = -1;
            while (rs.next()) {
                planoId = rs.getInt(1);
            }
            rs.close();
            ps.close();

            ps = conn.prepareStatement("INSERT INTO empresa(cnpj, nome, email, senha, id_plano, foto, estado, cidade, unidade) VALUES (?,?,?,?,?,?,?,?,?)");
            ps.setString(1, empresas.getCnpj());
            ps.setString(2, empresas.getNome());
            ps.setString(3, empresas.getEmail());
            ps.setString(4, empresas.getSenha());
            ps.setInt(5, planoId);
            ps.setBytes(6, empresas.getFoto());
            ps.setString(7, empresas.getEstado());
            ps.setString(8,empresas.getCidade());
            ps.setString(9, empresas.getUnidade());

            retorno = ps.executeUpdate() == 1;
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar EmpresasDAO.inserir(Empresa)");
            sqle.printStackTrace();
        }
        finally{
            banco.desconectar(conn);
            return retorno;
        }
    } // Método que inseri uma empresa

    public int inserirConta(Empresas empresa) {
        int idGerado = -1;
        try {
            int id = -1;
            conn = banco.conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT id FROM plano WHERE lower(nome) = lower(?)");
            ps.setString(1,empresa.getNomePlano());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
            rs.close();
            ps.close();

            ps = conn.prepareStatement("INSERT INTO empresa (nome, email, cnpj, unidade, estado, cidade, id_plano) VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, empresa.getNome());
            ps.setString(2, empresa.getEmail());
            ps.setString(3, empresa.getCnpj());
            ps.setString(4, empresa.getUnidade());
            ps.setString(5, empresa.getEstado());
            ps.setString(6,empresa.getCidade());
            ps.setInt(7, id);
            ps.execute();
            ps.close();

            ps = conn.prepareStatement("SELECT id FROM empresa WHERE lower(email) = lower(?) AND lower(cnpj) = lower(?)");
            ps.setString(1,empresa.getEmail());
            ps.setString(2,empresa.getCnpj());
            rs = ps.executeQuery();
            if (rs.next()) {
                idGerado = rs.getInt(1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("!!SQLException ao chamar EmpresasDAO.criaSenha(...)");
            e.printStackTrace();
        } finally {
            banco.desconectar(conn);
            return idGerado;
        }

    } // Método qeu inseri uma conta de empresa

    public boolean inserirSenha(int empresaId, String senha, String confSenha) {
        boolean retorno = false;
        try {
            conn = banco.conectar();
            if (senha.equals(confSenha)) {
                String sql = "UPDATE empresa SET senha = ? WHERE id = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, senha);
                ps.setInt(2, empresaId);

                retorno = ps.executeUpdate() == 1;
            }
        } catch (SQLException e) {
            System.out.println("!!SQLException ao chamar EmpresasDAO.criaConta(...)");
            e.printStackTrace();
        } finally {
            banco.desconectar(conn);
            return retorno;
        }
    } // Método que inseri a senha de uma conta de empresa pelo seu id

//=======================MÉTODOS READ=======================\\
    public Empresas selecionarPorId(int id){
        Empresas empresas = null;
        try{
            conn = banco.conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM empresa WHERE id = ? ORDER BY nome");

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                empresas = new Empresas(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("cnpj"),
                        rs.getString("senha"),
                        rs.getString("id_plano"),
                        rs.getString("estado"),
                        rs.getString("cidade"),
                        rs.getString("unidade"),
                        rs.getBytes("foto")
                );
            }
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar EmpresasDAO.selecionarPorId(int)!!");
            sqle.printStackTrace();
        }
        finally {
            banco.desconectar(conn);
            return empresas;
        }
    } // Método que seleciona a empresa por id

    public String selecionarInformacoesPlano(int id_empresa){
        String retorno = null;
        try{
            conn = banco.conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT count(*) filter(WHERE f.id_cargo = 1) AS gestores, count(*) filter(WHERE f.id_cargo = 2) AS lideres, p.nome AS nomePlano, p.mensalidade, p.armazenamento FROM funcionario f RIGHT JOIN empresa e ON e.id = f.id_empresa JOIN plano p ON p.id = e.id_plano WHERE e.id = ? GROUP BY(p.nome, p.mensalidade, p.armazenamento)");
            ps.setInt(1, id_empresa);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                retorno = "";
                retorno += rs.getInt("gestores")+";"+rs.getInt("lideres")+";"+rs.getString("nomeplano")+";"+rs.getDouble("mensalidade")+";"+rs.getInt("armazenamento");
            }
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar EmpresasDAO.selecionarInformacoesPlano(int)!!");
            sqle.printStackTrace();
        }
        finally {
            banco.desconectar(conn);
            return retorno;
        }
    } // Método que seleciona informações do plano da empresa por id_empresa

    public ArrayList<Empresas> selecionarPorEstado(String estado){
        ArrayList<Empresas> empresa = new ArrayList<>();
        try{
            conn = banco.conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT e.*, p.nome AS plano FROM empresa e JOIN plano p ON p.id=e.id_plano WHERE lower(e.estado) LIKE lower(?) ORDER BY e.nome");
            ps.setString(1, estado);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                empresa.add(new Empresas(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("cnpj"),
                        rs.getString("senha"),
                        rs.getString("plano"),
                        rs.getString("estado"),
                        rs.getString("cidade"),
                        rs.getString("unidade"),
                        rs.getBytes("foto")
                ));
            }
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar EmpresasDAO.selecionarPorestado(String)!!");
            sqle.printStackTrace();
        }
        finally {
            banco.desconectar(conn);
            return empresa;
        }
    } // Método que seleciona empresas por estado

    public ArrayList<Empresas> selecionarPorNome(String nome){
        ArrayList<Empresas> empresa = new ArrayList<>();
        try{
            conn = banco.conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT e.*, p.nome AS plano FROM empresa e JOIN plano p ON p.id=e.id_plano WHERE lower(e.nome) LIKE lower(?) ORDER BY e.nome");
            ps.setString(1, nome+"%");
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                empresa.add(new Empresas(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("cnpj"),
                        rs.getString("senha"),
                        rs.getString("plano"),
                        rs.getString("estado"),
                        rs.getString("cidade"),
                        rs.getString("unidade"),
                        rs.getBytes("foto")
                ));
            }
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar EmpresasDAO.selecionarPorNome(String)!!");
            sqle.printStackTrace();
        }
        finally {
            banco.desconectar(conn);
            return empresa;
        }
    } // Método que seleciona as empresas por nome

    public ArrayList<Empresas> selecionarTodos(){
        ArrayList<Empresas> empresa = new ArrayList<>();
        try{
            conn = banco.conectar();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT e.*, p.nome AS plano FROM empresa e JOIN plano p ON p.id = e.id_plano ORDER BY e.nome");

            while(rs.next()) {
                empresa.add(new Empresas(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("cnpj"),
                        rs.getString("senha"),
                        rs.getString("plano"),
                        rs.getString("estado"),
                        rs.getString("cidade"),
                        rs.getString("unidade"),
                        rs.getBytes("foto")
                ));
            }
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar EmpresasDAO.selecionarTodos()!!");
            sqle.printStackTrace();
        }
        finally {
            banco.desconectar(conn);
            return empresa;
        }
    } // Método que seleciona todas as empresas

    public Empresas selecionarPorEmail(String email){
        Empresas empresas = null;
        try{
            conn = banco.conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM empresa WHERE email LIKE ? ORDER BY e.nome");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                empresas = new Empresas(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("cnpj"),
                        rs.getString("senha"),
                        rs.getString("id_plano"),
                        rs.getString("estado"),
                        rs.getString("cidade"),
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
    } // Método que seleciona empresas por email

    public byte[] selecionarFotoPorId(int id){
        byte[] foto = null;
        try{
            conn = banco.conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT foto FROM empresa WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                foto = rs.getBytes(1);
            }
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar EmpresasDAO.selecionarFotoPorId(int)!!");
            sqle.printStackTrace();
        }
        finally {
            banco.desconectar(conn);
            return foto;
        }
    } // Método que seleciona foto da empresa por id

    public boolean verificaLoginEmpresa(String email, String senha) {
        boolean retorno = false;
        try {
            conn = banco.conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM empresa WHERE email = ? AND senha = ?");
            ps.setString(1,email);
            ps.setString(2,senha);

            ResultSet rset = ps.executeQuery();

            if (rset.next()) {
                retorno = true;
            }
        } catch (Exception e) {
            System.out.println("!!SQLException ao chamar EmpresasDAO.verificaLoginEmp(String email, String senha)!!");
            e.printStackTrace();
        } finally {
            banco.desconectar(conn);
            return retorno;
        }
    } // Método que verifica se aquela conta de empresa existe

//=======================MÉTODOS UPDATE=======================\\
    public boolean atualizar(Empresas empresa){
        boolean retorno = false;
        try{
            conn = banco.conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT id FROM plano WHERE lower(nome) = lower(?)");
            ps.setString(1,empresa.getNomePlano());
            ResultSet rs = ps.executeQuery();
            int planoId = -1;
            while (rs.next()) {
                planoId = rs.getInt(1);
            }
            rs.close();
            ps.close();

            ps = conn.prepareStatement("UPDATE empresa SET nome = ?, email = ?, senha = ?, id_plano = ?, estado = ?,cidade = ?, unidade = ? WHERE id = ?");
            ps.setString(1, empresa.getNome());
            ps.setString(2, empresa.getEmail());
            if (empresa.getSenha().equals("")) {
                ps.setNull(3, java.sql.Types.VARCHAR);
            } else {
                ps.setString(3, empresa.getSenha());
            }
            ps.setInt(4, planoId);
            ps.setString(5, empresa.getEstado());
            ps.setString(6,empresa.getCidade());
            ps.setString(7, empresa.getUnidade());
            ps.setInt(8, empresa.getId());

            retorno = ps.executeUpdate() >= 1;
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar EmpresasDAO.atualizar(Empresas)!!");
            sqle.printStackTrace();
        }
        finally {
            banco.desconectar(conn);
            return retorno;
        }
    } // Método que atualiza dados da empresa por id

    public boolean atualizarFoto(int id, byte[] foto){
        boolean retorno = false;
        try{
            conn = banco.conectar();
            PreparedStatement ps = conn.prepareStatement("UPDATE empresa SET foto = ? WHERE id = ?");
            ps.setBytes(1, foto);
            ps.setInt(2, id);

            retorno = ps.executeUpdate() >= 1;
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar EmpresasDAO.atualizarFoto(int, byte[])!!");
            sqle.printStackTrace();
        }
        finally {
            banco.desconectar(conn);
            return retorno;
        }
    } // Método que atualiza a foto da empresa por id

//=======================MÉTODOS DELETE=======================\\

    public boolean deletar(int id) {
        boolean retorno = false;
        try {
            conn = banco.conectar();
            conn.setAutoCommit(false);
            
            PreparedStatement ps = conn.prepareStatement("SELECT t.id FROM empresa e JOIN funcionario f ON f.id_empresa = e.id JOIN telefone t ON f.id = t.id_funcionario WHERE e.id = ?");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ps = conn.prepareStatement("DELETE FROM telefone WHERE id = ?");
                ps.setInt(1,rs.getInt(1));
                ps.execute();
            }
            ps.close();
            
            
            ps = conn.prepareStatement("SELECT qc.id FROM empresa e JOIN funcionario f ON f.id_empresa = e.id JOIN medicao m ON f.id = m.cod_gestor JOIN quantidadecondena qc ON m.id = qc.cod_medicao WHERE e.id = ?");
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while (rs.next()) {
                ps = conn.prepareStatement("DELETE FROM quantidadecondena WHERE id = ?");
                ps.setInt(1,rs.getInt(1));
                ps.execute();
            }
            ps.close();
            rs.close();
            
            
            ps = conn.prepareStatement("SELECT m.id FROM empresa e JOIN funcionario f ON f.id_empresa = e.id JOIN medicao m ON f.id = m.cod_gestor WHERE e.id = ?");
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while (rs.next()) {
                ps = conn.prepareStatement("DELETE FROM medicao WHERE id = ?");
                ps.setInt(1,rs.getInt(1));
                ps.execute();
            }
            rs.close();
            ps.close();


            ps = conn.prepareStatement("SELECT f.id FROM empresa e JOIN funcionario f ON f.id_empresa = e.id WHERE e.id = ?");
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while (rs.next()) {
                ps = conn.prepareStatement("DELETE FROM funcionario WHERE id = ?");
                ps.setInt(1,rs.getInt(1));
                ps.execute();
            }
            ps.close();
            rs.close();
            

            ps = conn.prepareStatement("DELETE FROM empresa WHERE id = ?");
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
    } // Método que deleta empresa por id
}
