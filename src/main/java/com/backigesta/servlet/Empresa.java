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

//Servlet usada no CRUD de empresas, da area restrita.
@WebServlet(urlPatterns = {"/selectEmpresa", "/adicionarEmpresa", "/alterarEmpresa", "/deletarEmpresa"})
public class Empresa extends HttpServlet {
    // Declarando os DAO's utilizados.
    EmpresaDAO daoEmpresas = new EmpresaDAO();
    PlanoDao planoDao = new PlanoDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Capturando o caminho utilizado para chegar na servlet
        String caminho = request.getServletPath();
        if(caminho.equals("/selectEmpresa")){
            //Redirecionando para o método de mostrar a tabela.
            mostrarEmpresa(request, response);
        }
    }
    //Envia o usuario para a JSP do CRUD, junto a lista de Empresas
    protected void mostrarEmpresa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Capturando os parâmetros de filtro e pesquisa;
        String procura = request.getParameter("search");
        String regiao = request.getParameter("regiao");

        //Criando uma ArrayList que vai conter os registros
        ArrayList<com.backigesta.model.Empresa> empresas;
        if (regiao != null && !"todos".equals(regiao)) {
            //Selecionando por filtro
            empresas = daoEmpresas.selecionarPorEstado(regiao);
        } else if(procura == null || "todos".equals(regiao)){
            //Selecionando todos
            empresas = daoEmpresas.selecionarTodos();
        } else {
            //Selecionando por pesquisa
            empresas = daoEmpresas.selecionarPorNomeOrEmail(procura);
        }

        //Buscando o nome dos planos disponiveis para mostrar no CRUD
        ArrayList<String> planos = planoDao.selecionarNomes();
        //Enviando ambas as Listas para o Request.
        request.setAttribute("planos",planos);
        request.setAttribute("empresas", empresas);
        //Redirecionando para a página do CRUD
        request.getRequestDispatcher("/WEB-INF/view/areaRestrita/company.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Capturando o caminho usado para chegar no doPost
        String caminho = request.getServletPath();
        //Redirecionando para o método de ação.
        if(caminho.equals("/adicionarEmpresa")){
            adicionarEmpresa(request, response);
        }
        else if(caminho.equals("/alterarEmpresa")){
            alterarEmpresa(request, response);
        }
        else if(caminho.equals("/deletarEmpresa")){
            deletarEmpresa(request, response);
        }
    }

    //Método de deletar um registro de empresa
    protected void deletarEmpresa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Capturando o id da empresa fornecido à ser deletada.
        int id = Integer.parseInt(request.getParameter("idEmpresa"));

        //Deletando ela, guardando o resultado da ação.
        boolean deletado = daoEmpresas.deletar(id);
        //Retornando se teve sucesso ou não.
        request.setAttribute("deletado", deletado ? "true" : "false");
        //Voltando para a pagina de CRUD mostrando as tabelas.
        mostrarEmpresa(request, response);
    }

    //Método de alterar um registro de empresa
    protected void alterarEmpresa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Capturando todos os parâmetros de atributos de uma empresa
        int id = Integer.parseInt(request.getParameter("idEmpresa"));
        String nome = request.getParameter("nomeEmpresa");
        String email = request.getParameter("emailEmpresa");
        String estado = request.getParameter("regiaoEmpresa");
        String cidade = request.getParameter("cidadeEmpresa");
        String unidade = request.getParameter("unidadeEmpresa");
        String senha = request.getParameter("senhaEmpresa");
        String plano = request.getParameter("planoEmpresa");

        // Criando objeto empresa com as informações passadas.
        com.backigesta.model.Empresa empresa = new com.backigesta.model.Empresa(id,nome,email,senha,plano,estado,cidade,unidade);
        //Atualizando o registro já existente.
        boolean alterado = daoEmpresas.atualizar(empresa);
        //Retornando se teve sucesso ou não.
        request.setAttribute("alterado", alterado ? "true" : "false");
        //Voltando para a pagina de CRUD mostrando as tabelas.
        mostrarEmpresa(request, response);
    }

    protected void adicionarEmpresa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Capturando todos os parâmetros de atributos de uma empresa
        String nome = request.getParameter("nomeEmpresa");
        String email = request.getParameter("emailEmpresa");
        String cnpj = Regex.extrairNumero(request.getParameter("cnpjEmpresa"));
        String estado = request.getParameter("regiaoEmpresa");
        String cidade = request.getParameter("cidadeEmpresa");
        String unidade = request.getParameter("unidadeEmpresa");
        String senha = request.getParameter("senhaEmpresa");
        String plano = request.getParameter("planoEmpresa");

        //Inserindo o novo objeto Empresa, criado com os atributos, no banco.
        boolean retorno = daoEmpresas.inserir(new com.backigesta.model.Empresa(nome,email,cnpj, senha, plano,estado,cidade, unidade));
        //Retornando se teve sucesso ou não
        request.setAttribute("adicionado", retorno ? "true" : "false");
        //Voltando para a pagina de CRUD mostrando as tabelas.
        mostrarEmpresa(request, response);
    }
}