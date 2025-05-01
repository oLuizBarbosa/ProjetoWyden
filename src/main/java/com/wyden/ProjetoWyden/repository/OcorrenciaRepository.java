// src/main/java/com/wyden/ProjetoWyden/repository/OcorrenciaRepository.java
package com.wyden.ProjetoWyden.repository;

import com.wyden.ProjetoWyden.models.Ocorrencia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {
    List<Ocorrencia> findByUsuarioEmail(String email);
}