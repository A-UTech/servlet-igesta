// Java Script usado para manipular elementos da página payment.jsp

//Método que prepara o popup de Alterar um Plano.
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

//Método que prepara o popup de
function deletarPlano(numero) {
    // Abre o dialog delete
    document.getElementById('delete').showModal();

    // Coloca o valor do numero no input hidden do dialog delete
    document.getElementById('idPlano').value = numero;
}

//Função base de abrir popup com seu id
function abrirPopup(idElemento) {
    document.getElementById(idElemento).showModal();
}

//Função base de fechar popup com seu id
function fecharPopup(idElemento) {
    document.getElementById(idElemento).close();
}


