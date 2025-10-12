const inputs = document.querySelectorAll('.token input');
const form = document.getElementById('tokenForm');

inputs.forEach((input, index) => {
    input.addEventListener('keyup', (e) => {
        const key = e.key;

        // Avança para o próximo input se digitou um número
        if (/\d/.test(key)) {
            if (index < inputs.length - 1) {
                inputs[index + 1].focus();
            } else {
                // Se for o último input, envia o formulário
                form.submit();
            }
        }

        // Permite usar Backspace para voltar
        if (key === "Backspace" && index > 0 && input.value === "") {
            inputs[index - 1].focus();
        }
    });
});