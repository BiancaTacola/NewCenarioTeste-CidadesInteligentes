package model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class DesastreModel {
    @Expose
    private int numeroEvento; // Mantém apenas este campo
    @Expose
    private String tipoDesastre;
    @Expose
    private String local;
    @Expose
    private String statusDesastre;
    @Expose
    private String dataEvento;
}
