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
        String email = (String) request.getAttribute("email");
        String senha = (String) request.getAttribute("senha");
    %>
</head>
<body>
    <section>
        <h1>Login de empresa</h1>
        <form action="loginEmpresa" method="post" id="loginEmpresa">
            <input type="email" name="email" placeholder="E-mail" required minlength="10" autocomplete="email" value="<%=email == null ? "" : email%>" autofocus>
            <div class="input-container">
                <input type="password" value="<%=senha%>" id="senha" name="password" placeholder="Senha" autocomplete="current-password" required pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[^a-zA-Z0-9])\S+$" title="A senha deve conter pelo menos uma letra maiúscula, uma letra minúscula, um número, um caractere especial e não pode conter espaços.">
                <img onclick="mudarOlho('senha','toggleSenha')" src="${pageContext.request.contextPath}/assets/icons/closed_eyes.png"
                     alt="mostrar senha"
                     class="eye-icon"
                     id="toggleSenha">
            </div>
            <button type="button" onclick="enviarFormulario('buttonSubmit','loginEmpresa')" id="buttonSubmit">Entrar</button>
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
    <script src="${pageContext.request.contextPath}/scripts/olhinhoInputs.js"></script>
    <script>
        <% if ("true".equals(loginCorreto)) { %>
            abrirPopupInformacoes("wrong.svg","Usuário ou senha incorreta","O email ou senha informado estão incorretos")
        <% } %>
    </script>
</body>
</html>