<html lang="pt-br">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="IGesta, o seu app de Análise de Dados inteligente">
    <link rel="stylesheet" href="styles/style.css">
    <link rel="shortcut icon" href="assets/logos/favicon.ico" type="image/x-icon">
    <title>IGesta</title>
</head>
<body>
    <header>
        <section>
            <img src="assets/logos/logo-branca.png">
            <h1>IGesta</h1>
        </section>
        <a href="mb-header.jsp"><div><p>≡</p></div></a>
        <ul class="links">
            <li><a href="#slide01" class="linkAnimation">Início</a></li>
            <li><a href="#slide06" class="linkAnimation">Empresa</a></li>
            <li><a href="#slide05" class="linkAnimation">Mensalidades</a></li>
            <li><a id="entrar" onclick="opcoesEntrar()"><button>Entrar</button></a></li>
        </ul>
    </header>
    <div class="overlay" id="popupOverlay">
        <a href="login.html">
            <div>Entrar como admin</div>
        </a>
        <a href="">
            <div>Entrar como empresa</div>
        </a>
    </div>

    <main>
        <div class="slides" id="slide01">
            <img src="assets/images/splash-helmet.png">
            <section>
                <h1>IGesta</h1>
                <p>Análise de Dados da Indústria</p>
            </section>
        </div>

        

        <div class="slides" id="slide02">
            <section id="phrases">
                <h2>INOVAÇÃO</h2>
                <h2>GESTÃO</h2>
                <h2>EVOLUÇÃO</h2>
            </section>
            <p>O IGesta é um app de bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla </p>
        </div>



        <div class="slides" id="slide03">
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
            <h2>Nossos objetivos</h2>
            <img src="assets/images/objectives01-pc.png">
            <p>Reduzir desperdícios</p>
        </div>



        <div class="slides" id="slide05">
            <div class="title">
                <h2>Mensalidades</h2>
                <p>Por armazenamento</p>
                <form action="">
                    <input type="hidden" name="plano" value="basico" id="plano">
                    <button type="submit">Assinar</button>
                </form>
            </div>
            <div class="container">
                <section class="card" onclick="deixarSelecionado(4)">
                    <div>
                        <h3>Básico</h3>
                        <p>3TB</p>
                    </div>
                    <p>R$200</p>
                </section>
                <section class="card" onclick="deixarSelecionado(5)">
                    <div>
                        <h3>Intermediário</h3>
                        <p>4TB</p>
                    </div>
                    <p>R$300</p>
                </section>
                <section class="card" onclick="deixarSelecionado(6)">
                    <div>
                        <h3>Avançado</h3>
                        <p>5TB</p>
                    </div>
                    <p>R$400</p>
                </section>
            </div>
        </div>



        <div class="slides" id="slide06">
            <img src="assets/images/team-pc.png">
            <section>
                <h2>Quem somos nós</h2>
                <p>Nós somos 14 estudantes da Germinare Tech, onde participamos de um projeto para desenvolver um aplicativo que ajude de alguma forma o ODS 9 (Objetivo de Desenvolvimento Sustentável), que é focado a ajudar as indústrias a se desenvolverem.</p>
            </section>
        </div>



        <div class="slides" id="slide07">
            <img src="assets/images/timeline.png">
        </div>



        <div class="slides" id="slide08">
            <p>A tecnologia que transforma dados em decisões</p>
        </div>

        

        <footer>
            <section>
                <h1>IGesta</h1>
                <p>Tecnologia que transforma<br>Dados em Decisões</p>
            </section>


            <section class="links">
                <h3>Links</h3>
                <ul>
                    <li><a href="#slide01" class="linkAnimation">Início</a></li>
                    <li><a href="#slide05" class="linkAnimation">Mensalidades</a></li>
                    <li><a href="updates.html" class="linkAnimation">Atualizações</a></li>
                    <li><a href="emailContato" class="linkAnimation">Contato</a></li>
                </ul>
            </section>

            <section>
                <h3>Conecte-se</h3>
                <abbr title="Facebook"><a href="https://www.facebook.com/profile.php?id=61576575636670" target="_blank"><img src="assets/icons/socialmedia-facebook.png"></a></abbr>
                <abbr title="Instagram"><a href="https://www.instagram.com/igesta.app/" target="_blank"><img src="assets/icons/socialmedia-instagram.png"></a></abbr>
                <abbr title="LinkedIn"><a href="https://www.linkedin.com/in/aeu-tech/" target="_blank"><img src="assets/icons/socialmedia-linkedin.png"></a></abbr>
                <abbr title="YouTube"><a href="https://www.youtube.com/@aeutech" target="_blank"><img src="assets/icons/socialmedia-youtube.png"></a></abbr>
            </section>
        </footer>
    </main>
    <script src="scripts/index.js"></script>
</body>
</html>