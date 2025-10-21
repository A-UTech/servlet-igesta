package com.backigesta.util;

import com.backigesta.model.Funcionarios;

public class Regex {

    public static String formatarTelefone(String telefone) {
        return telefone.replaceAll("[^0-9]","");
    }

    public static boolean pegarFuncionarioPorNome(Funcionarios funcionario, String nome) {
        return funcionario.getNome().matches(nome);
    }
}
