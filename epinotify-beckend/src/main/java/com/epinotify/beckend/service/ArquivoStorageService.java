package com.epinotify.beckend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

@Service
public class ArquivoStorageService {

    private static final Set<String> EXTENSOES_PERMITIDAS = Set.of(
            "jpg",
            "jpeg",
            "png",
            "pdf");

    private static final Set<String> TIPOS_PERMITIDOS = Set.of(
            "image/jpeg",
            "image/png",
            "application/pdf");

    private final Path diretorioUpload;

    public ArquivoStorageService(
            @Value("${epinotify.upload.dir}") String uploadDir) {

        this.diretorioUpload = Path.of(uploadDir)
                .toAbsolutePath()
                .normalize();
    }

    public String salvarTemporariamente(
            MultipartFile arquivo) {

        validarArquivo(arquivo);

        try {

            Files.createDirectories(
                    diretorioUpload);

            String extensao = extrairExtensao(
                    obterNomeOriginalSeguro(arquivo));

            String nomeArmazenado = UUID.randomUUID()
                    + "."
                    + extensao;

            Path destino = diretorioUpload
                    .resolve(nomeArmazenado)
                    .normalize();

            if (!destino.startsWith(diretorioUpload)) {
                throw new IllegalArgumentException(
                        "Caminho de arquivo inválido.");
            }

            arquivo.transferTo(destino);

            return destino.toString();

        } catch (IOException exception) {

            throw new IllegalStateException(
                    "Não foi possível armazenar o arquivo enviado.",
                    exception);
        }
    }

    public String obterNomeOriginalSeguro(
            MultipartFile arquivo) {

        String nome = arquivo.getOriginalFilename();

        if (nome == null || nome.isBlank()) {
            return "arquivo";
        }

        nome = nome.replace("\\", "/");

        int ultimaBarra = nome.lastIndexOf("/");

        if (ultimaBarra >= 0) {
            nome = nome.substring(
                    ultimaBarra + 1);
        }

        return nome;
    }

    public void excluir(String caminho) {

        if (caminho == null || caminho.isBlank()) {
            return;
        }

        Path arquivo = Path.of(caminho)
                .toAbsolutePath()
                .normalize();

        if (!arquivo.startsWith(diretorioUpload)) {
            throw new IllegalStateException(
                    "O arquivo informado não pertence ao diretório de uploads.");
        }

        try {

            Files.deleteIfExists(arquivo);

        } catch (IOException exception) {

            throw new IllegalStateException(
                    "Não foi possível excluir o arquivo.",
                    exception);
        }
    }

    private void validarArquivo(
            MultipartFile arquivo) {

        if (arquivo == null || arquivo.isEmpty()) {
            throw new IllegalArgumentException(
                    "Nenhum arquivo foi enviado.");
        }

        String nome = obterNomeOriginalSeguro(arquivo);

        String extensao = extrairExtensao(nome);

        if (!EXTENSOES_PERMITIDAS.contains(extensao)) {
            throw new IllegalArgumentException(
                    "Formato de arquivo não permitido. Envie JPG, JPEG, PNG ou PDF.");
        }

        String tipo = arquivo.getContentType();

        if (tipo == null
                || !TIPOS_PERMITIDOS.contains(
                        tipo.toLowerCase(Locale.ROOT))) {

            throw new IllegalArgumentException(
                    "Tipo de arquivo não permitido.");
        }
    }

    private String extrairExtensao(
            String nomeArquivo) {

        int ponto = nomeArquivo.lastIndexOf(".");

        if (ponto < 0
                || ponto == nomeArquivo.length() - 1) {

            throw new IllegalArgumentException(
                    "O arquivo enviado não possui uma extensão válida.");
        }

        return nomeArquivo
                .substring(ponto + 1)
                .toLowerCase(Locale.ROOT);
    }
}