//JavaScript para configuração dos olhos que escondem, ou revalam a senha

//Método que troca a visibilidade da senha
function mudarOlho(senha,imagem, branco=false) {
    //Instanciando as variaveis
    let olhoFechado;
    let olhoAberto;
    if (branco) { //Se quiser que os icones sejam brancos
        olhoFechado = "closed_eyes_branco.png";
        olhoAberto = "eyes_branco.png";
    } else { //Definidos como padrão para pretos.
        olhoFechado = "closed_eyes.png";
        olhoAberto = "eyes.png";
    }
    //Buscando o input de senha, e o botão do olho
    const senhaInput = document.getElementById(senha);
    const toggleSenha = document.getElementById(imagem);

    //Mudando o tipo de atributo para "password" ou "text", afim de esconder, ou revelar a senha
    const tipo = senhaInput.getAttribute("type") === "password" ? "text" : "password";
    senhaInput.setAttribute("type", tipo);

    // troca os ícones
    let caminho = toggleSenha.src.split('/');
    caminho[caminho.length - 1] = tipo === "password" ? olhoFechado : olhoAberto;
    let novoCaminho = caminho.join("/");
    toggleSenha.src = novoCaminho;
}