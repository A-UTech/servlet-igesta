//Método que pega e envia todas as informações de um funcionario X, para sua tela de Alterar registro.
function alterarColaborador(numero){
    //Separando o value do inputHidden com todas as informações
    let [nome, email, cargo, turno, senha] = document.getElementById('colaboradorAlterar'+numero).value.split(';');

    //Defindo todos os valores iniciais dos inputs da tela de edição com os do funcionario.
    document.getElementById("idColaborador").value = numero;
    document.getElementById("nomeColaborador").value = nome;
    document.getElementById("emailColaborador").value = email;
    document.getElementById("turnoColaborador").value = turno;
    document.getElementById("senhaColaborador").value = senha;

    //Definindo o select de "Cargos".
    const selectCargo = document.getElementById("cargoColaborador");
    if (cargo === 1) {
        selectCargo.innerHTML = "<option value='gestor_1' selected>Gestor</option> <option value='lider_2'>Líder</option>";
    } else {
        selectCargo.innerHTML = "<option value='lider_2' selected>Líder</option><option value='gestor_1'>Gestor</option>";
    }

    //Mostrando o popup
    document.getElementById('alterarColaborador').showModal();
}

//Método que envia todas as informações dos telefones do Funcionario clicado para o Popup de TelefoneOptions.
function alterarTelefones(numero){
    document.getElementById('adicionarTelefone').onclick = function (){ adicionarTelefone(numero) }
    document.getElementById('deletarTelefone').onclick = function (){ deletarTelefone(numero) }
    document.getElementById('alterarTelefone').onclick = function (){ editarTelefone(numero) }
    document.getElementById('telefoneOptions').showModal();
}

//Méetodo que pega as informações do funcionario para Inserir um telefone com seu ID.
function adicionarTelefone(numero){
    document.getElementById('funcionario').value = numero;
    let nome = document.getElementById('nomeFuncionario'+numero).innerHTML;
    document.getElementById('nomeFuncionarioAddTelefone').innerHTML = "Adicionar telefone para "+nome;
    document.getElementById('addTelefone').showModal();
}

function editarTelefone(numero){
    let idTelefone = document.getElementById('telefoneContato'+numero).value
    document.getElementById('idTelefoneEditar').value = idTelefone;

    let options = document.getElementById('telefoneContato'+numero).options;
    for(let i = 0; i<options.length; i++){
        if(options[i].value===idTelefone){
            document.getElementById('telefoneEditar').value = options[i].innerText;
        }
    }
    document.getElementById('editarTelefone').showModal();
}

//Mostra o popup de deletar um registro de colaborador, com seu Id imbutido.
function deletarColaborador(numero){
    document.getElementById('delete').showModal();
    document.getElementById('deletarColaborador').value = numero;
}

function deletarTelefone(numero){
    document.getElementById('telefoneId').value = document.getElementById('telefoneContato'+numero).value;
    document.getElementById('deleteTelefone').showModal();
}

//Método básico para abrirPopup
function abrirPopup(idElemento){
    document.getElementById(idElemento).showModal();
}