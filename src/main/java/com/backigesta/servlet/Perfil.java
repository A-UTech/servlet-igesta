package com.backigesta.servlet;

import com.backigesta.dao.AdminDAO;
import com.backigesta.dao.EmpresasDAO;
import com.backigesta.model.Admin;
import com.backigesta.model.Empresas;
import com.backigesta.model.Usuarios;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(urlPatterns = {"/atualizarPerfil","/uploadFoto","/entrarPerfil"})
@MultipartConfig
public class Perfil extends HttpServlet {
    AdminDAO daoAdmin = new AdminDAO();
    EmpresasDAO daoEmpresas = new EmpresasDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String caminho = request.getServletPath();
        if (caminho.equals("/entrarPerfil")) {
            entrarPerfil(request,response);
        }
    }

    protected void entrarPerfil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/view/perfil.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String caminho = request.getServletPath();
        if (caminho.equals("/atualizarPerfil")) {
            atualizarPerfil(request,response);
        } else if (caminho.equals("/uploadFoto")) {
            uploadFoto(request,response);
        }
    }

    protected void atualizarPerfil(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        String nome = request.getParameter("name");
        String email = request.getParameter("email");
        String senha = request.getParameter("password");

        Usuarios user = (Admin) session.getAttribute("admin");
        if(user != null) {
            user.setNome(nome);
            user.setEmail(email);
            user.setSenha(senha);
            daoAdmin.atualizar(user);
            session.setAttribute("admin",user);
        }
        else{
            user = (Empresas) request.getSession().getAttribute("empresa");
            user.setNome(nome);
            user.setEmail(email);
            user.setSenha(senha);
            daoEmpresas.atualizar(user);
            session.setAttribute("empresa",user);
        }

        entrarPerfil(request,response);
    }

    protected void uploadFoto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        //Coletando os parâmetros para encontrar o Usuario especificado.
        Usuarios user = (Usuarios) session.getAttribute("admin");

        String tipo = user.getClass().getSimpleName();

        //Transformando o arquivo de imagem em um ByteArray
        Part filePart = request.getPart("foto");
        byte[] bytea = filePart.getInputStream().readAllBytes();

        //Atualizando o usuario.
        switch (tipo) {
            case "Admin":
                new AdminDAO().atualizarFoto(user.getId(),bytea);
                break;
            case "Empresas":
                new EmpresasDAO().atualizarFoto(user.getId(),bytea);
        }
        user.setFoto(bytea);
        session.setAttribute("admin",user);

        entrarPerfil(request,response);
    }
}
