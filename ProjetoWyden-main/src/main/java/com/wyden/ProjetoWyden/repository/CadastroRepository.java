package com.wyden.ProjetoWyden.repository;

import com.wyden.ProjetoWyden.models.Cadastro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CadastroRepository extends JpaRepository<Cadastro, Long> {

    // Busca por email (usado no login)
    Optional<Cadastro> findByEmail(String email);

    // Verifica se email já existe (usado na validação)
    boolean existsByEmail(String email);

    // Busca por grupo (pode ser útil para autorização)
    List<Cadastro> findByGrupo(Cadastro.Role grupo);

    @Query("SELECT c.grupo as grupo, COUNT(c) as total FROM Cadastro c GROUP BY c.grupo")
    List<Object[]> countUsersByGroup();
}