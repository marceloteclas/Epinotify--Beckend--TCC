package com.epinotify.beckend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.time.LocalDate;
import java.time.LocalTime;

@Embeddable
public class Identificacao {

    @Column(name = "ident_tipo_obito")
    private String tipoObito;

    @Column(name = "ident_data_obito")
    private LocalDate dataObito;

    @Column(name = "ident_hora_obito")
    private LocalTime horaObito;

    @Column(name = "ident_cartao_sus", length = 20)
    private String cartaoSus;

    @Column(name = "ident_naturalidade", length = 150)
    private String naturalidade;

    @Column(name = "ident_nome_falecido", length = 200)
    private String nomeFalecido;

    @Column(name = "ident_nome_pai", length = 200)
    private String nomePai;

    @Column(name = "ident_nome_mae", length = 200)
    private String nomeMae;

    @Column(name = "ident_data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "ident_idade_valor")
    private Integer idadeValor;

    @Column(name = "ident_idade_unidade", length = 20)
    private String idadeUnidade;

    @Column(name = "ident_sexo", length = 20)
    private String sexo;

    @Column(name = "ident_raca_cor", length = 30)
    private String racaCor;

    @Column(name = "ident_situacao_conjugal", length = 50)
    private String situacaoConjugal;

    @Column(name = "ident_escolaridade_nivel", length = 50)
    private String escolaridadeNivel;

    @Column(name = "ident_escolaridade_serie")
    private Integer escolaridadeSerie;

    @Column(name = "ident_ocupacao_habitual", length = 200)
    private String ocupacaoHabitual;

    @Column(name = "ident_codigo_cbo", length = 20)
    private String codigoCbo;

    public Identificacao() {
    }

    public String getTipoObito() {
        return tipoObito;
    }

    public void setTipoObito(String tipoObito) {
        this.tipoObito = tipoObito;
    }

    public LocalDate getDataObito() {
        return dataObito;
    }

    public void setDataObito(LocalDate dataObito) {
        this.dataObito = dataObito;
    }

    public LocalTime getHoraObito() {
        return horaObito;
    }

    public void setHoraObito(LocalTime horaObito) {
        this.horaObito = horaObito;
    }

    public String getCartaoSus() {
        return cartaoSus;
    }

    public void setCartaoSus(String cartaoSus) {
        this.cartaoSus = cartaoSus;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public String getNomeFalecido() {
        return nomeFalecido;
    }

    public void setNomeFalecido(String nomeFalecido) {
        this.nomeFalecido = nomeFalecido;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getIdadeValor() {
        return idadeValor;
    }

    public void setIdadeValor(Integer idadeValor) {
        this.idadeValor = idadeValor;
    }

    public String getIdadeUnidade() {
        return idadeUnidade;
    }

    public void setIdadeUnidade(String idadeUnidade) {
        this.idadeUnidade = idadeUnidade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getRacaCor() {
        return racaCor;
    }

    public void setRacaCor(String racaCor) {
        this.racaCor = racaCor;
    }

    public String getSituacaoConjugal() {
        return situacaoConjugal;
    }

    public void setSituacaoConjugal(String situacaoConjugal) {
        this.situacaoConjugal = situacaoConjugal;
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
}