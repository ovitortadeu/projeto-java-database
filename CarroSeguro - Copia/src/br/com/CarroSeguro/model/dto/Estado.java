package br.com.CarroSeguro.model.dto;

public class Estado {

    private int idEstado;
    private String sgEstado;
    private String nmEstado;

    public Estado() {
    }

    public Estado(int idEstado, String sgEstado, String nmEstado) {
        this.idEstado = idEstado;
        this.sgEstado = sgEstado;
        this.nmEstado = nmEstado;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getSgEstado() {
        return sgEstado;
    }

    public void setSgEstado(String sgEstado) {
        this.sgEstado = sgEstado;
    }

    public String getNmEstado() {
        return nmEstado;
    }

    public void setNmEstado(String nmEstado) {
        this.nmEstado = nmEstado;
    }
}
