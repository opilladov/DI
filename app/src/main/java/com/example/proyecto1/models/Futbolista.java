package com.example.proyecto1.models;

public class Futbolista {

    private String titulo;
    private String descripcion;
    private String url;
    private String futbolistaId;

    public String getFutbolistaId() {
        return futbolistaId;
    }

    public void setFutbolistaId(String futbolistaId) {
        this.futbolistaId = futbolistaId;
    }

    public Futbolista() {

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}