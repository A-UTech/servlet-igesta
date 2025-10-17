package com.backigesta.servlet;

import com.backigesta.dao.AdminDAO;
import com.backigesta.dao.DAO;
import com.backigesta.dao.EmpresasDAO;
import com.backigesta.dao.FuncionariosDAO;
import com.backigesta.model.Usuarios;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/atualizarPerfil")
public class AtualizarPerfil extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("name");
        String email = request.getParameter("email");
        String senha = request.getParameter("password");

        int id = Integer.parseInt(request.getParameter("id"));
        String tipo = request.getParameter("tipo");

        DAO dao = null;

        switch (tipo) {
            case "Admin": {
                dao = new AdminDAO();
            }
            break;
            case "Empresas": {
                dao = new EmpresasDAO();
            }
            default: {
                //Colocar pagina de erro
            }
            break;
        }
        Usuarios user = dao.selecionarPorId(id);
        user.setNome(nome);
        user.setEmail(email);
        user.setSenha(senha);

        if(dao.atualizar(user)){}
    }
}
