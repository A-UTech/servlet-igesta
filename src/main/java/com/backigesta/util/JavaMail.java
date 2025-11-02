package com.backigesta.util;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

//Classe com métodos para envio de emails, com a API JavaMail.
public class JavaMail {

    //Buscando a senha de acesso ao email da AUTECH no .env
    private Dotenv dotenv = Dotenv.load();
    private String senha = dotenv.get("SENHA_EMAIL");

    //Método para enviar um pedido de suporte (Pagina de Contato)
    public boolean enviarEmailContato(String nome,String email, String mensagem) {
        // Configuração das propriedades do SMTP
        Properties props = new Properties();
        // Definindo as propriedades da conexão.
        props.put("mail.smtp.auth", "true"); //Ativa o recurso de autentificação
        props.put("mail.smtp.starttls.enable", "true"); //Muda conexão para uma protegida pelo protocolo TLS
        props.put("mail.smtp.host", "smtp.gmail.com"); //Define o servidor que envia o email
        props.put("mail.smtp.port", "587"); //Define a porta

        // Criando a Session com autentificação para se conectar com o e-mail
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("autech.inovacao@gmail.com",senha);
            }
        });
        session.setDebug(true);

        // Criando a mensagem que vai ser mandada pelo email
        try {
            //Declarando objeto Message que prepara o email.
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("autech.inovacao@gmail.com")); // Definindo o remetente.
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("autech.inovacao@gmail.com") //Definindo destinatário
            );
            //Definindo o titulo
            message.setSubject("Ajuda solicitada por: "+nome);
            //Definindo o corpo da menssagem
            String formatacao = "<body style='background-color: #2F3034; padding: 10px; border-radius: 10px; font-family: sans-serif;'><h1 style='color: #1A7B66;'>Email de contato: <span style='text-decoration: nome;'>"+email+"</span></h1><h3 style='color: #A1E1D3;'>"+mensagem+"<h3></body>";

            //Mudando a formatação para HTML
            message.setContent(formatacao, "text/html; charset=UTF-8");

            // Enviando mensagem
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            //Excessão base para todos os conflitos do JavaMail
            e.printStackTrace();
            return false;
        }
    }

    //Método para enviar o token de verificação (Logins)
    public boolean enviarToken(String email, String token) {
        // Configuração das propriedades SMTP
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true"); //Ativa o recurso de autentificação
        props.put("mail.smtp.starttls.enable", "true"); //Muda conexão para uma protegida pelo protocolo TLS
        props.put("mail.smtp.host", "smtp.gmail.com"); //Define o servidor que envia o email
        props.put("mail.smtp.port", "587"); //Define a porta

        // Criando a Session com autentificação para se conectar com o e-mail
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("autech.inovacao@gmail.com",senha);
            }
        });
        session.setDebug(true);

        // Criando a mensagem que vão ser mandadas pelo email
        try {
            //Declarando objeto Message que prepara o email.
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("autech.inovacao@gmail.com")); // Definindo o remetente.
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email) //Definindo destinatário
            );
            //Definindo o titulo
            message.setSubject("Seu código de verificação da conta IGesta");
            //Definindo o corpo da menssagem
            String formatacao =
                    "<body style='margin:0; padding:0; background-color:#141414; width:100%; min-height:100vh; font-family:Poppins, Arial, sans-serif; color:#FCFCFC; text-align:center;'>" +
                            "    <div style='display:inline-block; text-align:center;'>" +
                            "        <img src='https://i.postimg.cc/nz7wX3sX/logo-branca.png' alt='IGesta' style='max-width:300px; display:block; margin:30px auto;'>" +
                            "        <section style='width:100%; max-width:600px; margin:0 auto; display:block; text-align:center;'>" +
                            "            <div style='margin-bottom:20px;'>" +
                            "                <h1 style='font-weight:700; font-size:2rem; margin:0 0 10px 0;'>IGesta</h1>" +
                            "                <p style='font-size:1rem; color:#FCFCFC; margin:0 20px;'>Olá, recebemos uma solicitação para acessar sua conta. Use o código abaixo para concluir a verificação em 2 passos.</p>" +
                            "            </div>" +
                            "            <div style='margin-top:30px;'>" +
                            "                <h2 style='font-weight:700; font-size:1.3rem; color:#A1E1D3; margin-bottom:15px;'>Código</h2>" +
                            "                <p style='background-color:#A1E1D3; color:#141414; padding:20px 30px; border-radius:15px; font-weight:bold; letter-spacing:2px; display:inline-block; text-align:center; font-size: 20px;'>" + token + "</p>" +
                            "            </div>" +
                            "        </section>" +
                            "        <p style='font-size:0.8rem; color:#FCFCFC; margin-top:40px;'>© 2025 IGesta | Todos os direitos reservados</p>" +
                            "        <p style='font-size:0.8rem; color:#FCFCFC; margin-top:5px;'>Caso não foi solicitado, ignore este e-mail</p>" +
                            "    </div>" +
                            "</body>";

            //Mudando a formatação para HTML
            message.setContent(formatacao, "text/html; charset=UTF-8");

            // Enviando mensagem
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            //Excessão base para todos os conflitos do JavaMail
            e.printStackTrace();
            return false;
        }
    }

    //Método para enviar a proposta da empresa (Pagina de Proposta)
    public boolean enviarEmailProposta(String nome,String email, String cnpj, String mensagem) {
        // Configuração das propriedades SMTP
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true"); //Ativa o recurso de autentificação
        props.put("mail.smtp.starttls.enable", "true"); //Muda conexão para uma protegida pelo protocolo TLS
        props.put("mail.smtp.host", "smtp.gmail.com"); //Define o servidor que envia o email
        props.put("mail.smtp.port", "587"); //Define a porta

        // Criando a Session com autentificação para se conectar com o e-mail
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("autech.inovacao@gmail.com",senha);
            }
        });
        session.setDebug(true);

        // Criando a mensagem que vão ser mandadas pelo email
        try {
            //Declarando o objeto Message que prepara o email.
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("autech.inovacao@gmail.com")); //Definindo Remetente
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("autech.inovacao@gmail.com") //Definindo destinatário
            );
            //Definindo o titulo
            message.setSubject("Proposta solicitada por: "+nome);
            //Definindo o corpo da menssagem
            String formatacao = "<body style='background-color: #2F3034; padding: 10px; border-radius: 10px; font-family: sans-serif;'><h1 style='color: #1A7B66;'>Email de contato: <span style='text-decoration: nome;'>"+email+"</span></h1><h3 style='color: #A1E1D3;'>"+mensagem+"<h3><h2 style='color: #1A7B66;'>CNPJ da empresa: "+Regex.formatarCnpj(cnpj)+"</h2></body>";

            //Mudando a formatação para HTML
            message.setContent(formatacao, "text/html; charset=UTF-8");

            // Enviando mensagem
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            //Excessão base para todos os conflitos do JavaMail
            e.printStackTrace();
            return false;
        }
    }

}
