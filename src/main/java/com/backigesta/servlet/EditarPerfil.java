package com.backigesta.servlet;

import com.backigesta.dao.AdminDAO;
import com.backigesta.dao.EmpresasDAO;
import com.backigesta.dao.FuncionariosDAO;
import com.backigesta.model.Funcionarios;
import com.backigesta.model.Usuarios;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/editarPerfis")
//Servlet que redireciona os usuarios para pagina de edição de perfil.
public class EditarPerfil extends HttpServlet {
    @Override
    //MUDAR PARA APENAS DOPOST DEPOIS!!!!
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tipo = request.getParameter("tipo");
        int id = Integer.parseInt(request.getParameter("id"));

        Usuarios user=null;
        if(tipo.equals("Admin")){
            AdminDAO dao = new AdminDAO();
            user = dao.selecionarPorId(id);
        }
        else if(tipo.equals("Empresas")){
            EmpresasDAO dao = new EmpresasDAO();
            user = dao.selecionarPorId(id);
        }

        if(user != null){
            request.setAttribute("usuario", user);
            request.getRequestDispatcher("WEB-INF/view/perfil.jsp").forward(request, response);
        }
        else{
            //REDIRECIONAR DEPOIS PARA UMA PAGINA DE ERRO!
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuarios user = (Usuarios) request.getAttribute("usuario");
        if(user != null){
            request.setAttribute("usuario", user);
            request.getRequestDispatcher("WEB-INF/view/perfil.jsp").forward(request, response);
        }
        else{
            //REDIRECIONAR DEPOIS PARA UMA PAGINA DE ERRO!
        }
    }
}