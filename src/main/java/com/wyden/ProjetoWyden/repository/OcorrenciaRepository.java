package com.wyden.ProjetoWyden.repository;

import com.wyden.ProjetoWyden.models.Ocorrencia;
import com.wyden.ProjetoWyden.models.Ocorrencia.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {

    // Consulta paginada padrão
    Page<Ocorrencia> findByUsuarioId(Long usuarioId, Pageable pageable);

    // Consulta filtrada (sem estatísticas complexas)
    @Query("""
        SELECT o FROM Ocorrencia o WHERE
        (:titulo IS NULL OR o.titulo LIKE %:titulo%) AND
        (:status IS NULL OR o.status = :status)
        """)
    Page<Ocorrencia> filtrar(
            @Param("titulo") String titulo,
            @Param("status") Status status,
            Pageable pageable
    );

    // Atualização direta de status
    @Modifying
    @Transactional
    @Query("UPDATE Ocorrencia o SET o.status = :status WHERE o.id = :id")
    int atualizarStatus(@Param("id") Long id, @Param("status") Status status);
}