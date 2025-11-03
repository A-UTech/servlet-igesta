//JavaScript usado para confirmar se as senhas coincidem na pagina de Criação de Senhas para registrar uma empresa.

//Buscando os valores dos inputs no formulario
let senha = document.getElementById("idPassword");
let confirmarSenha = document.getElementById("idConfirmPassword");
let form = document.getElementById('confimarSenha');
let botao = document.getElementById('buttonSubmit');

//Método que mostra se as senhas batem ou não
function verificarSenhas() {
    if (((senha.value === confirmarSenha.value && senha.value !== "") || confirmarSenha.value === "") && form.checkValidity()) {
        //Caso batem, ficam verdes
        senha.style.color = 'green';
        confirmarSenha.style.color = 'green';
    } else {
        //Se não vermelhas
        senha.style.color = 'red';
        confirmarSenha.style.color = 'red';
    }
}
//Coloca o método acima para executar toda hora que o input for alterado.
senha.addEventListener("input", verificarSenhas);
confirmarSenha.addEventListener("input", verificarSenhas);

//Método que checa se as senhas batem ao tentar enviar o formulario.
function senhaConfirmar() {
    //Zerando a mensagem de erro.
    confirmarSenha.setCustomValidity("");
    //Caso as senhas batem
    if (senha.value === confirmarSenha.value && form.checkValidity()) {
        //Envia o formulario
        botao.disabled = true;
        form.submit();
    } else /*Caso o contrario*/ {
        //Checa as senhas com o regex
        let regex = new RegExp("(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[^a-zA-Z0-9])\\S+");
        if (!regex.test(senha.value)) {
            //Envia que ela não bate com o formato requisitado
            senha.reportValidity();
        } else {
            //Definindo uma nova menssagem de erro
            confirmarSenha.setCustomValidity('As senhas precisam ser iguais');
            //Enviando a menssagem
            confirmarSenha.reportValidity();
        }
    }
}