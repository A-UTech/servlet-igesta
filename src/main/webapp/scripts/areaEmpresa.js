function alterarColaborador(numero){
    let [nome, email, cargo, turno, senha] = document.getElementById('colaboradorAlterar'+numero).value.split(';');

    document.getElementById("idColaborador").value = numero;
    document.getElementById("nomeColaborador").value = nome;
    document.getElementById("emailColaborador").value = email;
    document.getElementById("turnoColaborador").value = turno;
    document.getElementById("senhaColaborador").value = senha;

    const selectCargo = document.getElementById("cargoColaborador");
    if (cargo === 1) {
        selectCargo.innerHTML = "<option value='gestor_1' selected>Gestor</option> <option value='lider_2'>Líder</option>";
    } else {
        selectCargo.innerHTML = "<option value='lider_2' selected>Líder</option><option value='gestor_1'>Gestor</option>";
    }

    document.getElementById('alterarColaborador').showModal();
}

function alterarContatoColaborador(numero) {
    // Pega os dados que estam no valor dos inputs hidden criados na página
    let tel = document.getElementById('telefoneContato'+numero);

    if (tel.value !== "") {
        // Coloca os valores nos inputs do dialog alterar
        document.getElementById('idTelefone').value = tel.value;
        document.getElementById('telefone').value = tel.options[tel.selectedIndex].innerHTML;

        // Abre o dialog alterar
        document.getElementById('alterarTelefone').showModal();
        document.getElementById('idTelefoneDelete').value = tel.value;
    }
    else{
        document.getElementById('alterar').close();
        abrirPopupInformacoes("wrong.svg", "Funcionario sem Telefone", "Adicione um primeiro antes de editar");
    }
}

function alterarOptions(numero, nome){
    let popup = document.getElementById('alterar');
    let div = document.getElementById('alterarButtons');
    div.innerHTML = "<button onclick=\"alterarColaborador("+numero+")\">Informações</button>" +
                          "<button onclick=\"alterarContatoColaborador("+numero+")\">Telefone</button>"
    document.getElementById('nomeFuncionario').innerHTML=nome
    popup.showModal();
}

function deletarColaborador(numero){
    document.getElementById('delete').showModal();
    document.getElementById('deletarColaborador').value = numero;
}

function abrirPopup(idElemento){
    document.getElementById(idElemento).showModal();
}