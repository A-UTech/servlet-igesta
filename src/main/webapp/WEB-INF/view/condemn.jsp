<%@ page import="com.backigesta.model.Admin" %>
<%@ page import="com.backigesta.model.Condena" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="pt-br">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/logos/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/restrict-area.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/popups.css">
    <title>Condenas</title>
    <%
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        List<Condena> condenas = (List<Condena>) request.getAttribute("condenas");

        String adicionado = (String) request.getAttribute("adicionado");
        String alterado = (String) request.getAttribute("alterado");
        String deletado = (String) request.getAttribute("deletado");
    %>
</head>
<body>
<aside>
    <a href="${pageContext.request.contextPath}/index.jsp" class="logo"><img src="${pageContext.request.contextPath}/assets/logos/igesta-outlined.svg"> <h2>IGesta</h2></a>

    <nav>
        <a href="selectEmpresa"><img src="${pageContext.request.contextPath}/assets/icons/aside-company.svg"><span>Empresa</span></a>
        <a href="selectCondena"><img src="${pageContext.request.contextPath}/assets/icons/aside-condemn.svg"><span>Condenas</span></a>
        <a href="selectPlano"><img src="${pageContext.request.contextPath}/assets/icons/aside-payment.svg"><span>Mensalidades</span></a>
        <a href="selectContato"><img src="${pageContext.request.contextPath}/assets/icons/aside-employeeContact.svg"><span>Contato dos funcionários</span></a>
        <a href="selectAdmin"><img src="${pageContext.request.contextPath}/assets/icons/aside-adm.svg"><span>Administradores</span></a>
    </nav>

    <a href="entrarPerfil" class="perfil">
        <% if (admin.getFoto() == null) { %>
            <img src="${pageContext.request.contextPath}/assets/icons/aside-perfil.svg">
        <% } else { %>
            <img src="getFoto?id=<%=admin.getId()%>&tipo=Admin">
        <% } %>
        <span><%=admin.getNome()%></span>
    </a>
</aside>
    <main>
        <header>
            <h1>Condenas</h1>
            <menu>
                <form action="selectCondena" method="get" id="procuraCondenaTodas">
                    <input type="hidden" value="todos" name="filter">
                    <button type="button" onclick="enviarFormulario('buttonTodos','procuraCondenaTodas')" id="buttonTodos" class="options">Todas</button>
                </form>
                <form action="selectCondena" method="get" id="procuraCondenaTotal">
                    <input type="hidden" value="total" name="filter">
                    <button type="button" onclick="enviarFormulario('buttonTotal','procuraCondenaTotal')" id="buttonTotal" class="options">Total</button>
                </form>
                <form action="selectCondena" method="get" id="procuraCondenaParcial">
                    <input type="hidden" value="parcial" name="filter">
                    <button type="button" onclick="enviarFormulario('buttonParcial','procuraCondenaParcial')" id="buttonParcial" class="options">Parcial</button>
                </form>
                <form action="selectCondena" class="search" id="procuraCondenaNome">
                    <input type="text" name="search" placeholder="Pesquisar">
                    <button type="button" onclick="enviarFormulario('buttonSearchNome','procuraCondenaNome')" id="buttonSearchNome" class="functions">
                        <img src="${pageContext.request.contextPath}/assets/icons/menu-search.svg" alt="Pesquisar">
                    </button>
                </form>
                <button onclick="abrirPopup('add')" class="functions">
                    <img src="${pageContext.request.contextPath}/assets/icons/menu-add.svg">
                </button>
            </menu>
        </header>
        <section>
            <div class="table condemn">
                <ul>
                    <li>Nome</li>
                    <li>Tipo</li>
                    <li>Descrição</li>
                    <li>Criador</li>
                    <li>Ações</li>
                </ul>
                <% for (Condena condena : condenas) { %>
                <ul>
                    <li><%=condena.getNome()%></li>
                    <li><%=condena.getTipoCondena()%></li>
                    <li><%=condena.getDescricao() != null ? condena.getDescricao() : "Sem descricao"%></li>
                    <li><%=condena.getNomeAdmin()%></li>
                    <li>
                        <a onclick="alterarCondena(<%=condena.getId()%>)"><img src="${pageContext.request.contextPath}/assets/icons/edit.svg"></a>
                        <input type="hidden" id="condenaAlterar<%=condena.getId()%>" value="<%=condena.getNome()%>;<%=condena.getTipoCondena()%>;<%=condena.getDescricao()%>">
                        <a onclick="deletarCondena(<%=condena.getId()%>)"><img src="${pageContext.request.contextPath}/assets/icons/trash.svg"></a>
                    </li>
                </ul>
                <% } %>
            </div>
        </section>
    </main>
    <dialog id="add" class="popupInputs">
        <h2>Adicionar condena</h2>
        <form action="adicionarCondena" method="post" autocomplete="off" id="adicionarCondena">
            <a onclick="document.getElementById('add').close()"><img src="${pageContext.request.contextPath}/assets/icons/arrow-left.png"></a>
            <input type="text" name="nomeCondena" placeholder="Nome" class="inputCapitalize" required>
            <select name="tipo" required>
                <option value="" disabled selected hidden>Tipo Condena</option>
                <option value="Total">Total</option>
                <option value="Parcial">Parcial</option>
            </select>
            <textarea name="descricaoCondena" placeholder="Descrição da condena"></textarea>
            <button type="button" id="buttonAdicionar" onclick="enviarFormulario('buttonAdicionar','adicionarCondena')">Adicionar</button>
        </form>
    </dialog>
    <dialog id="delete" class="popupButtons">
        <a onclick="document.getElementById('delete').close()"><img src="${pageContext.request.contextPath}/assets/icons/arrow-left.png"></a>
        <h2>Excluir condena?</h2>
        <div>
            <button onclick="document.getElementById('delete').close()">Não</button>
            <form action="deletarCondena" method="post" id="deletarCondena">
                <input type="hidden" id="IdCondena" name="condenaId">
                <button type="button" id="buttonDeletar" onclick="enviarFormulario('buttonDeletar','deletarCondena')">Sim</button>
            </form>
        </div>
    </dialog>
    <dialog id="alterar" class="popupInputs">
        <h2>Alterar condena</h2>
        <a onclick="document.getElementById('alterar').close()"><img src="${pageContext.request.contextPath}/assets/icons/arrow-left.png"></a>
        <form action="alterarCondena" method="post" id="alterarCondena">
            <input type="hidden" name="condenaId" id="condenaId">
            <input type="text" id="nomeCondena" name="nomeCondena" class="inputCapitalize" required>
            <select name="tipo" id="tipoCondena" required>
                <option value="" disabled hidden>Tipo Condena</option>
                <option value="Total" id="Total">Total</option>
                <option value="Parcial" id="Parcial">Parcial</option>
            </select>
            <textarea name="descricaoCondena" id="descricaoCondena" placeholder="Descrição da condena"></textarea>
            <button type="button" id="buttonAlterar" onclick="enviarFormulario('buttonAlterar','alterarCondena')">Alterar</button>
        </form>
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
    <script src="${pageContext.request.contextPath}/scripts/areaRestritaCondenas.js"></script>
    <script src="${pageContext.request.contextPath}/scripts/popupInformacoes.js"></script>
    <script src="${pageContext.request.contextPath}/scripts/mandarFormulario.js"></script>
    <script>
        <% if (adicionado != null) { %>
        <%
            boolean isAdicionado = Boolean.parseBoolean(adicionado);
        %>
        abrirPopupInformacoes("<%=isAdicionado ? "check.svg" : "wrong.svg"%>","<%=isAdicionado ? "Registro adicionado!" : "Erro ao adicionar"%>","<%=isAdicionado ? "O novo dado foi salvo com sucesso." : "Não foi possível salvar o registro. Tente novamente."%>")
        <% } else if (alterado != null) { %>
        <%
            boolean isAlterado = Boolean.parseBoolean(alterado);
        %>
        abrirPopupInformacoes("<%=isAlterado ? "check.svg" : "wrong.svg"%>","<%=isAlterado ? "Alteração concluída!" : "Erro ao editar"%>","<%=isAlterado ? "O registro foi atualizado com sucesso." : "Não foi possível atualizar o registro."%>")
        <% } else if (deletado != null) { %>
        <%
            boolean isDeletado = Boolean.parseBoolean(deletado);
        %>
        abrirPopupInformacoes("<%=isDeletado ? "check.svg" : "wrong.svg"%>","<%=isDeletado? "Registro removido!" : "Erro ao excluir"%>","<%=isDeletado ? "O dado foi excluído do sistema." : "Não foi possível remover o registro."%>")
        <% } %>
    </script>

</body>
</html>