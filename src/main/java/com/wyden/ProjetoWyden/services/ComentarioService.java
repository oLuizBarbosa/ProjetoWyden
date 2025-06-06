package com.wyden.ProjetoWyden.services;

import com.wyden.ProjetoWyden.models.Comentario;
import com.wyden.ProjetoWyden.repository.ComentarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ComentarioService {

    private final ComentarioRepository repository;

    public ComentarioService(ComentarioRepository repository) {
        this.repository = repository;
    }

    public Comentario criar(Comentario comentario) {
        return repository.save(comentario);
    }

    public List<Comentario> buscarPorOcorrencia(Long ocorrenciaId) {
        return repository.findByOcorrenciaId(ocorrenciaId);
    }

    // Consulta customizada para relatório de atividade
    public List<Object[]> relatorioAtividadeUsuarios() {
        return repository.countComentariosByUsuario();
    }
}
