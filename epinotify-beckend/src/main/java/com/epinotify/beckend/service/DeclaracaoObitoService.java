package com.epinotify.beckend.service;

import org.springframework.web.multipart.MultipartFile;

import com.epinotify.beckend.model.DeclaracaoObito;
import com.epinotify.beckend.model.StatusDeclaracao;
import com.epinotify.beckend.model.Usuario;

import com.epinotify.beckend.repository.DeclaracaoObitoRepository;
import com.epinotify.beckend.repository.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.List;

@Service
public class DeclaracaoObitoService {

        private final DeclaracaoObitoRepository declaracaoRepository;
        private final UsuarioRepository usuarioRepository;
        private final ArquivoStorageService arquivoStorageService;

        public DeclaracaoObitoService(
                        DeclaracaoObitoRepository declaracaoRepository,
                        UsuarioRepository usuarioRepository,
                        ArquivoStorageService arquivoStorageService) {

                this.declaracaoRepository = declaracaoRepository;
                this.usuarioRepository = usuarioRepository;
                this.arquivoStorageService = arquivoStorageService;
        }

        // =========================================================
        // CONSULTAS
        // =========================================================

        @Transactional(readOnly = true)
        public List<DeclaracaoObito> listarTodas() {

                return declaracaoRepository
                                .findAllByOrderByDataEnvioDesc();
        }

        @Transactional(readOnly = true)
        public DeclaracaoObito buscarPorId(Long id) {

                return declaracaoRepository.findById(id)
                                .orElseThrow(() -> new EntityNotFoundException(
                                                "Declaração de Óbito não encontrada com o ID: " + id));
        }

        @Transactional(readOnly = true)
        public List<DeclaracaoObito> buscarPorStatus(
                        StatusDeclaracao status) {

                return declaracaoRepository.findByStatus(status);
        }

        @Transactional(readOnly = true)
        public List<DeclaracaoObito> buscarPorNomeFalecido(
                        String nome) {

                return declaracaoRepository
                                .findByIdentificacaoNomeFalecidoContainingIgnoreCase(nome);
        }

        // =========================================================
        // CRIAÇÃO
        // =========================================================

        @Transactional
        public DeclaracaoObito criar(
                        DeclaracaoObito declaracao,
                        Long usuarioId) {

                Usuario usuario = usuarioRepository
                                .findById(usuarioId)
                                .orElseThrow(() -> new EntityNotFoundException(
                                                "Usuário não encontrado com o ID: " + usuarioId));

                normalizarNumeroDeclaracao(declaracao);

                validarNumeroDeclaracao(
                                declaracao.getNumeroDeclaracao(),
                                null);

                declaracao.setId(null);
                declaracao.setUsuario(usuario);
                declaracao.setStatus(
                                StatusDeclaracao.EM_PROCESSAMENTO);

                return declaracaoRepository.save(declaracao);
        }

        @Transactional
        public DeclaracaoObito criarPorUpload(
                        MultipartFile arquivo,
                        Long usuarioId) {

                String caminhoArquivo = arquivoStorageService
                                .salvarTemporariamente(arquivo);

                try {

                        DeclaracaoObito declaracao = new DeclaracaoObito();

                        declaracao.setNomeArquivoOriginal(
                                        arquivoStorageService
                                                        .obterNomeOriginalSeguro(arquivo));

                        declaracao.setTipoArquivo(
                                        arquivo.getContentType());

                        declaracao.setCaminhoArquivoTemporario(
                                        caminhoArquivo);

                        return criar(
                                        declaracao,
                                        usuarioId);

                } catch (RuntimeException exception) {

                        try {

                                arquivoStorageService.excluir(
                                                caminhoArquivo);

                        } catch (RuntimeException erroExclusao) {

                                exception.addSuppressed(
                                                erroExclusao);
                        }

                        throw exception;
                }
        }

        @Transactional
        public DeclaracaoObito reanexarArquivoOriginal(
                        Long id,
                        MultipartFile arquivo) {

                DeclaracaoObito declaracao = buscarPorId(id);

                String novoCaminho = arquivoStorageService
                                .salvarTemporariamente(arquivo);

                try {

                        declaracao.setNomeArquivoOriginal(
                                        arquivoStorageService
                                                        .obterNomeOriginalSeguro(arquivo));

                        declaracao.setTipoArquivo(
                                        arquivo.getContentType());

                        declaracao.setCaminhoArquivoTemporario(
                                        novoCaminho);

                        return declaracaoRepository.save(declaracao);

                } catch (RuntimeException exception) {

                        try {

                                arquivoStorageService.excluir(
                                                novoCaminho);

                        } catch (RuntimeException erroExclusao) {

                                exception.addSuppressed(
                                                erroExclusao);
                        }

                        throw exception;
                }
        }

        // =========================================================
        // ATUALIZAÇÃO DOS DADOS DA FICHA
        // =========================================================

        @Transactional
        public DeclaracaoObito atualizarDados(
                        Long id,
                        DeclaracaoObito dadosAtualizados) {

                DeclaracaoObito declaracao = buscarPorId(id);

                normalizarNumeroDeclaracao(dadosAtualizados);

                validarNumeroDeclaracao(
                                dadosAtualizados.getNumeroDeclaracao(),
                                id);

                declaracao.setNumeroDeclaracao(
                                dadosAtualizados.getNumeroDeclaracao());

                declaracao.setIdentificacao(
                                dadosAtualizados.getIdentificacao());

                declaracao.setResidencia(
                                dadosAtualizados.getResidencia());

                declaracao.setOcorrencia(
                                dadosAtualizados.getOcorrencia());

                declaracao.setFetalMenorUmAno(
                                dadosAtualizados.getFetalMenorUmAno());

                declaracao.setCondicoesCausasObito(
                                dadosAtualizados.getCondicoesCausasObito());

                if (dadosAtualizados.getMedico() != null) {

                        if (declaracao.getMedico() != null) {

                                dadosAtualizados.getMedico().setAssinaturaImagem(
                                                declaracao.getMedico().getAssinaturaImagem());

                                dadosAtualizados.getMedico().setAssinaturaTipo(
                                                declaracao.getMedico().getAssinaturaTipo());
                        }

                        declaracao.setMedico(
                                        dadosAtualizados.getMedico());
                }

                declaracao.setCausasExternas(
                                dadosAtualizados.getCausasExternas());

                declaracao.setCartorio(
                                dadosAtualizados.getCartorio());

                declaracao.setLocalidadeSemMedico(
                                dadosAtualizados.getLocalidadeSemMedico());

                return declaracaoRepository.save(declaracao);
        }

        // =========================================================
        // PROCESSAMENTO
        // =========================================================

        @Transactional
        public DeclaracaoObito marcarComoPendente(Long id) {

                DeclaracaoObito declaracao = buscarPorId(id);

                declaracao.setStatus(
                                StatusDeclaracao.PENDENTE);

                return declaracaoRepository.save(declaracao);
        }

        @Transactional
        public DeclaracaoObito marcarFalhaProcessamento(Long id) {

                DeclaracaoObito declaracao = buscarPorId(id);

                declaracao.setStatus(
                                StatusDeclaracao.FALHA_PROCESSAMENTO);

                return declaracaoRepository.save(declaracao);
        }

        // =========================================================
        // CONFIRMAÇÃO
        // =========================================================

        @Transactional
        public DeclaracaoObito confirmar(Long id) {

                DeclaracaoObito declaracao = buscarPorId(id);

                if (declaracao.getStatus() != StatusDeclaracao.PENDENTE) {

                        throw new IllegalStateException(
                                        "Somente declarações pendentes podem ser confirmadas.");
                }

                declaracao.setStatus(
                                StatusDeclaracao.CONCLUIDO);

                declaracao.setDataConfirmacao(
                                LocalDateTime.now());

                return declaracaoRepository.save(declaracao);
        }

        // =========================================================
        // EXCLUSÃO
        // =========================================================

        @Transactional
        public void excluir(Long id) {

                DeclaracaoObito declaracao = buscarPorId(id);

                if (declaracao.getStatus() == StatusDeclaracao.EM_PROCESSAMENTO) {
                        throw new IllegalStateException(
                                        "Aguarde o término do processamento antes de excluir a declaração.");
                }

                String caminhoArquivo = declaracao.getCaminhoArquivoTemporario();

                declaracaoRepository.delete(declaracao);
                declaracaoRepository.flush();

                arquivoStorageService.excluir(caminhoArquivo);
        }

        // =========================================================
        // DASHBOARD
        // =========================================================

        @Transactional(readOnly = true)
        public long contarTotal() {

                return declaracaoRepository.count();
        }

        @Transactional(readOnly = true)
        public long contarPendentes() {

                return declaracaoRepository.countByStatus(
                                StatusDeclaracao.PENDENTE);
        }

        @Transactional(readOnly = true)
        public long contarConcluidas() {

                return declaracaoRepository.countByStatus(
                                StatusDeclaracao.CONCLUIDO);
        }

        @Transactional(readOnly = true)
        public long contarCadastradasHoje() {

                LocalDate hoje = LocalDate.now();

                LocalDateTime inicio = hoje.atStartOfDay();

                LocalDateTime fim = hoje.atTime(LocalTime.MAX);

                return declaracaoRepository
                                .countByDataEnvioBetween(
                                                inicio,
                                                fim);
        }

        // =========================================================
        // MÉTODOS INTERNOS
        // =========================================================

        private void validarNumeroDeclaracao(
                        String numeroDeclaracao,
                        Long idAtual) {

                if (numeroDeclaracao == null
                                || numeroDeclaracao.isBlank()) {
                        return;
                }

                declaracaoRepository
                                .findByNumeroDeclaracao(numeroDeclaracao)
                                .ifPresent(declaracaoExistente -> {

                                        if (idAtual == null
                                                        || !declaracaoExistente
                                                                        .getId()
                                                                        .equals(idAtual)) {

                                                throw new IllegalArgumentException(
                                                                "Já existe uma Declaração de Óbito com este número.");
                                        }
                                });
        }

        private void normalizarNumeroDeclaracao(
                        DeclaracaoObito declaracao) {

                String numero = declaracao.getNumeroDeclaracao();

                if (numero != null && numero.isBlank()) {
                        declaracao.setNumeroDeclaracao(null);
                }
        }

}
