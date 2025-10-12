package com.backigesta.servlet;

import com.backigesta.util.AnonimizarDados;
import com.backigesta.util.JavaMail;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(urlPatterns = {"/token","/verificarToken"})
public class Token extends HttpServlet {

    String token = "";
    String email = "";
    JavaMail javaMail = new JavaMail();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String caminho = request.getServletPath();
        if (caminho.equals("/token")) {
            gerarToken(request,response);
        }
    }

    protected void gerarToken(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        email = "lucaslbr1a2b@gmail.com"; // Arrumar essa parte para pegar o email qeu está vindo da pagina da bia
        token = com.backigesta.util.Token.gerarToken();
        javaMail.enviarToken(email,token);
        request.setAttribute("email", AnonimizarDados.AnonimizarEmail(email));
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/forms-login_token.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String caminho = request.getServletPath();
        if (caminho.equals("/token")) {
            verificarToken(request,response);
        }
    }

    protected void verificarToken(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String verificao = request.getParameter("token1") + request.getParameter("token2") + request.getParameter("token3") + request.getParameter("token4") + request.getParameter("token5");
        if (token.equals(verificao)) {

        } else {
            request.setAttribute("status","false");
            gerarToken(request,response);
        }
    }
}
