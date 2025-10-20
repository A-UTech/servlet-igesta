<%@ page import="java.util.List" %>
<%@ page import="com.backigesta.model.Condenas" %>
<%@ page import="com.backigesta.model.Admin" %>
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

        List<Condenas> condenas = (List<Condenas>) request.getAttribute("condenas");

        String adicionado = (String) request.getAttribute("adicionado");
        String alterado = (String) request.getAttribute("alterado");
        String deletado = (String) request.getAttribute("deletado");
    %>
</head>
<body>
    <aside>
        <a href="${pageContext.request.contextPath}/index.jsp"><img src="${pageContext.request.contextPath}/assets/logos/logo-branca.png"></a>
        <div>
            <a href="selectEmpresas"><img src="${pageContext.request.contextPath}/assets/icons/aside-company.svg"></a>
            <a href="selectCondena"><img src="${pageContext.request.contextPath}/assets/icons/aside-condemn.svg"></a>
            <a href="selectPlano"><img src="${pageContext.request.contextPath}/assets/icons/aside-payment.svg"></a>
            <a href="selectContatoFuncionarios"><img src="${pageContext.request.contextPath}/assets/icons/aside-employeeContact.svg"></a>
            <a href=""><img src="${pageContext.request.contextPath}/assets/icons/aside-adm.svg"></a>
        </div>
        <a href="entrarPerfil">
            <% if (admin.getFoto() == null) { %>
                <img id="fotoPerfil" src="${pageContext.request.contextPath}/assets/icons/aside-perfil.svg">
            <% } else { %>
                <img id="fotoPerfil" src="getFoto?id=<%=admin.getId()%>&tipo=Admin">
            <% } %>
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
                <form action="selectCondena" class="search">
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
    <dialog id="add" class="popupInputs">
        <h2>Adicionar condena</h2>
        <form action="adicionarCondena" method="post" autocomplete="off">
            <a onclick="document.getElementById('add').close()"><img src="${pageontext.request.contextPath}/assets/icons/arrow-left.png"></a>
            <input type="text" name="nomeCondena" placeholder="Nome" class="inputCapitalize">
            <select name="tipo">
                <option value="" disabled selected >Tipo Condena</option>
                <option value="Total">Total</option>
                <option value="Parcial">Parcial</option>
            </select>
            <textarea name="descricaoCondena" placeholder="Mensagem" required></textarea>
            <button type="submit">Adicionar</button>
        </form>
    </dialog>
    <dialog id="delete" class="popupButtons">
        <a onclick="document.getElementById('delete').close()"><img src="${pageontext.request.contextPath}/assets/icons/arrow-left.png"></a>
        <h2>Excluir condena?</h2>
        <div>
            <button onclick="document.getElementById('delete').close()">Não</button>
            <form action="deletarCondena" method="post">
                <input type="hidden" id="deletarCondena" name="condenaId">
                <button type="submit">Sim</button>
            </form>
        </div>
    </dialog>
    <dialog id="alterar" class="popupInputs">
        <h2>Alterar condena</h2>
        <a onclick="document.getElementById('alterar').close()"><img src="${pageontext.request.contextPath}/assets/icons/arrow-left.png"></a>
        <form action="alterarCondena" method="post">
            <input type="hidden" name="condenaId" id="condenaId">
            <input type="text" id="nomeCondena" name="nomeCondena" class="inputCapitalize">
            <select name="tipo" id="tipoCondena"></select>
            <textarea name="descricaoCondena" id="descricaoCondena" placeholder="Mensagem" required></textarea>
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
    <script src="${pageContext.request.contextPath}/scripts/areaRestritaCondenas.js"></script>
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