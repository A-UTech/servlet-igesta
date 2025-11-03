// javascript para os eventos no index.jsp (landing page)

function opcoesEntrar() {
    // Pegando o elemento pelo id overlay
    let overlay = document.getElementById('popupOverlay');

    // Adicionado ou removendo a classe active para o elemento overlay
    overlay.classList.toggle('active');
}

//Função para definir o plano selecionado ao clicar para cadastrar sua empresa.
function deixarSelecionado(numero) {
    //Buscando todos os cards de mensalidades do index, com a classe 'card'
    let cards = document.querySelectorAll('.card');

    //Resetando as escolhas, removendo o atributo 'selected' de todos os cards
    for (let i = 4;i < cards.length; i++) {
        cards[i].classList.remove('selected');
    }

    //Atribuindo a escolha de acordo com o card selecionado, usando o parametro 'numero'
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

//selecionando o plano básico como padrão, ao iniciar a página.
window.addEventListener('DOMContentLoaded', () => {
    deixarSelecionado(4);
});