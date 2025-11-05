<%@ page import="com.backigesta.model.Empresa" %>
<%@ page import="com.backigesta.model.Admin" %>
<%@ page import="com.backigesta.dao.EmpresaDAO" %>
<%@ page import="com.backigesta.dao.AdminDAO" %>
<!DOCTYPE html>
<html lang="pt-br">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="assets/logos/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="styles/style.css">
    <title>IGesta</title>
    <%
        Empresa empresa = (Empresa) session.getAttribute("empresa");
        Admin admin = (Admin) session.getAttribute("admin");
    %>
</head>
<body>
<div class="overlay" id="popupOverlay">
    <% if (session.getAttribute("admin") == null && session.getAttribute("empresa") == null) { %>
    <a href="loginAdmin">
        <div>Entrar como admin</div>
    </a>
    <a href="loginEmpresa">
        <div>Entrar como empresa</div>
    </a>
    <% } else { %>
    <% if (session.getAttribute("admin") != null) { %>
    <a href="htmls/splash.html">
        <div>Área restrita</div>
    </a>
    <% } else { %>
    <a href="selectCollab">
        <div>Ver lideres e gestores</div>
    </a>
    <% } %>
    <a href="logout">
        <div>Sair</div>
    </a>
    <% } %>
</div>
<input type="checkbox" id="chatToggle" hidden>
<label for="chatToggle" class="chat-button">
    <img src="assets/logos/igestinha-outlined.png">
</label>
<a href="htmls/mb-chatbot.html" id="linkChat">
    <img src="assets/logos/igestinha-outlined.png">
</a>
<div class="chat-box"><iframe src="htmls/mb-chatbot.html"></iframe></div>

<header>
    <section>
        <img src="assets/logos/logo-branca.png">
        <h1>IGesta</h1>
    </section>
    <a href="mb-header.jsp"><p>≡</p></a>
    <ul class="links">
        <li><a href="#slide01" class="linkAnimation">Início</a></li>
        <li><a href="#slide06" class="linkAnimation">Empresa</a></li>
        <li><a href="#slide05" class="linkAnimation">Mensalidades</a></li>
        <% if (admin == null && empresa == null) { %>
        <li><a id="entrar" onclick="opcoesEntrar()"><button>Entrar</button></a></li>
        <% } else if (admin != null) { %>
        <li><a class="conta" onclick="opcoesEntrar()"><img src="<%= admin.getFoto() == null ? "assets/icons/aside-perfil.svg" : "getFoto?id=" + admin.getId() + "&tipo=Admin" %>"><%=admin.getNome()%></a></li>
        <% } else { %>
        <li><a class="conta" onclick="opcoesEntrar()"><img src="<%= empresa.getFoto() == null ? "assets/icons/aside-perfil.svg" : "getFoto?id=" + empresa.getId() + "&tipo=Empresas" %>"><%=empresa.getNome()%></a></li>
        <% } %>
    </ul>
</header>

<main>
    <div class="slides" id="slide01">
        <img src="assets/images/splash-helmet.png">
        <section>
            <h1>IGesta</h1>
            <p>Análise de Dados da Indústria</p>
        </section>
    </div>


    <div class="slides" id="slide02">
        <section>
            <h2>INOVAÇÃO</h2>
            <h2>GESTÃO</h2>
            <h2>EVOLUÇÃO</h2>
        </section>
        <p><span>IGesta</span> é uma solução <span>inteligente</span> que <span>simplifica</span> processos, <span>automatiza</span> a contagem e transforma <span>dados</span> em análises claras para <span>otimizar</span> a gestão nas indústrias alimentícias.</p>
    </div>


    <div class="slides" id="slide03">
        <h2>Funcionalidades</h2>
        <div class="container">
            <section class="card">
                <h3>Análise de Dados</h3>
                <p>Da complexidade dos dados à clareza estratégica</p>
            </section>
            <section class="card">
                <h3>Dashboards</h3>
                <p>Transforme dados em decisões</p>
            </section>
            <section class="card">
                <h3>Armazenamento na nuvem</h3>
                <p>Guarde hoje, acesse de onde quiser amanhã</p>
            </section>
            <section class="card">
                <h3>Chatbot</h3>
                <p>Decisões rápidas começam com respostas rápidas</p>
            </section>
        </div>
    </div>


    <div class="slides" id="slide04">
        <menu>
            <h2>Nossos objetivos</h2>
            <section>
                <a href="#obj01"><img src="assets/icons/circle.png"><p>Decisões</p></a>
                <a href="#obj02"><img src="assets/icons/circle.png"><p>Acesso</p></a>
                <a href="#obj03"><img src="assets/icons/circle.png"><p>Eficiência</p></a>
                <a href="#obj04"><img src="assets/icons/circle.png"><p>Agilidade</p></a>
            </section>
        </menu>

        <div class="objectives" id="obj01">
            <h3>Melhorar a tomada de decisões</h3>
            <section>
                <img src="assets/images/objectives01.png">
                <p>Melhore as decisões com gráficos, registros fáceis e com nosso chatbot que pode ser utilizado para dúvidas.</p>
            </section>
        </div>

        <div class="objectives" id="obj02">
            <h3>Maior acesso aos dados</h3>
            <section>
                <img src="assets/images/objectives02.png">
                <p>Acesse seus dados a qualquer momento, sem burocracias e poucos cliques.</p>
            </section>
        </div>

        <div class="objectives" id="obj03">
            <h3>Análise mais eficiente dos dados</h3>
            <section>
                <img src="assets/images/objectives03.png">
                <p>Facilite a procura de registros, com ajuda de filtros de busca e nosso chatbot.</p>
            </section>
        </div>

        <div class="objectives" id="obj04">
            <h3>Facilidade e agilidade no chão da fábrica</h3>
            <section>
                <img src="assets/images/objectives04.png">
                <p>Aumenta a velocidade da tomada de decisões.</p>
            </section>
        </div>
    </div>


    <div class="slides" id="slide05">
        <div class="title">
            <h2>Mensalidades</h2>
            <p>Por armazenamento</p>
            <form action="entrarCadastroEmpresa" id="formPlano" method="post">
                <input type="hidden" name="plano" value="Básico" id="plano">
                <button type="button" id="buttonPlano" onclick="enviarFormulario('buttonPlano','formPlano')">Assinar</button>
            </form>
        </div>
        <div class="container">
            <section class="card" onclick="deixarSelecionado(4)">
                <div>
                    <h3>Básico</h3>
                    <p>200GB</p>
                </div>
                <p>R$149,99</p>
            </section>
            <section class="card" onclick="deixarSelecionado(5)">
                <div>
                    <h3>Intermediário</h3>
                    <p>450GB</p>
                </div>
                <p>R$279,99</p>
            </section>
            <section class="card" onclick="deixarSelecionado(6)">
                <div>
                    <h3>Avançado</h3>
                    <p>1TB</p>
                </div>
                <p>R$599,99</p>
            </section>
        </div>
        <a href="enviarProsposta">Não gostou? Faça uma proposta!</a>
    </div>


    <div class="slides" id="slide06">
        <img src="assets/images/team.png">
        <section>
            <h2>Quem somos nós</h2>
            <p>Somos 14 estudantes da Germinare Tech e unimos nossas ideias para criar um projeto voltado ao ODS 9 (Indústria, Inovação e Infraestrutura). Nosso objetivo é desenvolver um aplicativo que auxilie as indústrias a se modernizarem, crescerem de forma sustentável e se tornarem mais competitivas, impulsionando a inovação e o desenvolvimento tecnológico.</p>
        </section>
    </div>


    <div class="slides" id="slide07">
        <h2>Nossa jornada</h2>
        <picture>
            <source media="(min-width: 768px)" srcset="assets/images/timeline-pc.png">
            <img src="assets/images/timeline-mb.png">
        </picture>
    </div>


    <div class="slides" id="slide08">
        <p>A <span>tecnologia</span> que transforma <span>dados</span> em decisões</p>
        <footer>
            <h1>IGesta</h1>
            <section class="links">
                <h3>Links</h3>
                <ul>
                    <li><a href="#slide01" class="linkAnimation">Início</a></li>
                    <li><a href="#slide05" class="linkAnimation">Mensalidades</a></li>
                    <li><a href="emailContato" class="linkAnimation">Contato</a></li>
                    <li><a href="" class="linkAnimation">Área restrita</a></li>
                </ul>
            </section>

            <section>
                <h3>Conecte-se</h3>
                <a title="Facebook" href="https://www.facebook.com/profile.php?id=61576575636670" target="_blank"><img src="assets/icons/socialmedia-facebook.png"></a>
                <a title="Instagram" href="https://www.instagram.com/igesta.app/" target="_blank"><img src="assets/icons/socialmedia-instagram.png"></a>
                <a title="LinkedIn" href="https://www.linkedin.com/in/aeu-tech/" target="_blank"><img src="assets/icons/socialmedia-linkedin.png"></a>
                <a title="YouTube" href="https://www.youtube.com/@aeutech" target="_blank"><img src="assets/icons/socialmedia-youtube.png"></a>
            </section>
        </footer>
    </div>
</main>
<script src="scripts/index.js"></script>
<script src="scripts/mandarFormulario.js"></script>
</body>
</html>