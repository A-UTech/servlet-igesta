package com.backigesta.util;

public class AnonimizarDados {

    public static String AnonimizarEmail(String email) {
        int posicaoFinal = email.indexOf("@");
        String [] vetorEmail = email.split("");
        for (int i = 3;i < posicaoFinal;i++) {
            vetorEmail[i] = "*";
        }
        return String.join("",vetorEmail);
    }

}
