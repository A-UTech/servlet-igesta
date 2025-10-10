package com.backigesta.servlet;

import com.backigesta.dao.AdminDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// Feita por Artur :)
// Utilize desta classe para puxar diretamente a foto de um Admin do Banco.
// Na JSP, você deve referência-la com o Value da Servlet (neste caso 'admin-foto'),
// e inserir o parâmetro 'id' do Admin logo após em uma Scriptlet.
//
// Exemplo de como usa-la com a tag <img>:
//      <img src="admin-foto?id=<%=[variavel do id]%>">

@WebServlet("/admin-foto")
public class AdminFoto extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdminDAO dao = new AdminDAO();
        int id = Integer.parseInt(req.getParameter("id"));

        byte[] foto = dao.selecionarFotoPorId(id);
        resp.setContentType("image/svg");
        ServletOutputStream sos = resp.getOutputStream();
        sos.write(foto);
        sos.close();
    }
}
