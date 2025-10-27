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

        String adicionado = (String) request.getAttribute("adicionado");
        String alterado = (String) request.getAttribute("alterado");
        String deletado = (String) request.getAttribute("deletado");
    %>

</head>
<body>
<aside>
    <img src="${pageContext.request.contextPath}/assets/logos/logo-branca.png">
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
                <button type="submit" class="options">Todos</button>
            </form>
            <form action="selectCollab">
                <input type="hidden" name="filter" value="lider">
                <button type="submit" class="options">Líderes</button>
            </form>
            <form action="selectCollab">
                <input type="hidden" name="filter" value="gestor">
                <button type="submit" class="options">Gestores</button>
            </form>
            <%--Barra de pesquisa por nome--%>
            <form action="selectCollab" id="search">
                <input type="text" name="search" placeholder="Pesquisar">
                <button type="submit" class="functions">
                    <img src="${pageContext.request.contextPath}/assets/icons/menu-search.svg">
                </button>
            </form>
            <button onclick="abrirPopup('add')" class="functions">
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
                <li><%=func.getNome()%></li>
                <li><%=func.getEmail()%></li>
                <li><%=Regex.formatarCpf(func.getCpf())%></li>
                <li><%=func.getNomeCargo()%></li>
                <li>
                    <%if(funcionarios.get(func).size()>0){%>
                    <select class="selectPhone" name="telefone" id="telefoneContato<%=func.getId()%>">
                        <option value="" disabled selected>Telefones</option>
                        <%for (Telefone tel : funcionarios.get(func)){%>
                            <option value="<%=tel.getId()%>"><%=Regex.formatarTelefone(tel.getTelefone())%></option>
                        <%}%>
                    </select>
                    <%} else {%>
                    <input type="hidden" name="telefone" id="telefoneContato<%=func.getId()%>">
                    <p>Sem Telefone</p>
                    <%}%>
                </li>
                <li>
                    <a onclick="alterarOptions(<%=func.getId()%>, '<%=func.getNome()%>')"><img src="${pageContext.request.contextPath}/assets/icons/edit.svg"></a>
                    <input type="hidden" id="colaboradorAlterar<%=func.getId()%>" value="<%=func.getNome()%>;<%=func.getEmail()%>;<%=func.getNomeCargo()%>;<%=func.getTurno()%>;<%=func.getSenha()%>">
                    <a onclick="deletarColaborador(<%=func.getId()%>)"><img src="${pageContext.request.contextPath}/assets/icons/trash.svg"></a>
                </li>
            </ul>
            <% } %>
        </div>
    </section>
</main>

<dialog id="add" class="popupButtons">
    <a onclick="document.getElementById('add').close()"><img src="${pageContext.request.contextPath}/assets/icons/arrow-left.png"></a>
    <h2>Adicionar</h2>
    <div>
        <button onclick="abrirPopup('addTelefone')">Telefone</button>
        <button onclick="abrirPopup('addCollab')">Colaborador</button>
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
        <input type="time" name="turno" placeholder="Horario de Turno" required>
        <input type="password" name="senha" placeholder="Senha" required pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[^a-zA-Z0-9])\S+$" title="A senha deve conter pelo menos uma letra maiúscula, uma letra minúscula, um número, um caractere especial e não pode conter espaços.">
        <button type="button" id="buttonAdicionarCollab" onclick="enviarFormulario('buttonAdicionarCollab','adicionarCollab')">Adicionar</button>
    </form>
</dialog>
<dialog id="addTelefone" class="popupInputs">
    <a onclick="document.getElementById('addTelefone').close()"><img src="${pageContext.request.contextPath}/assets/icons/arrow-left.png"></a>
    <h2>Adicionar telefone</h2>
    <form action="adicionarContatoCollab" method="post" autocomplete="off" id="adicionarContato">
        <select id="funcionarios" name="funcionarioId" required>
            <option value="" disabled selected >Funcionário</option>
            <% for (Funcionario func : funcionarios.keySet()) {%>
            <option value="<%=func.getId()%>"><%=func.getNome()%></option>
            <% } %>
        </select>
        <input type="text" name="contato" placeholder="Telefone" class="inputCapitalize" pattern="\(?[0-9]{2}\)?\s?[0-9]{5}-?[0-9]{4}" required title="Digite um telefone no formato (11) 91234-5678 ou apenas os numeros">
        <button type="button" id="buttonAdicionar" onclick="enviarFormulario('buttonAdicionar','adicionarContato')" >Adicionar</button>
    </form>
</dialog>

<dialog id="alterar" class="popupButtons">
    <a onclick="document.getElementById('alterar').close()"><img src="${pageContext.request.contextPath}/assets/icons/arrow-left.png"></a>
    <h2 id="nomeFuncionario">Funcionario</h2>
    <h3>alterar</h3>
    <div id="alterarButtons"></div>
</dialog>

<dialog id="alterarColaborador" class="popupInputs">
    <h2>Alterar Funcionario</h2>
    <a onclick="document.getElementById('alterarColaborador').close()"><img src="${pageContext.request.contextPath}/assets/icons/arrow-left.png"></a>
    <form action="alterarCollab" method="post" id="alterarCollab">
        <input type="text" name="nome" id="nomeColaborador" required>
        <input type="email" name="email" id="emailColaborador" required>
        <select name="cargo" id="cargoColaborador">
        </select>
        <input type="time" name="turno" id="turnoColaborador" required>
        <input type="password" name="senha" id="senhaColaborador" required pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[^a-zA-Z0-9])\S+$" title="A senha deve conter pelo menos uma letra maiúscula, uma letra minúscula, um número, um caractere especial e não pode conter espaços.">
        <input type="hidden" name="id" id="idColaborador">
        <button type="button" id="buttonAlterarColab" onclick="enviarFormulario('buttonAlterarColab','alterarCollab')">Alterar</button>
    </form>
</dialog>

<dialog id="alterarTelefone" class="popupInputs">
    <h2>Alterar Telefone</h2>
    <a onclick="document.getElementById('alterarTelefone').close()"><img src="${pageContext.request.contextPath}/assets/icons/arrow-left.png"></a>
    <form action="alterarContatoCollab" method="post" id="alterarContato">
        <input type="text" id="telefone" name="telefone" placeholder="Telefone" required pattern="\(?[0-9]{2}\)?\s?[0-9]{5}-?[0-9]{4}" required title="Digite um telefone no formato (11) 91234-5678 ou apenas os numeros">
        <input type="hidden" id="idTelefone" name="idTelefone" value="">
        <button type="button" id="buttonAlterar" onclick="enviarFormulario('buttonAlterar','alterarContato')">Alterar</button>
    </form>
    <form action="deletarContatoCollab" method="post" id="deletarContato">
        <input type="hidden" id="idTelefoneDelete" name="contatoId">
        <button type="button" id="buttonDeletarContato" onclick="enviarFormulario('buttonDeletarContato','deletarContato')">Deletar</button>
    </form>
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