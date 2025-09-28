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
        String status = (String) request.getAttribute("status");
    %>
</head>
<body>
    <section>
        <h1>Contato</h1>
        <form method="post" action="emailContato">
            <input type="email" name="email" class="inputs" id="iEmail" placeholder="E-Mail" required minlength="10" autocomplete="email" autofocus>
            <input type="text" name="nome" class="inputs" id="iName" placeholder="Nome" required>
            <textarea name="message" id="iMes" class="inputs" placeholder="Mensagem" required></textarea>
            <button class="buttons" type="submit">Enviar</button>
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
        <a href="${pageContext.request.contextPath}/index.jsp">
            <img src="${pageContext.request.contextPath}/assets/icons/arrow-left.png">
            <p>Voltar</p>
        </a>
    </div>
    <% if (status != null) { %>
        <div id="popupCheck">
            <a href="<%="true".equals(status) ? "index.jsp" : "#popupCheck"%>" class="overlay"></a>
            <div class="popup <%="true".equals(status) ? "check" : "wrong"%>">
                <img src="${pageContext.request.contextPath}/assets/icons/<%="true".equals(status) ? "check.svg" : "wrong.svg"%>">
                <p><%="true".equals(status) ? "Seu email foi enviado com sucesso!" : "Seu email não foi enviado, tente novamente mais tarde"%></p>
            </div>
        </div>
    <% } %>
</body>
</html>