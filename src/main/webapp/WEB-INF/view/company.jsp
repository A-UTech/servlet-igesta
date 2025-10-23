<<<<<<< HEAD
<%@ page import="com.backigesta.servlet.Empresas" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Set" %>
<%@ page import="com.backigesta.model.Admin" %>
=======
<%@ page import="com.backigesta.model.Empresas" %>
<%@ page import="com.backigesta.model.Admin" %>
<%@ page import="java.util.ArrayList" %>
>>>>>>> main
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

<<<<<<< HEAD
        HashMap<com.backigesta.model.Empresas, String> map = (HashMap<com.backigesta.model.Empresas, String>) request.getAttribute("empresas");
        com.backigesta.model.Empresas[] empresas = map.keySet().toArray(new com.backigesta.model.Empresas[0]);
        String[] planos = map.values().toArray(new String[0]);
=======
        ArrayList<Empresas> empresas = (ArrayList<Empresas>) request.getAttribute("empresas");
        ArrayList<String> planos = (ArrayList<String>) request.getAttribute("planos");
>>>>>>> main

        String adicionado = (String) request.getAttribute("adicionado");
        String alterado = (String) request.getAttribute("alterado");
        String deletado = (String) request.getAttribute("deletado");
    %>
</head>
<body>
<<<<<<< HEAD
    <aside>
        <a href="company.html"><img src="${pageContext.request.contextPath}/assets/logos/logo-branca.png"></a>
        <div>
            <a href="company.html"><img src="${pageContext.request.contextPath}/assets/icons/aside-company.svg"></a>
            <a href="condemn.html"><img src="${pageContext.request.contextPath}/assets/icons/aside-condemn.svg"></a>
            <a href="payment.html"><img src="${pageContext.request.contextPath}/assets/icons/aside-payment.svg"></a>
            <a href="employee-contact.html"><img src="${pageContext.request.contextPath}/assets/icons/aside-employeeContact.svg"></a>
            <a href="administrators.html"><img src="${pageContext.request.contextPath}/assets/icons/aside-adm.svg"></a>
        </div>
        <a href="entrarPerfil">
                <%if(admin.getFoto()!=null){%>
            <img id="fotoPerfil" src="getFoto?id=<%=admin.getId()%>&tipo=Admin">
                <%} else {%>
            <img src="${pageContext.request.contextPath}/assets/icons/aside-perfil.svg">
                <%}%>
        </a>
    </aside>
=======
<aside>
    <a href="${pageContext.request.contextPath}/index.jsp" class="logo"><img src="${pageContext.request.contextPath}/assets/logos/igesta-outlined.svg"> <h2>IGesta</h2></a>

    <nav>
        <a href="selectEmpresas"><img src="${pageContext.request.contextPath}/assets/icons/aside-company.svg"><span>Empresa</span></a>
        <a href="selectCondena"><img src="${pageContext.request.contextPath}/assets/icons/aside-condemn.svg"><span>Condenas</span></a>
        <a href="selectPlano"><img src="${pageContext.request.contextPath}/assets/icons/aside-payment.svg"><span>Mensalidades</span></a>
        <a href="selectContato"><img src="${pageContext.request.contextPath}/assets/icons/aside-employeeContact.svg"><span>Contato dos funcionários</span></a>
        <a href="selectAdmin"><img src="${pageContext.request.contextPath}/assets/icons/aside-adm.svg"><span>Administradores</span></a>
    </nav>

    <a href="" class="perfil"><img src="${pageContext.request.contextPath}/assets/icons/aside-perfil.svg"> <span>Lucas Lima</span></a>
</aside>
>>>>>>> main

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

<<<<<<< HEAD
                <form action="selectEmpresas" id="search">
                    <input type="text" name="search" placeholder="Pesquisar">
                    <button type="submit" class="functions">
                        <img src="${pageContext.request.contextPath}/assets/icons/menu-search.svg">
                    </button>
                </form>
                <button onclick="abrirPopup('addEmpresa')" class="functions">
=======
                <form action="selectEmpresas" method="get" class="search" id="procuraEmpresaNome">
                    <input type="text" name="search" placeholder="Pesquisar">
                    <button type="button" id="buttonSearchNome" onclick="enviarFormulario('buttonSearchNome','procuraEmpresaNome')" class="functions">
                        <img src="${pageContext.request.contextPath}/assets/icons/menu-search.svg" alt="Pesquisar">
                    </button>
                </form>
                <button onclick="abrirPopup('add')" class="functions">
>>>>>>> main
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
                    <li>Unidade</li>
                    <li>Plano</li>
                    <li>Ações</li>
                </ul>
<<<<<<< HEAD
                <%for(int i=0; i< map.size(); i++){%>
                <ul>
                    <li><%=empresas[i].getNome()%></li>
                    <li><%=empresas[i].getEmail()%></li>
                    <li><%=empresas[i].getCnpj()%></li>
                    <li><%=empresas[i].getRegiao()%></li>
                    <li><%=empresas[i].getUnidade()%></li>
                    <li><%=planos[i]%></li>
                    <li>
                        <a onclick="alterarEmpresa(<%=empresas[i].getId()%>)"><img src="${pageContext.request.contextPath}/assets/icons/edit.svg"></a>
                        <input type="hidden" id="empresaAlterar" value="<%=empresas[i].getNome()%>;<%=empresas[i].getEmail()%>;<%=empresas[i].getRegiao()%>;<%=empresas[i].getUnidade()%>;<%=empresas[i].getId_planos()%>">
                        <a onclick="deletarEmpresa(<%=empresas[i].getId()%>)"><img src="${pageContext.request.contextPath}/assets/icons/trash.svg"></a>
                    </li>
                </ul>
=======
                <% for(com.backigesta.model.Empresas empresa : empresas) {%>
                    <ul>
                        <li><%=empresa.getNome()%></li>
                        <li><%=empresa.getEmail()%></li>
                        <li><%=empresa.getCnpj()%></li>
                        <li><%=empresa.getRegiao()%></li>
                        <li><%=empresa.getUnidade()%></li>
                        <li><%=empresa.getNomePlano()%></li>
                        <li>
                            <a onclick="alterarEmpresa(<%=empresa.getId()%>)"><img src="${pageContext.request.contextPath}/assets/icons/edit.svg"></a>
                            <input type="hidden" id="empresaAlterar" value="<%=empresa.getNome()%>;<%=empresa.getEmail()%>;<%=empresa.getRegiao()%>;<%=empresa.getUnidade()%>;<%=empresa.getNomePlano()%>">
                            <a onclick="deletarEmpresa(<%=empresa.getId()%>)"><img src="${pageContext.request.contextPath}/assets/icons/trash.svg"></a>
                        </li>
                    </ul>
>>>>>>> main
                <%}%>
            </div>
        </section>
    </main>
<<<<<<< HEAD
        <dialog id="addEmpresa" class="popupInputs">
            <h2>Adicionar empresa</h2>
            <form action="adicionarEmpresas" method="post" autocomplete="off">
=======
        <dialog id="add" class="popupInputs">
            <h2>Adicionar empresa</h2>
            <form action="adicionarEmpresas" method="post" autocomplete="off" id="adicionarEmpresa">
>>>>>>> main
                <a onclick="document.getElementById('addEmpresa').close()"><img src="${pageContext.request.contextPath}/assets/icons/arrow-left.png"></a>
                <input type="text" name="nomeEmpresa" class="inputCapitalize" placeholder="Nome">
                <input type="email" name="emailEmpresa" placeholder="Email">
                <input type="password" name="senhaEmpresa" placeholder="Senha">
                <input type="text" name="cnpjEmpresa" placeholder="CNPJ">
                <select name="regiaoEmpresa">
                    <option selected disabled hidden>Selecionar Estado</option>
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
                <input type="text" name="unidadeEmpresa" placeholder="Unidade da Empresa">
                <select name="planoEmpresa">
                    <option selected disabled hidden>Plano de Assinatura</option>
<<<<<<< HEAD
                    <option value="1">Básico</option>
                    <option value="2">Intermediário</option>
                    <option value="3">Avançado</option>
                </select>
                <button type="submit">Adicionar</button>
=======
                    <% for (String plano : planos) { %>
                        <option value="<%=plano%>"><%=plano%></option>
                    <% } %>
                </select>
                <button type="button" id="buttonAdicionar" onclick="enviarFormulario('deletarEmpresa','adicionarEmpresa')">Adicionar</button>
>>>>>>> main
            </form>
        </dialog>
        <dialog id="delete" class="popupButtons">
            <a onclick="document.getElementById('delete').close()"><img src="${pageContext.request.contextPath}/assets/icons/arrow-left.png"></a>
            <h2>Excluir Empresa?</h2>
            <div>
                <button onclick="document.getElementById('delete').close()">Não</button>
<<<<<<< HEAD
                <form action="deletarEmpresas" method="post">
                    <input type="hidden" id="deletarEmpresa" name="idEmpresa">
                    <button type="submit">Sim</button>
=======
                <form action="deletarEmpresas" method="post" id="deletarEmpresa">
                    <input type="hidden" id="empresaId" name="idEmpresa">
                    <button type="button" id="buttonDeletar" onclick="enviarFormulario('buttonDeletar','deletarEmpresa')">Sim</button>
>>>>>>> main
                </form>
            </div>
        </dialog>
        <dialog id="alterar" class="popupInputs">
            <h2>Editar Empresa</h2>
<<<<<<< HEAD
            <form action="alterarEmpresas" method="post">
=======
            <form action="alterarEmpresas" method="post" id="alterarEmpresa">
>>>>>>> main
                <input type="hidden" name="idEmpresa" id="idEmpresa">
                <input type="text" name="nomeEmpresa" id="nomeEmpresa" placeholder="Nome">
                <input type="email" name="emailEmpresa" id="emailEmpresa" placeholder="Email">
                <select name="regiaoEmpresa" id="regiaoEmpresa">
                    <option selected disabled hidden>Selecionar Estado</option>
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
                <input type="text" name="unidadeEmpresa" id="unidadeEmpresa" placeholder="Unidade">
                <select name="planoEmpresa" id="planoEmpresa" name="planoEmpresa">
                    <option selected disabled hidden>Plano de Assinatura</option>
<<<<<<< HEAD
                    <option value="1">Básico</option>
                    <option value="2">Intermediário</option>
                    <option value="3">Avançado</option>
                </select>
                <input type="password" name="senhaEmpresa" placeholder="Nova Senha">
                <button type="submit">Alterar</button>
=======
                    <% for (String plano : planos) { %>
                        <option id="<%=plano%>" value="<%=plano%>"><%=plano%></option>
                    <% } %>
                </select>
                <input type="password" name="senhaEmpresa" placeholder="Nova Senha">
                <button type="button" id="buttonAlterar" onclick="enviarFormulario('buttonAlterar','alterarEmpresa')">Alterar</button>
>>>>>>> main
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
<<<<<<< HEAD
=======
        <script src="${pageContext.request.contextPath}/scripts/mandarFormulario.js"></script>
>>>>>>> main
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