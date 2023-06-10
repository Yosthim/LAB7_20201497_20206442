package com.example.lab7_20206442_20201497.Models.Beans;

public class Estadio {
    public int getIdEstadio() {
        return idEstadio;
    }

    public void setIdEstadio(int idEstadio) {
        this.idEstadio = idEstadio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    private  int idEstadio;
    private String nombre;
    private String provincia;
    private String club;
}
