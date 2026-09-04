package com.epinotify.beckend.controller;

import org.springframework.web.multipart.MultipartFile;

import com.epinotify.beckend.model.DeclaracaoObito;
import com.epinotify.beckend.model.Medico;
import com.epinotify.beckend.model.StatusDeclaracao;

import com.epinotify.beckend.service.DeclaracaoObitoService;
import com.epinotify.beckend.service.ArquivoStorageService;
import com.epinotify.beckend.service.OcrProcessamentoService;
import com.epinotify.beckend.dto.ResultadoOcrResponse;

import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/declaracoes")
@CrossOrigin(originPatterns = {
                "http://localhost:*",
                "http://127.0.0.1:*"
})
public class DeclaracaoObitoController {

        private final DeclaracaoObitoService declaracaoService;
        private final ArquivoStorageService arquivoStorageService;
        private final OcrProcessamentoService ocrProcessamentoService;

        public DeclaracaoObitoController(
                        DeclaracaoObitoService declaracaoService,
                        ArquivoStorageService arquivoStorageService,
                        OcrProcessamentoService ocrProcessamentoService) {

                this.declaracaoService = declaracaoService;
                this.arquivoStorageService = arquivoStorageService;
                this.ocrProcessamentoService = ocrProcessamentoService;
        }

        // =========================================================
        // CONSULTAS
        // =========================================================

        @GetMapping
        public ResponseEntity<List<DeclaracaoObito>> listarTodas() {

                return ResponseEntity.ok(
                                declaracaoService.listarTodas());
        }

        @GetMapping("/{id}")
        public ResponseEntity<DeclaracaoObito> buscarPorId(
                        @PathVariable Long id) {

                return ResponseEntity.ok(
                                declaracaoService.buscarPorId(id));
        }

        @GetMapping("/{id}/arquivo")
        public ResponseEntity<Resource> obterArquivoOriginal(
                        @PathVariable Long id) {

                DeclaracaoObito declaracao = declaracaoService.buscarPorId(id);
                Resource arquivo = arquivoStorageService.carregar(
                                declaracao.getCaminhoArquivoTemporario());

                MediaType mediaType;

                try {
                        mediaType = declaracao.getTipoArquivo() == null
                                        ? MediaType.APPLICATION_OCTET_STREAM
                                        : MediaType.parseMediaType(declaracao.getTipoArquivo());
                } catch (Exception exception) {
                        mediaType = MediaType.APPLICATION_OCTET_STREAM;
                }

                String nomeArquivo = declaracao.getNomeArquivoOriginal() == null
                                ? "declaracao-obito"
                                : declaracao.getNomeArquivoOriginal();

                String disposicao = ContentDisposition.inline()
                                .filename(nomeArquivo, StandardCharsets.UTF_8)
                                .build()
                                .toString();

                return ResponseEntity.ok()
                                .contentType(mediaType)
                                .header(HttpHeaders.CONTENT_DISPOSITION, disposicao)
                                .body(arquivo);
        }

        @GetMapping("/status/{status}")
        public ResponseEntity<List<DeclaracaoObito>> buscarPorStatus(
                        @PathVariable StatusDeclaracao status) {

                return ResponseEntity.ok(
                                declaracaoService.buscarPorStatus(status));
        }

        @GetMapping("/buscar")
        public ResponseEntity<List<DeclaracaoObito>> buscarPorNome(
                        @RequestParam String nome) {

                return ResponseEntity.ok(
                                declaracaoService
                                                .buscarPorNomeFalecido(nome));
        }

        // =========================================================
        // CRIAÇÃO
        // =========================================================

        @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity<DeclaracaoObito> upload(
                        @RequestParam("arquivo") MultipartFile arquivo,

                        @RequestParam Long usuarioId) {

                DeclaracaoObito declaracao = declaracaoService
                                .criarPorUpload(
                                                arquivo,
                                                usuarioId);

                ocrProcessamentoService.processar(
                                declaracao.getId());

                return ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(declaracao);
        }

        @GetMapping("/{id}/ocr")
        public ResponseEntity<ResultadoOcrResponse> obterResultadoOcr(
                        @PathVariable Long id) {

                return ResponseEntity.ok(
                                ocrProcessamentoService.obterResultado(id));
        }

        @PostMapping("/{id}/ocr/reprocessar")
        public ResponseEntity<DeclaracaoObito> reprocessarOcr(
                        @PathVariable Long id) {

                DeclaracaoObito declaracao = ocrProcessamentoService
                                .prepararReprocessamento(id);

                ocrProcessamentoService.processar(id);

                return ResponseEntity
                                .status(HttpStatus.ACCEPTED)
                                .body(declaracao);
        }

        @PostMapping
        public ResponseEntity<DeclaracaoObito> criar(
                        @RequestParam Long usuarioId,
                        @RequestBody DeclaracaoObito declaracao) {

                DeclaracaoObito declaracaoCriada = declaracaoService.criar(
                                declaracao,
                                usuarioId);

                return ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(declaracaoCriada);
        }

        // =========================================================
        // ALTERAÇÃO
        // =========================================================

        @PutMapping("/{id}")
        public ResponseEntity<DeclaracaoObito> atualizarDados(
                        @PathVariable Long id,
                        @RequestBody DeclaracaoObito declaracao) {

                return ResponseEntity.ok(
                                declaracaoService.atualizarDados(
                                                id,
                                                declaracao));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> excluir(
                        @PathVariable Long id) {

                declaracaoService.excluir(id);

                return ResponseEntity.noContent().build();
        }

        @PutMapping(value = "/{id}/arquivo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity<DeclaracaoObito> reanexarArquivoOriginal(
                        @PathVariable Long id,
                        @RequestParam("arquivo") MultipartFile arquivo) {

                return ResponseEntity.ok(
                                declaracaoService.reanexarArquivoOriginal(
                                                id,
                                                arquivo));
        }

        // =========================================================
        // STATUS
        // =========================================================

        @PatchMapping("/{id}/pendente")
        public ResponseEntity<DeclaracaoObito> marcarComoPendente(
                        @PathVariable Long id) {

                return ResponseEntity.ok(
                                declaracaoService
                                                .marcarComoPendente(id));
        }

        @PatchMapping("/{id}/falha")
        public ResponseEntity<DeclaracaoObito> marcarFalha(
                        @PathVariable Long id) {

                return ResponseEntity.ok(
                                declaracaoService
                                                .marcarFalhaProcessamento(id));
        }

        @PatchMapping("/{id}/confirmar")
        public ResponseEntity<DeclaracaoObito> confirmar(
                        @PathVariable Long id) {

                return ResponseEntity.ok(
                                declaracaoService.confirmar(id));
        }

        // =========================================================
        // DASHBOARD
        // =========================================================

        @GetMapping("/indicadores")
        public ResponseEntity<Map<String, Long>> indicadores() {

                Map<String, Long> indicadores = new LinkedHashMap<>();

                indicadores.put(
                                "total",
                                declaracaoService.contarTotal());

                indicadores.put(
                                "cadastradasHoje",
                                declaracaoService.contarCadastradasHoje());

                indicadores.put(
                                "pendentes",
                                declaracaoService
                                                .contarPendentes());

                indicadores.put(
                                "concluidas",
                                declaracaoService
                                                .contarConcluidas());

                return ResponseEntity.ok(indicadores);
        }

        // =========================================================
        // ASSINATURA DO MÉDICO
        // =========================================================

        @GetMapping("/{id}/assinatura")
        public ResponseEntity<byte[]> obterAssinatura(
                        @PathVariable Long id) {

                DeclaracaoObito declaracao = declaracaoService.buscarPorId(id);

                Medico medico = declaracao.getMedico();

                if (medico == null
                                || medico.getAssinaturaImagem() == null
                                || medico.getAssinaturaImagem().length == 0) {

                        return ResponseEntity
                                        .notFound()
                                        .build();
                }

                String tipo = medico.getAssinaturaTipo();

                MediaType mediaType;

                try {

                        mediaType = tipo != null
                                        && !tipo.isBlank()
                                                        ? MediaType.parseMediaType(tipo)
                                                        : MediaType.parseMediaType("image/png");

                } catch (Exception exception) {

                        mediaType = MediaType.APPLICATION_OCTET_STREAM;
                }

                return ResponseEntity
                                .ok()
                                .contentType(mediaType)
                                .body(
                                                medico.getAssinaturaImagem());
        }
}
