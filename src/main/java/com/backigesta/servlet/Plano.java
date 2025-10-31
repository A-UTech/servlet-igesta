package com.backigesta.servlet;

import com.backigesta.dao.PlanoDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

//Servlet usada no CRUD de planos da Area Restrita
@WebServlet(urlPatterns = {"/selectPlano","/adicionarPlano","/alterarPlano","/deletarPlano"})
public class Plano extends HttpServlet {
    // Declarando os DAO's utilizados.
    PlanoDao planoDao = new PlanoDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Capturando o caminho de como ele chegou no método doGet
        String caminho = request.getServletPath();

        // Direcionado o cliente a partir do caminho que chegou no servlet
        if (caminho.equals("/selectPlano")) {
            // Jogando ele no método para mostrar os selects do banco
            mostrarSelects(request,response);
        }
    }

    protected void mostrarSelects(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Capturando os parâmetros de filter e search
        String procura = request.getParameter("search");

        // Criando o objeto ArrayList com a interface List
        List<com.backigesta.model.Plano> lista = null;

        // Direcionado qual método de procura será usado
        if (procura != null) {
            lista = planoDao.selecionarPorNome(procura);
        } else {
            lista = planoDao.selecionarTodos();
        }

        // Colocando o atributo lista no request
        request.setAttribute("planos",lista);

        // Direcionado para onde quero mandar os atributos do request
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/areaRestrita/payment.jsp");

        // Enviando para a pagina
        rd.forward(request,response);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Capturando o caminho de como ele chegou no método doPost
        String caminho = request.getServletPath();

        // Direcionado o cliente apartir do caminho que chegou no servlet
        if (caminho.equals("/deletarPlano")) {
            // Jogando ele no método para deletar dados do banco
            deletarPlano(request,response);
        } else if (caminho.equals("/alterarPlano")) {
            // Jogando ele no método para alterar dados do banco
            alterarPlano(request,response);
        } else if (caminho.equals("/adicionarPlano")) {
            // Jogando ele no método para adicionar dados do banco
            adicionarPlano(request,response);
        }
    }

    protected void deletarPlano(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Capturando os parâmetros de condenaId que estam saindo de um formulario
        int id = Integer.parseInt(request.getParameter("planoId"));

        // Usando o método da classe CondenasDao para deletar um registro
        boolean deletado = planoDao.deletar(id);

        // Colocando o atributo deletado no request
        request.setAttribute("deletado",deletado ? "true" : "false");

        // Jogando ele no método para mostrar os selects do banco
        mostrarSelects(request,response);
    }

    protected void alterarPlano(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Capturando os parâmetros de condenaId, nomeCondena e tipo que estam saindo de um formulario
        int id = Integer.parseInt(request.getParameter("planoId"));
        String nomePlano = request.getParameter("nomePlano");
        double mensalidade = Double.parseDouble(request.getParameter("mensalidade").replace(",","."));
        int armazenamento = Integer.parseInt(request.getParameter("armazenamento"));

        // Usando o método da classe CondenasDao para alterar os dados daquele registro
        boolean alterado = planoDao.atualizar(new com.backigesta.model.Plano(id,nomePlano,mensalidade,armazenamento));

        // Colocando o atributo alterado no request
        request.setAttribute("alterado",alterado ? "true" : "false");

        // Jogando ele no método para mostrar os selects do banco
        mostrarSelects(request,response);
    }

    protected void adicionarPlano(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Capturando os parâmetros de nomeCondena, tipo e descricao que estam saindo de um formulario
        String nomePlano = request.getParameter("nomePlano");
        double mensalidade = Double.parseDouble(request.getParameter("mensalidade").replace(",","."));
        int armazenamento = Integer.parseInt(request.getParameter("armazenamento"));

        // Usando o método da classe CondenasDao para adicioanar um registro
        boolean adicionado = planoDao.inserir(new com.backigesta.model.Plano(nomePlano,mensalidade,armazenamento));

        // Colocando o atributo adicionado no request
        request.setAttribute("adicionado",adicionado ? "true" : "false");

        // Jogando ele no método para mostrar os selects do banco
        mostrarSelects(request,response);
    }
}
