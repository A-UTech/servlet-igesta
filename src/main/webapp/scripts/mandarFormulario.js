//JavaScript base usado para enviar um formulario.

//Método de envio de formulario.
//Os botões têm como atributo, um 'onclick="enviarFormulario(?,?)"'
//Como forma de evitar que o usuario clique varias vezes, e envie o forms mais de uma vez.
function enviarFormulario(button, form) {
    //Buscando os elementos de botão e formulario a partir de seus id's.
    let botao = document.getElementById(button);
    let formulario = document.getElementById(form);

    // Checa se todos os campos estão válidos
    if (formulario.checkValidity()) {
        //Desabilita o botão (Para evitar problemas ao clicar varias vezes)
        botao.disabled = true;
        formulario.submit();  // envia só se válido
    } else {
        formulario.reportValidity(); // mostra mensagens de required/pattern
    }
}