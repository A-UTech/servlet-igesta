<%@ page session="true" %>
<%
  String email = (String) session.getAttribute("EmpresaLogada");
  if (email == null) {
    // Se não estiver logado, redireciona para o login
    response.sendRedirect("loginEmp.html");
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
<p>Você está na área da empresa.</p>

<form action="logout" method="post">
  <button type="submit">Sair</button>
</form>
</body>
</html>
