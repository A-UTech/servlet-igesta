package com.backigesta.servlet;

import com.backigesta.dao.FuncionariosDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// Feita por Artur :)
// Utilize desta classe para puxar diretamente a foto de uma Funcionario do Banco.
// Na JSP, você deve referência-la com o Value da Servlet (neste caso 'funcionarios-foto'),
// e inserir o parâmetro 'id' deste Funcionario logo após em uma Scriptlet.
//
// Exemplo de como usa-la com a tag <img>:
//      <img src="funcionario-foto?id=<%=[variavel do id]%>">

@WebServlet("/funcionarios-foto")
public class FuncionariosFoto extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FuncionariosDAO dao = new FuncionariosDAO();
        int id = Integer.parseInt(req.getParameter("id"));

        byte[] foto = dao.selecionarFotoPorId(id);
        resp.setContentType("image/png");
        ServletOutputStream sos = resp.getOutputStream();
        sos.write(foto);
        sos.close();
    }
}
