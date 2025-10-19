function opcoesEntrar() {
    // Pegando o elemento pelo id overlay
    let overlay = document.getElementById('popupOverlay');

    // Adicionado ou removendo a classe active para o elemento overlay
    overlay.classList.toggle('active');
}

function deixarSelecionado(numero) {
    let cards = document.querySelectorAll('.card');

    for (let i = 4;i < cards.length; i++) {
        cards[i].classList.remove('selected');
    }

    if (numero === 4) {
        cards[4].classList.add('selected');
        document.getElementById('plano').value = 'Básico';
    } else if (numero === 5) {
        cards[5].classList.add('selected');
        document.getElementById('plano').value = 'Intermediário';
    } else {
        cards[6].classList.add('selected');
        document.getElementById('plano').value = 'Avançado';
    }
}

window.addEventListener('DOMContentLoaded', () => {
    deixarSelecionado(4);
});