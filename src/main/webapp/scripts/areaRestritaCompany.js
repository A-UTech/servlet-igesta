function alterarEmpresa(idEmpresa){
<<<<<<< HEAD
    let[nome, email, regiao, unidade, plano, senha] = document.getElementById('empresaAlterar').value.split(';')

    document.getElementById('nomeEmpresa').value = nome;
    document.getElementById('emailEmpresa').value = email;
    document.getElementById('planoEmpresa').value = plano;
=======
    let[nome, email, regiao, unidade, plano] = document.getElementById('empresaAlterar').value.split(';')

    document.getElementById('nomeEmpresa').value = nome;
    document.getElementById('emailEmpresa').value = email;
    document.getElementById(plano).selected = true;
>>>>>>> main
    document.getElementById('regiaoEmpresa').value = regiao;
    document.getElementById('unidadeEmpresa').value = unidade;
    document.getElementById('idEmpresa').value = idEmpresa;

    document.getElementById('alterar').showModal();
}

function deletarEmpresa(numero) {
    // Abre o dialog delete
    document.getElementById('delete').showModal();

    // Coloca o valor do numero no input hidden do dialog delete
<<<<<<< HEAD
    document.getElementById('deletarEmpresa').value = numero;
=======
    document.getElementById('empresaId').value = numero;
>>>>>>> main
}

function abrirPopup(idElemento){
    document.getElementById(idElemento).showModal();
}

function fecharPopup(idElemento){
    document.getElementById(idElemento).close();
}