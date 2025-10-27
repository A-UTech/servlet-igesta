package com.backigesta.util;

import com.backigesta.model.Funcionario;

//Classe com métodos para manipulação das expressões regulares usadas nas paginas.
public class Regex {

    //Extrair só os números de uma String de telefone.
    public static String extrairNumero(String telefone) {
        return telefone.replaceAll("[^0-9]","");
    }

    //Checa se um nome, existe no nome de um objeto Funcionario.
    public static boolean pegarFuncionarioPorNome(Funcionario funcionario, String nome) {
        return funcionario.getNome().matches(nome);
    }

    //Extrair só os números de uma String de CNPJ
    public static String formatarCnpj(String cnpj) {
        String formatdo = cnpj.substring(0,2) + "." + cnpj.substring(2,5) + "." + cnpj.substring(5,8) + "/" + cnpj.substring(8,11) + "-" + cnpj.substring(11,13);
        return formatdo;
    }

    //Retorna o telefone no formato tradicional: (XX) XXXXX-XXXX
    public static String formatarTelefone(String telefone) {
        String formato = "(" +telefone.substring(0,2) + ") " + telefone.substring(2,7) + "-" + telefone.substring(7,11);
        return formato;
    }

    //Retorna o CPF ao formato tradicional: XXX.XXX.XXX-XX
    public static String formatarCpf(String cpf) {
        String formato = cpf.substring(0,3) + "." + cpf.substring(3,6) + "." + cpf.substring(6,9) + "-" + cpf.substring(9,11);
        return formato;
    }
}
