
// Esse arquivo Java Script está sendo usando para manipular elementos da página de CRUD de Administradores da Area restrita

//Método para preparar o popup de Alterar um Administrador.
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

//Método que prepara o popup de deletar um Administrador
function deletarAdmin(numero) {
    // Abre o dialog delete
    document.getElementById('delete').showModal();

    // Coloca o valor do numero no input hidden do dialog delete
    document.getElementById('IdAdmin').value = numero;
}
// Função base de abrir um popup com seu id
function abrirPopup(idElemento) {

    document.getElementById(idElemento).showModal();
}

//Função base de fechar um popup com seu id
function fecharPopup(idElemento) {
    // Ele deleta o dialog com o parâmetro do método
    document.getElementById(idElemento).close();
}


