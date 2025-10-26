package com.backigesta.servlet;

import com.backigesta.dao.EmpresasDAO;
import com.backigesta.dao.PlanoDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/selectEmpresas", "/adicionarEmpresas", "/alterarEmpresas", "/deletarEmpresas"})
public class Empresas extends HttpServlet {
    EmpresasDAO daoEmpresas = new EmpresasDAO();
    PlanoDao planoDao = new PlanoDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getServletPath().equals("/selectEmpresas")){
            mostrarEmpresa(request, response);
        }
    }
    protected void mostrarEmpresa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String procura = request.getParameter("search");
        String regiao = request.getParameter("regiao");

        ArrayList<com.backigesta.model.Empresas> empresas;
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
        String estado = request.getParameter("regiaoEmpresa");
        String cidade = request.getParameter("cidadeEmpresa");
        String unidade = request.getParameter("unidadeEmpresa");
        String senha = request.getParameter("senhaEmpresa");
        String plano = request.getParameter("planoEmpresa");

        com.backigesta.model.Empresas empresa = new com.backigesta.model.Empresas(id,nome,email,senha,plano,estado,cidade,unidade);
        boolean alterado = daoEmpresas.atualizar(empresa);

        request.setAttribute("alterado", alterado ? "true" : "false");

        mostrarEmpresa(request, response);
    }

    protected void adicionarEmpresa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nomeEmpresa");
        String email = request.getParameter("emailEmpresa");
        String cnpj = request.getParameter("cnpjEmpresa");
        String estado = request.getParameter("regiaoEmpresa");
        String cidade = request.getParameter("cidadeEmpresa");
        String unidade = request.getParameter("unidadeEmpresa");
        String senha = request.getParameter("senhaEmpresa");
        String plano = request.getParameter("planoEmpresa");

        boolean retorno = daoEmpresas.inserir(new com.backigesta.model.Empresas(nome,email,cnpj, senha, plano,estado,cidade, unidade));
        request.setAttribute("adicionado", retorno ? "true" : "false");
        mostrarEmpresa(request, response);
    }
}