//JavaScript para a animação de entrada da Área Restrita (splash.hmtl)

//Pegando os elementos da logo, e dos atalhos.
const logo = document.getElementById('logo')
const links = document.getElementById('links')
//Escondendo os links
let posicaoLinks = 50
let opacidade = 0
links.style.opacity = opacidade;
links.style.left = posicaoLinks + "px"
//Posição inicial da logo
let posicao = 400;
logo.style.left = posicao + "px"
//Tempo
let tempoInicial = 50;
intervalo = setInterval(() => {
    if (tempoInicial === 0) { //Executa as ações até que o tempoInicial chegue em zero
        posicao -= 6.25; //Diminui
        logo.style.left = posicao + "px"
        if (posicao <= 140 && opacidade <= 1) { //Executa quando a logo chegar no canto esquerdo
            //Aumenta a opacidade dos atalhos, e move eles um pouco para esquerda
            opacidade += 0.04;
            posicaoLinks -= 2;
            links.style.left = posicaoLinks + "px"
            links.style.opacity = opacidade;
        }
        //Quando a posição da logo chegar em 0, para a animação.
        if (posicao === 0) {
            clearInterval(intervalo)
        }
    } else {
        tempoInicial -= 1;
    }
}, 20);