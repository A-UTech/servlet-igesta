<%@ page import="com.backigesta.model.Empresa" %>
<%@ page import="com.backigesta.model.Admin" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.backigesta.util.Regex" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/logos/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/restrict-area.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/popups.css">
    <title>Empresa</title>
    <%
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        ArrayList<Empresa> empresas = (ArrayList<Empresa>) request.getAttribute("empresas");
        ArrayList<String> planos = (ArrayList<String>) request.getAttribute("planos");

        String adicionado = (String) request.getAttribute("adicionado");
        String alterado = (String) request.getAttribute("alterado");
        String deletado = (String) request.getAttribute("deletado");
    %>
</head>
<body>
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
            <img src="getFoto?id=<%=admin.getId()%>&tipo=Admin">
        <% } %>
        <span><%=admin.getNome()%></span>
    </a>
</aside>

    <main>
        <header>
            <h1>Empresas</h1>
            <menu>
                <form action="selectEmpresas" method="get" id="filtroRegiao" name="filtroRegiao">
                    <select name="regiao" id="selectState" onchange="this.form.submit()">
                        <option selected disabled hidden>Estado</option>
                        <option value="todos">Todos</option>
                        <option value="AC">Acre</option>
                        <option value="AL">Alagoas</option>
                        <option value="AP">Amapá</option>
                        <option value="AM">Amazonas</option>
                        <option value="BA">Bahia</option>
                        <option value="CE">Ceará</option>
                        <option value="DF">Distrito Federal</option>
                        <option value="ES">Espirito Santo</option>
                        <option value="GO">Goiás</option>
                        <option value="MA">Maranhão</option>
                        <option value="MT">Mato Grosso</option>
                        <option value="MS">Mato Grosso do Sul</option>
                        <option value="MG">Minas Gerais</option>
                        <option value="PA">Pará</option>
                        <option value="PR">Paraná</option>
                        <option value="PE">Pernambuco</option>
                        <option value="PI">Piauí</option>
                        <option value="RJ">Rio de Janeiro</option>
                        <option value="RN">Rio Grande do Norte</option>
                        <option value="RS">Rio Grande do Sul</option>
                        <option value="RO">Rondônia</option>
                        <option value="RR">Roráima</option>
                        <option value="SC">Santa Catarina</option>
                        <option value="SP">São Paulo</option>
                        <option value="SE">Sergipe</option>
                        <option value="TO">Tocantins</option>
                    </select>
                </form>

                <form action="selectEmpresas" method="get" class="search" id="procuraEmpresaNome">
                    <input type="text" name="search" placeholder="Pesquisar">
                    <button type="button" id="buttonSearchNome" onclick="enviarFormulario('buttonSearchNome','procuraEmpresaNome')" class="functions">
                        <img src="${pageContext.request.contextPath}/assets/icons/menu-search.svg" alt="Pesquisar">
                    </button>
                </form>
                <button onclick="abrirPopup('add')" class="functions">
                    <img src="${pageContext.request.contextPath}/assets/icons/menu-add.svg">
                </button>
            </menu>
        </header>
        <section>
            <div class="table company">
                <ul>
                    <li>Nome</li>
                    <li>Email</li>
                    <li>CNPJ</li>
                    <li>Estado</li>
                    <li>Cidade</li>
                    <li>Unidade</li>
                    <li>Plano</li>
                    <li>Ações</li>
                </ul>
                <% for(Empresa empresa : empresas) {%>
                    <ul>
                        <li><%=empresa.getNome()%></li>
                        <li><%=empresa.getEmail()%></li>
                        <li><%=Regex.formatarCnpj(empresa.getCnpj())%></li>
                        <li><%=empresa.getEstado()%></li>
                        <li><%=empresa.getCidade()%></li>
                        <li><%=empresa.getUnidade()%></li>
                        <li><%=empresa.getNomePlano()%></li>
                        <li>
                            <a onclick="alterarEmpresa(<%=empresa.getId()%>)"><img src="${pageContext.request.contextPath}/assets/icons/edit.svg"></a>
                            <input type="hidden" id="empresaAlterar<%=empresa.getId()%>" value="<%=empresa.getNome()%>;<%=empresa.getEmail()%>;<%=empresa.getEstado()%>;<%=empresa.getCidade()%>;<%=empresa.getUnidade()%>;<%=empresa.getNomePlano()%>">
                            <a onclick="deletarEmpresa(<%=empresa.getId()%>)"><img src="${pageContext.request.contextPath}/assets/icons/trash.svg"></a>
                        </li>
                    </ul>
                <%}%>
            </div>
        </section>
    </main>
        <dialog id="add" class="popupInputs">
            <h2>Adicionar empresa</h2>
            <form action="adicionarEmpresa" method="post" autocomplete="off" id="adicionarEmpresa">
                <a onclick="document.getElementById('add').close()"><img src="${pageContext.request.contextPath}/assets/icons/arrow-left.png"></a>
                <input type="text" name="nomeEmpresa" class="inputCapitalize" placeholder="Nome" required>
                <input type="email" name="emailEmpresa" placeholder="Email" required>
                <div class="input-container">
                    <input type="password" id="senhaAdd" name="senhaEmpresa" placeholder="Senha" required pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[^a-zA-Z0-9])\S+$" title="A senha deve conter pelo menos uma letra maiúscula, uma letra minúscula, um número, um caractere especial e não pode conter espaços.">
                    <img onclick="mudarOlho('senhaAdd','toggleSenhaAdd')" src="${pageContext.request.contextPath}/assets/icons/closed_eyes.png"
                         alt="mostrar senha"
                         class="eye-icon"
                         id="toggleSenhaAdd">
                </div>
                <input type="text" name="cnpjEmpresa" placeholder="CNPJ" required pattern="[0-9]{2}\.?[0-9]{3}\.?[0-9]{3}\/?[0-9]{4}-?[0-9]{2}" title="Digite o CNPJ no formato 12.345.678/0001-90 ou apenas os números.">
                <select name="regiaoEmpresa" required>
                    <option value="" selected disabled>Selecionar Estado</option>
                    <option value="AC">AC</option>
                    <option value="AL">AL</option>
                    <option value="AP">AP</option>
                    <option value="AM">AM</option>
                    <option value="BA">BA</option>
                    <option value="CE">CE</option>
                    <option value="DF">DF</option>
                    <option value="ES">ES</option>
                    <option value="GO">GO</option>
                    <option value="MA">MA</option>
                    <option value="MT">MT</option>
                    <option value="MS">MS</option>
                    <option value="MG">MG</option>
                    <option value="PA">PA</option>
                    <option value="PR">PR</option>
                    <option value="PE">PE</option>
                    <option value="PI">PI</option>
                    <option value="RJ">RJ</option>
                    <option value="RN">RN</option>
                    <option value="RS">RS</option>
                    <option value="RO">RO</option>
                    <option value="RR">RR</option>
                    <option value="SC">SC</option>
                    <option value="SP">SP</option>
                    <option value="SE">SE</option>
                    <option value="TO">TO</option>
                </select>
                <input type="text" name="cidadeEmpresa" placeholder="Cidade" required>
                <input type="text" name="unidadeEmpresa" placeholder="Unidade da Empresa" required>
                <select name="planoEmpresa" required>
                    <option value="" selected disabled >Plano de Assinatura</option>
                    <% for (String plano : planos) { %>
                        <option value="<%=plano%>"><%=plano%></option>
                    <% } %>
                </select>
                <button type="button" id="buttonAdicionar" onclick="enviarFormulario('deletarEmpresa','adicionarEmpresa')">Adicionar</button>
            </form>
        </dialog>
        <dialog id="delete" class="popupButtons">
            <a onclick="document.getElementById('delete').close()"><img src="${pageContext.request.contextPath}/assets/icons/arrow-left.png"></a>
            <h2>Excluir Empresa?</h2>
            <div>
                <button onclick="document.getElementById('delete').close()">Não</button>
                <form action="deletarEmpresa" method="post" id="deletarEmpresa">
                    <input type="hidden" id="empresaId" name="idEmpresa">
                    <button type="button" id="buttonDeletar" onclick="enviarFormulario('buttonDeletar','deletarEmpresa')">Sim</button>
                </form>
            </div>
        </dialog>
        <dialog id="alterar" class="popupInputs">
            <h2>Editar Empresa</h2>
            <form action="alterarEmpresa" method="post" id="alterarEmpresa">
                <a onclick="document.getElementById('alterar').close()"><img src="${pageContext.request.contextPath}/assets/icons/arrow-left.png"></a>
                <input type="hidden" name="idEmpresa" id="idEmpresa">
                <input type="text" name="nomeEmpresa" id="nomeEmpresa" placeholder="Nome" required>
                <input type="email" name="emailEmpresa" id="emailEmpresa" placeholder="Email" required>
                <select name="regiaoEmpresa" id="regiaoEmpresa" required>
                    <option value="" selected disabled>Selecionar Estado</option>
                    <option value="AC">AC</option>
                    <option value="AL">AL</option>
                    <option value="AP">AP</option>
                    <option value="AM">AM</option>
                    <option value="BA">BA</option>
                    <option value="CE">CE</option>
                    <option value="DF">DF</option>
                    <option value="ES">ES</option>
                    <option value="GO">GO</option>
                    <option value="MA">MA</option>
                    <option value="MT">MT</option>
                    <option value="MS">MS</option>
                    <option value="MG">MG</option>
                    <option value="PA">PA</option>
                    <option value="PR">PR</option>
                    <option value="PE">PE</option>
                    <option value="PI">PI</option>
                    <option value="RJ">RJ</option>
                    <option value="RN">RN</option>
                    <option value="RS">RS</option>
                    <option value="RO">RO</option>
                    <option value="RR">RR</option>
                    <option value="SC">SC</option>
                    <option value="SP">SP</option>
                    <option value="SE">SE</option>
                    <option value="TO">TO</option>
                </select>
                <input type="text" name="cidadeEmpresa" placeholder="Cidade" id="cidadeEmpresa" required>
                <input type="text" name="unidadeEmpresa" id="unidadeEmpresa" placeholder="Unidade" required>
                <select name="planoEmpresa" id="planoEmpresa" name="planoEmpresa" required>
                    <option selected disabled value="">Plano de Assinatura</option>
                    <% for (String plano : planos) { %>
                        <option id="<%=plano%>" value="<%=plano%>"><%=plano%></option>
                    <% } %>
                </select>
                <div class="input-container">
                    <input type="password" id="senhaAlterar" name="senhaEmpresa" placeholder="Nova senha" pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[^a-zA-Z0-9])\S+$" title="A senha deve conter pelo menos uma letra maiúscula, uma letra minúscula, um número, um caractere especial e não pode conter espaços.">
                    <img onclick="mudarOlho('senhaAlterar','toggleSenhaAlterar')" src="${pageContext.request.contextPath}/assets/icons/closed_eyes.png"
                         alt="mostrar senha"
                         class="eye-icon"
                         id="toggleSenhaAlterar">
                </div>
                <button type="button" id="buttonAlterar" onclick="enviarFormulario('buttonAlterar','alterarEmpresa')">Alterar</button>
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
        <script src="${pageContext.request.contextPath}/scripts/popupInformacoes.js"></script>
        <script src="${pageContext.request.contextPath}/scripts/areaRestritaCompany.js"></script>
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
        </script>
    </body>
</html>