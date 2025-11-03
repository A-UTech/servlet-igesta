// Java Script usado para manipular elementos da página employee-contact.jsp

//Método para preparar o popup de alterar Contatos
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

//Método que prepara o popup de Deletar um Contato
function deletarContato(numero) {
    let tel = document.getElementById('telefoneContato'+numero);

    if (tel.value !== "") {
        document.getElementById('idContato').value = tel.value;

        // Abre o dialog delete
        document.getElementById('delete').showModal();
    }
}

//Função base de abrir popup com seu id
function abrirPopup(idElemento) {
    document.getElementById(idElemento).showModal();
}

//Função base de fechar popup com seu id
function fecharPopup(idElemento) {
    document.getElementById(idElemento).close();
}


