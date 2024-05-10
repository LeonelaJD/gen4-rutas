package com.jimenez.app.rutas.models;

import com.jimenez.app.rutas.models.enums.Marcas;
import com.jimenez.app.rutas.models.enums.Tipos;

public class Camion {
    //atributos

    private long id;
    private String matricula;
    private Tipos tipoCamion;
    private Marcas marca;
    private Integer modelo;
    private Integer capacidad;
    private Double kilometraje;
    private Boolean disponibilidad;

    //gets and sets
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Tipos getTipoCamion() {
        return tipoCamion;
    }

    public void setTipoCamion(Tipos tipoCamion) {
        this.tipoCamion = tipoCamion;
    }

    public Integer getModelo() {
        return modelo;
    }

    public void setModelo(Integer modelo) {
        this.modelo = modelo;
    }

    public Marcas getMarca() {
        return marca;
    }

    public void setMarca(Marcas marca) {
        this.marca = marca;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Double getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(Double kilometraje) {
        this.kilometraje = kilometraje;
    }

    public Boolean getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(Boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
}
