package com.backigesta.servlet;

import com.backigesta.dao.AdminDAO;
import com.backigesta.dao.DAO;
import com.backigesta.dao.EmpresasDAO;
import com.backigesta.dao.FuncionariosDAO;
import com.backigesta.model.Admin;
import com.backigesta.model.Empresas;
import com.backigesta.model.Funcionarios;
import com.backigesta.model.Usuarios;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;

@WebServlet(urlPatterns = {"/atualizarPerfil","/uploadFoto","/entrarPerfil"})
public class Perfil extends HttpServlet {


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

        entrarPerfil(request,response);
    }

    protected void uploadFoto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Coletando os parâmetros para encontrar o Usuario especificado.
        int id = Integer.parseInt(request.getParameter("id"));
        String tipo = request.getParameter("tipo");

        Usuarios user = null;

        //Transformando o arquivo de imagem em um ByteArray

        Part filePart = request.getPart("foto");
        byte[] bytea = filePart.getInputStream().readAllBytes();

        //Encontrando o tipo de usuario
        switch (tipo) {
            case "Admin": {
                AdminDAO dao = new AdminDAO();
                user = dao.selecionarPorId(id);
                user.setFoto(bytea);
                dao.atualizar((Admin) user);
                break;
            }
            case "Empresas": {
                EmpresasDAO dao = new EmpresasDAO();
                user = dao.selecionarPorId(id);
                user.setFoto(bytea);
                dao.atualizar((Empresas) user);
                break;
            }
            case "Funcionarios": {
                FuncionariosDAO dao = new FuncionariosDAO();
                user = dao.selecionarPorId(id);
                user.setFoto(bytea);
                dao.atualizar((Funcionarios) user);
                break;
            }
        }
        request.setAttribute("usuario", user);
        request.getRequestDispatcher("/editarPerfis").forward(request, response);
    }
}
