<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/logos/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/forms.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/popups.css">
    <title>IGesta</title>
    <%
        String email = (String) request.getAttribute("email");
        String erro = (String) request.getAttribute("erro");
    %>
</head>
<body>
    <div class="mens">
        <h2>Estamos quase lá!</h2>
    </div>
    <section class="token">
        <div>
            <h1>Token</h1>
            <p>Um token de 5 dígitos foi enviado para o e-mail <%=email%></p>
        </div>
        <form action="verificarToken" method="post" autocomplete="off" class="token">
            <div>
                <input type="text" name="token1" required maxlength="1" pattern="[0-9]">
                <input type="text" name="token2" required maxlength="1" pattern="[0-9]">
                <input type="text" name="token3" required maxlength="1" pattern="[0-9]">
                <input type="text" name="token4" required maxlength="1" pattern="[0-9]">
                <input type="text" name="token5" required maxlength="1" pattern="[0-9]">
            </div>
            <button type="submit" id="buttonSubmit">Entrar</button>
        </form>
    </section>
    <div class="tokenBack">
        <a href="">
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
    <script src="${pageContext.request.contextPath}/scripts/token.js"></script>
    <script src="${pageContext.request.contextPath}/scripts/popupInformacoes.js"></script>
    <script>
        <% if (erro != null) { %>
            abrirPopupInformacoes("wrong.svg","Token incorreto","O token informado está incorreto. Um novo código foi enviado ao seu e-mail.")
        <% } %>
    </script>
</body>
</html>