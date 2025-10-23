
// Esse arquivo Java Script está sendo usando para manipular elementos da página employee-contact.jsp

function alterarContato(numero) {
    // Pega os dados que estam no valor dos inputs hidden criados na página
    let tel = document.getElementById('telefoneContato'+numero);

    if (tel.value !== "") {
        // Coloca os valores nos inputs do dialog alterar
        document.getElementById('idTelefone').value = tel.value;
        document.getElementById('telefone').value = tel.options[tel.selectedIndex].innerHTML;

        // Abre o dialog alterar
        document.getElementById("alterar").showModal();
    }
}

function deletarContato(numero) {
    let tel = document.getElementById('telefoneContato'+numero);

    if (tel.value !== "") {
        document.getElementById('idContato').value = tel.value;

        // Abre o dialog delete
        document.getElementById('delete').showModal();
    }
}

function abrirPopup(idElemento) {
    // Ele abre o dialog com o parâmetro do método
    document.getElementById(idElemento).showModal();
}

function fecharPopup(idElemento) {
    // Ele deleta o dialog com o parâmetro do método
    document.getElementById(idElemento).close();
}


