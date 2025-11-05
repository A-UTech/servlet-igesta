package com.backigesta.servlet;

import com.backigesta.dao.AdminDAO;
import com.backigesta.dao.EmpresaDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

//Servlet usada para as ações de login.
@WebServlet(urlPatterns = {"/loginAdmin","/loginEmpresa","/logout"})
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Capturando o caminho utilizado para chegar no método doGet
        String caminho = request.getServletPath();
        // Direcionado o cliente ao método correto apartir do caminho que chegou no servlet
        if (caminho.equals("/loginAdmin")) {
            mandarLoginAdmin(request,response);
        } else if (caminho.equals("/loginEmpresa")) {
            mandarLoginEmpresa(request,response);
        } else {
            logoutConta(request,response);
        }
    }

    //Sair da conta
    protected void logoutConta(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Obtém a sessão atual, se existir
        HttpSession session = request.getSession(false);

        // Se a sessão existir ela será apagada
        if (session != null) {
            session.invalidate();
        }
        // Redireciona para o index.jsp
        response.sendRedirect("index.jsp");
    }
    //Envia para a página de login dos Admins
    protected void mandarLoginAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/formularios/forms-login_adm.jsp");
        rd.forward(request,response);
    }

    //Envia para a pagina de login das Empresas
    protected void mandarLoginEmpresa(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/formularios/forms-login_cmp.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Capturando o caminho utilizado para chegar no método doPost
        String caminho = request.getServletPath();
        // Direcionado o cliente ao método correto apartir do caminho que chegou no servlet
        if (caminho.equals("/loginAdmin")) {
            verificarLoginAdmin(request,response);
        } else if (caminho.equals("/loginEmpresa")) {
            verificarLoginEmpresa(request,response);
        }
    }

    protected void verificarLoginAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //Declarando o DAO de admin;
        AdminDAO adminDAO = new AdminDAO();
        //Extraindo parametros do formulario de login
        String email = request.getParameter("email");
        String senha = request.getParameter("password");
        //??? ಠ_ಠ
        RequestDispatcher rd = null;
        //Checa caso a senha bate com o admin dono do email fornecido
        if (adminDAO.verificaLoginAdmin(email,senha)) { //batem
            //Redireciona para a página de verificação em duas etapas.
            rd = request.getRequestDispatcher("token");
            rd.forward(request,response);
        } else { //não batem
            //Retorna para a página de login, sinalizando que a conta não existe.
            request.setAttribute("semConta","true");
            mandarLoginAdmin(request,response);
        }
    }

    protected void verificarLoginEmpresa(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //Declarando o DAO de empresa;
        EmpresaDAO empresasDAO = new EmpresaDAO();
        //Extraindo parametros do formulario de login
        String email = request.getParameter("email");
        String senha = request.getParameter("password");
        RequestDispatcher rd = null;
        //Checa caso a senha bate com a empresa dono do email fornecido
        if (empresasDAO.verificaLoginEmpresa(email,senha)) { //batem
            //Redireciona para a página de verificação em duas etapas.
            rd = request.getRequestDispatcher("token");
            rd.forward(request,response);
        } else { //não batem
            //Retorna para a página de login, sinalizando que a conta não existe.
            email = (String) request.getAttribute("email");
            senha = (String) request.getAttribute("senha");
            if (email == null && senha == null) {
                request.setAttribute("semConta","true");
            }
            mandarLoginEmpresa(request,response);
        }
    }
}
