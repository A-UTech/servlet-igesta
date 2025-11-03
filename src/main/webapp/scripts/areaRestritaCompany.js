// Javascript usado para os eventos do CRUD de Empresas na Area Restrita

//Método para preparar o popup de alterar Empresas.
function alterarEmpresa(idEmpresa){
    // Pega os dados que estam no valor dos inputs hidden criados na página
    let[nome, email, regiao,cidade , unidade, plano] = document.getElementById('empresaAlterar'+idEmpresa).value.split(';')

    // Coloca os valores nos inputs do dialog alterar
    document.getElementById('nomeEmpresa').value = nome;
    document.getElementById('emailEmpresa').value = email;
    document.getElementById(plano).selected = true;
    document.getElementById('regiaoEmpresa').value = regiao;
    document.getElementById('cidadeEmpresa').value = cidade;
    document.getElementById('unidadeEmpresa').value = unidade;
    document.getElementById('idEmpresa').value = idEmpresa;

    //Mostra o popup
    document.getElementById('alterar').showModal();
}

//Método que prepara o popup de Deletar uma Empresa
function deletarEmpresa(numero) {
    // Abre o dialog delete
    document.getElementById('delete').showModal();

    // Coloca o valor do numero no input hidden do dialog delete
    document.getElementById('empresaId').value = numero;
}

//Função base de abrir popup com seu id
function abrirPopup(idElemento){
    document.getElementById(idElemento).showModal();
}

//Função base de fechar popup com seu id
function fecharPopup(idElemento){
    document.getElementById(idElemento).close();
}