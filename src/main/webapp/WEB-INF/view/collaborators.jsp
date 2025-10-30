<%@ page import="com.backigesta.model.Empresa" %>
<%@ page import="com.backigesta.model.Funcionario" %>
<%@ page import="com.backigesta.model.Telefone" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.backigesta.util.Regex" %>

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

    <%
        //Checando caso o usuario atual têm uma session de "empresa". Caso o contrário, volta para a Landing.
        Empresa empresa = (Empresa) session.getAttribute("empresa");
        if (empresa == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        //Declarando/Buscando as variaveis dadas pela Servlet.
        HashMap<Funcionario, ArrayList<Telefone>> funcionarios = (HashMap<Funcionario, ArrayList<Telefone>>) request.getAttribute("funcionarios");

        //infoPlano -> separa todas as informações do plano da empresa em um vetor. Sua síntaxe vem assim: numGestores;numLideres;nomePlano;preco;armazenamento
        String[] infoPlano = ((String) request.getAttribute("infoPlano")).split(";");

        String selecionado = (String) request.getAttribute("selecionado");

        String adicionado = (String) request.getAttribute("adicionado");
        String alterado = (String) request.getAttribute("alterado");
        String deletado = (String) request.getAttribute("deletado");
    %>

</head>
<body>
<aside>
    <a href="${pageContext.request.contextPath}/index.jsp"><img src="${pageContext.request.contextPath}/assets/logos/logo-branca.png"></a>
    <div>
        <h3><%=infoPlano[2]%></h3>
        <p><%=infoPlano[4]%> GB • R$<%=infoPlano[3]%></p>
    </div>
    <div>
        <h3><%=infoPlano[1]%></h3>
        <p>líderes</p>
    </div>
    <div>
        <h3><%=infoPlano[0]%></h3>
        <p>gestores</p>
    </div>
</aside>
<main>
    <header>
        <h1>Colaboradores</h1>
        <menu>
            <%--Botões de filtragem por Cargo--%>
            <form action="selectCollab">
                <input type="hidden" name="filter">
                <button type="submit" id="buttonTodos" class="options">Todos</button>
            </form>
            <form action="selectCollab">
                <input type="hidden" name="filter" value="lider">
                <button type="submit" id="buttonLider" class="options">Líderes</button>
            </form>
            <form action="selectCollab">
                <input type="hidden" name="filter" value="gestor">
                <button type="submit" id="buttonGestor" class="options">Gestores</button>
            </form>
            <%--Barra de pesquisa por nome--%>
            <form action="selectCollab" id="search">
                <input type="text" name="search" placeholder="Pesquisar por nome ou email">
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
                <li>Telefones</li>
                <li>Ações</li>
            </ul>
            <%for(Funcionario func : funcionarios.keySet()){%>
            <ul>
                <li id="nomeFuncionario<%=func.getId()%>"><%=func.getNome()%></li>
                <li><%=func.getEmail()%></li>
                <li><%=Regex.formatarCpf(func.getCpf())%></li>
                <li><%=func.getNomeCargo()%></li>
                <li>
                    <%if(funcionarios.get(func).size()>0){%>
                    <select class="selectPhone" name="telefone" id="telefoneContato<%=func.getId()%>">
                        <%for (Telefone tel : funcionarios.get(func)){%>
                        <option value="<%=tel.getId()%>"><%=Regex.formatarTelefone(tel.getTelefone())%></option>
                        <%}%>
                    </select>
                    <a onclick="alterarTelefones(<%=func.getId()%>)"><img src="${pageContext.request.contextPath}/assets/icons/telefone.svg"></a>
                    <%} else {%>
                    <input type="hidden" name="telefone" id="telefoneContato<%=func.getId()%>">
                    <p>Sem Telefone<a onclick="adicionarTelefone(<%=func.getId()%>)"><img src="${pageContext.request.contextPath}/assets/icons/telefoneAdd.svg"></a></p>
                    <%}%>
                </li>
                <li>
                    <a onclick="alterarColaborador(<%=func.getId()%>)"><img src="${pageContext.request.contextPath}/assets/icons/edit.svg"></a>
                    <input type="hidden" id="colaboradorAlterar<%=func.getId()%>" value="<%=func.getNome()%>;<%=func.getEmail()%>;<%=func.getNomeCargo()%>;<%=func.getTurno()%>;<%=func.getSenha()%>">
                    <a onclick="deletarColaborador(<%=func.getId()%>)"><img src="${pageContext.request.contextPath}/assets/icons/trash.svg"></a>
                </li>
            </ul>
            <% } %>
        </div>
    </section>
</main>

<dialog id="telefoneOptions" class="popupButtons">
    <a onclick="document.getElementById('telefoneOptions').close()"><img src="${pageContext.request.contextPath}/assets/icons/arrow-left.png"></a>
    <h2>Telefone</h2>
    <div>
        <img id="adicionarTelefone"  src="${pageContext.request.contextPath}/assets/icons/menu-add.svg" alt="">
        <img id="alterarTelefone" src="${pageContext.request.contextPath}/assets/icons/edit.svg" alt="">
        <img id="deletarTelefone" src="${pageContext.request.contextPath}/assets/icons/trash.svg" alt="">
    </div>
</dialog>

<dialog id="addCollab" class="popupInputs">
    <h2>Adicionar Funcionario</h2>
    <form action="adicionarCollab" method="post" id="adicionarCollab">
        <a onclick="document.getElementById('addCollab').close()"><img src="${pageContext.request.contextPath}/assets/icons/arrow-left.png"></a>
        <input type="text" name="nome" class="inputCapitalize" placeholder="nome" required>
        <input type="email" name="email" placeholder="email" required>
        <input type="text" name="cpf" placeholder="CPF" required pattern="[0-9]{3}\.?[0-9]{3}\.?[0-9]{3}-?[0-9]{2}" title="Digite o CPF no formato 123.456.789-09 ou apenas os números.">
        <select name="cargo" required>
            <option selected disabled value="">Selecionar Cargo</option>
            <option value="Gestor(a);1">Gestor</option>
            <option value="Lider;2">Líder</option>
        </select>
        <input type="text" placeholder="Horario de Turno" pattern="[0-9]{2}:[0-9]{2}" title="Digite no formato HH:MM" name="turno" onfocus="this.type='time'" onblur="if(!this.value) this.type='text'" required>
        <div class="input-container">
            <input type="password" id="senhaColaboradorAdd" name="senha" placeholder="Senha" required pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[^a-zA-Z0-9])\S+$" title="A senha deve conter pelo menos uma letra maiúscula, uma letra minúscula, um número, um caractere especial e não pode conter espaços.">
            <img onclick="mudarOlho('senhaColaboradorAdd','toggleSenhaAdd')" src="${pageContext.request.contextPath}/assets/icons/closed_eyes.png" alt="mostrar senha" class="eye-icon" id="toggleSenhaAdd">
        </div>
        <button type="button" id="buttonAdicionarCollab" onclick="enviarFormulario('buttonAdicionarCollab','adicionarCollab')">Adicionar</button>
    </form>
</dialog>

<dialog id="addTelefone" class="popupInputs">
    <a onclick="document.getElementById('addTelefone').close()"><img src="${pageContext.request.contextPath}/assets/icons/arrow-left.png"></a>
    <h2 id="nomeFuncionarioAddTelefone"></h2>
    <form action="adicionarContatoCollab" method="post" autocomplete="off" id="adicionarContato">
        <input type="hidden" name="funcionarioId" id="funcionario">
        <input type="text" name="contato" placeholder="Telefone" class="inputCapitalize" pattern="\(?[0-9]{2}\)?\s?[0-9]{5}-?[0-9]{4}" required title="Digite um telefone no formato (11) 91234-5678 ou apenas os numeros">
        <button type="button" id="buttonAdicionar" onclick="enviarFormulario('buttonAdicionar','adicionarContato')" >Adicionar</button>
    </form>
</dialog>

<dialog id="alterarColaborador" class="popupInputs">
    <h2>Alterar Funcionario</h2>
    <a onclick="document.getElementById('alterarColaborador').close()"><img src="${pageContext.request.contextPath}/assets/icons/arrow-left.png"></a>
    <form action="alterarCollab" method="post" id="alterarCollab">
        <input type="text" name="nome" id="nomeColaborador" required>
        <input type="email" name="email" id="emailColaborador" required>
        <select name="cargo" id="cargoColaborador">
        </select>
        <input type="text" placeholder="Horario de Turno" id="turnoColaborador" pattern="[0-9]{2}:[0-9]{2}" title="Digite no formato HH:MM" name="turno" onfocus="this.type='time'" onblur="if(!this.value) this.type='text'" required>
        <div class="input-container">
            <input type="password" id="senhaColaborador" name="senha" placeholder="Senha" required pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[^a-zA-Z0-9])\S+$" title="A senha deve conter pelo menos uma letra maiúscula, uma letra minúscula, um número, um caractere especial e não pode conter espaços.">
            <img onclick="mudarOlho('senhaColaborador','toggleSenhaAlterar')" src="${pageContext.request.contextPath}/assets/icons/closed_eyes.png" alt="mostrar senha" class="eye-icon" id="toggleSenhaAlterar">
        </div>
        <input type="hidden" name="id" id="idColaborador">
        <button type="button" id="buttonAlterarColab" onclick="enviarFormulario('buttonAlterarColab','alterarCollab')">Alterar</button>
    </form>
</dialog>

<dialog id="editarTelefone" class="popupInputs">
    <a onclick="document.getElementById('editarTelefone').close()"><img src="${pageContext.request.contextPath}/assets/icons/arrow-left.png"></a>
    <h2>Editar Telefone</h2>
    <p id="telefone"></p>
    <form action="alterarContatoCollab" method="post" id="alterarContatoForm">
        <input type="text" id="telefoneEditar" name="telefone" pattern="\(?[0-9]{2}\)?\s?[0-9]{5}-?[0-9]{4}" required>
        <input type="hidden" id="idTelefoneEditar" name="idTelefone">
        <button type="button" id="buttonAlterarTelefone" onclick="enviarFormulario('buttonAlterarTelefone', 'alterarContatoForm')">Alterar</button>
    </form>
</dialog>

<dialog id="deleteTelefone" class="popupButtons">
    <a onclick="document.getElementById('deleteTelefone').close()"><img src="${pageContext.request.contextPath}/assets/icons/arrow-left.png"></a>
    <h2>Excluir Telefone?</h2>
    <div>
        <button onclick="document.getElementById('deleteTelefone').close()">Não</button>
        <form action="deletarContatoCollab" method="post" id="deletarTelefoneForm">
            <input type="hidden" id="telefoneId" name="contatoId">
            <button type="button" id="buttonDeletarTel" onclick="enviarFormulario('buttonDeletarTel','deletarTelefoneForm')">Sim</button>
        </form>
    </div>
</dialog>

<dialog id="delete" class="popupButtons">
    <a onclick="document.getElementById('delete').close()"><img src="${pageContext.request.contextPath}/assets/icons/arrow-left.png"></a>
    <h2>Excluir Funcionario?</h2>
    <div>
        <button onclick="document.getElementById('delete').close()">Não</button>
        <form action="deletarCollab" method="post" id="deletarCollab">
            <input type="hidden" id="deletarColaborador" name="idColaborador">
            <button type="button" id="buttonDeletar" onclick="enviarFormulario('buttonDeletar','deletarCollab') ">Sim</button>
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
<script src="${pageContext.request.contextPath}/scripts/mandarFormulario.js"></script>
<script src="${pageContext.request.contextPath}/scripts/olhinhoInputs.js"></script>
<script>
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

    <% if ("todos".equalsIgnoreCase(selecionado)) { %>
    document.getElementById('buttonTodos').classList.remove("options")
    document.getElementById('buttonTodos').classList.add('selected');
    <% } else if ("lider".equalsIgnoreCase(selecionado)) { %>
    document.getElementById('buttonLider').classList.remove("options")
    document.getElementById('buttonLider').classList.add('selected');
    <% } else if ("gestor".equalsIgnoreCase(selecionado)) { %>
    document.getElementById('buttonGestor').classList.remove("options")
    document.getElementById('buttonGestor').classList.add('selected');
    <% } %>
</script>
</body>
</html>