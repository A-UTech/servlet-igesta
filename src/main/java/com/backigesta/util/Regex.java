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
        String formatdo = cnpj.replaceAll("([0-9]{2})([0-9]{3})([0-9]{3})([0-9]{3})([0-9]{2})","$1.$2.$3/$4-$5");
        return formatdo;
    }

    //Retorna o telefone no formato tradicional: (XX) XXXXX-XXXX
    public static String formatarTelefone(String telefone) {
        String formato = telefone.replaceAll("([0-9]{2})([0-9]{5})([0-9]{4})","($1) $2-$3");
        return formato;
    }

    //Retorna o CPF ao formato tradicional: XXX.XXX.XXX-XX
    public static String formatarCpf(String cpf) {
        String formato = cpf.replaceAll("([0-9]{3})([0-9]{3})([0-9]{3})([0-9]{2})","$1.$2.$3-$4");
        return formato;
    }
}
