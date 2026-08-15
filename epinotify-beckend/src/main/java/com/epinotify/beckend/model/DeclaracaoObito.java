package com.epinotify.beckend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "declaracoes_obito")
public class DeclaracaoObito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Número identificador da própria Declaração de Óbito,
    // quando disponível no documento.
    @Column(name = "numero_declaracao", length = 30, unique = true)
    private String numeroDeclaracao;

    // Profissional responsável pelo processamento/conferência.
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // Situação atual do registro dentro da plataforma.
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private StatusDeclaracao status;

    // Dados do arquivo enviado.
    @Column(name = "nome_arquivo_original", length = 255)
    private String nomeArquivoOriginal;

    @Column(name = "tipo_arquivo", length = 100)
    private String tipoArquivo;

    // Usado apenas enquanto o arquivo original precisar existir.
    @JsonIgnore
    @Column(name = "caminho_arquivo_temporario", length = 500)
    private String caminhoArquivoTemporario;

    // Bloco I
    @Embedded
    private Identificacao identificacao;

    // Bloco II
    @Embedded
    private Residencia residencia;

    // Bloco III
    @Embedded
    private Ocorrencia ocorrencia;

    // Bloco IV
    @Embedded
    private FetalMenorUmAno fetalMenorUmAno;

    // Bloco V
    @Embedded
    private CondicoesCausasObito condicoesCausasObito;

    // Bloco VI
    @Embedded
    private Medico medico;

    // Bloco VII
    @Embedded
    private CausasExternas causasExternas;

    // Bloco VIII
    @Embedded
    private Cartorio cartorio;

    // Bloco IX
    @Embedded
    private LocalidadeSemMedico localidadeSemMedico;

    // Controle interno da plataforma.
    @Column(name = "data_envio", nullable = false)
    private LocalDateTime dataEnvio;

    @Column(name = "data_atualizacao", nullable = false)
    private LocalDateTime dataAtualizacao;

    @Column(name = "data_confirmacao")
    private LocalDateTime dataConfirmacao;

    public DeclaracaoObito() {
    }

    @PrePersist
    public void prePersist() {
        LocalDateTime agora = LocalDateTime.now();

        this.dataEnvio = agora;
        this.dataAtualizacao = agora;

        if (this.status == null) {
            this.status = StatusDeclaracao.EM_PROCESSAMENTO;
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroDeclaracao() {
        return numeroDeclaracao;
    }

    public void setNumeroDeclaracao(String numeroDeclaracao) {
        this.numeroDeclaracao = numeroDeclaracao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public StatusDeclaracao getStatus() {
        return status;
    }

    public void setStatus(StatusDeclaracao status) {
        this.status = status;
    }

    public String getNomeArquivoOriginal() {
        return nomeArquivoOriginal;
    }

    public void setNomeArquivoOriginal(String nomeArquivoOriginal) {
        this.nomeArquivoOriginal = nomeArquivoOriginal;
    }

    public String getTipoArquivo() {
        return tipoArquivo;
    }

    public void setTipoArquivo(String tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }

    public String getCaminhoArquivoTemporario() {
        return caminhoArquivoTemporario;
    }

    public void setCaminhoArquivoTemporario(String caminhoArquivoTemporario) {
        this.caminhoArquivoTemporario = caminhoArquivoTemporario;
    }

    public Identificacao getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(Identificacao identificacao) {
        this.identificacao = identificacao;
    }

    public Residencia getResidencia() {
        return residencia;
    }

    public void setResidencia(Residencia residencia) {
        this.residencia = residencia;
    }

    public Ocorrencia getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(Ocorrencia ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    public FetalMenorUmAno getFetalMenorUmAno() {
        return fetalMenorUmAno;
    }

    public void setFetalMenorUmAno(FetalMenorUmAno fetalMenorUmAno) {
        this.fetalMenorUmAno = fetalMenorUmAno;
    }

    public CondicoesCausasObito getCondicoesCausasObito() {
        return condicoesCausasObito;
    }

    public void setCondicoesCausasObito(
            CondicoesCausasObito condicoesCausasObito) {
        this.condicoesCausasObito = condicoesCausasObito;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public CausasExternas getCausasExternas() {
        return causasExternas;
    }

    public void setCausasExternas(CausasExternas causasExternas) {
        this.causasExternas = causasExternas;
    }

    public Cartorio getCartorio() {
        return cartorio;
    }

    public void setCartorio(Cartorio cartorio) {
        this.cartorio = cartorio;
    }

    public LocalidadeSemMedico getLocalidadeSemMedico() {
        return localidadeSemMedico;
    }

    public void setLocalidadeSemMedico(
            LocalidadeSemMedico localidadeSemMedico) {
        this.localidadeSemMedico = localidadeSemMedico;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public LocalDateTime getDataConfirmacao() {
        return dataConfirmacao;
    }

    public void setDataConfirmacao(LocalDateTime dataConfirmacao) {
        this.dataConfirmacao = dataConfirmacao;
    }
}