<%@ page import="com.backigesta.model.Usuarios" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/perfil.css">
    <title>Perfil</title>
    <%
        Usuarios user = (Usuarios) request.getAttribute("usuario");
        int id = user.getId();
        String tipo = user.getClass().getSimpleName();
    %>
</head>
<body>
    <div class="mens">

        <img src="getFoto?id=<%=id%>&tipo=<%=tipo%>">
        <form action="uploadFoto" name="trocarFoto" method="post" enctype="multipart/form-data">
            <input type="file" id="foto" name="foto" accept="image/jpeg, image/png" style="display: none">
            <label for="foto"><img src="${pageContext.request.contextPath}/assets/icons/camera.png"></label>
            <input type="hidden" name="id" value="<%=id%>">
            <input type="hidden" name="tipo" value="<%=tipo%>">
        </form>
        <script>
            const imageInput = document.getElementById("foto");

            imageInput.addEventListener('change', function (event) {
                const file = event.target.files[0];
                if (file) {
                    document.trocarFoto.submit();
                }
            });
        </script>
    </div>

    <section>
        <a href="">
            <img src="${pageContext.request.contextPath}/assets/icons/arrow-right.svg">
            <span>Voltar</span>
        </a>
        <h1>Perfil</h1>
        <form action="atualizarPerfil" method="post" id="changePsswd">
            <div>
                <p>Nome:</p>
                <input type="text" name="name" required autofocus title="Nome" value="<%=user.getNome()%>">
            </div>
            
            <div>
                <p>E-mail:</p>
                <input type="email" name="email" required minlength="10" autocomplete="email" title="Email" value="<%=user.getEmail()%>">
            </div>

            <div>
                <p>Senha:</p>
                <input type="password" name="password" required minlength="8" autocomplete="current-password" title="Senha" value="<%=user.getSenha()%>">
            </div>
            <input type="hidden" name="id" value="<%=id%>">
            <input type="hidden" name="tipo" value="<%=tipo%>">
            <button type="submit">Trocar senha</button>
        </form>
    </section>
</body>
</html>