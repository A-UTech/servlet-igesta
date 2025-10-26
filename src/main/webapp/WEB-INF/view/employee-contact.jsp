<%@ page import="com.backigesta.model.Telefone" %>
<%@ page import="com.backigesta.model.Funcionarios" %>
<%@ page import="java.util.*" %>
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

        HashMap<Funcionarios,ArrayList<Telefone>> mapa = (HashMap<Funcionarios, ArrayList<Telefone>>) request.getAttribute("contatos");
        Map<Funcionarios, ArrayList<Telefone>> contatos = new TreeMap<>(
                Comparator.comparing(p -> p.getNome())
        );
        contatos.putAll(mapa);

        ArrayList<Funcionarios> funcionarios = (ArrayList<Funcionarios>) request.getAttribute("funcionarios");

        String adicionado = (String) request.getAttribute("adicionado");
        String alterado = (String) request.getAttribute("alterado");
        String deletado = (String) request.getAttribute("deletado");
    %>
</head>
<aside>
    <a href="${pageContext.request.contextPath}/index.jsp" class="logo"><img src="${pageContext.request.contextPath}/assets/logos/igesta-outlined.svg"> <h2>IGesta</h2></a>

    <nav>
        <a href="selectEmpresas"><img src="${pageContext.request.contextPath}/assets/icons/aside-company.svg"><span>Empresa</span></a>
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
            <h1>Contato dos Funcionários</h1>
            <menu>
                <form action="selectContato" id="searchName" class="search">
                    <input type="text" name="searchName" placeholder="Pesquisar por nome">
                    <button type="button" onclick="enviarFormulario('buttonSearchNome','searchName')" id="buttonSearchNome" class="functions">
                        <img src="${pageContext.request.contextPath}/assets/icons/menu-search.svg" alt="Pesquisar">
                    </button>
                </form>
                <form action="selectContato" id="searchEmail" class="search">
                    <input type="text" name="searchEmail" placeholder="Pesquisar por email">
                    <button type="button" onclick="enviarFormulario('buttonSearchTelefone','searchEmail')" id="buttonSearchTelefone" class="functions">
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

                    <% for (Funcionarios contato : contatos.keySet()) { %>
                        <ul>
                            <li><%=contato.getNomeEmpresa()%></li>
                            <li><%=contato.getNome()%></li>
                            <li><%=contato.getEmail()%></li>
                            <li>
                                <select class="selectPhone" name="telefone" id="telefoneContato<%=contato.getId()%>">
                                    <option value="" selected hidden disabled>Telefones</option>
                                    <% for (Telefone telefone : contatos.get(contato)) { %>
                                        <option value="<%=telefone.getId()%>"><%=telefone.getTelefone()%></option>
                                    <% } %>
                                </select>
                            </li>
                            <li>
                                <a onclick='alterarContato(<%=contato.getId()%>)'><img src="${pageContext.request.contextPath}/assets/icons/edit.svg"></a>
                                <a onclick="deletarContato(<%=contato.getId()%>)"><img src="${pageContext.request.contextPath}/assets/icons/trash.svg"></a>
                            </li>
                        </ul>
                    <% } %>
                </div>
        </section>
    </main>
    <dialog id="add" class="popupInputs">
        <h2>Adicionar condena</h2>
        <form action="adicionarContato" method="post" autocomplete="off" id="adicionarContato">
            <a onclick="document.getElementById('add').close()"><img src="${pageContext.request.contextPath}/assets/icons/arrow-left.png"></a>
            <select id="funcionarios" name="funcionarioId">
                <option value="" disabled selected >Funcionário</option>
                <% for (Funcionarios funcionario : funcionarios) { %>
                    <option value="<%=funcionario.getId()%>"><%=funcionario.getNome()%></option>
                <% } %>
            </select>
            <input type="text" name="contato" placeholder="Telefone" class="inputCapitalize" pattern="^\(?[0-9]{2}\)? ?[0-9]{5}\-?[0-9]{4}">
            <button type="button" id="buttonAdicionar" onclick="enviarFormulario('buttonAdicionar','adicionarContato')" >Adicionar</button>
        </form>
    </dialog>
    <dialog id="delete" class="popupButtons">
        <a onclick="document.getElementById('delete').close()"><img src="${pageContext.request.contextPath}/assets/icons/arrow-left.png"></a>
        <h2>Excluir condena?</h2>
        <div>
            <button onclick="document.getElementById('delete').close()">Não</button>
            <form action="deletarContato" method="post" id="deletarContato">
                <input type="hidden" id="idContato" name="contatoId">
                <button type="button" id="buttonDeletar" onclick="enviarFormulario('buttonDeletar','deletarContato')">Sim</button>
            </form>
        </div>
    </dialog>
    <dialog id="alterar" class="popupInputs">
        <h2>Alterar condena</h2>
        <a onclick="document.getElementById('alterar').close()"><img src="${pageContext.request.contextPath}/assets/icons/arrow-left.png"></a>
        <form action="alterarContato" method="post" id="alterarContato">
            <input type="text" id="telefone" name="telefone">
            <input type="hidden" id="idTelefone" name="idTelefone" value="">
            <button type="button" id="buttonAlterar" onclick="enviarFormulario('buttonAlterar','alterarContato')">Alterar</button>
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