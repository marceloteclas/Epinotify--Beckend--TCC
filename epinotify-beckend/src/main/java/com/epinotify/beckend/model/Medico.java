package com.epinotify.beckend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.time.LocalDate;

@Embeddable
public class Medico {

    // Campo 41
    @Column(name = "medico_nome", length = 200)
    private String nomeMedico;

    // Campo 42
    @Column(name = "medico_crm", length = 30)
    private String crm;

    // Campo 43
    @Column(name = "medico_tipo_atestante", length = 30)
    private String tipoMedicoAtestante;

    // Campo 44
    @Column(name = "medico_municipio_svo_iml", length = 150)
    private String municipioSvoIml;

    @Column(name = "medico_uf_svo_iml", length = 2)
    private String ufSvoIml;

    // Campo 45
    @Column(name = "medico_meio_contato", length = 200)
    private String meioContato;

    // Campo 46
    @Column(name = "medico_data_atestado")
    private LocalDate dataAtestado;

    // Campo 47
    @JsonIgnore
    @Column(name = "medico_assinatura_imagem", columnDefinition = "bytea")
    private byte[] assinaturaImagem;

    @Column(name = "medico_assinatura_tipo", length = 50)
    private String assinaturaTipo;

    public Medico() {
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getTipoMedicoAtestante() {
        return tipoMedicoAtestante;
    }

    public void setTipoMedicoAtestante(String tipoMedicoAtestante) {
        this.tipoMedicoAtestante = tipoMedicoAtestante;
    }

    public String getMunicipioSvoIml() {
        return municipioSvoIml;
    }

    public void setMunicipioSvoIml(String municipioSvoIml) {
        this.municipioSvoIml = municipioSvoIml;
    }

    public String getUfSvoIml() {
        return ufSvoIml;
    }

    public void setUfSvoIml(String ufSvoIml) {
        this.ufSvoIml = ufSvoIml;
    }

    public String getMeioContato() {
        return meioContato;
    }

    public void setMeioContato(String meioContato) {
        this.meioContato = meioContato;
    }

    public LocalDate getDataAtestado() {
        return dataAtestado;
    }

    public void setDataAtestado(LocalDate dataAtestado) {
        this.dataAtestado = dataAtestado;
    }

    public byte[] getAssinaturaImagem() {
        return assinaturaImagem;
    }

    public void setAssinaturaImagem(byte[] assinaturaImagem) {
        this.assinaturaImagem = assinaturaImagem;
    }

    public String getAssinaturaTipo() {
        return assinaturaTipo;
    }

    public void setAssinaturaTipo(String assinaturaTipo) {
        this.assinaturaTipo = assinaturaTipo;
    }
}