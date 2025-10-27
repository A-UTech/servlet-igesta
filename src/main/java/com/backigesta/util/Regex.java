package com.backigesta.util;

import com.backigesta.model.Funcionario;

public class Regex {

    public static String extrairNumero(String telefone) {
        return telefone.replaceAll("[^0-9]","");
    }

    public static boolean pegarFuncionarioPorNome(Funcionario funcionario, String nome) {
        return funcionario.getNome().matches(nome);
    }

    public static String formatarCnpj(String cnpj) {
        String formatdo = cnpj.substring(0,2) + "." + cnpj.substring(2,5) + "." + cnpj.substring(5,8) + "/" + cnpj.substring(8,11) + "-" + cnpj.substring(11,13);
        return formatdo;
    }

    public static String formatarTelefone(String telefone) {
        String formato = "(" +telefone.substring(0,2) + ") " + telefone.substring(2,7) + "-" + telefone.substring(7,11);
        return formato;
    }

    public static String formatarCpf(String cpf) {
        String formato = cpf.substring(0,3) + "." + cpf.substring(3,6) + "." + cpf.substring(6,9) + "-" + cpf.substring(9,11);
        return formato;
    }
}
