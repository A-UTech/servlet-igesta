package com.backigesta.servlet;

import com.backigesta.dao.EmpresaDAO;
import com.backigesta.dao.FuncionarioDAO;
import com.backigesta.dao.TelefoneDao;
import com.backigesta.model.Empresa;
import com.backigesta.model.Funcionario;
import com.backigesta.model.Telefone;
import com.backigesta.util.Regex;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//Servlet usada para as ações do CRUD de Colaboradores, da area da empresa.
@WebServlet(urlPatterns = {"/selectCollab", "/alterarCollab", "/deletarCollab", "/adicionarCollab", "/adicionarContatoCollab", "/alterarContatoCollab", "/deletarContatoCollab"})
public class Colaborador extends HttpServlet {
    //Definindo os DAOs que são utilizados nos métodos.
    FuncionarioDAO daoFuncionarios = new FuncionarioDAO();
    EmpresaDAO daoEmpresas = new EmpresaDAO();
    TelefoneDao daoTelefones = new TelefoneDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Redirecionando do Get, para o método mostrarSelect, apartir do caminho da Servlet.
        if(request.getServletPath().equals("/selectCollab")) {
            mostrarSelects(request,response);
        }
    }

    //Função que retorna Hashmap de todos os funcionarios, com seus contatos
    protected void mostrarSelects(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Declarando a session
        HttpSession session = request.getSession(false);

        //Buscando os parâmetros de filtragem (Procura por nome / Tipo de Cargo)
        String procura = request.getParameter("search");
        String cargo = request.getParameter("filter");

        //Buscando o Id da empresa em sessão.
        int idEmpresa = ((Empresa) session.getAttribute("empresa")).getId();

        //Criando e preenchendo
        List<Funcionario> funcionarios;
        if (procura != null) {
            //Caso HOUVE pesquisa por nome:
            funcionarios = daoFuncionarios.selecionarPorNome(procura, idEmpresa);
        } else if (cargo == null || cargo.equals("")) {
            //Caso não houve o uso de filtros
            funcionarios = daoFuncionarios.selecionarTodos(idEmpresa);
        } else {
            //Caso HOUVE filtragem por cargo.
            funcionarios = daoFuncionarios.selecionarPorCargo(cargo, idEmpresa);
        }

        if ("lider".equalsIgnoreCase(cargo)) {
            request.setAttribute("selecionado","lider");
        } else if ("gestor".equalsIgnoreCase(cargo)) {
            request.setAttribute("selecionado","gestor");
        } else {
            request.setAttribute("selecionado","todos");
        }

        //Definindo um HashMap, que carrega o objeto do funcionario, mais uma ArrayList de seus telefones registrados.
        HashMap<Funcionario, ArrayList<Telefone>> funcMap = new HashMap<>();
        for (Funcionario func : funcionarios) {
            funcMap.put(func, daoTelefones.selecionarPorIdFuncionario(func.getId()));
        }

        //Selecionando todos os funcionarios com telefone.
        ArrayList<Funcionario> funcionariosComTelefone = daoFuncionarios.selecionarTodosComTelefone();

        //Buscando informações do Plano atual da empresa
        String infoPlano = daoEmpresas.selecionarInformacoesPlano(idEmpresa);

        //Setando todos os Atributos para a Request.
        request.setAttribute("comTelefone", funcionariosComTelefone);
        request.setAttribute("infoPlano", infoPlano);
        request.setAttribute("funcionarios", funcMap);

        //Enviando para JSP
        request.getRequestDispatcher("/WEB-INF/view/collaborators.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Buscando os Endpoints vindos de Formularios, e redirecionando para os respectivos métodos.
        String caminho = request.getServletPath();
        if(caminho.equals("/adicionarCollab")) {
            //Método de adicionar registro de colaborador
            adicionarColaborador(request,response);
        }
        else if(caminho.equals("/deletarCollab")) {
            //Método de deletar registro de colaborador
            deletarColaborador(request,response);
        }
        else if(caminho.equals("/alterarCollab")) {
            //Método de alterar um registro de colaborador
            alterarColaborador(request,response);
        }
        else if(caminho.equals("/adicionarContatoCollab")) {
            //Método de adicionar contato ao colaborador
            adicionarContatoEmpresa(request,response);
        }
        else if(caminho.equals("/alterarContatoCollab")) {
            //Método de alterar contato de um colaborador
            alterarContatoEmpresa(request,response);
        }
        else if(caminho.equals("/deletarContatoCollab")) {
            //Método de deletar contato de um colaborador
            deletarContatoEmpresa(request,response);
        }
    }

    //Método para inserir um colaborador ao banco.
    protected void adicionarColaborador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Pegando os valores dados no formulario.
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String cpf = request.getParameter("cpf");
        String senha = request.getParameter("senha");

        //O input de Cargo, retorna uma concatenação do nome e id de cargo, separados por ";"
        String[] cargo = request.getParameter("cargo").split(";");
        String nomeCargo = cargo[0];
        int idPermissao = Integer.parseInt(cargo[1]);

        //Transformando o input de tempo para LocalTime.
        String[] tempo = request.getParameter("turno").split(":");
        LocalTime turno = new Time(Integer.parseInt(tempo[0]), Integer.parseInt(tempo[1]), 0).toLocalTime();
        //Buscando as informações da empresa pelo objeto na Session.
        String nomeEmpresa = ((Empresa) request.getSession().getAttribute("empresa")).getNome();
        int idEmpresa = ((Empresa) request.getSession().getAttribute("empresa")).getId();

        //Inserindo a classe ao banco com seu DAO
        boolean adicionado = daoFuncionarios.inserir(
                new Funcionario(nome, email, cpf, senha, nomeEmpresa, nomeCargo, idPermissao, turno), idEmpresa
        );
        //O Atributo "adicionado" servirá para mostrar o Popup de confirmação de ação na Area Restrita e da Empresa.
        request.setAttribute("adicionado",adicionado ? "true" : "false");
        mostrarSelects(request,response);
    }

    //Método para deletar um registro de colaborador do banco.
    protected void deletarColaborador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Buscando o respectivo Id do Funcionario, dado no botão do Popup de confirmação.
        int id = Integer.parseInt(request.getParameter("idColaborador"));
        //Executado a ação.
        boolean deletado = daoFuncionarios.deletar(id);
        //O Atributo "deletado" servirá para mostrar o Popup de confirmação de ação na Area Restrita e da Empresa.
        request.setAttribute("deletado",deletado ? "true" : "false");
        //Voltando para mostrarSelects.
        mostrarSelects(request,response);
    }

    //Método para alterar um registro já existente no banco, de um colaborador.
    protected void alterarColaborador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Buscando o id do funcionario e da empresa.
        int id = Integer.parseInt(request.getParameter("id"));
        int idEmpresa = ((Empresa) request.getSession().getAttribute("empresa")).getId();

        //Buscando o objeto do funcionario no Banco a partir do DAO.
        Funcionario funcionario = daoFuncionarios.selecionarPorId(id);

        //Transformando o Value de Tempo em um LocalTime
        String[] tempo = request.getParameter("turno").split(":");
        LocalTime turno = new Time(Integer.parseInt(tempo[0]), Integer.parseInt(tempo[1]), 0).toLocalTime();

        //Alterando todos os atributos com os parâmetros passados no formulario.
        funcionario.setNome(request.getParameter("nome"));
        funcionario.setEmail(request.getParameter("email"));
        //O input de Cargo, retorna uma concatenação do nome e id de cargo, dessa separados por "_"
        String[] cargo = request.getParameter("cargo").split("_");
        funcionario.setNomeCargo(cargo[0]);
        funcionario.setIdPermissoes(Integer.parseInt(cargo[1]));
        funcionario.setSenha(request.getParameter("senha"));
        funcionario.setTurno(turno);

        //Atualizando Funcionario
        boolean alterado = daoFuncionarios.atualizar(funcionario, idEmpresa);
        //O Atributo "alterado" servirá para mostrar o Popup de confirmação de ação na Area Restrita e da Empresa.
        request.setAttribute("alterado", alterado ? "true" : "false");
        //Retornando para mostrarSelects.
        mostrarSelects(request, response);
    }

    protected void adicionarContatoEmpresa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Capturando os parâmetros de nomeCondena, tipo e descricao que estam saindo de um formulario
        int id = Integer.parseInt(request.getParameter("funcionarioId"));
        String contato = Regex.extrairNumero(request.getParameter("contato"));

        // Usando o método da classe CondenasDao para adicioanar um registro
        boolean adicionado = daoTelefones.inserir(new Telefone(contato,id));

        // Colocando o atributo adicionado no request
        request.setAttribute("adicionado",adicionado ? "true" : "false");

        // Jogando ele no método para mostrar os selects do banco
        mostrarSelects(request,response);
    }
    protected void deletarContatoEmpresa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Capturando os parâmetros que estam saindo de um formulario
        int id = Integer.parseInt(request.getParameter("contatoId"));

        // Usando o método da classe telefoneDao para deletar um registro
        boolean deletado = daoTelefones.deletar(id);

        // Colocando o atributo deletado no request
        request.setAttribute("deletado",deletado ? "true" : "false");

        // Jogando ele no método para mostrar os selects do banco
        mostrarSelects(request,response);
    }

    protected void alterarContatoEmpresa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Capturando os parâmetros de condenaId, nomeCondena e tipo que estam saindo de um formulario
        int id = Integer.parseInt(request.getParameter("idTelefone"));
        String contato = Regex.extrairNumero(request.getParameter("telefone"));

        // Usando o método da classe CondenasDao para alterar os dados daquele registro
        boolean alterado = daoTelefones.atualizar(new Telefone(id,contato));

        // Colocando o atributo alterado no request
        request.setAttribute("alterado",alterado ? "true" : "false");

        // Jogando ele no método para mostrar os selects do banco
        mostrarSelects(request,response);
    }


}