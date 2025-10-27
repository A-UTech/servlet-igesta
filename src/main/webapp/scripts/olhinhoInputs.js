function mudarOlho(senha,imagem, braco= false) {
    let olhoFechado;
    let olhoAberto;
    if (braco) {
        olhoFechado = "closed_eyes_branco.png";
        olhoAberto = "eyes_branco.png";
    } else {
        olhoFechado = "closed_eyes.png";
        olhoAberto = "eyes.png";
    }
    const senhaInput = document.getElementById(senha);
    const toggleSenha = document.getElementById(imagem);
    const tipo = senhaInput.getAttribute("type") === "password" ? "text" : "password";
    senhaInput.setAttribute("type", tipo);

    // troca os ícones
    let caminho = toggleSenha.src.split('/');
    caminho[caminho.length - 1] = tipo === "password" ? olhoFechado : olhoAberto;
    let novoCaminho = caminho.join("/");
    toggleSenha.src = novoCaminho;
}