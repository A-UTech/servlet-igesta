// Java Script usado para manipular elementos da página condenasFeia.jsp

//Método para preparar o popup de Alterar Condenas
function alterarCondena(numero) {
    // Pega os dados que estam no valor dos inputs hidden criados na página
    let [nome,tipo,descricao] = document.getElementById("condenaAlterar"+numero).value.split(";");

    // Coloca os valores nos inputs do dialog alterar
    document.getElementById('nomeCondena').value = nome;
    document.getElementById('condenaId').value = numero;
    document.getElementById('descricaoCondena').value = (descricao != "null" ? descricao : "Sem descricao");
    document.getElementById(tipo).selected = true;

    // Abre o dialog alterar
    document.getElementById("alterar").showModal();
}

//Método que prepara o popup de Deletar uma Condena
function deletarCondena(numero) {
    // Abre o dialog delete
    document.getElementById('delete').showModal();

    // Coloca o valor do numero no input hidden do dialog delete
    document.getElementById('IdCondena').value = numero;
}

//Função base de abrir popup com seu id
function abrirPopup(idElemento) {
    document.getElementById(idElemento).showModal();
}

//Função base de fechar popup com seu id
function fecharPopup(idElemento) {
    document.getElementById(idElemento).close();
}


