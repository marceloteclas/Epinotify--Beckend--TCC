package com.epinotify.beckend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Ocorrencia {

    @Column(name = "ocorr_local", length = 50)
    private String localOcorrencia;

    @Column(name = "ocorr_estabelecimento", length = 200)
    private String estabelecimento;

    @Column(name = "ocorr_codigo_cnes", length = 20)
    private String codigoCnes;

    @Column(name = "ocorr_logradouro", length = 250)
    private String logradouro;

    @Column(name = "ocorr_numero", length = 20)
    private String numero;

    @Column(name = "ocorr_complemento", length = 100)
    private String complemento;

    @Column(name = "ocorr_cep", length = 10)
    private String cep;

    @Column(name = "ocorr_bairro_distrito", length = 150)
    private String bairroDistrito;

    @Column(name = "ocorr_codigo_bairro_distrito", length = 20)
    private String codigoBairroDistrito;

    @Column(name = "ocorr_municipio", length = 150)
    private String municipioOcorrencia;

    @Column(name = "ocorr_codigo_municipio", length = 20)
    private String codigoMunicipioOcorrencia;

    @Column(name = "ocorr_uf", length = 2)
    private String uf;

    public Ocorrencia() {
    }

    public String getLocalOcorrencia() {
        return localOcorrencia;
    }

    public void setLocalOcorrencia(String localOcorrencia) {
        this.localOcorrencia = localOcorrencia;
    }

    public String getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(String estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public String getCodigoCnes() {
        return codigoCnes;
    }

    public void setCodigoCnes(String codigoCnes) {
        this.codigoCnes = codigoCnes;
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

    public String getMunicipioOcorrencia() {
        return municipioOcorrencia;
    }

    public void setMunicipioOcorrencia(String municipioOcorrencia) {
        this.municipioOcorrencia = municipioOcorrencia;
    }

    public String getCodigoMunicipioOcorrencia() {
        return codigoMunicipioOcorrencia;
    }

    public void setCodigoMunicipioOcorrencia(String codigoMunicipioOcorrencia) {
        this.codigoMunicipioOcorrencia = codigoMunicipioOcorrencia;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}