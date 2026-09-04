package com.epinotify.beckend.service;

import java.nio.file.Path;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import tools.jackson.databind.JsonNode;

@Service
public class OcrClientService {

    private final RestClient restClient;

    public OcrClientService(
            @Value("${epinotify.ocr.base-url}") String baseUrl,
            @Value("${epinotify.ocr.connect-timeout:10s}") Duration connectTimeout,
            @Value("${epinotify.ocr.read-timeout:5m}") Duration readTimeout) {

        SimpleClientHttpRequestFactory requestFactory =
                new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(connectTimeout);
        requestFactory.setReadTimeout(readTimeout);

        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .requestFactory(requestFactory)
                .build();
    }

    public JsonNode extrairDeclaracao(
            Path arquivo,
            String tipoArquivo) {

        HttpHeaders cabecalhosArquivo = new HttpHeaders();
        cabecalhosArquivo.setContentType(
                MediaType.parseMediaType(tipoArquivo));

        HttpEntity<FileSystemResource> parteArquivo = new HttpEntity<>(
                new FileSystemResource(arquivo),
                cabecalhosArquivo);

        MultiValueMap<String, Object> partes = new LinkedMultiValueMap<>();
        partes.add("arquivo", parteArquivo);

        try {
            JsonNode resultado = restClient.post()
                    .uri("/extrair/declaracao-obito")
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .body(partes)
                    .retrieve()
                    .body(JsonNode.class);

            if (resultado == null || resultado.isNull()) {
                throw new IllegalStateException(
                        "A API OCR retornou uma resposta vazia.");
            }

            return resultado;
        } catch (RestClientResponseException exception) {
            throw new IllegalStateException(
                    "A API OCR recusou o documento (HTTP "
                            + exception.getStatusCode().value()
                            + "): "
                            + exception.getResponseBodyAsString(),
                    exception);
        }
    }
}
