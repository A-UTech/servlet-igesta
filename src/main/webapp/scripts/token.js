const inputs = document.querySelectorAll('.token input');
const form = document.getElementById('verificarToken');

inputs.forEach((input, i) => {
    input.addEventListener('keydown', e => {
        const { key } = e;

        if (/^[0-9]$/.test(key)) {
            input.value = '';
            setTimeout(() => i < inputs.length - 1 ? inputs[i + 1].focus() : form.submit(), 10);
        }
        else if (key === 'Backspace' && !input.value && i > 0) {
            inputs[i - 1].focus();
            inputs[i - 1].select();
        }
        else if (key === 'Enter') {
            e.preventDefault();
            i === inputs.length - 1 ? form.submit() : inputs[i + 1].focus();
        }
        else if (!['Backspace', 'Tab'].includes(key)) {
            e.preventDefault();
        }
    });

    input.addEventListener('focus', () => input.select());
});