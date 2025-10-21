package com.backigesta.servlet;

import com.backigesta.dao.EmpresasDAO;
import com.backigesta.dao.PlanoDao;
import com.backigesta.model.Empresas;
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
    EmpresasDAO empresasDAO = new EmpresasDAO();
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
        String confSenha = request.getParameter("confirmPassword");

        // pega o id salvo na sessão
        int empresaId = (int) session.getAttribute("empresaId");

        if (empresasDAO.criaSenha(empresaId, senha, confSenha)) {
            Empresas empresa = empresasDAO.selecionarPorId(empresaId);
            request.setAttribute("email",empresa.getEmail());
            request.setAttribute("senha",empresa.getSenha());
            // limpa o id da sessão
            request.getSession(false).removeAttribute("empresaId");
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/forms-login_cmp.jsp");
            rd.forward(request,response);
        }
    }


    protected void cadastrarEmpresa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // pega os parâmetros do cadastro
        String plano = request.getParameter("plano");
        String nome = request.getParameter("name");
        String email = request.getParameter("email");
        String cnpj = request.getParameter("cnpj").replaceAll("[^0-9]", "");
        String unidade = request.getParameter("unitArea");
        String estado = request.getParameter("states");

        // define objeto que irá chamar os métodos
        int empresaId = empresasDAO.criaConta(new Empresas(nome,email,cnpj,plano,estado,unidade));

        if (empresaId != -1) {
            // guarda o id da empresa criada na sessão
            request.getSession().setAttribute("empresaId", empresaId);
            response.sendRedirect(request.getContextPath() + "/htmls/forms-register_password.html");
        }
    }
}
