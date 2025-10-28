function abrirPopupInformacoes(icon,titulo,texto) {
    // Pegando o elemento pelo id overlay
    let overlay = document.getElementById('popupOverlay');

    // Adicionado a classe active para o elemento overlay
    overlay.classList.add('active');

    // Colocando a imagem no elemento de id icon
    let iconSrc = document.getElementById("icon").src;
    iconSrc = iconSrc.substring(0, iconSrc.lastIndexOf('/')+1);
    document.getElementById("icon").src=iconSrc+=icon;


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
