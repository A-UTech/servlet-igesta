package com.backigesta;

import com.backigesta.dao.AdminDAO;
import com.backigesta.dao.EmpresasDAO;
import com.backigesta.dao.FuncionariosDAO;
import com.backigesta.model.Admin;
import com.backigesta.model.Empresas;
import com.backigesta.model.Funcionarios;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

//Essa classe Application é apenas para testar as classes.

public class Application {
    public static void main(String[] args) {
        Funcionarios func = new Funcionarios(1, "nome", "nome", "sobre", "email", "cpf", 1, 1, 1, LocalTime.now());
        Admin adm = new Admin(1, "Artur", "Silva2", "aoasilva@gmail", "123");
        Empresas emp = new Empresas(1, "Igesta", "da Silva4", "aoasilva@gmail", "123");

        EmpresasDAO empDAO = new EmpresasDAO();
        FuncionariosDAO funcDAO = new FuncionariosDAO();
        AdminDAO admDAO = new AdminDAO();
    }

}