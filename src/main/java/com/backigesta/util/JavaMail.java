package com.backigesta.util;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class JavaMail {

    private Dotenv dotenv = Dotenv.configure().directory("/").filename(".env").load();
    private String senha = dotenv.get("SENHA_EMAIL");

    public boolean enviarEmailContato(String nome,String email, String mensagem) {
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

    public boolean enviarToken(String email, String token) {
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
                    InternetAddress.parse(email)
            );
            message.setSubject("Seu código de verificação da conta IGesta");
            String formatacao = "<head>" +
                    "    <meta charset='utf-8' />" +
                    "    <meta name='viewport' content='width=device-width,initial-scale=1' />" +
                    "    <title>Verificação em 2 passos - IGesta</title>" +
                    "  </head>" +
                    "  <body style='margin:0; padding:0; background-color:#F4F6F8; font-family:-apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif; color:#333333;'>" +
                    "    <table role='presentation' width='100%' cellpadding='0' cellspacing='0' style='background-color:#F4F6F8; width:100%; padding:24px 0;'>" +
                    "      <tr>" +
                    "        <td align='center'" +
                    "          <table role='presentation' width='600' cellpadding='0' cellspacing='0' style='background-color:#FFFFFF; border-radius:8px; overflow:hidden; box-shadow:0 2px 6px rgba(0,0,0,0.1);'>" +
                    "            <tr>" +
                    "              <td align='center' style='background-color:#1A7B66; color:#FCFCFC; padding:24px;'>" +
                    "                <h1 style='margin:0; font-size:22px;'>IGesta</h1>" +
                    "                <div style='font-size:14px; color:#A1E1D3;'>Verificação em 2 passos</div>" +
                    "              </td>" +
                    "            </tr>" +
                    "            <tr>" +
                    "              <td style='padding:28px; color:#2F3034; background-color:#FFFFFF;'>" +
                    "                <p style='margin:0 0 16px 0; font-size:16px;'>Olá,</p>" +
                    "                <p style='margin:0 0 22px 0; font-size:15px; line-height:1.6;'>" +
                    "                  Recebemos uma solicitação para acessar sua conta. Use o código abaixo para concluir a verificação em 2 passos." +
                    "                </p>" +
                    "                <div style='text-align:center; margin:20px 0;'>" +
                    "                  <div style='display:inline-block; background-color:#A1E1D3; color:#2F3034; font-weight:600; font-size:22px; letter-spacing:5px; padding:14px 28px; border-radius:8px;'>" + token +
                    "                  </div>" +
                    "                </div>" +
                    "                <p style='margin:20px 0 0 0; font-size:13px; color:#555;'>" +
                    "                  Se você não solicitou esse código, ignore este e-mail. Ninguém poderá acessar sua conta sem esse código." +
                    "                </p>" +
                    "              </td>" +
                    "            </tr>" +
                    "            <tr>" +
                    "              <td align='center' style='background-color:#A1E1D3; padding:16px; color:#2F3034; font-size:12px;'>" +
                    "                © <span id='year'>2025</span> IGesta — Todos os direitos reservados" +
                    "              </td>" +
                    "            </tr>" +
                    "          </table>" +
                    "        </td>" +
                    "      </tr>" +
                    "    </table>" +
                    "    <script>" +
                    "      try {" +
                    "        document.getElementById('year').textContent = new Date().getFullYear();" +
                    "      } catch(e){}" +
                    "    </script>" +
                    "  </body>";

            message.setContent(formatacao, "text/html; charset=UTF-8");

            // Enviando mensagem
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean enviarEmailProposta(String nome,String email, String cnpj, String mensagem) {
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
            message.setSubject("Proposta solicitada por: "+nome);
            String formatacao = "<body style='background-color: #2F3034; padding: 10px; border-radius: 10px; font-family: sans-serif;'><h1 style='color: #1A7B66;'>Email de contato: <span style='text-decoration: nome;'>"+email+"</span></h1><h3 style='color: #A1E1D3;'>"+mensagem+"<h3><h2 style='color: #1A7B66;'>CNPJ da empresa: "+cnpj+"</h2></body>";

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
