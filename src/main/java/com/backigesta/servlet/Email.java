package com.backigesta.servlet;

import com.backigesta.util.JavaMail;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(urlPatterns = {"/emailContato"})
public class Email extends HttpServlet {
    JavaMail javaMail = new JavaMail();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Pegando o caminho de onde minha requisição está vindo
        String caminho = request.getServletPath();
        if (caminho.equals("/emailContato")) {
            // Enviando cliente para a pagina forms-contact.jsp
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/forms-contact.jsp");
            rd.forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Pegando o caminho de onde minha requisição está vindo
        String caminho = request.getServletPath();
        if (caminho.equals("/emailContato")) {
            // Método para mandar email de suporte para a empresa
            mandarEmailContato(request,response);
        }
    }

    protected void mandarEmailContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Pegando 3 parâmetros que estão vindo de um forms na minha pagian forms-contact.jsp
        String email = request.getParameter("email");
        String nome = request.getParameter("nome");
        String mensagem = request.getParameter("message");

        // Usando método da classe JavaMail
        boolean status = javaMail.enviarEmail(nome,email,mensagem);

        // Preparando resposta que será retornada para a pagína forms-contact.jsp
        request.setAttribute("status",status ? "true" : "false");

        // Enviando cliente para a pagina forms-contact.jsp
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/forms-contact.jsp");
        rd.forward(request,response);
    }
}
