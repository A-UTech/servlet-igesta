package com.backigesta.servlet;

import com.backigesta.dao.EmpresaDAO;
import com.backigesta.dao.PlanoDao;
import com.backigesta.util.Regex;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/selectEmpresa", "/adicionarEmpresa", "/alterarEmpresa", "/deletarEmpresa"})
public class Empresa extends HttpServlet {
    EmpresaDAO daoEmpresas = new EmpresaDAO();
    PlanoDao planoDao = new PlanoDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getServletPath().equals("/selectEmpresa")){
            mostrarEmpresa(request, response);
        }
    }
    protected void mostrarEmpresa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String procura = request.getParameter("search");
        String regiao = request.getParameter("regiao");

        ArrayList<com.backigesta.model.Empresa> empresas;
        if (regiao != null && !"todos".equals(regiao)) {
            empresas = daoEmpresas.selecionarPorEstado(regiao);
        } else if(procura == null || "todos".equals(regiao)){
            empresas = daoEmpresas.selecionarTodos();
        } else {
            empresas = daoEmpresas.selecionarPorNome(procura);
        }

        ArrayList<String> planos = planoDao.selecionarNomes();
        request.setAttribute("planos",planos);
        request.setAttribute("empresas", empresas);
        request.getRequestDispatcher("/WEB-INF/view/company.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getServletPath().equals("/adicionarEmpresa")){
            adicionarEmpresa(request, response);
        }
        else if(request.getServletPath().equals("/alterarEmpresa")){
            alterarEmpresa(request, response);
        }
        else if(request.getServletPath().equals("/deletarEmpresa")){
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
        String estado = request.getParameter("regiaoEmpresa");
        String cidade = request.getParameter("cidadeEmpresa");
        String unidade = request.getParameter("unidadeEmpresa");
        String senha = request.getParameter("senhaEmpresa");
        String plano = request.getParameter("planoEmpresa");

        com.backigesta.model.Empresa empresa = new com.backigesta.model.Empresa(id,nome,email,senha,plano,estado,cidade,unidade);
        boolean alterado = daoEmpresas.atualizar(empresa);

        request.setAttribute("alterado", alterado ? "true" : "false");

        mostrarEmpresa(request, response);
    }

    protected void adicionarEmpresa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nomeEmpresa");
        String email = request.getParameter("emailEmpresa");
        String cnpj = Regex.extrairNumero(request.getParameter("cnpjEmpresa"));
        String estado = request.getParameter("regiaoEmpresa");
        String cidade = request.getParameter("cidadeEmpresa");
        String unidade = request.getParameter("unidadeEmpresa");
        String senha = request.getParameter("senhaEmpresa");
        String plano = request.getParameter("planoEmpresa");

        boolean retorno = daoEmpresas.inserir(new com.backigesta.model.Empresa(nome,email,cnpj, senha, plano,estado,cidade, unidade));
        request.setAttribute("adicionado", retorno ? "true" : "false");
        mostrarEmpresa(request, response);
    }
}