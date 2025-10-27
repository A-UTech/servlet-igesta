package com.backigesta.servlet;

import com.backigesta.dao.AdminDAO;
import com.backigesta.dao.EmpresaDAO;
import com.backigesta.model.Admin;
import com.backigesta.model.Empresa;
import com.backigesta.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(urlPatterns = {"/atualizarPerfil","/uploadFoto","/entrarPerfil"})
@MultipartConfig
public class Perfil extends HttpServlet {
    AdminDAO daoAdmin = new AdminDAO();
    EmpresaDAO daoEmpresas = new EmpresaDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String caminho = request.getServletPath();
        if (caminho.equals("/entrarPerfil")) {
            paginaVolta(request,response);
        }
    }

    public void paginaVolta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String pagina = request.getHeader("referer");
        pagina = pagina.substring(pagina.lastIndexOf("/") + 1);
        System.out.println(pagina);
        if (pagina.toLowerCase().matches(".*condena.*") ) {
            session.setAttribute("caminhoVolta","selectCondena");
        } else if (pagina.toLowerCase().matches(".*admin.*")) {
            session.setAttribute("caminhoVolta","selectAdmin");
        } else if (pagina.toLowerCase().matches(".*contato.*")) {
            session.setAttribute("caminhoVolta","selectContato");
        } else if (pagina.toLowerCase().matches(".*empresa.*")) {
            session.setAttribute("caminhoVolta","selectEmpresa");
        } else if (pagina.toLowerCase().matches(".*plano.*")) {
            session.setAttribute("caminhoVolta","selectPlano");
        }
        entrarPerfil(request, response);
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

        Admin user = (Admin) session.getAttribute("admin");
        Empresa empresa = (Empresa) session.getAttribute("empresa");
        if(user != null) {
            user.setNome(nome);
            user.setEmail(email);
            user.setSenha(senha);
            daoAdmin.atualizar(user);
            session.setAttribute("admin",user);
        }
        else{
            user.setNome(nome);
            user.setEmail(email);
            user.setSenha(senha);
            daoEmpresas.atualizar(empresa);
            session.setAttribute("empresa",user);
        }

        entrarPerfil(request,response);
    }

    protected void uploadFoto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        //Coletando os parâmetros para encontrar o Usuario especificado.
        Usuario user = (Usuario) session.getAttribute("admin");

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
                new EmpresaDAO().atualizarFoto(user.getId(),bytea);
        }
        user.setFoto(bytea);
        session.setAttribute("admin",user);

        entrarPerfil(request,response);
    }
}
