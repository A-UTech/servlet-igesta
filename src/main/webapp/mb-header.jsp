
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="assets/logos/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="styles/mb-header.css">
    <title>IGesta</title>
</head>
<body>
<a href="index.jsp"><p>✕</p></a>
<section>
    <header>
        <a href="index.jsp"><img src="assets/logos/logo-branca.png"></a>
    </header>
    <main>
        <ul>
            <li><a href="index.jsp"><p>Início</p></a></li>
            <li><a href="index.jsp#slide06"><p>Empresa</p></a></li>
            <li><a href="index.jsp#slide05"><p>Mensalidades</p></a></li>
            <li><a onclick="opcoesEntrar()"><input type="button" value="Entrar"></a></li>
        </ul>
        <div class="overlay" id="popupOverlay">
            <a href="">
                <div>Entrar como admin</div>
            </a>
            <a href="">
                <div>Entrar como empresa</div>
            </a>
        </div>
    </main>
</section>
<script src="scripts/index.js"></script>
</body>
</html>
