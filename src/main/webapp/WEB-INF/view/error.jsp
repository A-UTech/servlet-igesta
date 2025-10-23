<!DOCTYPE html>
<html lang="pt-br">
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/error.css">
    <title>Error</title>
    <%
        Integer erroCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String tipoExcecao = null;
        if (exception != null) {
            tipoExcecao = exception.getClass().getSimpleName();
        }
    %>
</head>
<body>
    <img src="${pageContext.request.contextPath}/assets/logos/igesta-outlined.svg">
    <div class="errorContainer">
        <h1>Ocorreu um erro</h1>
        <% if (tipoExcecao.equals("NullPointerException")) { %>
            <p>Você está sem conexão com a internet. Por favor, verifique sua rede.</p>
        <% } else if (tipoExcecao.equals("ClassNotFoundException")) { %>
            <p>Erro interno no sistema. Entre em contato com o suporte.</p>
        <% } else if (tipoExcecao.equals("IllegalArgumentException")) { %>
            <p>Algum dado informado está inválido. Verifique e tente novamente.</p>
        <% } else if (erroCode != null && erroCode == 404) { %>
            <p>A página solicitada não foi encontrada.</p>
        <% } else if (erroCode != null && erroCode == 500) { %>
            <p>Ocorreu um problema interno no servidor.</p>
        <% } else { %>
            <p>Tente novamente mais tarde.</p>
        <% } %>
    </div>
    <a href="${pageContext.request.contextPath}/index.jsp"><p>🏠︎</p><p>Voltar ao início</p></a>
</body>
</html>