package com.epinotify.beckend.dto;

import java.time.LocalDateTime;

import com.epinotify.beckend.model.StatusDeclaracao;
import tools.jackson.databind.JsonNode;

public record ResultadoOcrResponse(
        Long declaracaoId,
        StatusDeclaracao statusDeclaracao,
        boolean processamentoConcluido,
        JsonNode resultado,
        String erro,
        LocalDateTime concluidoEm) {
}
