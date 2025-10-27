<%@ page import="com.backigesta.model.Admin" %>
<%@ page import="com.backigesta.model.Plano" %>
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
    <title>Pagamento</title>
    <%
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        List<Plano> planos = (List<Plano>) request.getAttribute("planos");

        String adicionado = (String) request.getAttribute("adicionado");
        String alterado = (String) request.getAttribute("alterado");
        String deletado = (String) request.getAttribute("deletado");
    %>
</head>
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
            <h1>Pagamento</h1>
            <menu>
                <form action="selectPlano" class="search" id="procuraPlanoNome">
                    <input type="text" name="search" placeholder="Pesquisar">
                    <button type="button" onclick="enviarFormulario('buttonSearchNome','procuraPlanoNome')" id="buttonSearchNome" class="functions">
                        <img src="${pageContext.request.contextPath}/assets/icons/menu-search.svg" alt="Pesquisar">
                    </button>
                </form>
                <button onclick="abrirPopup('add')" class="functions">
                    <img src="${pageContext.request.contextPath}/assets/icons/menu-add.svg">
                </button>
            </menu>
        </header>

        <section>
                <div class="table payment">
                    <ul>
                        <li>Nome</li>
                        <li>Mensalidade</li>
                        <li>Armazenamento</li>
                        <li>Ações</li>
                    </ul>

                    <% for (Plano plano : planos) { %>
                        <ul>
                            <li><%=plano.getNome()%></li>
                            <li>R$<%=plano.getMensalidade()%></li>
                            <li><%=plano.getArmazenamento()%>GB</li>
                            <li>
                                <a onclick="alterarPlano(<%=plano.getId()%>)"><img src="${pageContext.request.contextPath}/assets/icons/edit.svg"></a>
                                <input type="hidden" id="planoAlterar<%=plano.getId()%>" value="<%=plano.getNome()%>;<%=plano.getMensalidade()%>;<%=plano.getArmazenamento()%>">
                                <a onclick="deletarPlano(<%=plano.getId()%>)"><img src="${pageContext.request.contextPath}/assets/icons/trash.svg"></a>
                            </li>
                        </ul>
                    <% } %>

                </div>
        </section>
        
    </main>
    <dialog id="add" class="popupInputs">
        <h2>Adicionar Plano</h2>
        <form action="adicionarPlano" method="post" autocomplete="off" id="adicionarPlano">
            <a onclick="fecharPopup('add')"><img src="${pageContext.request.contextPath}/assets/icons/arrow-left.png"></a>
            <input type="text" name="nomePlano" placeholder="Nome" class="inputCapitalize" required>
            <input type="number" step="any" name="mensalidade" placeholder="Mensalidade" class="inputCapitalize" required>
            <input type="number" name="armazenamento" placeholder="Armazenamento" class="inputCapitalize" required>
            <button type="button" id="buttonAdicionar" onclick="enviarFormulario('buttonAdicionar','adicionarPlano')" >Adicionar</button>
        </form>
    </dialog>
    <dialog id="delete" class="popupButtons">
        <a onclick="fecharPopup('delete')"><img src="${pageContext.request.contextPath}/assets/icons/arrow-left.png"></a>
        <h2>Excluir Plano?</h2>
        <div>
            <button onclick="document.getElementById('delete').close()">Não</button>
            <form action="deletarPlano" method="post" id="deletarPlano">
                <input type="hidden" id="idPlano" name="planoId">
                <button type='button' id="buttonDeletar" onclick="enviarFormulario('buttonDeletar','deletarPlano')" >Sim</button>
            </form>
        </div>
    </dialog>
    <dialog id="alterar" class="popupInputs">
        <h2>Alterar Plano</h2>
        <a onclick="fecharPopup('alterar')"><img src="${pageContext.request.contextPath}/assets/icons/arrow-left.png"></a>
        <form action="alterarPlano" method="post" id="alterarPlano">
            <input type="hidden" name="planoId" id="planoId">
            <input type="text" id="nomePlano" name="nomePlano" class="inputCapitalize" required>
            <input type="number" step="any" id="mensalidade" name="mensalidade" class="inputCapitalize" required>
            <input type="number" id="armazenamento" name="armazenamento" class="inputCapitalize" required>
            <button type="button" id="buttonAlterar" onclick="enviarFormulario('buttonAlterar','alterarPlano')">Alterar</button>
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
    <script src="${pageContext.request.contextPath}/scripts/areaRestritaPlanos.js"></script>
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