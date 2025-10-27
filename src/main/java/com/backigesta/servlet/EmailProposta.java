package com.backigesta.servlet;

import com.backigesta.util.JavaMail;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//Servlet usada no envio de uma proposta de plano à nossa empresa.
@WebServlet(urlPatterns = "/enviarProsposta")
public class EmailProposta extends HttpServlet {
    //Declarando um objeto JavaMail, (para enviar um email para nós mesmos)
    JavaMail javaMail = new JavaMail();

    @Override
    //Caso chamado por doGet, apenas envia o usuario para a página de propostas.
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/forms-propose.jsp").forward(request, response);
    }

    @Override
    //Caso chamado por doPost (formulario), envia o email de acordo com informações passadas.
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        enviarEmailProposta(request, response);
    }

    protected void enviarEmailProposta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Coletando os parâmetros do formulario para enviar a menssagem.
        String nome = request.getParameter("text");
        String email = request.getParameter("email");
        String cnpj = request.getParameter("cnpj");
        String menssagem = request.getParameter("message");

        //Enviando o email, com todos os parâmetros passados.
        boolean status = javaMail.enviarEmailProposta(nome, email, cnpj, menssagem);
        //Retornando se a ação teve sucesso.
        request.setAttribute("status", status ? "true" : "false");
        //Voltando para a pagina de propostas
        request.getRequestDispatcher("/WEB-INF/view/forms-propose.jsp").forward(request, response);
    }
}
