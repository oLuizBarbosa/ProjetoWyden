package com.wyden.ProjetoWyden.repository;

import com.wyden.ProjetoWyden.models.Ocorrencia;
import com.wyden.ProjetoWyden.models.Ocorrencia.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {

    // Busca por email do usuário (já existente)
    @Query("SELECT o FROM Ocorrencia o WHERE o.usuario.email = :email")
    List<Ocorrencia> findByUsuarioEmail(@Param("email") String email);

    // Busca por status (útil para filtros)
    List<Ocorrencia> findByStatus(Status status);

    // Busca por prioridade (útil para filtros)
    List<Ocorrencia> findByPrioridade(Ocorrencia.Prioridade prioridade);

    // Busca ocorrências abertas de um usuário específico
    @Query("SELECT o FROM Ocorrencia o WHERE o.usuario.id = :usuarioId AND o.status = 'ABERTO'")
    List<Ocorrencia> findAbertasByUsuarioId(@Param("usuarioId") Long usuarioId);

    @Query("""
        SELECT 
            DATE(o.dataAbertura) as data,
            COUNT(o) as total,
            SUM(CASE WHEN o.status = 'FECHADO' THEN 1 ELSE 0 END) as resolvidas
        FROM Ocorrencia o
        WHERE o.dataAbertura BETWEEN :inicio AND :fim
        GROUP BY DATE(o.dataAbertura)
        """)
    List<Object[]> countOcorrenciasByPeriodo(
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim);
}