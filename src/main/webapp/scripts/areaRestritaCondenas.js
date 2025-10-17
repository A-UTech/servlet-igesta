
// Esse arquivo Java Script está sendo usando para manipular elementos da página condenas.jsp

function alterarCondena(numero) {
    // Pega os dados que estam no valor dos inputs hidden criados na página
    let [nome,tipo,descricao] = document.getElementById("condenaAlterar"+numero).value.split(";");
    console.log(nome,tipo,descricao)

    // Coloca os valores nos inputs do dialog alterar
    document.getElementById('nomeCondena').value = nome;
    document.getElementById('condenaId').value = numero;
    document.getElementById('descricaoCondena').value = (descricao != "null" ? descricao : "Sem descricao");

    // Define as ordens das opções que seram mostradas no option
    const selectTipo = document.getElementById("tipoCondena");
    if (tipo === "Parcial") {
        selectTipo.innerHTML = "<option value='Parcial' selected>Parcial</option> <option value='Total'>Total</option>";
    } else {
        selectTipo.innerHTML = "<option value='Total' selected>Total</option><option value='Parcial'>Parcial</option>";
    }

    // Abre o dialog alterar
    document.getElementById("alterar").showModal();
}

function deletarCondena(numero) {
    // Abre o dialog delete
    document.getElementById('delete').showModal();

    // Coloca o valor do numero no input hidden do dialog delete
    document.getElementById('deletarCondena').value = numero;
}

function abrirPopup(idElemento) {
    // Ele abre o dialog com o parâmetro do método
    document.getElementById(idElemento).showModal();
}

function fecharPopup(idElemento) {
    // Ele deleta o dialog com o parâmetro do método
    document.getElementById(idElemento).close();
}


