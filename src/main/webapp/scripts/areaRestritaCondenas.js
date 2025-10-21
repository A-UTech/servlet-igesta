
// Esse arquivo Java Script está sendo usando para manipular elementos da página condenas.jsp

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

function deletarCondena(numero) {
    // Abre o dialog delete
    document.getElementById('delete').showModal();

    // Coloca o valor do numero no input hidden do dialog delete
    document.getElementById('IdCondena').value = numero;
}

function abrirPopup(idElemento) {
    // Ele abre o dialog com o parâmetro do método
    document.getElementById(idElemento).showModal();
}

function fecharPopup(idElemento) {
    // Ele deleta o dialog com o parâmetro do método
    document.getElementById(idElemento).close();
}


