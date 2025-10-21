package com.backigesta.servlet;

import com.backigesta.dao.ContatoFuncionarioDao;
import com.backigesta.dao.FuncionariosDAO;
import com.backigesta.dao.TelefoneDao;
import com.backigesta.model.ContatoFuncionario;
import com.backigesta.model.Telefone;
import com.backigesta.util.Regex;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/selectContatoFuncionarios","/adicionarContatoFuncionarios","/alterarContatoFuncionarios","/deletarContatoFuncionarios"})
public class ContatoFuncionarios extends HttpServlet {

    ContatoFuncionarioDao contatoFuncionarioDao = new ContatoFuncionarioDao();
    FuncionariosDAO funcionariosDAO = new FuncionariosDAO();
    TelefoneDao telefoneDao = new TelefoneDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Capturando o caminho de como ele chegou no método doGet
        String caminho = request.getServletPath();

        // Direcionado o cliente a partir do caminho que chegou no servlet
        if (caminho.equals("/selectContatoFuncionarios")) {
            // Jogando ele no método para mostrar os selects do banco
            mostrarSelects(request,response);
        }
    }

    protected void mostrarSelects(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Capturando os parâmetros de searchNome e searchPhone
        String procuraNome = request.getParameter("searchName");
        String procuraTelefone = request.getParameter("searchPhone");

        // Criando o objeto ArrayList com a interface List
        List<ContatoFuncionario> lista;

        // Direcionado qual método de procura será usado
        if (procuraNome != null) {
            lista = contatoFuncionarioDao.buscarContatoFuncionarioPorNome(procuraNome);
        } else if (procuraTelefone != null) {
            lista = contatoFuncionarioDao.buscarContatoFuncionarioPorTelefone(Regex.formatarTelefone(procuraTelefone));
        } else {
            lista = contatoFuncionarioDao.buscarContatoFuncionario();
        }

        ArrayList<String> funcionarios = funcionariosDAO.buscarApenasNome();

        // Colocando o atributo lista no request
        request.setAttribute("contatos",lista);
        request.setAttribute("funcionarios",funcionarios);

        // Direcionado para onde quero mandar os atributos do request
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/employee-contact.jsp");

        // Enviando para a pagina
        rd.forward(request,response);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Capturando o caminho de como ele chegou no método doPost
        String caminho = request.getServletPath();

        // Direcionado o cliente apartir do caminho que chegou no servlet
        if (caminho.equals("/deletarContatoFuncionarios")) {
            // Jogando ele no método para deletar dados do banco
            deletarContato(request,response);
        } else if (caminho.equals("/alterarContatoFuncionarios")) {
            // Jogando ele no método para alterar dados do banco
            alterarContato(request,response);
        } else if (caminho.equals("/adicionarContatoFuncionarios")) {
            // Jogando ele no método para adicionar dados do banco
            adicionarContato(request,response);
        }
    }

    protected void deletarContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Capturando os parâmetros de condenaId que estam saindo de um formulario
        int id = Integer.parseInt(request.getParameter("contatoId"));

        // Usando o método da classe CondenasDao para deletar um registro
        boolean deletado = telefoneDao.deletarTelefone(id);

        // Colocando o atributo deletado no request
        request.setAttribute("deletado",deletado ? "true" : "false");

        // Jogando ele no método para mostrar os selects do banco
        mostrarSelects(request,response);
    }

    protected void alterarContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Capturando os parâmetros de condenaId, nomeCondena e tipo que estam saindo de um formulario
        int id = Integer.parseInt(request.getParameter("contatoId"));
        String contato = request.getParameter("contato");

        // Usando o método da classe CondenasDao para alterar os dados daquele registro
        boolean alterado = telefoneDao.atualizarTelefone(new Telefone(id,contato));

        // Colocando o atributo alterado no request
        request.setAttribute("alterado",alterado ? "true" : "false");

        // Jogando ele no método para mostrar os selects do banco
        mostrarSelects(request,response);
    }

    protected void adicionarContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Capturando os parâmetros de nomeCondena, tipo e descricao que estam saindo de um formulario
        String nomeContato = request.getParameter("nomeContato");
        String contato = request.getParameter("contato");

        // Usando o método da classe CondenasDao para adicioanar um registro
        boolean adicionado = telefoneDao.inserirTelefone(new Telefone(nomeContato,contato));

        // Colocando o atributo adicionado no request
        request.setAttribute("adicionado",adicionado ? "true" : "false");

        // Jogando ele no método para mostrar os selects do banco
        mostrarSelects(request,response);
    }

}
