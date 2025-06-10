package com.wyden.ProjetoWyden.services;

import com.wyden.ProjetoWyden.models.Comentario;
import com.wyden.ProjetoWyden.models.Ocorrencia;
import com.wyden.ProjetoWyden.repository.ComentarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComentarioService {

    private final ComentarioRepository repository;
    private final OcorrenciaService ocorrenciaService;

    @Transactional
    public Comentario criar(Comentario comentario) {
        if (comentario.getTexto().length() > 500) {
            throw new IllegalArgumentException("Comentário muito longo");
        }
        return repository.save(comentario);
    }

    public List<Comentario> buscarPorOcorrencia(Long ocorrenciaId) {
        return repository.findByOcorrenciaId(ocorrenciaId);
    }

    @Transactional
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}