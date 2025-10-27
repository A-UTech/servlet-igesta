<%@ page import="com.backigesta.servlet.Plano" %>
<!DOCTYPE html>
<html lang="pt-br">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/logos/favicon.ico" type="image/x-icon">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/forms.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/popups.css">
  <title>IGesta</title>
  <%
    String temConta = (String) request.getAttribute("existeConta");
    String plano = (String) request.getAttribute("plano");
  %>
</head>
<body>
<section>
  <h1>Criar conta</h1>
  <form action="criarContaEmpresa" method="post" id="criarContaEmpresa">
    <input type="hidden" name="plano" value="<%=plano%>">
    <input type="text" name="name" placeholder="Nome" required autofocus pattern="^[A-Z][a-z '-]*$" title="Caso o sistema não permita seu nome original, faça cadastro com outro nome, depois entre em contato conosco">
    <input type="email" name="email" placeholder="E-mail" required minlength="10" autocomplete="email" pattern="^[a-zA-Z0-9_\.\-]{1,}@[a-zA-Z_\.\-]{2,}\.(com|br|org|me)$" title="Coloque um dominio .com, ou .br, ou .org, ou .me">
    <input type="text" name="cnpj" placeholder="CNPJ" required minlength="14" maxlength="18" pattern="[0-9]{2}\.?[0-9]{3}\.?[0-9]{3}\/?[0-9]{4}-?[0-9]{2}" title="Digite o CNPJ no formato 12.345.678/0001-90 ou apenas os números.">
    <input type="text" name="unitArea" placeholder="Unidade da empresa" required autofocus pattern="^[A-Za-z0-9 '\-]{2,}" title="Coloque o nome da unidade da empresa">
    <input type="text" list="estados" name="states" placeholder="Estado" required autofocus pattern="^(AC|AP|AM|PA|RO|RR|TO|AL|BA|CE|MA|PB|PE|PI|RN|SE|GO|MT|MS|DF|ES|MG|RJ|SP|PR|RS|SC)$" title="Coloque a sigla do estado do Brasil onde está a empresa. Ex: SP">
    <datalist name="estados" id="estados">
      <option value="AC"></option>
      <option value="AP"></option>
      <option value="AM"></option>
      <option value="PA"></option>
      <option value="RO"></option>
      <option value="RR"></option>
      <option value="TO"></option>
      <option value="AL"></option>
      <option value="BA"></option>
      <option value="CE"></option>
      <option value="MA"></option>
      <option value="PB"></option>
      <option value="PE"></option>
      <option value="PI"></option>
      <option value="RN"></option>
      <option value="SE"></option>
      <option value="GO"></option>
      <option value="MT"></option>
      <option value="MS"></option>
      <option value="DF"></option>
      <option value="ES"></option>
      <option value="MG"></option>
      <option value="RJ"></option>
      <option value="SP"></option>
      <option value="PR"></option>
      <option value="RS"></option>
      <option value="SC"></option>
    </datalist>
    <input type="text" placeholder="Cidade" required name="cidade">
    <button type="button" onclick="enviarFormulario('buttonSubmit','criarContaEmpresa')" id="buttonSubmit">Criar</button>
  </form>
</section>
<div class="mens">
  <h2>Olá</h2>
  <p>seja bem vindo!</p>
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
<script src="${pageContext.request.contextPath}/scripts/mandarFormulario.js"></script>
<script src="${pageContext.request.contextPath}/scripts/popupInformacoes.js"></script>
<script>
  <% if ("true".equals(temConta)) { %>
    abrirPopupInformacoes("wrong.svg","Conta já existe","O email ou cnpj de empresa já existem")
  <% } %>
</script>
</body>
</html>