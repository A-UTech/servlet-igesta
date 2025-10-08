package com.backigesta.util;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class JavaMail {

    private Dotenv dotenv = Dotenv.configure().directory("/").filename(".env").load();
    private String senha = dotenv.get("SENHA_EMAIL");

    public boolean enviarEmail(String nome,String email, String mensagem) {
        // Configuração das propriedades SMTP
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Criando a Session com autenticação para se conectar com o e-mail
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("autech.inovacao@gmail.com",senha);
            }
        });
        session.setDebug(true);

        // Criando a mensagem que vão ser mandadas pelo email
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("autech.inovacao@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("autech.inovacao@gmail.com")
            );
            message.setSubject("Ajuda solicitada por: "+nome);
            String formatacao = "<body style='background-color: #2F3034; padding: 10px; border-radius: 10px; font-family: sans-serif;'><h1 style='color: #1A7B66;'>Email de contato: <span style='text-decoration: nome;'>"+email+"</span></h1><h3 style='color: #A1E1D3;'>"+mensagem+"<h3></body>";

            message.setContent(formatacao, "text/html; charset=UTF-8");

            // Enviando mensagem
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

}
