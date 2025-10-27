package com.backigesta.servlet;

import com.backigesta.dao.EmpresaDAO;
import com.backigesta.util.Regex;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = {"/criarContaEmpresa", "/criarSenhaEmpresa","/entrarCadastroEmpresa"})
public class CadastroEmpresa extends HttpServlet {
    EmpresaDAO empresaDAO = new EmpresaDAO();
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
        request.setAttribute("plano",plano);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/forms-register.jsp");
        rd.forward(request,response);
    }

    protected void criarSenha(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String senha = request.getParameter("password");


        // pega o id salvo na sessão
        com.backigesta.model.Empresa empresa = (com.backigesta.model.Empresa) session.getAttribute("contaEmpresa");

        empresa.setSenha(senha);
        empresaDAO.inserir(empresa);
        request.setAttribute("email",empresa.getEmail());
        request.setAttribute("senha",empresa.getSenha());
        // limpa a empresa da sessão
        request.getSession(false).removeAttribute("contaEmpresa");
        RequestDispatcher rd = request.getRequestDispatcher("loginEmpresa");
        rd.forward(request,response);
    }


    protected void cadastrarEmpresa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // pega os parâmetros do cadastro
        String plano = request.getParameter("plano");
        String nome = request.getParameter("name");
        String email = request.getParameter("email");
        String cnpj = Regex.extrairNumero(request.getParameter("cnpj"));
        String unidade = request.getParameter("unitArea");
        String cidade = request.getParameter("cidade");
        String estado = request.getParameter("states");

        // define objeto que irá chamar os métodos
        com.backigesta.model.Empresa empresa = new com.backigesta.model.Empresa(nome,email,cnpj,plano,estado,cidade,unidade);

        if (!empresaDAO.existeCnpjOrEmail(empresa.getEmail(),empresa.getCnpj())) {
            request.getSession().setAttribute("contaEmpresa", empresa);
            response.sendRedirect(request.getContextPath() + "/htmls/forms-register_password.html");
        } else {
            request.setAttribute("existeConta","true");
            RequestDispatcher rd = request.getRequestDispatcher("entrarCadastroEmpresa");
            rd.forward(request,response);
        }
    }
}
