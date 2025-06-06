package com.wyden.ProjetoWyden.repository;

import com.wyden.ProjetoWyden.models.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    // Busca comentários de uma ocorrência específica
    @Query("SELECT c FROM Comentario c WHERE c.ocorrencia.id = :ocorrenciaId ORDER BY c.dataCriacao DESC")
    List<Comentario> findByOcorrenciaId(@Param("ocorrenciaId") Long ocorrenciaId);

    // Contagem de comentários por ocorrência (útil para relatórios)
    @Query("SELECT COUNT(c) FROM Comentario c WHERE c.ocorrencia.id = :ocorrenciaId")
    Long countByOcorrenciaId(@Param("ocorrenciaId") Long ocorrenciaId);

    @Query("""
        SELECT 
            u.nome as usuario,
            COUNT(c) as totalComentarios,
            MAX(c.dataCriacao) as ultimaAtividade
        FROM Comentario c
        JOIN c.usuario u
        GROUP BY u.nome
        ORDER BY totalComentarios DESC
        """)
    List<Object[]> countComentariosByUsuario();
}