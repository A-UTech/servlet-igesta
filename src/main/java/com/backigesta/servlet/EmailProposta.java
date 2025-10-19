package com.backigesta.servlet;

import com.backigesta.util.JavaMail;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/enviarProsposta")
public class EmailProposta extends HttpServlet {
    JavaMail javaMail = new JavaMail();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/forms-propose.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        enviarEmailProposta(request, response);
    }

    protected void enviarEmailProposta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("text");
        String email = request.getParameter("email");
        String cnpj = request.getParameter("cnpj");
        String menssagem = request.getParameter("message");

        boolean status = javaMail.enviarEmailProposta(nome, email, cnpj, menssagem);
        request.setAttribute("status", status ? "true" : "false");
        request.getRequestDispatcher("/WEB-INF/view/forms-propose.jsp").forward(request, response);
    }
}
