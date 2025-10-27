package com.backigesta.servlet;

import com.backigesta.dao.AdminDAO;
import com.backigesta.dao.EmpresaDAO;
import com.backigesta.dao.FuncionarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//Servlet geral para

@WebServlet("/getFoto")
public class Foto extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String tipo = request.getParameter("tipo");

        byte[] foto = null;
        if(tipo.equals("Funcionarios")){
            FuncionarioDAO dao = new FuncionarioDAO();
            foto = dao.selecionarFotoPorId(id);
        }
        else if(tipo.equals("Admin")){
            AdminDAO dao = new AdminDAO();
            foto = dao.selecionarFotoPorId(id);
        }
        else if(tipo.equals("Empresas")){
            EmpresaDAO dao = new EmpresaDAO();
            foto = dao.selecionarFotoPorId(id);
        }

        response.setContentType("image/png");
        ServletOutputStream sos = response.getOutputStream();

        sos.write(foto);
        sos.close();
    }
}
