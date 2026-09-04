package com.epinotify.beckend.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epinotify.beckend.model.DeclaracaoObito;
import com.epinotify.beckend.model.StatusDeclaracao;
import com.epinotify.beckend.repository.DeclaracaoObitoRepository;
import com.epinotify.beckend.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
class DeclaracaoObitoServiceTest {

    @Mock
    private DeclaracaoObitoRepository declaracaoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private ArquivoStorageService arquivoStorageService;

    @InjectMocks
    private DeclaracaoObitoService declaracaoService;

    @Test
    void excluirRemoveRegistroEArquivoOriginal() {
        DeclaracaoObito declaracao = new DeclaracaoObito();
        declaracao.setId(12L);
        declaracao.setStatus(StatusDeclaracao.PENDENTE);
        declaracao.setCaminhoArquivoTemporario(
                "uploads/declaracoes/documento.pdf");
        when(declaracaoRepository.findById(12L))
                .thenReturn(Optional.of(declaracao));

        declaracaoService.excluir(12L);

        InOrder ordem = inOrder(
                declaracaoRepository,
                arquivoStorageService);
        ordem.verify(declaracaoRepository).delete(declaracao);
        ordem.verify(declaracaoRepository).flush();
        ordem.verify(arquivoStorageService).excluir(
                "uploads/declaracoes/documento.pdf");
    }

    @Test
    void excluirRecusaDeclaracaoAindaEmProcessamento() {
        DeclaracaoObito declaracao = new DeclaracaoObito();
        declaracao.setId(13L);
        declaracao.setStatus(StatusDeclaracao.EM_PROCESSAMENTO);
        when(declaracaoRepository.findById(13L))
                .thenReturn(Optional.of(declaracao));

        assertThrows(
                IllegalStateException.class,
                () -> declaracaoService.excluir(13L));

        verify(declaracaoRepository, never()).delete(declaracao);
        verify(arquivoStorageService, never()).excluir(
                declaracao.getCaminhoArquivoTemporario());
    }
}
