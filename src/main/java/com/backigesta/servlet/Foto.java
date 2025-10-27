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

//Servlet geral para extrair uma foto de uma conta Administrador ou Empresa
//Retorna uma imagem para ser usada na tag <img>
//Sintaxe de uso: /getFoto?id=<id>&tipo=<tipo>
@WebServlet("/getFoto")
public class Foto extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Capturando o id, e tipo de usuario.
        int id = Integer.parseInt(request.getParameter("id"));
        String tipo = request.getParameter("tipo");

        //ByteA da foto a ser extraida.
        byte[] foto = null;
        if(tipo.equals("Funcionarios")){
            //Extraindo da tabela de funcionarios
            FuncionarioDAO dao = new FuncionarioDAO();
            foto = dao.selecionarFotoPorId(id);
        }
        else if(tipo.equals("Admin")){
            //Extraindo da tabela de Administradores
            AdminDAO dao = new AdminDAO();
            foto = dao.selecionarFotoPorId(id);
        }
        else if(tipo.equals("Empresas")){
            //Extraindo da tabela de Empresas
            EmpresaDAO dao = new EmpresaDAO();
            foto = dao.selecionarFotoPorId(id);
        }

        //Alterando tipo do conteúdo para uma imagem png.
        response.setContentType("image/png");
        //Declarando a OutputStream, que vai reescrever a imagem com os bytes dela.
        ServletOutputStream sos = response.getOutputStream();

        //Reescrevendo a imagem.
        sos.write(foto);
        //Fechando a OutputStream.
        sos.close();
    }
}
