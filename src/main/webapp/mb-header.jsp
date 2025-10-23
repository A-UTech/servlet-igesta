<%@ page import="com.backigesta.model.Admin" %>
<%@ page import="com.backigesta.model.Empresas" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="assets/logos/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="styles/mb-header.css">
    <title>IGesta</title>
    <%
        Empresas empresa = (Empresas) session.getAttribute("empresa");
        Admin admin = (Admin) session.getAttribute("admin");
    %>
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
            <% if (admin == null && empresa == null) { %>
                <li><a id="entrar" onclick="opcoesEntrar()"><button>Entrar</button></a></li>
            <% } else if (admin != null) { %>
                <li><a class="conta" onclick="opcoesEntrar()"><img src="<%= admin.getFoto() == null ? "assets/icons/aside-perfil.svg" : "getFoto?id=" + admin.getId() + "&tipo=Admin" %>"><%=admin.getNome()%></a></li>
            <% } else { %>
<<<<<<< HEAD
                <li><a class="conta" onclick="opcoesEntrar()"><img src="<%= empresa.getFoto() == null ? "assets/icons/aside-perfil.svg" : "getFoto?id=" + empresa.getId() + "&tipo=Empresas" %>"><%=empresa.getNome()%></a></li>
=======
                <li><a class="conta" onclick="opcoesEntrar()"><img src="<%= empresa.getFoto() == null ? "assets/icons/aside-perfil.svg" : "empresas-foto?id=" + empresa.getId() + "&tipo=Empresas" %>"><%=empresa.getNome()%></a></li>
>>>>>>> d850abced838118a24345f846131b597d8980f3f
            <% } %>
        </ul>
        <div class="overlay" id="popupOverlay">
            <% if (session.getAttribute("admin") == null && session.getAttribute("empresa") == null) { %>
            <a href="loginAdmin">
                <div>Entrar como admin</div>
            </a>
            <a href="loginEmpresa">
                <div>Entrar como empresa</div>
            </a>
            <% } else { %>
                <% if (session.getAttribute("admin") != null) { %>
                <a href="htmls/splash.html">
                    <div>Área restrita</div>
                </a>
                <% } else { %>
                <a href="">
                    <div>Ver lideres e gestores</div>
                </a>
                <% } %>
                <a href="logout">
                    <div>Sair</div>
                </a>
            <% } %>
        </div>
    </main>
</section>
<script src="scripts/index.js"></script>
</body>
</html>
