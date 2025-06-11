package com.wyden.ProjetoWyden.repository;

import com.wyden.ProjetoWyden.models.Cadastro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

@Repository
public interface CadastroRepository extends JpaRepository<Cadastro, Long> {

    Optional<Cadastro> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("SELECT c FROM Cadastro c WHERE c.grupo = :grupo")
    List<Cadastro> findByGrupo(@Param("grupo") Role grupo);

    // Método para atualização de senha
    @Modifying
    @Transactional
    @Query("UPDATE Cadastro c SET c.senha = :novaSenha WHERE c.id = :usuarioId")
    int atualizarSenha(@Param("usuarioId") Long usuarioId, @Param("novaSenha") String novaSenha);

    // Consulta para contagem de usuários por grupo (simplificada)
    @Query("SELECT c.grupo, COUNT(c) FROM Cadastro c GROUP BY c.grupo")
    List<Object[]> contarUsuariosPorGrupo();
}