package com.backigesta.util;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class JavaMail {

    private Dotenv dotenv = Dotenv.load();
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
            message.setText("E-mail de contato: "+email+"\n"+mensagem);

            // Enviando mensagem
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

}
