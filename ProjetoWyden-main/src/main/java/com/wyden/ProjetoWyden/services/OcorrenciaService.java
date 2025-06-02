package com.wyden.ProjetoWyden.services;

import com.wyden.ProjetoWyden.models.Ocorrencia;
import com.wyden.ProjetoWyden.models.Ocorrencia.Status;
import com.wyden.ProjetoWyden.repository.OcorrenciaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class OcorrenciaService {

    private final OcorrenciaRepository repository;

    public OcorrenciaService(OcorrenciaRepository repository) {
        this.repository = repository;
    }

    public Ocorrencia criar(Ocorrencia ocorrencia) {
        return repository.save(ocorrencia);
    }

    public Page<Ocorrencia> buscarTodas(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<Ocorrencia> buscarPorStatus(Status status) {
        return repository.findByStatus(status);
    }

    // Consulta customizada para relatório
    public List<Object[]> relatorioOcorrenciasPorPeriodo(LocalDate inicio, LocalDate fim) {
        return repository.countOcorrenciasByPeriodo(inicio.atStartOfDay(), fim.plusDays(1).atStartOfDay());
    }
}