package com.backigesta.util;

import java.util.Random;

public class Token {
    public static String gerarToken() {
        Random rd = new Random();
        String token = "";
        for (int i = 0;i < 5;i++) {
            token += String.valueOf(rd.nextInt(10));
        }
        return token;
    }

    public static String AnonimizarEmail(String email) {
        int posicaoFinal = email.indexOf("@");
        String [] vetorEmail = email.split("");
        for (int i = 3;i < posicaoFinal;i++) {
            vetorEmail[i] = "*";
        }
        return String.join("",vetorEmail);
    }
}