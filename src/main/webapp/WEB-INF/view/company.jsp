<%@ page import="com.backigesta.servlet.Empresas" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/logos/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/restrict-area.css">
    <title>Empresa</title>
    <%
        List<com.backigesta.model.Empresas> empresasList = (List<com.backigesta.model.Empresas>) request.getAttribute("empresas");
    %>
</head>
<body>
    <aside>
        <a href=""><img src="${pageContext.request.contextPath}/assets/logos/logo-branca.png"></a>
        <div>
            <a href="company.jsp"><img src="${pageContext.request.contextPath}/assets/icons/aside-company.svg"></a>
            <a href="${pageContext.request.contextPath}/htmls/condemn.html"><img src="${pageContext.request.contextPath}/assets/icons/aside-condemn.svg"></a>
            <a href="${pageContext.request.contextPath}/htmls/payment.html"><img src="${pageContext.request.contextPath}/assets/icons/aside-payment.svg"></a>
        </div>
        <a href="${pageContext.request.contextPath}/htmls/perfil.jsp"><img src="${pageContext.request.contextPath}/assets/icons/aside-perfil.svg"></a>
    </aside>

    <main>
        <header>
            <h1>Empresas</h1>
            <menu>
                <form action="" method="post">
                    <select name="state" id="selectState">
                        <option selected disabled hidden>Estado</option>
                        <option value="blabla">bla bla</option>
                        <option value="bleble">ble ble</option>
                        <option value="blibli">bli bli</option>
                        <option value="bloblo">blo blo</option>
                        <option value="blublu">blu blu</option>
                    </select>
                </form>
                <form action="" method="post">
                    <button type="submit" class="functions" id="search" >
                        <input type="text" name="search" placeholder="Pesquisar">
                        <img src="${pageContext.request.contextPath}/assets/icons/menu-search.svg">
                    </button>
                </form>
                <form action="" method="post"><button type="submit" class="functions"><img src="${pageContext.request.contextPath}/assets/icons/menu-add.svg"></button></form>
            </menu>
        </header>
        <section>
            <div class="table company">
                <ul>
                    <li>Nome</li>
                    <li>Email</li>
                    <li>CNPJ</li>
                    <li>Estado</li>
                    <li>Unidade</li>
                    <li>Plano</li>
                    <li>Ações</li>
                </ul>
                <%for(com.backigesta.model.Empresas emp : empresasList){%>
                <ul>
                    <li><%=emp.getNome()%></li>
                    <li><%=emp.getEmail()%></li>
                    <li><%=emp.getCnpj()%></li>
                    <li><%=emp.getRegiao()%></li>
                    <li><%=emp.getUnidade()%></li>
                    <li><%=emp.getId_planos()%></li>
                    <li>
                    <a href=""><img src="${pageContext.request.contextPath}/assets/icons/edit.svg"></a>
                    <a href=""><img src="${pageContext.request.contextPath}/assets/icons/trash.svg"></a>
                    </li>
                </ul>
                <%}%>
            </div>
        </section>
    </main>
</body>
</html>