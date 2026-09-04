package com.epinotify.beckend.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.epinotify.beckend.dto.ResultadoOcrResponse;
import com.epinotify.beckend.model.DeclaracaoObito;
import com.epinotify.beckend.model.StatusDeclaracao;
import com.epinotify.beckend.repository.DeclaracaoObitoRepository;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OcrProcessamentoService {

    private final DeclaracaoObitoRepository declaracaoRepository;
    private final OcrClientService ocrClientService;
    private final ObjectMapper objectMapper;

    public OcrProcessamentoService(
            DeclaracaoObitoRepository declaracaoRepository,
            OcrClientService ocrClientService,
            ObjectMapper objectMapper) {
        this.declaracaoRepository = declaracaoRepository;
        this.ocrClientService = ocrClientService;
        this.objectMapper = objectMapper;
    }

    @Async("ocrTaskExecutor")
    public void processar(Long declaracaoId) {
        DeclaracaoObito declaracao = buscar(declaracaoId);

        try {
            String caminho = declaracao.getCaminhoArquivoTemporario();
            if (caminho == null || caminho.isBlank()) {
                throw new IllegalStateException(
                        "A declaração não possui arquivo para processamento.");
            }

            Path arquivo = Path.of(caminho).toAbsolutePath().normalize();
            if (!Files.isRegularFile(arquivo)) {
                throw new IllegalStateException(
                        "O arquivo da declaração não foi encontrado.");
            }

            JsonNode resultado = ocrClientService.extrairDeclaracao(
                    arquivo,
                    declaracao.getTipoArquivo());

            DeclaracaoObito atualizada = buscar(declaracaoId);
            atualizada.setResultadoOcrJson(
                    objectMapper.writeValueAsString(resultado));
            atualizada.setErroProcessamentoOcr(null);
            atualizada.setDataProcessamentoOcr(LocalDateTime.now());
            atualizada.setStatus(StatusDeclaracao.PENDENTE);
            declaracaoRepository.save(atualizada);
        } catch (Exception exception) {
            registrarFalha(declaracaoId, exception);
        }
    }

    public DeclaracaoObito prepararReprocessamento(Long declaracaoId) {
        DeclaracaoObito declaracao = buscar(declaracaoId);

        if (declaracao.getStatus() == StatusDeclaracao.EM_PROCESSAMENTO) {
            throw new IllegalStateException(
                    "A declaração já está sendo processada pelo OCR.");
        }

        String caminho = declaracao.getCaminhoArquivoTemporario();
        if (caminho == null || caminho.isBlank()) {
            throw new IllegalStateException(
                    "A declaração não possui arquivo original para reprocessamento.");
        }

        Path arquivo = Path.of(caminho).toAbsolutePath().normalize();
        if (!Files.isRegularFile(arquivo)) {
            throw new IllegalStateException(
                    "O arquivo original da declaração não foi encontrado.");
        }

        declaracao.setResultadoOcrJson(null);
        declaracao.setErroProcessamentoOcr(null);
        declaracao.setDataProcessamentoOcr(null);
        declaracao.setStatus(StatusDeclaracao.EM_PROCESSAMENTO);

        return declaracaoRepository.save(declaracao);
    }

    public ResultadoOcrResponse obterResultado(Long declaracaoId) {
        DeclaracaoObito declaracao = buscar(declaracaoId);
        JsonNode resultado = null;

        if (declaracao.getResultadoOcrJson() != null) {
            try {
                resultado = objectMapper.readTree(
                        declaracao.getResultadoOcrJson());
            } catch (JacksonException exception) {
                throw new IllegalStateException(
                        "O resultado OCR armazenado está inválido.",
                        exception);
            }
        }

        return new ResultadoOcrResponse(
                declaracao.getId(),
                declaracao.getStatus(),
                declaracao.getStatus() != StatusDeclaracao.EM_PROCESSAMENTO,
                resultado,
                declaracao.getErroProcessamentoOcr(),
                declaracao.getDataProcessamentoOcr());
    }

    private DeclaracaoObito buscar(Long declaracaoId) {
        return declaracaoRepository.findById(declaracaoId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Declaração de Óbito não encontrada com o ID: "
                                + declaracaoId));
    }

    private void registrarFalha(
            Long declaracaoId,
            Exception exception) {
        DeclaracaoObito declaracao = buscar(declaracaoId);
        String mensagem = exception.getMessage();
        if (mensagem == null || mensagem.isBlank()) {
            mensagem = exception.getClass().getSimpleName();
        }
        if (mensagem.length() > 1900) {
            mensagem = mensagem.substring(0, 1900);
        }
        declaracao.setResultadoOcrJson(null);
        declaracao.setErroProcessamentoOcr(mensagem);
        declaracao.setDataProcessamentoOcr(LocalDateTime.now());
        declaracao.setStatus(StatusDeclaracao.FALHA_PROCESSAMENTO);
        declaracaoRepository.save(declaracao);
    }
}
