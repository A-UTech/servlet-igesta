package com.backigesta.model;

public class Permissoes {
//========== ATRIBUTOS ==========\\

    private int id;
    private boolean visualizacao;
    private boolean enviar_contagens;
    private boolean historico_contagens;

//========== CONSTRUTORES ==========\\

    public Permissoes() {} // Construtor Vazio

    public Permissoes(int id, boolean visualizacao, boolean enviar_contagens, boolean historico_contagens) {
        this.id = id;
        this.visualizacao = visualizacao;
        this.enviar_contagens = enviar_contagens;
        this.historico_contagens = historico_contagens;
    } // Construtor Completo

    public Permissoes(boolean visualizacao) {
        this.visualizacao = visualizacao;
        this.enviar_contagens = false;
        this.historico_contagens = false;
    } // Construtor com visualizacao

    public Permissoes(boolean admin, boolean marcador) {
        if (admin) {
            this.visualizacao = true;
            this.enviar_contagens = true;
            this.historico_contagens = true;
        }
    } // Construtor para admin (usei 2 parâmetros pra diferenciar do outro boolean)

//========== MÉTODOS GET ==========\\

    public int getId() {
        return id;
    }

    public boolean getVisualizacao() {
        return visualizacao;
    }

    public boolean getEnviar_contagens() {
        return enviar_contagens;
    }

    public boolean getHistorico_contagens() {
        return historico_contagens;
    }

//========== MÉTODOS SET ==========\\

    public void setId(int id) {
        this.id = id;
    }

    public void setVisualizacao(boolean visualizacao) {
        this.visualizacao = visualizacao;
    }

    public void setEnviar_contagens(boolean enviar_contagens) {
        this.enviar_contagens = enviar_contagens;
    }

    public void setHistorico_contagens(boolean historico_contagens) {
        this.historico_contagens = historico_contagens;
    }

//========== TO STRING ==========\\

    @Override
    public String toString() {
        return "Permissoes { " +
                "id=" + id +
                ", visualizacao=" + visualizacao +
                ", enviar_contagens=" + enviar_contagens +
                ", historico_contagens=" + historico_contagens +
                " }";
    }
}
