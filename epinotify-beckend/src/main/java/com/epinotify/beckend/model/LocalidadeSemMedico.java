package com.epinotify.beckend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class LocalidadeSemMedico {

    // Campo 58
    @Column(name = "sem_medico_declarante", length = 200)
    private String declarante;

    // Campo 59 - Testemunha A
    @Column(name = "sem_medico_testemunha_a", length = 200)
    private String testemunhaA;

    // Campo 59 - Testemunha B
    @Column(name = "sem_medico_testemunha_b", length = 200)
    private String testemunhaB;

    public LocalidadeSemMedico() {
    }

    public String getDeclarante() {
        return declarante;
    }

    public void setDeclarante(String declarante) {
        this.declarante = declarante;
    }

    public String getTestemunhaA() {
        return testemunhaA;
    }

    public void setTestemunhaA(String testemunhaA) {
        this.testemunhaA = testemunhaA;
    }

    public String getTestemunhaB() {
        return testemunhaB;
    }

    public void setTestemunhaB(String testemunhaB) {
        this.testemunhaB = testemunhaB;
    }
}