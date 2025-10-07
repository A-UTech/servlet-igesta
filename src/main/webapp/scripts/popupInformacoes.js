function abrirPopupInformacoes(icon,titulo,texto) {
    // Pegando o elemento pelo id overlay
    let overlay = document.getElementById('popupOverlay');

    // Adicionado a classe active para o elemento overlay
    overlay.classList.add('active');

    // Colocando a imagem no elemento de id icon
    document.getElementById("icon").src += icon;

    // Colocando os textos no elementos de id title e text
    document.getElementById("title").innerText = titulo;
    document.getElementById("text").innerText = texto;
}

function fecharPopupInformacoes() {
    // Pegando o elemento pelo id overlay
    let overlay = document.getElementById('popupOverlay');

    // Removendo a classe do elemento de id overlay
    overlay.classList.remove('active');
}
