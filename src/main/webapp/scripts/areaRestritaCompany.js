function alterarEmpresa(idEmpresa){
    let[nome, email, regiao, unidade, plano] = document.getElementById('empresaAlterar').value.split(';')

    document.getElementById('nomeEmpresa').value = nome;
    document.getElementById('emailEmpresa').value = email;
    document.getElementById(plano).selected = true;
    document.getElementById('regiaoEmpresa').value = regiao;
    document.getElementById('unidadeEmpresa').value = unidade;
    document.getElementById('idEmpresa').value = idEmpresa;

    document.getElementById('alterar').showModal();
}

function deletarEmpresa(numero) {
    // Abre o dialog delete
    document.getElementById('delete').showModal();

    // Coloca o valor do numero no input hidden do dialog delete
    document.getElementById('empresaId').value = numero;
}

function abrirPopup(idElemento){
    document.getElementById(idElemento).showModal();
}

function fecharPopup(idElemento){
    document.getElementById(idElemento).close();
}