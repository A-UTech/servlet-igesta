
// Esse arquivo Java Script está sendo usando para manipular elementos da página condenasFeia.jsp

function alterarAdmin(numero) {
    // Pega os dados que estam no valor dos inputs hidden criados na página
    let [nome,email,senha] = document.getElementById("alterarAdmin"+numero).value.split(";");

    // Coloca os valores nos inputs do dialog alterar
    document.getElementById('nomeAdmin').value = nome;
    document.getElementById('adminId').value = numero;
    document.getElementById('emailAdmin').value = email;
    document.getElementById("senhaAlterar").value = senha;

    // Abre o dialog alterar
    document.getElementById("alterar").showModal();
}

function deletarAdmin(numero) {
    // Abre o dialog delete
    document.getElementById('delete').showModal();

    // Coloca o valor do numero no input hidden do dialog delete
    document.getElementById('IdAdmin').value = numero;
}

function abrirPopup(idElemento) {
    // Ele abre o dialog com o parâmetro do método
    document.getElementById(idElemento).showModal();
}

function fecharPopup(idElemento) {
    // Ele deleta o dialog com o parâmetro do método
    document.getElementById(idElemento).close();
}


