package model;

public class QualidadeArModel {
    private int numeroEvento;
    private String tipoEvento;
    private String local;
    private int nivelPoluicao;
    private String dataEvento;

    // Construtor padrão
    public QualidadeArModel() {
    }

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
    public void setNivelPoluicao(int nivelPoluicao) {
        this.nivelPoluicao = nivelPoluicao;
    }

    public void setDataEvento(String dataEvento) {
        this.dataEvento = dataEvento;
    }
}
