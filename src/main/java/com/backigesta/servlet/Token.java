package com.backigesta.servlet;

import com.backigesta.dao.AdminDAO;
import com.backigesta.dao.EmpresaDAO;
import com.backigesta.model.Admin;
import com.backigesta.model.Empresa;
import com.backigesta.util.JavaMail;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

//Servlet usada para envio e checagem da Verificação em 2 fatores
@WebServlet(urlPatterns = {"/token","/verificarToken"})
public class Token extends HttpServlet {

    //Declarando um objeto JavaMail
    JavaMail javaMail = new JavaMail();

    // Método que dá acesso a pagina de enserir o código
    public void entrarToken(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/formulario/forms-login_token.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Capturando o caminho utilizado para chegar no método doGet
        String caminho = request.getServletPath();
        // Direcionado o cliente a partir do caminho que chegou no servlet
        if (caminho.equals("/token")) {
            gerarToken(request,response);
        } else if (caminho.equals("/verificarToken")) {
            verificarToken(request,response);
        }
    }

    //Método para verificar o código fornecido com o enviado.
    protected void verificarToken(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Declarando o objeto da sessão
        HttpSession session = request.getSession(false);
        //Extraindo o token e email da session
        String token = (String) session.getAttribute("token");
        String email = (String) session.getAttribute("email");
        //Declarando os DAO's de Admin e Empresa
        AdminDAO adminDAO = new AdminDAO();
        EmpresaDAO empresasDAO = new EmpresaDAO();
        //Extraindo código fornecido
        String verificao = request.getParameter("token1") + request.getParameter("token2") + request.getParameter("token3") + request.getParameter("token4") + request.getParameter("token5");
        // Checando se os códigos batem.
        if (verificao.equals(token)) { //batem
            //Atribuindo a conta do email à session
            Admin admin = adminDAO.selecionarPorEmail(email);
            if (admin != null) {
                session.setAttribute("admin", admin);
            } else {
                Empresa empresa = empresasDAO.selecionarPorEmail(email);
                session.setAttribute("empresa",empresa);
            }
            response.sendRedirect("index.jsp");
        } else { //não batem
            //Volta para a página de tokens, sinalizando que houve erro.
            request.setAttribute("erro","true");
            entrarToken(request,response);
        }
    }

    //Método que envia um token para verificação de login.
    protected void gerarToken(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Declarando um objeto da Session
        HttpSession session = request.getSession(true);
        //Extraindo o email da Session.
        String email = (String) session.getAttribute("email");
        //Caso não houver email
        if (email == null) {
            //Pegar email do parâmetro passado no login e definindo na Session
            email = request.getParameter("email");
            session.setAttribute("email", email);
        }

        //Gerando o token, guardando ele na Session e enviando para o email fornecido
        String token = com.backigesta.util.Token.gerarToken();
        session.setAttribute("token",token);
        javaMail.enviarToken(email,token);
        //Anonimizando o email da Session.
        request.setAttribute("email", com.backigesta.util.Token.AnonimizarEmail(email));
        //Redirecionando para pagina de Verificação
        entrarToken(request,response);
    }
}
