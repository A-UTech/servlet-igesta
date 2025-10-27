function enviarFormulario(button, form) {
    let botao = document.getElementById(button);
    let formulario = document.getElementById(form);

    // Checa se todos os campos estão válidos
    if (formulario.checkValidity()) {
        botao.disabled = true;
        formulario.submit();  // envia só se válido
    } else {
        formulario.reportValidity(); // mostra mensagens de required/pattern
    }
}