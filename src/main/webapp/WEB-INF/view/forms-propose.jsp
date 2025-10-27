<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/logos/favicon.ico" type="image/x-icon">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/forms.css">
  <title>IGesta</title>
  <%
    String status = (String) request.getAttribute("status");
  %>
</head>
<body>
<section>
  <h1>Proposta</h1>
  <form action="enviarProsposta" method="post" id="enviarProposta">
    <input type="text" name="name" placeholder="Nome" required autocomplete="name" autofocus>
    <input type="email" name="email" placeholder="E-Mail" required autocomplete="email" minlength="10" >
    <input type="text" name="cnpj" placeholder="CNPJ" required minlength="14" maxlength="14" pattern="^[0-9]{14}$" title="Digite apenas os números">
    <textarea name="message" placeholder="Mensagem" required></textarea>
    <button type="button" id="buttonProposta" onclick="enviarFormulario('buttonProposta','enviarProposta')">Entrar</button>
  </form>
</section>
<div class="mens">
  <div>
    <h2>Olá</h2>
    <p>seja bem vindo!</p>
  </div>
  <div>
    <p style="width: 300px;">Aqui, você poderá nos apresentar uma proposta para o valor da mensalidade e armazenamento</p>
  </div>
</div>
<div class="back">
  <a href="${pageContext.request.contextPath}/index.html">
    <img src="${pageContext.request.contextPath}/assets/icons/arrow-left.png">
    <p>Voltar</p>
  </a>
</div>
  <script src="${pageContext.request.contextPath}/scripts/popupInformacoes.js"></script>
  <script src="${pageContext.request.contextPath}/scripts/mandarFormulario.js"></script>
  <script>
    <%
    if (status != null) {
        boolean isStatus = Boolean.parseBoolean(status);
    %>
    abrirPopupInformacoes("<%=isStatus ? "check.svg" : "wrong.svg"%>","<%=isStatus ? "Proposta enviada!" : "Erro no envio"%>","<%=isStatus ? "Sua proposta foi enviado com sucesso. Em breve entraremos em contato." : "Não foi possível enviar sua mensagem. Tente novamente mais tarde."%>")
    <%}%>
  </script>
</body>
</html>