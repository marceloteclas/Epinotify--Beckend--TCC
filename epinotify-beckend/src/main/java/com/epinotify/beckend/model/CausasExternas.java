package com.epinotify.beckend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class CausasExternas {

    // Campo 48
    @Column(name = "externas_tipo_morte", length = 30)
    private String tipoMorteNaoNatural;

    // Campo 49
    @Column(name = "externas_acidente_trabalho", length = 20)
    private String acidenteTrabalho;

    // Campo 50
    @Column(name = "externas_fonte_informacao", length = 50)
    private String fonteInformacao;

    @Column(name = "externas_numero_ocorrencia_policial", length = 50)
    private String numeroOcorrenciaPolicial;

    // Campo 51
    @Column(name = "externas_descricao_evento", length = 1000)
    private String descricaoEvento;

    @Column(name = "externas_tipo_local_ocorrencia", length = 50)
    private String tipoLocalOcorrencia;

    // Campo 52
    @Column(name = "externas_logradouro", length = 250)
    private String logradouro;

    @Column(name = "externas_numero", length = 20)
    private String numero;

    @Column(name = "externas_bairro", length = 150)
    private String bairro;

    @Column(name = "externas_municipio", length = 150)
    private String municipio;

    @Column(name = "externas_uf", length = 2)
    private String uf;

    public CausasExternas() {
    }

    public String getTipoMorteNaoNatural() {
        return tipoMorteNaoNatural;
    }

    public void setTipoMorteNaoNatural(String tipoMorteNaoNatural) {
        this.tipoMorteNaoNatural = tipoMorteNaoNatural;
    }

    public String getAcidenteTrabalho() {
        return acidenteTrabalho;
    }

    public void setAcidenteTrabalho(String acidenteTrabalho) {
        this.acidenteTrabalho = acidenteTrabalho;
    }

    public String getFonteInformacao() {
        return fonteInformacao;
    }

    public void setFonteInformacao(String fonteInformacao) {
        this.fonteInformacao = fonteInformacao;
    }

    public String getNumeroOcorrenciaPolicial() {
        return numeroOcorrenciaPolicial;
    }

    public void setNumeroOcorrenciaPolicial(String numeroOcorrenciaPolicial) {
        this.numeroOcorrenciaPolicial = numeroOcorrenciaPolicial;
    }

    public String getDescricaoEvento() {
        return descricaoEvento;
    }

    public void setDescricaoEvento(String descricaoEvento) {
        this.descricaoEvento = descricaoEvento;
    }

    public String getTipoLocalOcorrencia() {
        return tipoLocalOcorrencia;
    }

    public void setTipoLocalOcorrencia(String tipoLocalOcorrencia) {
        this.tipoLocalOcorrencia = tipoLocalOcorrencia;
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

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
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