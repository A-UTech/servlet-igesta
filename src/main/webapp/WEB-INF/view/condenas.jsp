<%@ page import="java.util.List" %>
<%@ page import="com.backigesta.model.Condenas" %>
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
        List<Condenas> condenas = (List<Condenas>) request.getAttribute("condenas");

        String adicionado = (String) request.getAttribute("adicionado");
        String alterado = (String) request.getAttribute("alterado");
        String deletado = (String) request.getAttribute("deletado");

        // Aqui preciso mudar depois porque ainda não estamos criando um HttpSession para salvar a pessoa que está usando a pagina
        int id = 7;
    %>
</head>
<body>
    <aside>
        <a href=""><img src="${pageContext.request.contextPath}/assets/logos/logo-branca.png"></a>
        <div>
            <a href="company.html"><img src="${pageContext.request.contextPath}/assets/icons/aside-company.svg"></a>
            <a href="condemn.html"><img src="${pageContext.request.contextPath}/assets/icons/aside-condemn.svg"></a>
            <a href="payment.html"><img src="${pageContext.request.contextPath}/assets/icons/aside-payment.svg"></a>
        </div>
        <a href="">
            <%-- Nessa parte tenho que arrumar porque preciso colocar um if se a pessoa não tiver uma foto--%>
            <img id="fotoPerfil" src="admin-foto?id=<%=id%>">
        </a>
    </aside>

    <main>
        <header>
            <h1>Condenas</h1>
            <menu>
                <form action="selectCondena" method="get">
                    <input type="hidden" value="todos" name="filter">
                    <button type="submit" id="todos" class="options">Todas</button>
                </form>
                <form action="selectCondena" method="get">
                    <input type="hidden" value="total" name="filter">
                    <button type="submit" id="total" class="options">Total</button>
                </form>
                <form action="selectCondena" method="get">
                    <input type="hidden" value="parcial" name="filter">
                    <button type="submit" id="parcial" class="options">Parcial</button>
                </form>
                <form action="selectCondena" id="search">
                    <input type="text" name="search" placeholder="Pesquisar">
                    <button type="submit" class="functions">
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
                <% for (Condenas condena : condenas) { %>
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
    <dialog id="add">
        <form action="adicionarCondena" method="post">
            <a onclick="fecharPopup('add')">X</a>
            <input type="text" name="nomeCondena" required>
            <select name="tipo" required>
                <option value="" disabled selected >Tipo Condena</option>
                <option value="Total">Total</option>
                <option value="Parcial">Parcial</option>
            </select>
            <textarea name="descricaoCondena" placeholder="Descrição"></textarea>
            <button type="submit">Adicionar</button>
        </form>
    </dialog>
    <dialog id="delete">
        <a onclick="fecharPopup('delete')">X</a>
        <h3>Você tem certeza que quer apagar essa condena?</h3>
        <button onclick="document.getElementById('delete').close()">Não</button>
        <form action="deletarCondena" method="post">
            <input type="hidden" id="deletarCondena" name="condenaId">
            <button type="submit">Sim</button>
        </form>
    </dialog>
    <dialog id="alterar">
        <a onclick="fecharPopup('alterar')">X</a>
        <form action="alterarCondena" method="post">
            <input type="hidden" name="condenaId" id="condenaId">
            <input type="text" id="nomeCondena" name="nomeCondena">
            <select name="tipo" id="tipoCondena">

            </select>
            <textarea name="descricaoCondena" id="descricaoCondena" placeholder="Descrição"></textarea>
            <button type="submit">Alterar</button>
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
    <script src="${pageContext.request.contextPath}/scripts/areaRestrita.js"></script>
    <script src="${pageContext.request.contextPath}/scripts/popupInformacoes.js"></script>
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