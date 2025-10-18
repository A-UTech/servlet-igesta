package com.backigesta.servlet;

import com.backigesta.dao.EmpresasDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/criarContaEmpresa", "/criarSenhaEmpresa","/entrarCadastroEmpresa"})
public class CadastroEmpresa extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String caminho = request.getServletPath();
        if (caminho.equals("/criarContaEmpresa")) {
            cadastrarEmpresa(request, response);
        } else if (caminho.equals("/criarSenhaEmpresa")) {
            criarSenha(request, response);
        } else if (caminho.equals("/entrarCadastroEmpresa")) {
            entrarCadastroEmpresa(request,response);
        }
    }

    protected void entrarCadastroEmpresa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String plano = request.getParameter("plano");


    }

    protected void criarSenha(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String senha = request.getParameter("password");
        String confSenha = request.getParameter("confirmPassword");

        // pega o id salvo na sessão
        Integer idEmpresa = (Integer) request.getSession().getAttribute("idEmpresa");

        if (idEmpresa == null) {
            response.sendRedirect(request.getContextPath() + "/htmls/erro.html");
            System.out.println("Erro na sessão");
            return;
        }

        EmpresasDAO empresasDAO = new EmpresasDAO();
        if (empresasDAO.criaSenha(idEmpresa, senha, confSenha)) {
            // limpa o id da sessão
            request.getSession().removeAttribute("idEmpresa");
            response.sendRedirect("loginEmpresa.html");
        } else {
            response.sendRedirect(request.getContextPath() + "/htmls/erro.html");
            System.out.println("Erro na senha");
        }
    }


    protected void cadastrarEmpresa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // pega os parâmetros do cadastro
        String nome = request.getParameter("name");
        String email = request.getParameter("email");
        String cnpj = request.getParameter("cnpj").replaceAll("[^0-9]", "");
        String unidade = request.getParameter("unitArea");
        String estado = request.getParameter("states");

        // define objeto que irá chamar os métodos
        EmpresasDAO empresasDAO = new EmpresasDAO();
        Integer idEmpresa = empresasDAO.criaConta(nome, email, cnpj, unidade, estado);

        if (idEmpresa != null) {
            // guarda o id da empresa criada na sessão
            request.getSession().setAttribute("idEmpresa", idEmpresa);
            response.sendRedirect(request.getContextPath() + "htmls/forms-register_password.html");
        }
    }
}
