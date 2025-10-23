package com.backigesta.servlet;

import com.backigesta.dao.CondenasDao;

import com.backigesta.model.Admin;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/selectCondena","/adicionarCondena","/alterarCondena","/deletarCondena"})
public class Condenas extends HttpServlet {

    CondenasDao daoCondenas = new CondenasDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Capturando o caminho de como ele chegou no método doGet
        String caminho = request.getServletPath();

        // Direcionado o cliente a partir do caminho que chegou no servlet
        if (caminho.equals("/selectCondena")) {
            // Jogando ele no método para mostrar os selects do banco
            mostrarSelects(request,response);
        }
    }

    protected void mostrarSelects(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Capturando os parâmetros de filter e search
        String filtro = request.getParameter("filter");
        String procura = request.getParameter("search");

        // Criando o objeto ArrayList com a interface List
        List<com.backigesta.model.Condenas> lista = null;

        // Direcionado qual método de procura será usado
        if (procura != null) {
            lista = daoCondenas.buscarCondenasNome(procura.toLowerCase());
        } else if (filtro == null || filtro.equals("todos")) {
            lista = daoCondenas.buscarCondenas();
        } else {
            lista = daoCondenas.buscarCondenasTipo(filtro.toLowerCase());
        }

        // Colocando o atributo lista no request
        request.setAttribute("condenas",lista);

        // Direcionado para onde quero mandar os atributos do request
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/condemn.jsp");

        // Enviando para a pagina
        rd.forward(request,response);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Capturando o caminho de como ele chegou no método doPost
        String caminho = request.getServletPath();

        // Direcionado o cliente apartir do caminho que chegou no servlet
        if (caminho.equals("/deletarCondena")) {
            // Jogando ele no método para deletar dados do banco
            deletarCondena(request,response);
        } else if (caminho.equals("/alterarCondena")) {
            // Jogando ele no método para alterar dados do banco
            alterarCondena(request,response);
        } else if (caminho.equals("/adicionarCondena")) {
            // Jogando ele no método para adicionar dados do banco
            adicionarContena(request,response);
        }
    }

    protected void deletarCondena(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Capturando os parâmetros de condenaId que estam saindo de um formulario
        int id = Integer.parseInt(request.getParameter("condenaId"));

        // Usando o método da classe CondenasDao para deletar um registro
        boolean deletado = daoCondenas.deletarCondena(id);

        // Colocando o atributo deletado no request
        request.setAttribute("deletado",deletado ? "true" : "false");

        // Jogando ele no método para mostrar os selects do banco
        mostrarSelects(request,response);
    }

    protected void alterarCondena(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Capturando os parâmetros de condenaId, nomeCondena e tipo que estam saindo de um formulario
        int id = Integer.parseInt(request.getParameter("condenaId"));
        String nomeCondena = request.getParameter("nomeCondena");
        String tipoCondena = request.getParameter("tipo");
        String descricaoCondena = request.getParameter("descricaoCondena");

        // Usando o método da classe CondenasDao para alterar os dados daquele registro
        boolean alterado = daoCondenas.alterarCondena(new com.backigesta.model.Condenas(id,nomeCondena,descricaoCondena,tipoCondena));

        // Colocando o atributo alterado no request
        request.setAttribute("alterado",alterado ? "true" : "false");

        // Jogando ele no método para mostrar os selects do banco
        mostrarSelects(request,response);
    }

    protected void adicionarContena(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Admin admin = (Admin) session.getAttribute("admin");

        // Capturando os parâmetros de nomeCondena, tipo e descricao que estam saindo de um formulario
        String nomeCondena = request.getParameter("nomeCondena");
        String tipoCondena = request.getParameter("tipo");
        String descricaoCondena = request.getParameter("descricaoCondena");

        // Usando o método da classe CondenasDao para adicioanar um registro
        boolean adicionado = daoCondenas.adicionarCondena(new com.backigesta.model.Condenas(nomeCondena,admin.getNome(),descricaoCondena,tipoCondena));

        // Colocando o atributo adicionado no request
        request.setAttribute("adicionado",adicionado ? "true" : "false");

        // Jogando ele no método para mostrar os selects do banco
        mostrarSelects(request,response);
    }

}
