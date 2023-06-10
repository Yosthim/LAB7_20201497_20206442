package com.example.lab7_20206442_20201497.Models.Beans;

import java.util.Date;

public class Partido {
    private int idPartido;
    private Date fecha;
    private int numeroJornada;
    private String local;
    private String visita;
    private Arbitro arbitro;

    public int getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getNumeroJornada() {
        return numeroJornada;
    }

    public void setNumeroJornada(int numeroJornada) {
        this.numeroJornada = numeroJornada;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getVisita() {
        return visita;
    }

    public void setVisita(String visita) {
        this.visita = visita;
    }

    public Arbitro getArbitro() {
        return arbitro;
    }

    public void setArbitro(Arbitro arbitro) {
        this.arbitro = arbitro;
    }


}
