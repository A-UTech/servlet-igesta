// Esse arquivo Java Script está sendo usando para manipular elementos da página payment.jsp

function alterarPlano(numero) {
    // Pega os dados que estam no valor dos inputs hidden criados na página
    let [nome,mensalidade,armazenamento] = document.getElementById("planoAlterar"+numero).value.split(";");

    // Coloca os valores nos inputs do dialog alterar
    document.getElementById('nomePlano').value = nome;
    document.getElementById("mensalidade").value = mensalidade;
    document.getElementById('armazenamento').value = armazenamento;
    document.getElementById('planoId').value = numero;


    // Abre o dialog alterar
    document.getElementById("alterar").showModal();
}

function deletarPlano(numero) {
    // Abre o dialog delete
    document.getElementById('delete').showModal();

    // Coloca o valor do numero no input hidden do dialog delete
    document.getElementById('idPlano').value = numero;
}

function abrirPopup(idElemento) {
    // Ele abre o dialog com o parâmetro do método
    document.getElementById(idElemento).showModal();
}

function fecharPopup(idElemento) {
    // Ele deleta o dialog com o parâmetro do método
    document.getElementById(idElemento).close();
}


