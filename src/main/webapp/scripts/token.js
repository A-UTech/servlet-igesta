// JavaScript usado para manipular os campos do token (ex: código de verificação)

const form = document.getElementById('verificarToken');
const inputs = document.querySelectorAll('.token input');

// Adiciona apenas UM listener para todo o container dos inputs
document.querySelector('.token').addEventListener('keydown', e => {
    const input = e.target;
    const key = e.key;
    const index = Array.from(inputs).indexOf(input);

    // Permite apenas números, Backspace, Tab e Enter
    if (!/^[0-9]$/.test(key) && !['Backspace', 'Tab', 'Enter'].includes(key)) {
        e.preventDefault();
        return;
    }

    // Se digitar número
    if (/^[0-9]$/.test(key)) {
        input.value = '';
        setTimeout(() => {
            if (index < inputs.length - 1) inputs[index + 1].focus();
            else form.submit();
        }, 10);
    }

    // Se pressionar Backspace e o campo estiver vazio
    if (key === 'Backspace' && !input.value && index > 0) {
        inputs[index - 1].focus();
        inputs[index - 1].select();
    }

    // Se pressionar Enter
    if (key === 'Enter') {
        e.preventDefault();
        index === inputs.length - 1 ? form.submit() : inputs[index + 1].focus();
    }
});

// Seleciona o valor automaticamente ao focar
inputs.forEach(input => input.addEventListener('focus', () => input.select()));
