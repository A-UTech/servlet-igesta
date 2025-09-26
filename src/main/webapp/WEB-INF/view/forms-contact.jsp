<!DOCTYPE html>
<html lang="pt-br">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="../../assets/logos/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/forms.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/popups.css">
    <title>IGesta</title>
    <%
        String chegou = (String) request.getAttribute("chegou");
    %>
</head>
<body>
    <section>
        <h1>Contato</h1>
        <form method="post" action="emailContato">
            <input type="email" name="email" class="inputs" id="iEmail" placeholder="E-Mail" required minlength="10" autocomplete="email" autofocus>
            <input type="text" name="nome" class="inputs" id="iName" placeholder="Nome" required>
            <textarea name="message" id="iMes" class="inputs" placeholder="Mensagem" required></textarea>
            <button class="buttons" type="submit">Entrar</button>
        </form>
    </section>
    <div class="mens">
        <div>
            <h2>Olá</h2>
            <p>seja bem vindo!</p>
        </div>
        <div>
            <p><a href="mailto:aeutech.inovacao@gmail.com">aeutech.inovacao@gmail.com</a></p>
            <p>+55 (11) 99925-1812</p>
            <p>+55 (11) 97130-0174</p>
        </div>
    </div>
    <div class="back">
        <a href="../../index.html">
            <img src="${pageContext.request.contextPath}/assets/icons/arrow-left.png">
            <p>Voltar</p>
        </a>
    </div>
    <% if ("true".equals(chegou)) { %>
        <div id="popupCheck">
            <a href="${pageContext.request.contextPath}/index.jsp" class="overlay"></a>
            <div class="popup check">
                <img src="${pageContext.request.contextPath}/assets/icons/check.svg">
                <p>Seu email foi enviado com sucesso!</p>
            </div>
        </div>
    <% } else if ("false".equals(chegou)) { %>
        <div id="popupCheck">
            <a href="#popupCheck" class="overlay"></a>
            <div class="popup wrong">
                <img src="${pageContext.request.contextPath}/assets/icons/wrong.svg">
                <p>Seu email não foi enviado, tente novamente mais tarde</p>
            </div>
        </div>
    <% } %>
</body>
</html>