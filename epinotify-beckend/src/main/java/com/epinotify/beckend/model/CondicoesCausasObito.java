package com.epinotify.beckend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class CondicoesCausasObito {

    // Campo 37
    @Column(name = "causas_obito_mulher_idade_fertil", length = 50)
    private String obitoMulherIdadeFertil;

    // Campo 38
    @Column(name = "causas_assistencia_medica", length = 20)
    private String recebeuAssistenciaMedica;

    // Campo 39
    @Column(name = "causas_necropsia", length = 20)
    private String necropsia;

    // Campo 40 - Parte I - Linha A
    @Column(name = "causas_linha_a", length = 500)
    private String causaLinhaA;

    @Column(name = "causas_tempo_linha_a", length = 100)
    private String tempoAproximadoLinhaA;

    @Column(name = "causas_cid_linha_a", length = 20)
    private String cidLinhaA;

    // Campo 40 - Parte I - Linha B
    @Column(name = "causas_linha_b", length = 500)
    private String causaLinhaB;

    @Column(name = "causas_tempo_linha_b", length = 100)
    private String tempoAproximadoLinhaB;

    @Column(name = "causas_cid_linha_b", length = 20)
    private String cidLinhaB;

    // Campo 40 - Parte I - Linha C
    @Column(name = "causas_linha_c", length = 500)
    private String causaLinhaC;

    @Column(name = "causas_tempo_linha_c", length = 100)
    private String tempoAproximadoLinhaC;

    @Column(name = "causas_cid_linha_c", length = 20)
    private String cidLinhaC;

    // Campo 40 - Parte I - Linha D
    @Column(name = "causas_linha_d", length = 500)
    private String causaLinhaD;

    @Column(name = "causas_tempo_linha_d", length = 100)
    private String tempoAproximadoLinhaD;

    @Column(name = "causas_cid_linha_d", length = 20)
    private String cidLinhaD;

    // Campo 40 - Parte II
    @Column(name = "causas_parte_ii", length = 1000)
    private String outrasCondicoesSignificativas;

    @Column(name = "causas_tempo_parte_ii", length = 100)
    private String tempoAproximadoParteII;

    @Column(name = "causas_cid_parte_ii", length = 20)
    private String cidParteII;

    public CondicoesCausasObito() {
    }

    public String getObitoMulherIdadeFertil() {
        return obitoMulherIdadeFertil;
    }

    public void setObitoMulherIdadeFertil(String obitoMulherIdadeFertil) {
        this.obitoMulherIdadeFertil = obitoMulherIdadeFertil;
    }

    public String getRecebeuAssistenciaMedica() {
        return recebeuAssistenciaMedica;
    }

    public void setRecebeuAssistenciaMedica(String recebeuAssistenciaMedica) {
        this.recebeuAssistenciaMedica = recebeuAssistenciaMedica;
    }

    public String getNecropsia() {
        return necropsia;
    }

    public void setNecropsia(String necropsia) {
        this.necropsia = necropsia;
    }

    public String getCausaLinhaA() {
        return causaLinhaA;
    }

    public void setCausaLinhaA(String causaLinhaA) {
        this.causaLinhaA = causaLinhaA;
    }

    public String getTempoAproximadoLinhaA() {
        return tempoAproximadoLinhaA;
    }

    public void setTempoAproximadoLinhaA(String tempoAproximadoLinhaA) {
        this.tempoAproximadoLinhaA = tempoAproximadoLinhaA;
    }

    public String getCidLinhaA() {
        return cidLinhaA;
    }

    public void setCidLinhaA(String cidLinhaA) {
        this.cidLinhaA = cidLinhaA;
    }

    public String getCausaLinhaB() {
        return causaLinhaB;
    }

    public void setCausaLinhaB(String causaLinhaB) {
        this.causaLinhaB = causaLinhaB;
    }

    public String getTempoAproximadoLinhaB() {
        return tempoAproximadoLinhaB;
    }

    public void setTempoAproximadoLinhaB(String tempoAproximadoLinhaB) {
        this.tempoAproximadoLinhaB = tempoAproximadoLinhaB;
    }

    public String getCidLinhaB() {
        return cidLinhaB;
    }

    public void setCidLinhaB(String cidLinhaB) {
        this.cidLinhaB = cidLinhaB;
    }

    public String getCausaLinhaC() {
        return causaLinhaC;
    }

    public void setCausaLinhaC(String causaLinhaC) {
        this.causaLinhaC = causaLinhaC;
    }

    public String getTempoAproximadoLinhaC() {
        return tempoAproximadoLinhaC;
    }

    public void setTempoAproximadoLinhaC(String tempoAproximadoLinhaC) {
        this.tempoAproximadoLinhaC = tempoAproximadoLinhaC;
    }

    public String getCidLinhaC() {
        return cidLinhaC;
    }

    public void setCidLinhaC(String cidLinhaC) {
        this.cidLinhaC = cidLinhaC;
    }

    public String getCausaLinhaD() {
        return causaLinhaD;
    }

    public void setCausaLinhaD(String causaLinhaD) {
        this.causaLinhaD = causaLinhaD;
    }

    public String getTempoAproximadoLinhaD() {
        return tempoAproximadoLinhaD;
    }

    public void setTempoAproximadoLinhaD(String tempoAproximadoLinhaD) {
        this.tempoAproximadoLinhaD = tempoAproximadoLinhaD;
    }

    public String getCidLinhaD() {
        return cidLinhaD;
    }

    public void setCidLinhaD(String cidLinhaD) {
        this.cidLinhaD = cidLinhaD;
    }

    public String getOutrasCondicoesSignificativas() {
        return outrasCondicoesSignificativas;
    }

    public void setOutrasCondicoesSignificativas(String outrasCondicoesSignificativas) {
        this.outrasCondicoesSignificativas = outrasCondicoesSignificativas;
    }

    public String getTempoAproximadoParteII() {
        return tempoAproximadoParteII;
    }

    public void setTempoAproximadoParteII(String tempoAproximadoParteII) {
        this.tempoAproximadoParteII = tempoAproximadoParteII;
    }

    public String getCidParteII() {
        return cidParteII;
    }

    public void setCidParteII(String cidParteII) {
        this.cidParteII = cidParteII;
    }
}