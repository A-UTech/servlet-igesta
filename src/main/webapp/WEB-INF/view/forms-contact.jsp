<!DOCTYPE html>
<html lang="pt-br">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="../../assets/logos/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/popups.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/forms.css">
    <title>IGesta</title>
    <%
        String status = (String) request.getAttribute("status");
    %>
</head>
<body>
    <section>
        <h1>Contato</h1>
        <form method="post" action="emailContato">
            <input type="email" name="email" class="inputs" placeholder="E-Mail" required minlength="10" autocomplete="email" autofocus>
            <input type="text" name="nome" class="inputs" placeholder="Nome" required>
            <textarea name="message" class="textarea" placeholder="Mensagem" required></textarea>
            <button id="buttonSubmit" type="submit">Enviar</button>
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
    <div class="overlay" id="popupOverlay">
        <div class="popup">
            <div class="icon">
                <img id="icon" src="${pageContext.request.contextPath}/assets/icons/" alt="">
            </div>
            <h2 id="title"></h2>
            <p id="text"></p>
            <button onclick="fecharPopupInformacoes()">Ok</button>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/scripts/popupInformacoes.js"></script>
    <script>
        <% if (status != null) { %>
            <%
                boolean isStatus = Boolean.parseBoolean(status);
            %>
            abrirPopupInformacoes("<%=isStatus ? "check.svg" : "wrong.svg"%>","<%=isStatus ? "Mensagem enviada!" : "Erro no envio"%>","<%=isStatus ? "Seu e-mail foi enviado com sucesso. Em breve entraremos em contato." : "Não foi possível enviar sua mensagem. Tente novamente mais tarde."%>")
        <% } %>
    </script>
</body>
</html>