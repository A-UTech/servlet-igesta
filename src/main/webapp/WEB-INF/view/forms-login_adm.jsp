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
        String loginCorreto = (String) request.getAttribute("semConta");
    %>
</head>
<body>
    <section>
        <h1>Login de administrador</h1>
        <form action="loginAdmin" method="post" id="loginAdmin">
            <input type="email" name="email" placeholder="E-mail" required minlength="10" autocomplete="email" autofocus>
            <input type="password" name="password" placeholder="Senha" required min="6" autocomplete="current-password">
            <button type="button" onclick="enviarFormulario('buttonSubmit','loginAdmin')" id="buttonSubmit">Entrar</button>
        </form>
    </section>
    <div class="mens">
        <h2>Olá</h2>
        <p>seja bem vindo novamente!</p>
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
    <script src="${pageContext.request.contextPath}/scripts/mandarFormulario.js"></script>
    <script>
        <% if ("true".equals(loginCorreto)) { %>
            abrirPopupInformacoes("wrong.svg","Usuário ou senha incorreta","O email ou senha informado estão incorretos")
        <% } %>
    </script>
</body>
</html>