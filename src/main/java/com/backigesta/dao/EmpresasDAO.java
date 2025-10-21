package com.backigesta.dao;

import com.backigesta.conexao.Conexao;
import com.backigesta.model.Admin;
import com.backigesta.model.Empresas;
import com.backigesta.model.Usuarios;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpresasDAO{
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
            ps.setString(5, empresas.getNomePlano());
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

    public int criaConta(Empresas empresa) {
        int idGerado = -1;
        Connection conn = banco.conectar();
        try {
            int id = -1;
            PreparedStatement ps = conn.prepareStatement("select id from planos where lower(nome) = lower(?)");
            ps.setString(1,empresa.getNomePlano());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
            rs.close();
            ps.close();

            ps = conn.prepareStatement("INSERT INTO empresas (nome, email, cnpj, unidade, regiao, id_planos) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, empresa.getNome());
            ps.setString(2, empresa.getEmail());
            ps.setString(3, empresa.getCnpj());
            ps.setString(4, empresa.getUnidade());
            ps.setString(5, empresa.getRegiao());
            ps.setInt(6, id);
            ps.execute();
            ps.close();

            ps = conn.prepareStatement("select id from empresas where lower(email) = lower(?) and lower(cnpj) = lower(?)");
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

    }

    public boolean criaSenha(int empresaId, String senha, String confSenha) {
        boolean sucesso = false;
        Connection conn = banco.conectar();
        try {
            if (senha.equals(confSenha)) {
                String sql = "UPDATE empresas SET senha = ? WHERE id = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, senha);
                ps.setInt(2, empresaId);

                sucesso = ps.executeUpdate() > 0;
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("!!SQLException ao chamar EmpresasDAO.criaConta(...)");
            e.printStackTrace();
        }
        return sucesso;
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
                        rs.getString("id_planos"),
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

    public List<Empresas> selecionarPorRegiao(String regiao){
        Connection conn = banco.conectar();
        List<Empresas> empresa = new ArrayList<>();
        try{
            String sql = "SELECT * FROM empresas where regiao like ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, regiao);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                empresa.add(new Empresas(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("cnpj"),
                        rs.getString("senha"),
                        rs.getString("id_planos"),
                        rs.getString("regiao"),
                        rs.getString("unidade"),
                        rs.getBytes("foto")
                ));
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

    public List<Empresas> selecionarPorNome(String nome){
        Connection conn = banco.conectar();
        List<Empresas> empresa = new ArrayList<>();
        try{
            String sql = "SELECT * FROM empresas where nome like ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                empresa.add(new Empresas(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("cnpj"),
                        rs.getString("senha"),
                        rs.getString("id_planos"),
                        rs.getString("regiao"),
                        rs.getString("unidade"),
                        rs.getBytes("foto")
                ));
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

    public List<Empresas> selecionarTodos(){
        Connection conn = banco.conectar();
        List<Empresas> empresa = new ArrayList<>();
        try{
            String sql = "SELECT * FROM empresas";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()) {
                empresa.add(new Empresas(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("cnpj"),
                        rs.getString("senha"),
                        rs.getString("id_planos"),
                        rs.getString("regiao"),
                        rs.getString("unidade"),
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

    public Empresas selecionarPorEmail(String email){
        Connection conn = null;
        Empresas empresas = null;
        try{
            conn = banco.conectar();
            String sql = "SELECT * FROM empresas WHERE email like ?";
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
                        rs.getString("id_planos"),
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

    public String selecionarInformacoesPlano(int id_empresa){
        Connection conn = banco.conectar();
        String retorno = null;
        try{
            String sql = "select count(*) filter(where f.id_cargo=1) AS gestores, count(*) filter(where f.id_cargo=2) AS lideres, p.nome AS nomePlano, p.mensalidade, p.armazenamento from funcionarios f join empresas e on e.id=f.id_empresa join planos p on p.id=e.id_planos where f.id_empresa=? group by(p.nome, p.mensalidade, p.armazenamento)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id_empresa);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                retorno = "";
                retorno += rs.getInt("gestores")+";"+rs.getInt("lideres")+";"+rs.getString("nomeplano")+";"+rs.getDouble("mensalidade")+";"+rs.getInt("armazenamento");
            }
            conn.close();
        }
        catch(SQLException sqle){
            System.out.println("!!SQLException ao chamar EmpresasDAO.selecionarInformacoesPlano(int)!!");
            sqle.printStackTrace();
        }
        finally {
            return retorno;
        }
    }

    //=======================MÉTODOS UPDATE=======================\\
    public boolean atualizar(Usuarios user){
        Empresas empresas = (Empresas) user;
        boolean retorno = false;
        Connection conn = banco.conectar();
        try{
            String sql = "UPDATE empresas SET cnpj=?, nome=?, email=?, senha=?, id_planos=?, regiao=?, unidade=?,foto=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, empresas.getCnpj());
            ps.setString(2, empresas.getNome());
            ps.setString(3, empresas.getEmail());
            ps.setString(4, empresas.getSenha());
            ps.setString(5, empresas.getNomePlano());
            ps.setBytes(6, empresas.getFoto());
            ps.setInt(7, empresas.getId());
            ps.setString(7, empresas.getRegiao());
            ps.setString(8, empresas.getUnidade());

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
