package com.backigesta.servlet;

import com.backigesta.dao.AdminDAO;
import com.backigesta.dao.EmpresasDAO;
import com.backigesta.model.Admin;
import com.backigesta.model.Empresas;
import com.backigesta.util.JavaMail;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(urlPatterns = {"/token","/verificarToken"})
public class Token extends HttpServlet {

    JavaMail javaMail = new JavaMail();

    public void entrarToken(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/forms-login_token.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String caminho = request.getServletPath();
        if (caminho.equals("/token")) {
            gerarToken(request,response);
        } else if (caminho.equals("/verificarToken")) {
            verificarToken(request,response);
        }
    }

    protected void verificarToken(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String token = (String) session.getAttribute("token");
        String email = (String) session.getAttribute("email");
        AdminDAO adminDAO = new AdminDAO();
        EmpresasDAO empresasDAO = new EmpresasDAO();
        String verificao = request.getParameter("token1") + request.getParameter("token2") + request.getParameter("token3") + request.getParameter("token4") + request.getParameter("token5");
        if (verificao.equals(token)) {
            Admin admin = adminDAO.selecionarPorEmail(email);
            if (admin != null) {
                session.setAttribute("admin", admin);
            } else {
                Empresas empresa = empresasDAO.selecionarPorEmail(email);
                session.setAttribute("empresa",empresa);
            }
            response.sendRedirect("index.jsp");
        } else {
            request.setAttribute("erro","true");
            entrarToken(request,response);
        }
    }

    protected void gerarToken(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String email = (String) session.getAttribute("email");
        if (email == null) {
            email = request.getParameter("email");
            session.setAttribute("email", email);
        }

        String token = com.backigesta.util.Token.gerarToken();
        session.setAttribute("token",token);
        javaMail.enviarToken(email,token);
        request.setAttribute("email", com.backigesta.util.Token.AnonimizarEmail(email));
        entrarToken(request,response);
    }
}
