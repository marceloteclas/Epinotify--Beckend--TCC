package com.epinotify.beckend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Residencia {

    @Column(name = "res_logradouro", length = 250)
    private String logradouro;

    @Column(name = "res_numero", length = 20)
    private String numero;

    @Column(name = "res_complemento", length = 100)
    private String complemento;

    @Column(name = "res_cep", length = 10)
    private String cep;

    @Column(name = "res_bairro_distrito", length = 150)
    private String bairroDistrito;

    @Column(name = "res_codigo_bairro_distrito", length = 20)
    private String codigoBairroDistrito;

    @Column(name = "res_municipio", length = 150)
    private String municipioResidencia;

    @Column(name = "res_codigo_municipio", length = 20)
    private String codigoMunicipioResidencia;

    @Column(name = "res_uf", length = 2)
    private String uf;

    public Residencia() {
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairroDistrito() {
        return bairroDistrito;
    }

    public void setBairroDistrito(String bairroDistrito) {
        this.bairroDistrito = bairroDistrito;
    }

    public String getCodigoBairroDistrito() {
        return codigoBairroDistrito;
    }

    public void setCodigoBairroDistrito(String codigoBairroDistrito) {
        this.codigoBairroDistrito = codigoBairroDistrito;
    }

    public String getMunicipioResidencia() {
        return municipioResidencia;
    }

    public void setMunicipioResidencia(String municipioResidencia) {
        this.municipioResidencia = municipioResidencia;
    }

    public String getCodigoMunicipioResidencia() {
        return codigoMunicipioResidencia;
    }

    public void setCodigoMunicipioResidencia(String codigoMunicipioResidencia) {
        this.codigoMunicipioResidencia = codigoMunicipioResidencia;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}