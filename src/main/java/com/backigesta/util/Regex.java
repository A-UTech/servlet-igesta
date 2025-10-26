package com.backigesta.util;

import com.backigesta.model.Funcionarios;

public class Regex {

    public static String extrairNumero(String telefone) {
        return telefone.replaceAll("[^0-9]","");
    }

    public static boolean pegarFuncionarioPorNome(Funcionarios funcionario, String nome) {
        return funcionario.getNome().matches(nome);
    }

    public static String formatarCnpj(String cnpj) {
        String formatdo = cnpj.substring(0,2) + "." + cnpj.substring(2,5) + "." + cnpj.substring(5,8) + "/" + cnpj.substring(8,11) + "-" + cnpj.substring(11,13);
        return formatdo;
    }
}
