<%@ page import="com.backigesta.model.Telefone" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.backigesta.model.ContatoFuncionario" %>
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
    <title>Contato dos Funcionários</title>
    <%
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        ArrayList<ContatoFuncionario> contatos = (ArrayList<ContatoFuncionario>) request.getAttribute("contatos");
        ArrayList<String> funcionarios = (ArrayList<String>) request.getAttribute("funcionarios");

        String adicionado = (String) request.getAttribute("adicionado");
        String alterado = (String) request.getAttribute("alterado");
        String deletado = (String) request.getAttribute("deletado");
    %>
</head>
    <aside>
        <a href=""><img src="${pageContext.request.contextPath}/assets/logos/logo-branca.png"></a>
        <div>
            <a href="selectEmpresas"><img src="${pageContext.request.contextPath}/assets/icons/aside-company.svg"></a>
            <a href="selectCondena"><img src="${pageContext.request.contextPath}/assets/icons/aside-condemn.svg"></a>
            <a href="selectPlano"><img src="${pageContext.request.contextPath}/assets/icons/aside-payment.svg"></a>
            <a href="selectContatoFuncionarios"><img src="${pageContext.request.contextPath}/assets/icons/aside-employeeContact.svg"></a>
            <a href=""><img src="${pageContext.request.contextPath}/assets/icons/aside-adm.svg"></a>
        </div>
        <a href=""><img src="${pageContext.request.contextPath}/assets/icons/aside-perfil.svg"></a>
    </aside>

    <main>
        <header>
            <h1>Contato dos Funcionários</h1>
            <menu>
                <form action="selectContatoFuncionarios" id="searchName" class="search">
                    <input type="text" name="searchName" placeholder="Pesquisar por nome">
                    <button type="submit" class="functions">
                        <img src="${pageContext.request.contextPath}/assets/icons/menu-search.svg" alt="Pesquisar">
                    </button>
                </form>
                <form action="selectContatoFuncionarios" id="searchPhone" class="search">
                    <input type="text" name="searchPhone" placeholder="Pesquisar por telefone">
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
                <div class="table employeeContact">
                    <ul>
                        <li>Empresa</li>
                        <li>Nome</li>
                        <li>Email</li>
                        <li>Telefone</li>
                        <li>Ações</li>
                    </ul>

                    <% for (ContatoFuncionario contatoFuncionario : contatos) { %>
                        <ul>
                            <li><%=contatoFuncionario.getNomeEmpresa()%></li>
                            <li><%=contatoFuncionario.getNome()%></li>
                            <li><%=contatoFuncionario.getEmail()%></li>
                            <li><%=contatoFuncionario.getTelefone()%></li>
                            <li>
                                <a onclick="alterarContato(<%=contatoFuncionario.getId()%>)"><img src="${pageContext.request.contextPath}/assets/icons/edit.svg"></a>
                                <input type="hidden" id="contatoFuncionarioAlterar<%=contatoFuncionario.getId()%>" value="<%=contatoFuncionario.getNome()%>;<%=contatoFuncionario.getTelefone()%>">
                                <a onclick="deletarContato(<%=contatoFuncionario.getId()%>)"><img src="${pageContext.request.contextPath}/assets/icons/trash.svg"></a>
                            </li>
                        </ul>
                    <% } %>

                </div>
        </section>
    </main>
    <dialog id="add" class="popupInputs">
        <h2>Adicionar condena</h2>
        <form action="adicionarContatoFuncionarios" method="post" autocomplete="off">
            <a onclick="document.getElementById('add').close()"><img src="${pageContext.request.contextPath}/assets/icons/arrow-left.png"></a>
            <select name="nomeContato">
                <option value="" disabled selected >Funcionário</option>
                <% for (String funcionario : funcionarios) { %>
                    <option value="<%=funcionario%>"><%=funcionario%></option>
                <% } %>
            </select>
            <input type="text" name="contato" placeholder="Telefone" class="inputCapitalize" pattern="^\(?[0-9]{2}\)? ?[0-9]{5}\-?[0-9]{4}">
            <button type="submit">Adicionar</button>
        </form>
    </dialog>
    <dialog id="delete" class="popupButtons">
        <a onclick="document.getElementById('delete').close()"><img src="${pageContext.request.contextPath}/assets/icons/arrow-left.png"></a>
        <h2>Excluir condena?</h2>
        <div>
            <button onclick="document.getElementById('delete').close()">Não</button>
            <form action="deletarContatoFuncionarios" method="post">
                <input type="hidden" id="deletarContato" name="contatoId">
                <button type="submit">Sim</button>
            </form>
        </div>
    </dialog>
    <dialog id="alterar" class="popupInputs">
        <h2>Alterar condena</h2>
        <a onclick="document.getElementById('alterar').close()"><img src="${pageContext.request.contextPath}/assets/icons/arrow-left.png"></a>
        <form action="alterarContatoFuncionarios" method="post">
            <input type="hidden" name="contatoId" id="contatoId">
            <input type="text" id="nomeContato" name="nomeContato" disabled class="inputCapitalize">
            <input type="text" id="contato" name="contato" class="inputCapitalize" pattern="^\(?[0-9]{2}\)? ?[0-9]{5}\-?[0-9]{4}">
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
    <script src="${pageContext.request.contextPath}/scripts/areaRestritaContato.js"></script>
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