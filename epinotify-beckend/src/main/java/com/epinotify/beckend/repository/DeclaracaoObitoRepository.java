package com.epinotify.beckend.repository;

import com.epinotify.beckend.model.DeclaracaoObito;
import com.epinotify.beckend.model.StatusDeclaracao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DeclaracaoObitoRepository
                extends JpaRepository<DeclaracaoObito, Long> {

        Optional<DeclaracaoObito> findByNumeroDeclaracao(
                        String numeroDeclaracao);

        boolean existsByNumeroDeclaracao(
                        String numeroDeclaracao);

        List<DeclaracaoObito> findByStatus(
                        StatusDeclaracao status);

        List<DeclaracaoObito> findAllByOrderByDataEnvioDesc();

        List<DeclaracaoObito> findByIdentificacaoNomeFalecidoContainingIgnoreCase(
                        String nomeFalecido);

        long countByStatus(
                        StatusDeclaracao status);

        long countByStatusAndDataConfirmacaoBetween(
                        StatusDeclaracao status,
                        LocalDateTime inicio,
                        LocalDateTime fim);

        long countByDataEnvioBetween(
                        LocalDateTime inicio,
                        LocalDateTime fim);
}
