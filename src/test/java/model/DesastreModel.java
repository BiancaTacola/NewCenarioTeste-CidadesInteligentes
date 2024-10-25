package model;

public class DesastreModel {
    private int numeroEvento;
    private String tipoDesastre;
    private String local;
    private String statusDesastre;
    private String dataEvento;

    // Getters e Setters
    public void setNumeroEvento(int numeroEvento) {
        this.numeroEvento = numeroEvento;
    }

    public void setTipoDesastre(String tipoDesastre) {
        this.tipoDesastre = tipoDesastre;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setStatusDesastre(String statusDesastre) {
        this.statusDesastre = statusDesastre;
    }

    public void setDataEvento(String dataEvento) {
        this.dataEvento = dataEvento;
    }

    // Outros métodos e construtores, se necessário
}
