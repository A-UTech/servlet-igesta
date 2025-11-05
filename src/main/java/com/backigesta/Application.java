package com.backigesta;

//Essa classe Application é apenas para testar as classes.

import com.backigesta.dao.EmpresaDAO;

public class Application {
    public static void main(String[] args) {
        EmpresaDAO dao = new EmpresaDAO();
        System.out.println(dao.selecionarTodos());
    }
}
