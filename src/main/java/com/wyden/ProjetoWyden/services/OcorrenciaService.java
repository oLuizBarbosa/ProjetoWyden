package com.wyden.ProjetoWyden.services;

import com.wyden.ProjetoWyden.models.Ocorrencia.Status;
import com.wyden.ProjetoWyden.models.*;
import com.wyden.ProjetoWyden.repository.OcorrenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OcorrenciaService {

    private final OcorrenciaRepository repository;

    @Transactional
    public Ocorrencia criar(Ocorrencia ocorrencia) {
        log.info("Criando ocorrência para o usuário: {}", ocorrencia.getUsuario().getId());
        ocorrencia.setDataAbertura(LocalDateTime.now());
        ocorrencia.setStatus(Status.ABERTO);
        return repository.save(ocorrencia);
    }

    public Page<Ocorrencia> listarPaginado(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<Ocorrencia> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public void atualizarStatus(Long id, Status novoStatus) {
        repository.findById(id).ifPresent(ocorrencia -> {
            if (ocorrencia.getStatus() == Status.FECHADO && novoStatus != Status.FECHADO) {
                ocorrencia.setDataFechamento(null);
            }
            ocorrencia.setStatus(novoStatus);
            repository.save(ocorrencia);
        });
    }
}