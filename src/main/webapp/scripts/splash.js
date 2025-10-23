const logo = document.getElementById('logo')
const links = document.getElementById('links')
let posicaoLinks = 50
let opacidade = 0
links.style.opacity = opacidade;
links.style.left = posicaoLinks + "px"
let posicao = 400;
logo.style.left = posicao + "px"
let tempoInicial = 50;
intervalo = setInterval(() => {
    if (tempoInicial === 0) {
        posicao -= 6.25;
        logo.style.left = posicao + "px"
        if (posicao <= 140 && opacidade <= 1) {
            opacidade += 0.04;
            posicaoLinks -= 2;
            links.style.left = posicaoLinks + "px"
            links.style.opacity = opacidade;
        }
        if (posicao === 0) {
            clearInterval(intervalo)
        }
    } else {
        tempoInicial -= 1;
    }
}, 20);