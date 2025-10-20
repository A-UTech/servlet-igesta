function alterarColaborador(numero){
    let [nome, email, cargo, turno, senha] = document.getElementById('colaboradorAlterar'+numero).value.split(';');

    document.getElementById("idColaborador").value = numero;
    document.getElementById("nomeColaborador").value = nome;
    document.getElementById("emailColaborador").value = email;
    document.getElementById("turnoColaborador").value = turno;
    document.getElementById("senhaColaborador").value = senha;

    const selectCargo = document.getElementById("cargoColaborador");
    if (cargo === 1) {
        selectCargo.innerHTML = "<option value='1' selected>Gestor</option> <option value='2'>Líder</option>";
    } else {
        selectCargo.innerHTML = "<option value='2' selected>Líder</option><option value='1'>Gestor</option>";
    }

    document.getElementById('alterar').showModal();
}

function deletarColaborador(numero){
    document.getElementById('delete').showModal();
    document.getElementById('deletarColaborador').value = numero;
}

function abrirPopup(idElemento){
    document.getElementById(idElemento).showModal();
}