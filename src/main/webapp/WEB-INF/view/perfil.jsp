<%@ page import="com.backigesta.model.Usuario" %>
<%@ page import="com.backigesta.model.Admin" %>
<%@ page import="com.backigesta.model.Empresa" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/perfil.css">
    <title>Perfil</title>
    <%
        if (session.getAttribute("admin") == null && session.getAttribute("empresa") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        String caminhoVolta = (String) session.getAttribute("caminhoVolta");
        Admin admin = (Admin) session.getAttribute("admin");
        Empresa empresa = (Empresa) session.getAttribute("empresa");
        String tipo = "Empresa";
        Usuario user = empresa;
        if (admin != null) {
            tipo = "Admin";
            user = admin;
        }
    %>
</head>
<body>
    <div class="mens">
        <form action="uploadFoto" name="trocarFoto" method="post" enctype="multipart/form-data">
            <div>
                <% if (user.getFoto() == null) { %>
                <img src="${pageContext.request.contextPath}/assets/icons/aside-perfil.svg">
                <% } else { %>
                <img id="perfilPhoto" src="getFoto?id=<%=user.getId()%>&tipo=<%=tipo%>">
                <% } %>
                <input type="file" id="foto" name="foto" accept="image/jpeg, image/png" style="display: none">
                <label for="foto"><img id="cameraPhoto" src="${pageContext.request.contextPath}/assets/icons/camera.png"></label>
            </div>
            <input type="hidden" name="id" value="<%=user.getId()%>">
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
        <a href="<%=caminhoVolta%>">
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
                <div class="input-container">
                    <input type="password" id="senha" name="password" placeholder="Senha" autocomplete="current-password" value="<%=user.getSenha()%>" required pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[^a-zA-Z0-9])\S+$" title="A senha deve conter pelo menos uma letra maiúscula, uma letra minúscula, um número, um caractere especial e não pode conter espaços.">
                    <img onclick="mudarOlho('senha','toggleSenha')" src="${pageContext.request.contextPath}/assets/icons/closed_eyes.png"
                         alt="mostrar senha"
                         class="eye-icon"
                         id="toggleSenha">
                </div>
            </div>
            <input type="hidden" name="id" value="<%=user.getId()%>">
            <input type="hidden" name="tipo" value="<%=tipo%>">
            <button type="button" id="buttonPerfil" onclick="enviarFormulario('buttonPerfil','changePsswd')">Atualizar</button>
        </form>
    </section>
    <script src="${pageContext.request.contextPath}/scripts/mandarFormulario.js"></script>
    <script src="${pageContext.request.contextPath}/scripts/olhinhoInputs.js"></script>
</body>
</html>