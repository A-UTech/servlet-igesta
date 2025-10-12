<%@ page session="true" %>
<%
  String email = (String) session.getAttribute("Administradorlogado");
  if (email == null) {
    // Se não estiver logado, redireciona para o login
    response.sendRedirect("loginAdm.html");
    return;
  }
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <title></title>
</head>
<body>
<h2>Bem-vindo, <%= email %>!</h2>
<p>Você está na sua área restrita.</p>

<form action="logout" method="post">
  <button type="submit">Sair</button>
</form>
</body>
</html>
