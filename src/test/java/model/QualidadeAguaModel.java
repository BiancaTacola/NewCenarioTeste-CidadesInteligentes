package model;

public class QualidadeAguaModel {
    private int numeroEvento;
    private String tipoEvento;
    private String local;
    private double nivelContaminacao;
    private String dataEvento;

    // Getters e Setters

    public void setNumeroEvento(int numeroEvento) {
        this.numeroEvento = numeroEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setNivelContaminacao(double nivelContaminacao) {
        this.nivelContaminacao = nivelContaminacao;
    }

    public void setDataEvento(String dataEvento) {
        this.dataEvento = dataEvento;
    }
}
