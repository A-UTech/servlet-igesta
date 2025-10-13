<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="../assets/logos/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="../styles/forms.css">
    <title>IGesta</title>
</head>
<body>
    <section>
        <h1>Login de empresa</h1>
        <form action="loginEmpresa" method="post">
            <input type="email" name="email" placeholder="E-mail" required minlength="10" autocomplete="email" autofocus>
            <input type="password" name="password" placeholder="Senha" required minlength="6" autocomplete="current-password">
            <a href=""><button type="submit">Entrar</button></a>
        </form>
    </section>
    <div class="mens">
        <h2>Olá</h2>
        <p>seja bem vindo novamente!</p>
    </div>
    <div class="back">
        <a href="${pageContext.request.contextPath}/index.html">
            <img src="${pageContext.request.contextPath}/assets/icons/arrow-left.png">
            <p>Voltar</p>
        </a>
    </div>
</body>
</html>