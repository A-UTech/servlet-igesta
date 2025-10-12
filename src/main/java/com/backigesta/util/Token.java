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
}
