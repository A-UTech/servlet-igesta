let senha = document.getElementById("idPassword");
let confirmarSenha = document.getElementById("idConfirmPassword");
let form = document.getElementById('confimarSenha');
let botao = document.getElementById('buttonSubmit');

function verificarSenhas() {
    if (((senha.value === confirmarSenha.value && senha.value !== "") || confirmarSenha.value === "") && form.checkValidity()) {
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
    if (senha.value === confirmarSenha.value && form.checkValidity()) {
        botao.disabled = true;
        form.submit();
    } else {
        let regex = new RegExp("(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[^a-zA-Z0-9])\\S+");
        if (!regex.test(senha.value)) {
            senha.reportValidity();
        } else {
            confirmarSenha.setCustomValidity('As senhas precisam ser iguais');
            confirmarSenha.reportValidity();
        }
    }
}