package com.backigesta.servlet;

import com.backigesta.dao.EmpresasDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Executable;
import java.util.HashMap;
import java.util.List;

@WebServlet(urlPatterns = {"/selectEmpresas", "/adicionarEmpresas", "/alterarEmpresas", "/deletarEmpresas"})
public class Empresas extends HttpServlet {
    EmpresasDAO daoEmpresas = new EmpresasDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getServletPath().equals("/selectEmpresas")){
            mostrarEmpresa(request, response);
        }
    }
    protected void mostrarEmpresa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String procura = request.getParameter("search");
        String regiao = request.getParameter("regiao");

        List<com.backigesta.model.Empresas> empresas;
        if (regiao != null && !regiao.equals("todos")) {
            empresas = daoEmpresas.selecionarPorRegiao(regiao);
        }
        else if(procura == null || regiao.equals("todos")){
            empresas = daoEmpresas.selecionarTodos();
        }
        else {
            empresas = daoEmpresas.selecionarPorNome(procura);
        }
        request.setAttribute("empresas", empresas);
        request.getRequestDispatcher("/WEB-INF/view/company.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getServletPath().equals("/adicionarEmpresas")){
            adicionarEmpresa(request, response);
        }
        else if(request.getServletPath().equals("/alterarEmpresas")){
            alterarEmpresa(request, response);
        }
        else if(request.getServletPath().equals("/deletarEmpresas")){
            deletarEmpresa(request, response);
        }
    }

    protected void deletarEmpresa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("idEmpresa"));

        boolean deletado = daoEmpresas.deletar(id);
        request.setAttribute("deletado", deletado ? "true" : "false");
        mostrarEmpresa(request, response);
    }

    protected void alterarEmpresa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("idEmpresa"));
        String nome = request.getParameter("nomeEmpresa");
        String email = request.getParameter("emailEmpresa");
        String regiao = request.getParameter("regiaoEmpresa");
        String unidade = request.getParameter("unidadeEmpresa");
        String senha = request.getParameter("senhaEmpresa");
        String plano = request.getParameter("planoEmpresa");

        com.backigesta.model.Empresas empresa = daoEmpresas.selecionarPorId(id);
        empresa.setNome(nome);
        empresa.setEmail(email);
        empresa.setRegiao(regiao);
        empresa.setUnidade(unidade);
        if(!senha.equals("")){
            empresa.setSenha(senha);
        }
        empresa.setNomePlano(plano);
        boolean alterado = daoEmpresas.atualizar(empresa);

        request.setAttribute("alterado", alterado ? "true" : "false");

        mostrarEmpresa(request, response);
    }

    protected void adicionarEmpresa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nomeEmpresa");
        String email = request.getParameter("emailEmpresa");
        String cnpj = request.getParameter("cnpjEmpresa");
        String estado = request.getParameter("regiaoEmpresa");
        String unidade = request.getParameter("unidadeEmpresa");
        String senha = request.getParameter("senhaEmpresa");
        String plano = request.getParameter("planoEmpresa");

        boolean retorno = daoEmpresas.inserir(new com.backigesta.model.Empresas(nome,email,cnpj, senha, plano,estado,unidade));
        request.setAttribute("adicionado", retorno ? "true" : "false");
        mostrarEmpresa(request, response);
    }
}