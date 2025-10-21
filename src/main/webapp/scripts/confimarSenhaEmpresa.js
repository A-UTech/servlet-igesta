let senha = document.getElementById("idPassword");
let confirmarSenha = document.getElementById("idConfirmPassword");
let form = document.getElementById('confimarSenha');
let botao = document.getElementById('buttonSubmit');

function verificarSenhas() {
    if ( (senha.value === confirmarSenha.value && senha.value !== "") || confirmarSenha.value === "") {
        senha.style.color = 'green';
        confirmarSenha.style.color = 'green';
    } else {
        senha.style.color = 'red';
        confirmarSenha.style.color = 'red';
    }
}

senha.addEventListener("input", verificarSenhas);
confirmarSenha.addEventListener("input", verificarSenhas);

function senhaConfirmar() {
    confirmarSenha.setCustomValidity("");
    if (senha.value === confirmarSenha.value) {
        botao.disabled = true;
        form.submit();
    } else {
        confirmarSenha.setCustomValidity('As senhas precisam ser iguais');
        confirmarSenha.reportValidity();
    }
}