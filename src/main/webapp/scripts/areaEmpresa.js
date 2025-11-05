// javascript para os eventos do CRUD de colaboradores na Area da Empresa

//Método que prepara o popup de alterar um funcionario.
function alterarColaborador(numero){
    //Separando o value do inputHidden com todas as informações
    let [nome, email, cargo, turno, senha] = document.getElementById('colaboradorAlterar'+numero).value.split(';');

    //Defindo todos os valores iniciais dos inputs da tela de edição com os do funcionario.
    document.getElementById("idColaborador").value = numero;
    document.getElementById("nomeColaborador").value = nome;
    document.getElementById("emailColaborador").value = email;
    document.getElementById("turnoColaborador").value = turno;
    document.getElementById("senhaColaborador").value = senha;

    //Definindo o select de "Cargos", inserindo o html interno das opções.
    const selectCargo = document.getElementById("cargoColaborador");
    if (cargo.toUpperCase().charAt(0) === 'G') {
        selectCargo.innerHTML = "<option value='gestor_1' selected>Gestor</option> <option value='lider_2'>Líder</option>";
    } else {
        selectCargo.innerHTML = "<option value='lider_2' selected>Líder</option><option value='gestor_1'>Gestor</option>";
    }

    //Mostrando o popup
    document.getElementById('alterarColaborador').showModal();
}

//Método que envia todas as informações dos telefones do Funcionario clicado para o Popup de TelefoneOptions.
function alterarTelefones(numero){
    //todos atribuem uma função anonima no onclick, que chama sua respectiva função para editar telefone.
    document.getElementById('adicionarTelefone').onclick = function (){ adicionarTelefone(numero) }
    document.getElementById('deletarTelefone').onclick = function (){ deletarTelefone(numero) }
    document.getElementById('alterarTelefone').onclick = function (){ editarTelefone(numero) }
    //Mostrando o popup
    document.getElementById('telefoneOptions').showModal();
}

//Método que pega as informações do funcionario para Inserir um telefone com seu ID.
function adicionarTelefone(numero){
    //Atribuindo o id do funcionario que recebe o telefone, no input hidden
    document.getElementById('funcionario').value = numero;
    //Buscando o nome do funcionario para mostrar na menssagem do popup.
    let nome = document.getElementById('nomeFuncionario'+numero).innerHTML;
    document.getElementById('nomeFuncionarioAddTelefone').innerHTML = "Adicionar telefone para "+nome;
    //Mostrando o popup
    document.getElementById('addTelefone').showModal();
}

//Método que edita um telefone, previamente selecionado na linha do funcionario.
function editarTelefone(numero){
    //Buscando o telefone selecionado na tag 'select' do funcionario (a partir do value)
    let idTelefone = document.getElementById('telefoneContato'+numero).value
    //Colocando o id do telefone selecionado no formulario para alterar
    document.getElementById('idTelefoneEditar').value = idTelefone;

    //Buscando o telefone da opção selecionada, nas options.
    let options = document.getElementById('telefoneContato'+numero).options;
    for(let i = 0; i<options.length; i++){
        if(options[i].value===idTelefone){
            //Colocando o telefone selecionado no input text de alterar
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

//Mostra o popup de deletar um telefone, com o id do telefone em um input hidden no formulario para deletar
function deletarTelefone(numero){
    document.getElementById('telefoneId').value = document.getElementById('telefoneContato'+numero).value;
    document.getElementById('deleteTelefone').showModal();
}

//Método básico para abrirPopup
function abrirPopup(idElemento){
    document.getElementById(idElemento).showModal();
}