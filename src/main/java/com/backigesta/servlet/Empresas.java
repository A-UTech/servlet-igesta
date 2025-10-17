package com.backigesta.servlet;

import com.backigesta.dao.EmpresasDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/selectEmpresas", "/adicionarEmpresas"})
public class Empresas extends HttpServlet {
    EmpresasDAO daoEmpresas = new EmpresasDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getServletPath().equals("/selectEmpresas")){
            mostrarEmpresas(request, response);
        }
    }
    protected void mostrarEmpresas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String procura = request.getParameter("search");
        List<com.backigesta.model.Empresas> empresas;
        if (procura == null || procura.equals("todos")) {
            empresas = daoEmpresas.selecionarTodos();
        } else{
            empresas = daoEmpresas.selecionarPorNome(procura);
        }
        request.setAttribute("empresas", empresas);
        request.getRequestDispatcher("/WEB-INF/view/company.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getServletPath().equals("/adicionarEmpresas")){
            adicionarEmpresa(request, response);
        }
    }

    protected void adicionarEmpresa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //WIP
    }
}
