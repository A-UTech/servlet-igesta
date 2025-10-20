<%@ page import="com.backigesta.model.Empresas" %>
<%@ page import="com.backigesta.model.Funcionarios" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/logos/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/collaborators.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/popups.css">
    <title>Colaboradores</title>

    <%--Remover isto depois--%>
    <%@ page import="com.backigesta.dao.EmpresasDAO" %>
    <%@ page import="java.util.List" %>
    <%
        com.backigesta.model.Empresas eu = new EmpresasDAO().selecionarPorId(1);
        session.setAttribute("empresa", eu);
    %>
    <%--Remover isto depois--%>


    <%
        Empresas empresa = (Empresas) session.getAttribute("empresa");
        if (empresa == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        List<Funcionarios> funcionarios = (List<Funcionarios>) request.getAttribute("funcionarios");

        String adicionado = (String) request.getAttribute("adicionado");
        String alterado = (String) request.getAttribute("alterado");
        String deletado = (String) request.getAttribute("deletado");
    %>

</head>
<body>
    <aside>
        <img src="${pageContext.request.contextPath}/assets/logos/logo-branca.png">
        <div>
            <h3>Básico</h3>
            <p>3 TB • R$200</p>
        </div>
        <div>
            <h3>12</h3>
            <p>líderes</p>
        </div>
        <div>
            <h3>05</h3>
            <p>gestores</p>
        </div>
    </aside>
    <main>
        <header>
            <h1>Colaboradores</h1>
            <menu>
                <form action="selectCollab">
                    <input type="hidden" name="filter" value="0">
                    <button type="submit" class="options">Todos</button>
                </form>
                <form action="selectCollab">
                    <input type="hidden" name="filter" value="2">
                    <button type="submit" class="options">Líderes</button>
                </form>
                <form action="selectCollab">
                    <input type="hidden" name="filter" value="1">
                    <button type="submit" class="options">Gestores</button>
                </form>
                <form action="selectCollab" id="search">
                    <input type="text" name="search" placeholder="Pesquisar">
                    <button type="submit" class="functions">
                        <img src="${pageContext.request.contextPath}/assets/icons/menu-search.svg">
                    </button>
                </form>
                <button onclick="abrirPopup('addCollab')" class="functions">
                    <img src="${pageContext.request.contextPath}/assets/icons/menu-add.svg">
                </button>
            </menu>
        </header>
        <div class="perfil">
            <p><%=empresa.getNome()%></p>
            <a href="entrarPerfil">
                <%if(empresa.getFoto()!=null){%>
                <img id="fotoPerfil" src="getFoto?id=<%=empresa.getId()%>&tipo=Empresas">
                <%} else {%>
                <img src="${pageContext.request.contextPath}/assets/icons/aside-perfil.svg">
                <%}%>
            </a>
        </div>
        <section>

            <div>
                <ul>
                    <li>Nome</li>
                    <li>Email</li>
                    <li>CPF</li>
                    <li>Cargo</li>
                    <li>Ações</li>
                </ul>
                <%for(Funcionarios func : funcionarios){%>
                <ul>
                    <li><%=func.getNome()%></li>
                    <li><%=func.getEmail()%></li>
                    <li><%=func.getCpf()%></li>
                    <li><%=func.getCargo()%></li>
                    <li>
                        <a onclick="alterarColaborador(<%=func.getId()%>)"><img src="${pageContext.request.contextPath}/assets/icons/edit.svg"></a>
                        <input type="hidden" id="colaboradorAlterar<%=func.getId()%>" value="<%=func.getNome()%>;<%=func.getEmail()%>;<%=func.getId_cargo()%>;<%=func.getTurno()%>;<%=func.getSenha()%>">
                        <a onclick="deletarColaborador(<%=func.getId()%>)"><img src="${pageContext.request.contextPath}/assets/icons/trash.svg"></a>
                    </li>
                </ul>
                <% } %>
            </div>
        </section>
    </main>
    <dialog id="addCollab" class="popupInputs">
        <h2>Adicionar Funcionario</h2>
        <form action="adicionarCollab" method="post">
            <a onclick="document.getElementById('addCollab').close()"><img src="${pageContext.request.contextPath}/assets/icons/arrow-left.png"></a>
            <input type="text" name="nome" class="inputCapitalize" placeholder="nome" required>
            <input type="email" name="email" placeholder="email" required>
            <input type="text" name="cpf" placeholder="CPF" required>
            <select name="cargo" required>
                <option selected disabled hidden>Selecionar Cargo</option>
                <option value="1">Gestor</option>
                <option value="2">Líder</option>
            </select>
            <input type="time" name="turno" placeholder="Horario de Turno" required>
            <input type="password" name="senha" placeholder="Senha" required>
            <button type="submit">Adicionar</button>
        </form>
    </dialog>
    <dialog id="alterar" class="popupInputs">
        <h2>Alterar Funcionario</h2>
        <form action="alterarCollab" method="post">
            <input type="text" name="nome" id="nomeColaborador">
            <input type="email" name="email" id="emailColaborador">
            <select name="cargo" id="cargoColaborador">
            </select>
            <input type="time" name="turno" id="turnoColaborador">
            <input type="password" name="senha" id="senhaColaborador">
            <input type="hidden" name="id" id="idColaborador">
            <button type="submit">Alterar</button>
        </form>
    </dialog>

    <dialog id="delete" class="popupButtons">
        <a onclick="document.getElementById('delete').close()"><img src="${pageContext.request.contextPath}/assets/icons/arrow-left.png"></a>
        <h2>Excluir Funcionario?</h2>
        <div>
            <button onclick="document.getElementById('delete').close()">Não</button>
            <form action="deletarCollab" method="post">
                <input type="hidden" id="deletarColaborador" name="idColaborador">
                <button type="submit">Sim</button>
            </form>
        </div>
    </dialog>

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

    <script src="${pageContext.request.contextPath}/scripts/areaEmpresa.js"></script>
    <script src="${pageContext.request.contextPath}/scripts/popupInformacoes.js"></script>
    <script>
        console.log('<%=adicionado%>');
        <% if (adicionado != null) { %>
        <%
            boolean isAdicionado = Boolean.parseBoolean(adicionado);

        %>
        abrirPopupInformacoes("<%=isAdicionado ? "check.svg" : "wrong.svg"%>", "<%=isAdicionado ? "Registro adicionado!" : "Erro ao adicionar"%>", "<%=isAdicionado ? "O novo dado foi salvo com sucesso." : "Não foi possível salvar o registro. Tente novamente."%>")
        <% } else if (alterado != null) { %>
        <%
            boolean isAlterado = Boolean.parseBoolean(alterado);
        %>
        abrirPopupInformacoes("<%=isAlterado ? "check.svg" : "wrong.svg"%>", "<%=isAlterado ? "Alteração concluída!" : "Erro ao editar"%>", "<%=isAlterado ? "O registro foi atualizado com sucesso." : "Não foi possível atualizar o registro."%>")
        <% } else if (deletado != null) { %>
        <%
            boolean isDeletado = Boolean.parseBoolean(deletado);
        %>
        abrirPopupInformacoes("<%=isDeletado ? "check.svg" : "wrong.svg"%>", "<%=isDeletado? "Registro removido!" : "Erro ao excluir"%>", "<%=isDeletado ? "O dado foi excluído do sistema." : "Não foi possível remover o registro."%>")
        <% } %>
    </script>
</body>
</html>