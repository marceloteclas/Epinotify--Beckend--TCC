package com.epinotify.beckend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.time.LocalDate;

@Embeddable
public class Cartorio {

    // Campo 53
    @Column(name = "cartorio_nome", length = 200)
    private String nomeCartorio;

    @Column(name = "cartorio_codigo", length = 30)
    private String codigoCartorio;

    // Campo 54
    @Column(name = "cartorio_registro", length = 50)
    private String registro;

    // Campo 55
    @Column(name = "cartorio_data_registro")
    private LocalDate dataRegistro;

    // Campo 56
    @Column(name = "cartorio_municipio", length = 150)
    private String municipio;

    // Campo 57
    @Column(name = "cartorio_uf", length = 2)
    private String uf;

    public Cartorio() {
    }

    public String getNomeCartorio() {
        return nomeCartorio;
    }

    public void setNomeCartorio(String nomeCartorio) {
        this.nomeCartorio = nomeCartorio;
    }

    public String getCodigoCartorio() {
        return codigoCartorio;
    }

    public void setCodigoCartorio(String codigoCartorio) {
        this.codigoCartorio = codigoCartorio;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDate dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}