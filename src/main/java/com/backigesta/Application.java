package com.backigesta;

import com.backigesta.dao.AdminDAO;
import com.backigesta.dao.EmpresasDAO;
import com.backigesta.model.Admin;
import com.backigesta.model.Empresas;

import java.util.ArrayList;
import java.util.List;

//Essa classe Application é apenas para testar as classes.

public class Application {
    public static void main(String[] args) {
        EmpresasDAO dao = new EmpresasDAO();

        Empresas aeutech = new Empresas("A&UTech", "AeuechInovacao@gmail.com", "12345678910123", "autechinovacao");
//        Empresas igesta = new Empresas("Igesta", "igesta@org.br", "12345678912345", "123igestinhosforever");

        dao.inserir(aeutech);
//        dao.inserir(igesta);

//        Empresas igesta = dao.selecionarPorId(2);
//        igesta.setFoto("src/main/webapp/assets/logos/logo-branca.png");
//        dao.atualizar(igesta);
//        System.out.println(igesta);

        List<Empresas> empresas = dao.selecionarTodos();
        for(Empresas empresa : empresas) {
            System.out.println(empresa);
        }
    }
}
