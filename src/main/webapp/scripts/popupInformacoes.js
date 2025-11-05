//JavaScript usado para mostrar popup de informação. (Os que aparecem quando uma ação falhou, por exemplo)

//Método para abrir os popups de informação
function abrirPopupInformacoes(icon,titulo,texto,voltar = false) {
    // Pegando o elemento pelo id overlay
    console.log(voltar)
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

    //Caso verdadeiro, envia de volta para o index.
    if (voltar) {
        document.getElementById("buttonInicio").onclick = fecharPopupInformacoes(true);
    }
}

//Método para fechar os popups de informação
function fecharPopupInformacoes(inicio = false) {
    //Caso verdadeiro, envia para o index.
    if (inicio) {
        window.location.href = "index.jsp";
    } else {
        //(padrão) Caso falso, apenas fecha o popup.
        // Pegando o elemento pelo id overlay
        let overlay = document.getElementById('popupOverlay');

        // Removendo a classe do elemento de id overlay
        overlay.classList.remove('active');
    }
}
