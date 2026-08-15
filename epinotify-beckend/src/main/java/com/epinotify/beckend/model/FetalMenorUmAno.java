package com.epinotify.beckend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class FetalMenorUmAno {

    @Column(name = "fetal_idade_mae")
    private Integer idadeMae;

    @Column(name = "fetal_escolaridade_nivel", length = 50)
    private String escolaridadeNivel;

    @Column(name = "fetal_escolaridade_serie")
    private Integer escolaridadeSerie;

    @Column(name = "fetal_ocupacao_habitual", length = 200)
    private String ocupacaoHabitual;

    @Column(name = "fetal_codigo_cbo", length = 20)
    private String codigoCbo;

    @Column(name = "fetal_filhos_nascidos_vivos")
    private Integer numeroFilhosNascidosVivos;

    @Column(name = "fetal_perdas_fetais_abortos")
    private Integer numeroPerdasFetaisAbortos;

    @Column(name = "fetal_semanas_gestacao")
    private Integer semanasGestacao;

    @Column(name = "fetal_tipo_gravidez", length = 30)
    private String tipoGravidez;

    @Column(name = "fetal_tipo_parto", length = 30)
    private String tipoParto;

    @Column(name = "fetal_morte_relacao_parto", length = 30)
    private String morteRelacaoParto;

    @Column(name = "fetal_peso_ao_nascer")
    private Integer pesoAoNascer;

    @Column(name = "fetal_numero_dnv", length = 30)
    private String numeroDeclaracaoNascidoVivo;

    public FetalMenorUmAno() {
    }

    public Integer getIdadeMae() {
        return idadeMae;
    }

    public void setIdadeMae(Integer idadeMae) {
        this.idadeMae = idadeMae;
    }

    public String getEscolaridadeNivel() {
        return escolaridadeNivel;
    }

    public void setEscolaridadeNivel(String escolaridadeNivel) {
        this.escolaridadeNivel = escolaridadeNivel;
    }

    public Integer getEscolaridadeSerie() {
        return escolaridadeSerie;
    }

    public void setEscolaridadeSerie(Integer escolaridadeSerie) {
        this.escolaridadeSerie = escolaridadeSerie;
    }

    public String getOcupacaoHabitual() {
        return ocupacaoHabitual;
    }

    public void setOcupacaoHabitual(String ocupacaoHabitual) {
        this.ocupacaoHabitual = ocupacaoHabitual;
    }

    public String getCodigoCbo() {
        return codigoCbo;
    }

    public void setCodigoCbo(String codigoCbo) {
        this.codigoCbo = codigoCbo;
    }

    public Integer getNumeroFilhosNascidosVivos() {
        return numeroFilhosNascidosVivos;
    }

    public void setNumeroFilhosNascidosVivos(Integer numeroFilhosNascidosVivos) {
        this.numeroFilhosNascidosVivos = numeroFilhosNascidosVivos;
    }

    public Integer getNumeroPerdasFetaisAbortos() {
        return numeroPerdasFetaisAbortos;
    }

    public void setNumeroPerdasFetaisAbortos(Integer numeroPerdasFetaisAbortos) {
        this.numeroPerdasFetaisAbortos = numeroPerdasFetaisAbortos;
    }

    public Integer getSemanasGestacao() {
        return semanasGestacao;
    }

    public void setSemanasGestacao(Integer semanasGestacao) {
        this.semanasGestacao = semanasGestacao;
    }

    public String getTipoGravidez() {
        return tipoGravidez;
    }

    public void setTipoGravidez(String tipoGravidez) {
        this.tipoGravidez = tipoGravidez;
    }

    public String getTipoParto() {
        return tipoParto;
    }

    public void setTipoParto(String tipoParto) {
        this.tipoParto = tipoParto;
    }

    public String getMorteRelacaoParto() {
        return morteRelacaoParto;
    }

    public void setMorteRelacaoParto(String morteRelacaoParto) {
        this.morteRelacaoParto = morteRelacaoParto;
    }

    public Integer getPesoAoNascer() {
        return pesoAoNascer;
    }

    public void setPesoAoNascer(Integer pesoAoNascer) {
        this.pesoAoNascer = pesoAoNascer;
    }

    public String getNumeroDeclaracaoNascidoVivo() {
        return numeroDeclaracaoNascidoVivo;
    }

    public void setNumeroDeclaracaoNascidoVivo(String numeroDeclaracaoNascidoVivo) {
        this.numeroDeclaracaoNascidoVivo = numeroDeclaracaoNascidoVivo;
    }
}