package com.backigesta.util;

import java.util.Random;

//Classe usada para a paágina de Verificação de login, e envio de token.
public class Token {
    //Gera um token aleatório
    public static String gerarToken() {
        //Declarando o objeto random, e a String de retorno
        Random rd = new Random();
        String token = "";
        //Concatenando 5 numeros aleatorios de 0-9 em token
        for (int i = 0;i < 5;i++) {
            token += String.valueOf(rd.nextInt(10));
        }
        //Retornando o token
        return token;
    }

    //Anonimiza o email que o token foi enviado
    public static String AnonimizarEmail(String email) {
        //Marcando o nome do email
        int posicaoFinal = email.indexOf("@");
        String [] vetorEmail = email.split("");
        //Substituindo todos os caracteres a partir do quarto, por "*".
        for (int i = 3;i < posicaoFinal;i++) {
            vetorEmail[i] = "*";
        }
        //Retorna o email "escondido".
        return String.join("",vetorEmail);
    }
}