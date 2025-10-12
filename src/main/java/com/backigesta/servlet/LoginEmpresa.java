package com.backigesta.servlet;

import com.backigesta.dao.AdminDAO;
import com.backigesta.dao.EmpresasDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/forms-login_cmp", "/forms-login_adm"})
public class LoginEmpresa extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        // Captura qual URL chamou o servlet
        String path = request.getServletPath();

        try {
            if ("/forms-login_cmp".equals(path)) {
                // ----- LOGIN DA EMPRESA -----
                EmpresasDAO emp = new EmpresasDAO();

                if (emp.verificaLoginEmp(email, senha)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("Empresalogada", email);
                    // Usando o contexto do projeto para evitar erros de caminho
                    response.sendRedirect(request.getContextPath() + "/jsps/areaEmpresa.jsp");
                } else {
                    response.sendRedirect(request.getContextPath() + "/htmls/erroEmp.html");
                }

            } else if ("/forms-login_adm".equals(path)) {
                // ----- LOGIN DO ADMIN -----
                AdminDAO adm = new AdminDAO();

                if (adm.verificaLoginAdm(email, senha)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("Administradorlogado", email);
                    response.sendRedirect(request.getContextPath() + "/jsps/areaRestrita.jsp");
                } else {
                    response.sendRedirect(request.getContextPath() + "/htmls/erroAdm.html");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/htmls/erroBanco.html");
            System.out.println("!! Exception ao fazer login !!");
        }
    }
}
