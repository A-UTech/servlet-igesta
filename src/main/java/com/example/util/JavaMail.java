package com.example.util;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class JavaMail {

    public static boolean enviarEmail(String nome,String email, String mensagem) {
        Dotenv dotenv = Dotenv.load();
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        "autech.inovacao@gmail.com",dotenv.get("SENHA_EMAIL")
                );
            }
        });
        session.setDebug(true);
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("autech.inovacao@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("autech.inovacao@gmail.com")
            );
            message.setSubject("Ajuda solicitada por: "+nome);
            message.setText("E-mail de contato: "+email+"\n"+mensagem);
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

}
