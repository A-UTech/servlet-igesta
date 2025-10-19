
// Esse arquivo Java Script está sendo usando para manipular elementos da página employee-contact.jsp

function alterarContato(numero) {
    // Pega os dados que estam no valor dos inputs hidden criados na página
    let [nome,telefone] = document.getElementById("contatoFuncionarioAlterar"+numero).value.split(";");

    // Coloca os valores nos inputs do dialog alterar
    document.getElementById('nomeContato').value = nome;
    document.getElementById('contatoId').value = numero;
    document.getElementById('contato').value = telefone;

    // Abre o dialog alterar
    document.getElementById("alterar").showModal();
}

function deletarContato(numero) {
    // Abre o dialog delete
    document.getElementById('delete').showModal();

    // Coloca o valor do numero no input hidden do dialog delete
    document.getElementById('deletarContato').value = numero;
}

function abrirPopup(idElemento) {
    // Ele abre o dialog com o parâmetro do método
    document.getElementById(idElemento).showModal();
}

function fecharPopup(idElemento) {
    // Ele deleta o dialog com o parâmetro do método
    document.getElementById(idElemento).close();
}


