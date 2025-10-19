function enviarFormulario(button, form) {
    let botao = document.getElementById(button);
    let formulario = document.getElementById(form);
    botao.disabled = true;
    formulario.submit();
}