package com.wyden.ProjetoWyden.repository;

import com.wyden.ProjetoWyden.models.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    // Consulta única ordenada por data
    @Query("SELECT c FROM Comentario c WHERE c.ocorrencia.id = :ocorrenciaId ORDER BY c.dataCriacao DESC")
    List<Comentario> findByOcorrenciaId(@Param("ocorrenciaId") Long ocorrenciaId);

    // Contagem simples de comentários por usuário (para dashboard)
    @Query("SELECT c.usuario.nome, COUNT(c) FROM Comentario c GROUP BY c.usuario.nome")
    List<Object[]> contarComentariosPorUsuario();
}