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

//Servlet usada para ações de entrar/cadastrar uma conta de empresa
@WebServlet(urlPatterns = {"/criarContaEmpresa", "/criarSenhaEmpresa","/entrarCadastroEmpresa"})
public class CadastroEmpresa extends HttpServlet {
    //Declarando os DAO's utilizados.
    EmpresaDAO empresaDAO = new EmpresaDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Capturando o caminho utilizado para chegar no método doGet
        String caminho = request.getServletPath();

        // Direcionando o cliente à um método a partir do caminho
        if (caminho.equals("/entrarCadastroEmpresa")) {
            entrarCadastroEmpresa(request,response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Capturando o caminho utilizado para chegar no método doPost
        String caminho = request.getServletPath();

        // Direcionando o cliente à um método a partir do caminho
        if (caminho.equals("/criarContaEmpresa")) {
            cadastrarEmpresa(request, response);
        } else if (caminho.equals("/criarSenhaEmpresa")) {
            criarSenha(request, response);
        } else if (caminho.equals("/entrarCadastroEmpresa")) {
            entrarCadastroEmpresa(request,response);
        }
    }

    // Método para logar em uma empresa
    protected void entrarCadastroEmpresa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String plano = request.getParameter("plano");
        if (plano != null) {
            session.setAttribute("plano", plano);
        }
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/formularios/forms-register.jsp");
        rd.forward(request,response);
    }

    // Método para definir a senha da conta Empresarial.
    protected void criarSenha(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Declarando o objeto da sessão.
        HttpSession session = request.getSession(false);

        // Capturando a senha dada no Formulario.
        String senha = request.getParameter("password");

        // Pegando o objeto Empresa salvo na sessão
        com.backigesta.model.Empresa empresa = (com.backigesta.model.Empresa) session.getAttribute("contaEmpresa");

        //Alterando a senha do objeto
        empresa.setSenha(senha);
        // Inserindo a objeto empresa no banco
        empresaDAO.inserir(empresa);
        request.setAttribute("email",empresa.getEmail());
        request.setAttribute("senha",empresa.getSenha());
        // limpando o objeto empresa da sessão
        session.removeAttribute("contaEmpresa");
        session.removeAttribute("plano");
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

        // define um objeto com os parâmetros passados.
        com.backigesta.model.Empresa empresa = new com.backigesta.model.Empresa(nome,email,cnpj,plano,estado,cidade,unidade);

        // Checando se o email e cnpj fornecidos existem no Banco?
        if (!empresaDAO.existeCnpjOrEmail(empresa.getEmail(),empresa.getCnpj())) {
            //Enviando o objeto de Empresa para a Session.
            request.getSession().setAttribute("contaEmpresa", empresa);
            response.sendRedirect(request.getContextPath() + "/htmls/forms-register_password.html");
        } else {
            //!!!!
            request.setAttribute("existeConta","true");
            RequestDispatcher rd = request.getRequestDispatcher("entrarCadastroEmpresa");
            rd.forward(request,response);
        }
    }
}