package com.example;

import com.example.model.*;

import java.time.LocalTime;
//Essa classe Application é apenas para testar as classes.

public class Application {
    public static void main(String[] args) {
        Usuarios user = new Admin();
        Admin adm = new Admin("Artur", "Silva", "aoasilva12200@hotmail.com", "12banana34", 1);
        Empresas empresa = new Empresas("IGesta", "igesta.inovacao@gmail.com", "1122334455", "12342", 3);
        LocalTime turno = LocalTime.now();
        Funcionarios funcionarios = new Funcionarios("Artur", "Alves", "aoasilva12@gmail.com", "50315923890", "12toSemIdeia34", 1, 3, 2, 3, turno);
        Cargo lider = new Cargo(1, "lider", "Lider das linhas da Area Quente");

        System.out.println(user);
        System.out.println(adm);
        System.out.println(empresa);
        System.out.println(funcionarios);
        System.out.println(lider);
    }
}