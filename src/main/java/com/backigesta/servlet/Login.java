package com.backigesta.servlet;

import com.backigesta.dao.AdminDAO;
import com.backigesta.dao.EmpresasDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/loginAdmin","/loginEmpresa"})
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String caminho = request.getServletPath();
        if (caminho.equals("/loginAdmin")) {
            mandarLoginAdmin(request,response);
        } else if (caminho.equals("/loginEmpresa")) {
            mandarLoginEmpresa(request,response);
        }
    }

    protected void mandarLoginAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/forms-login_adm.jsp");
        rd.forward(request,response);
    }

    protected void mandarLoginEmpresa(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/forms-login_cmp.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String caminho = request.getServletPath();
        if (caminho.equals("/loginAdmin")) {
            verificarLoginAdmin(request,response);
        } else if (caminho.equals("/loginEmpresa")) {
            verificarLoginEmpresa(request,response);
        }
    }

    protected void verificarLoginAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        AdminDAO adminDAO = new AdminDAO();
        String email = request.getParameter("email");
        String senha = request.getParameter("password");
        RequestDispatcher rd = null;
        if (adminDAO.verificaLoginAdmin(email,senha)) {
            rd = request.getRequestDispatcher("token");
            rd.forward(request,response);
        } else {
            request.setAttribute("semConta","Usuário ou senha inválidos");
            mandarLoginAdmin(request,response);
        }
    }

    protected void verificarLoginEmpresa(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        EmpresasDAO empresasDAO = new EmpresasDAO();
        String email = request.getParameter("email");
        String senha = request.getParameter("password");
        RequestDispatcher rd = null;
        if (empresasDAO.verificaLoginEmpresa(email,senha)) {
            rd = request.getRequestDispatcher("token");
            rd.forward(request,response);
        } else {
            request.setAttribute("semConta","Usuário ou senha inválidos");
            mandarLoginEmpresa(request,response);
        }
    }
}
