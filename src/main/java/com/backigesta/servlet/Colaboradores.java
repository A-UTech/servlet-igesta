package com.backigesta.servlet;

import com.backigesta.dao.EmpresasDAO;
import com.backigesta.dao.FuncionariosDAO;
import com.backigesta.model.Empresas;
import com.backigesta.model.Funcionarios;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

@WebServlet(urlPatterns = {"/selectCollab", "/alterarCollab", "/deletarCollab", "/adicionarCollab"})
public class Colaboradores extends HttpServlet {
    FuncionariosDAO daoFuncionarios = new FuncionariosDAO();
    EmpresasDAO daoEmpresas = new EmpresasDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getServletPath().equals("/selectCollab")) {
            mostrarSelects(request,response);
        }
    }

    protected void mostrarSelects(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String procura = request.getParameter("search");

        String cargo = request.getParameter("filter");

        int idEmpresa = ((Empresas) request.getSession().getAttribute("empresa")).getId();

        List<Funcionarios> funcionarios;
        if (procura != null) {
            funcionarios = daoFuncionarios.selecionarPorNome(procura, idEmpresa);
        } else if (cargo == null || cargo.equals("")) {
            funcionarios = daoFuncionarios.selecionarTodos(idEmpresa);
        } else {
            funcionarios = daoFuncionarios.selecionarPorCargo(cargo, idEmpresa);
        }

        String infoPlano = daoEmpresas.selecionarInformacoesPlano(idEmpresa);
        request.setAttribute("infoPlano", infoPlano);
        request.setAttribute("funcionarios", funcionarios);
        request.getRequestDispatcher("/WEB-INF/view/collaborators.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String caminho = request.getServletPath();
        if(caminho.equals("/adicionarCollab")) {
            adicionarColaborador(request,response);
        }
        else if(caminho.equals("/deletarCollab")) {
            deletarColaborador(request,response);
        }
        else if(caminho.equals("/alterarCollab")) {
            alterarColaborador(request,response);
        }
    }

    protected void adicionarColaborador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String cpf = request.getParameter("cpf");
        String senha = request.getParameter("senha");

        String[] cargo = request.getParameter("cargo").split(";");
        String nomeCargo = cargo[0];
        int idPermissao = Integer.parseInt(cargo[1]);

        String[] tempo = request.getParameter("turno").split(":");
        LocalTime turno = new Time(Integer.parseInt(tempo[0]), Integer.parseInt(tempo[1]), 0).toLocalTime();
        String nomeEmpresa = ((Empresas) request.getSession().getAttribute("empresa")).getNome();
        int idEmpresa = ((Empresas) request.getSession().getAttribute("empresa")).getId();

        boolean adicionado = daoFuncionarios.inserir(
                new Funcionarios(nome, email, cpf, senha, nomeEmpresa, nomeCargo, idPermissao, turno), idEmpresa
        );
        request.setAttribute("adicionado",adicionado ? "true" : "false");
        mostrarSelects(request,response);
    }

    protected void deletarColaborador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("idColaborador"));
        boolean deletado = daoFuncionarios.deletar(id);
        request.setAttribute("deletado",deletado ? "true" : "false");
        mostrarSelects(request,response);
    }

    protected void alterarColaborador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int idEmpresa = ((Empresas) request.getSession().getAttribute("empresa")).getId();

        Funcionarios funcionario = daoFuncionarios.selecionarPorId(id);

        String[] tempo = request.getParameter("turno").split(":");
        LocalTime turno = new Time(Integer.parseInt(tempo[0]), Integer.parseInt(tempo[1]), 0).toLocalTime();

        funcionario.setNome(request.getParameter("nome"));
        funcionario.setEmail(request.getParameter("email"));
        String[] cargo = request.getParameter("cargo").split("_");
        funcionario.setNomeCargo(cargo[0]);
        funcionario.setIdPermissoes(Integer.parseInt(cargo[1]));
        funcionario.setSenha(request.getParameter("senha"));
        funcionario.setTurno(turno);


        boolean alterado = daoFuncionarios.atualizar(funcionario, idEmpresa);
        request.setAttribute("alterado", alterado ? "true" : "false");
        mostrarSelects(request, response);
    }
}