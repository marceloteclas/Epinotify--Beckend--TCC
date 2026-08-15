package com.epinotify.beckend.exception;

import org.springframework.web.multipart.MaxUploadSizeExceededException;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

        @ExceptionHandler(EntityNotFoundException.class)
        public ResponseEntity<Map<String, Object>> tratarNaoEncontrado(
                        EntityNotFoundException exception) {

                return criarResposta(
                                HttpStatus.NOT_FOUND,
                                exception.getMessage());
        }

        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<Map<String, Object>> tratarArgumentoInvalido(
                        IllegalArgumentException exception) {

                return criarResposta(
                                HttpStatus.BAD_REQUEST,
                                exception.getMessage());
        }

        @ExceptionHandler(IllegalStateException.class)
        public ResponseEntity<Map<String, Object>> tratarEstadoInvalido(
                        IllegalStateException exception) {

                return criarResposta(
                                HttpStatus.CONFLICT,
                                exception.getMessage());
        }

        private ResponseEntity<Map<String, Object>> criarResposta(
                        HttpStatus status,
                        String mensagem) {

                Map<String, Object> resposta = new LinkedHashMap<>();

                resposta.put(
                                "timestamp",
                                LocalDateTime.now());

                resposta.put(
                                "status",
                                status.value());

                resposta.put(
                                "erro",
                                status.getReasonPhrase());

                resposta.put(
                                "mensagem",
                                mensagem);

                return ResponseEntity
                                .status(status)
                                .body(resposta);
        }

        @ExceptionHandler(MaxUploadSizeExceededException.class)
        public ResponseEntity<Map<String, Object>> tratarArquivoMuitoGrande(
                        MaxUploadSizeExceededException exception) {

                return criarResposta(
                                HttpStatus.PAYLOAD_TOO_LARGE,
                                "O arquivo enviado excede o tamanho máximo permitido.");
        }

}