<%@ page import="com.backigesta.model.Admin" %>
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
    <title>Administradores</title>
    <%
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        List<Admin> admins = (List<Admin>) request.getAttribute("admins");

        String adicionado = (String) request.getAttribute("adicionado");
        String alterado = (String) request.getAttribute("alterado");
        String deletado = (String) request.getAttribute("deletado");
    %>
</head>
<aside>
    <a href="${pageContext.request.contextPath}/index.jsp" class="logo"><img src="${pageContext.request.contextPath}/assets/logos/igesta-outlined.svg"> <h2>IGesta</h2></a>

    <nav>
        <a href="selectEmpresa"><img src="${pageContext.request.contextPath}/assets/icons/aside-company.svg"><span>Empresas</span></a>
        <a href="selectCondena"><img src="${pageContext.request.contextPath}/assets/icons/aside-condemn.svg"><span>Condenas</span></a>
        <a href="selectPlano"><img src="${pageContext.request.contextPath}/assets/icons/aside-payment.svg"><span>Mensalidades</span></a>
        <a href="selectContato"><img src="${pageContext.request.contextPath}/assets/icons/aside-employeeContact.svg"><span>Contato dos funcionários</span></a>
        <a href="selectAdmin"><img src="${pageContext.request.contextPath}/assets/icons/aside-adm.svg"><span>Administradores</span></a>
    </nav>

    <a href="entrarPerfil" class="perfil">
        <% if (admin.getFoto() == null) { %>
            <img src="${pageContext.request.contextPath}/assets/icons/aside-perfil.svg">
        <% } else { %>
            <img class="perfil" src="getFoto?id=<%=admin.getId()%>&tipo=Admin">
        <% } %>
         <span><%=admin.getNome()%></span>
    </a>
</aside>

    <main>
        <header>
            <h1>Administradores</h1>
            <menu>
                <form action="" method="get" class="search" id="procuraAdmin">
                    <input type="text" name="search" placeholder="Pesquisar por nome ou email">
                    <button type="button" id="buttonSearchNome" onclick="enviarFormulario('buttonSearchNome','procuraAdmin')" class="functions">
                        <img src="${pageContext.request.contextPath}/assets/icons/menu-search.svg" alt="Pesquisar">
                    </button>
                </form>
                <button type='button' onclick="abrirPopup('add')" class="functions"><img src="${pageContext.request.contextPath}/assets/icons/menu-add.svg"></button>
            </menu>
        </header>

        <section>
                <div class="table adm">
                    <ul>
                        <li>Nome</li>
                        <li>Email</li>
                        <li>Senha</li>
                        <li>Ações</li>
                    </ul>

                    <% for (Admin admin1 : admins) { %>
                        <ul>
                            <li><%=admin1.getNome()%></li>
                            <li><%=admin1.getEmail()%></li>
                            <li>
                                <div class="container">
                                    <input type="password" id="senha<%=admin1.getId()%>" disabled value="<%=admin1.getSenha()%>">
                                    <img onclick="mudarOlho('senha<%=admin1.getId()%>','toggleSenha<%=admin1.getId()%>',true)" src="${pageContext.request.contextPath}/assets/icons/closed_eyes_branco.png"
                                         alt="mostrar senha"
                                         class="eye-icon"
                                         id="toggleSenha<%=admin1.getId()%>">
                                </div>
                            </li>
                            <li>
                                <a onclick="alterarAdmin(<%=admin1.getId()%>)"><img src="${pageContext.request.contextPath}/assets/icons/edit.svg"></a>
                                <input type="hidden" id="alterarAdmin<%=admin1.getId()%>" value="<%=admin1.getNome()%>;<%=admin1.getEmail()%>;<%=admin1.getSenha()%>">
                                <a onclick="deletarAdmin(<%=admin1.getId()%>)"><img src="${pageContext.request.contextPath}/assets/icons/trash.svg"></a>
                            </li>
                        </ul>
                    <% } %>
                </div>
        </section>
    </main>
    <dialog id="add" class="popupInputs">
        <h2>Adicionar admin</h2>
        <form action="adicionarAdmin" method="post" autocomplete="off" id="adicionarAdmin">
            <a onclick="document.getElementById('add').close()"><img src="${pageContext.request.contextPath}/assets/icons/arrow-left.png"></a>
            <input type="text" name="nomeAdmin" placeholder="Nome" class="inputCapitalize" required>
            <input type="email" name="emailAdmin" placeholder="Email" required>
            <div class="input-container">
                <input type="password" id="senhaAdd" name="senhaAdmin" placeholder="Senha" required pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[^a-zA-Z0-9])\S+$" title="A senha deve conter pelo menos uma letra maiúscula, uma letra minúscula, um número, um caractere especial e não pode conter espaços.">
                <img onclick="mudarOlho('senhaAdd','toggleSenhaAdd')" src="${pageContext.request.contextPath}/assets/icons/closed_eyes.png"
                     alt="mostrar senha"
                     class="eye-icon"
                     id="toggleSenhaAdd">
            </div>
            <button type="button" id="buttonAdicionar" onclick="enviarFormulario('buttonAdicionar','adicionarAdmin')">Adicionar</button>
        </form>
    </dialog>
    <dialog id="delete" class="popupButtons">
        <a onclick="document.getElementById('delete').close()"><img src="${pageContext.request.contextPath}/assets/icons/arrow-left.png"></a>
        <h2>Excluir admin?</h2>
        <div>
            <button onclick="document.getElementById('delete').close()">Não</button>
            <form action="deletarAdmin" method="post" id="deletarAdmin">
                <input type="hidden" id="IdAdmin" name="adminId">
                <button type="button" id="buttonDeletar" onclick="enviarFormulario('buttonDeletar','deletarAdmin')">Sim</button>
            </form>
        </div>
    </dialog>
    <dialog id="alterar" class="popupInputs">
        <h2>Alterar admin</h2>
        <a onclick="document.getElementById('alterar').close()"><img src="${pageContext.request.contextPath}/assets/icons/arrow-left.png"></a>
        <form action="alterarAdmin" method="post" id="alterarAdmin">
            <input type="hidden" id="adminId" name="idAdmin">
            <input type="text" id="nomeAdmin" name="nomeAdmin" placeholder="Nome" class="inputCapitalize">
            <input type="email" id="emailAdmin" name="emailAdmin" placeholder="Email" >
            <div class="input-container">
                <input type="password" id="senhaAlterar" name="senhaAdmin" placeholder="Senha" required pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[^a-zA-Z0-9])\S+$" title="A senha deve conter pelo menos uma letra maiúscula, uma letra minúscula, um número, um caractere especial e não pode conter espaços.">
                <img onclick="mudarOlho('senhaAlterar','toggleSenhaAlterar')" src="${pageContext.request.contextPath}/assets/icons/closed_eyes.png"
                     alt="mostrar senha"
                     class="eye-icon"
                     id="toggleSenhaAlterar">
            </div>
            <button type="button" id="buttonAlterar" onclick="enviarFormulario('buttonAlterar','alterarAdmin')">Alterar</button>
        </form>
    </dialog>
    <div class="overlay" id="popupOverlay">
        <div class="popup" id="popupMaior">
            <div class="icon">
                <img id="icon" src="${pageContext.request.contextPath}/assets/icons/" alt="">
            </div>
            <h2 id="title"></h2>
            <p id="text"></p>
            <button onclick="fecharPopupInformacoes()">Ok</button>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/scripts/popupInformacoes.js"></script>
    <script src="${pageContext.request.contextPath}/scripts/mandarFormulario.js"></script>
    <script src="${pageContext.request.contextPath}/scripts/areaRestritaAdmin.js"></script>
    <script src="${pageContext.request.contextPath}/scripts/olhinhoInputs.js"></script>
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