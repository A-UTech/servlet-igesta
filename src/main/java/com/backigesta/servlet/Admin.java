package com.backigesta.servlet;

import com.backigesta.dao.AdminDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(urlPatterns = {"/selectAdmin","/adicionarAdmin","/alterarAdmin","/deletarAdmin"})
public class Admin extends HttpServlet {
    AdminDAO adminDAO = new AdminDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Capturando o caminho de como ele chegou no método doGet
        String caminho = request.getServletPath();

        // Direcionado o cliente a partir do caminho que chegou no servlet
        if (caminho.equals("/selectAdmin")) {
            // Jogando ele no método para mostrar os selects do banco
            mostrarSelects(request,response);
        }
    }

    protected void mostrarSelects(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Capturando os parâmetro de search
        String procura = request.getParameter("search");

        // Criando o objeto ArrayList com a interface List
        List<com.backigesta.model.Admin> lista = null;

        // Direcionado qual método de procura será usado
        if (procura != null && !procura.equals("")) {
            lista = adminDAO.selecionarPorNome(procura);
        } else {
            lista = adminDAO.selecionarTodos();
        }

        // Colocando o atributo lista no request
        request.setAttribute("admins",lista);

        // Direcionado para onde quero mandar os atributos do request
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/administrators.jsp");

        // Enviando para a pagina
        rd.forward(request,response);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Capturando o caminho de como ele chegou no método doPost
        String caminho = request.getServletPath();

        // Direcionado o cliente apartir do caminho que chegou no servlet
        if (caminho.equals("/deletarAdmin")) {
            // Jogando ele no método para deletar dados do banco
            deletarAdmin(request,response);
        } else if (caminho.equals("/alterarAdmin")) {
            // Jogando ele no método para alterar dados do banco
            alterarAdmin(request,response);
        } else if (caminho.equals("/adicionarAdmin")) {
            // Jogando ele no método para adicionar dados do banco
            adicionarAdmin(request,response);
        }
    }

    protected void deletarAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Capturando os parâmetros de condenaId que estam saindo de um formulario
        int id = Integer.parseInt(request.getParameter("adminId"));

        // Usando o método da classe CondenasDao para deletar um registro
        boolean deletado = adminDAO.deletar(id);

        // Colocando o atributo deletado no request
        request.setAttribute("deletado",deletado ? "true" : "false");

        // Jogando ele no método para mostrar os selects do banco
        mostrarSelects(request,response);
    }

    protected void alterarAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Capturando os parâmetros de condenaId, nomeCondena e tipo que estam saindo de um formulario
        int id = Integer.parseInt(request.getParameter("idAdmin"));
        String nomeAdmin = request.getParameter("nomeAdmin");
        String emailAdmin = request.getParameter("emailAdmin");
        String senhaAdmin = request.getParameter("senhaAdmin");

        // Usando o método da classe CondenasDao para alterar os dados daquele registro
        boolean alterado = adminDAO.atualizar(new com.backigesta.model.Admin(id,nomeAdmin,emailAdmin,senhaAdmin));

        // Colocando o atributo alterado no request
        request.setAttribute("alterado",alterado ? "true" : "false");

        // Jogando ele no método para mostrar os selects do banco
        mostrarSelects(request,response);
    }

    protected void adicionarAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Capturando os parâmetros de nomeCondena, tipo e descricao que estam saindo de um formulario
        String nomeAdmin = request.getParameter("nomeAdmin");
        String emailAdmin = request.getParameter("emailAdmin");
        String senhaAdmin = request.getParameter("senhaAdmin");

        // Usando o método da classe CondenasDao para adicioanar um registro
        boolean adicionado = adminDAO.inserir(new com.backigesta.model.Admin(nomeAdmin,emailAdmin,senhaAdmin));

        // Colocando o atributo adicionado no request
        request.setAttribute("adicionado",adicionado ? "true" : "false");

        // Jogando ele no método para mostrar os selects do banco
        mostrarSelects(request,response);
    }
}
