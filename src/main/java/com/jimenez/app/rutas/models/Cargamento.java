package com.jimenez.app.rutas.models;

import com.jimenez.app.rutas.models.enums.Estatus;

public class Cargamento {

    //atributos
    private Long id;
    private Long rutaId;
    private String descripcion;
    private Double peso;
    private Estatus estatus;

    //gets and sets

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRutaId() {
        return rutaId;
    }

    public void setRutasId(Long rutasId) {
        this.rutaId = rutaId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Estatus getEstatus() {
        return estatus;
    }

    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }
}
