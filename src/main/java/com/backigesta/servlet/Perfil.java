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

//Servlet usada para a configuração do perifl, e suas ações.
@WebServlet(urlPatterns = {"/atualizarPerfil","/uploadFoto","/entrarPerfil"})
@MultipartConfig
public class Perfil extends HttpServlet {
    // Declarando os DAO's utilizados.
    AdminDAO daoAdmin = new AdminDAO();
    EmpresaDAO daoEmpresas = new EmpresaDAO();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Capturando o caminho utilizado para chegar no método doGet
        String caminho = request.getServletPath();
        if (caminho.equals("/entrarPerfil")) {
            //Método de acessar a pagina de perfil
            paginaVolta(request,response);
        }
    }

    //Método de acessar a pagina de perfil
    public void paginaVolta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Declarando o objeto da Session.
        HttpSession session = request.getSession(false);

        //Traçando a última pagina acessada antes de entrar no Perfil.
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
        //Enviando para o perfil.
        entrarPerfil(request, response);
    }
    protected void entrarPerfil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Redirecionando para a JSP do perfil.
        request.getRequestDispatcher("WEB-INF/view/perfil.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Capturando o caminho utilizado pra chegar no método doPost
        String caminho = request.getServletPath();
        // Direcionado o cliente a partir do caminho que chegou no servlet
        if (caminho.equals("/atualizarPerfil")) {
            atualizarPerfil(request,response);
        } else if (caminho.equals("/uploadFoto")) {
            uploadFoto(request,response);
        }
    }

    //Método para atualizar informações do perfil
    protected void atualizarPerfil(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        //Declarando o objeto da Session.
        HttpSession session = request.getSession(false);

        //Capturando os parâmetros a serem alterados
        String nome = request.getParameter("name");
        String email = request.getParameter("email");
        String senha = request.getParameter("password");

        //Capturando os objetos de Admin e Empresa da session.
        Admin user = (Admin) session.getAttribute("admin");
        Empresa empresa = (Empresa) session.getAttribute("empresa");
        //Caso houver admin.
        if(user != null) {
            //Alterando as informações do objeto
            user.setNome(nome);
            user.setEmail(email);
            user.setSenha(senha);
            //Atualizando no Banco
            daoAdmin.atualizar(user);
            //Atualizando na Session
            session.setAttribute("admin",user);
        }
        else{
            //Alterando as informações do objeto
            user.setNome(nome);
            user.setEmail(email);
            user.setSenha(senha);
            //Atualizando no Banco
            daoEmpresas.atualizar(empresa);
            //Atualizando na Session
            session.setAttribute("empresa",user);
        }

        //Após atualizar, retorna para a pagina de perfil
        entrarPerfil(request,response);
    }

    //Método de enviar uma nova foto
    protected void uploadFoto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Declarando a session.
        HttpSession session = request.getSession(false);

        //Buscando na session o usuario logado no momento com a classe Abstrata.
        Usuario user = (Usuario) session.getAttribute("admin");

        //Extraindo o nome da Classe do objeto.
        String tipo = user.getClass().getSimpleName();

        //Transformando o arquivo de imagem fornecido em um ByteArray
        Part filePart = request.getPart("foto");
        byte[] bytea = filePart.getInputStream().readAllBytes();

        //Atualizando o usuario no banco.
        switch (tipo) {
            case "Admin":
                new AdminDAO().atualizarFoto(user.getId(),bytea);
                break;
            case "Empresas":
                new EmpresaDAO().atualizarFoto(user.getId(),bytea);
        }
        //Atualizando o usuario na Session.
        user.setFoto(bytea);
        session.setAttribute("admin",user);

        //Voltando para a pagina de perfil.
        entrarPerfil(request,response);
    }
}
