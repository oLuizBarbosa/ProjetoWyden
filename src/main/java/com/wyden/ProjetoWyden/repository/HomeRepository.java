package com.wyden.ProjetoWyden.repository;

import com.wyden.ProjetoWyden.models.Home;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HomeRepository extends JpaRepository<Home, Long> {
    List<Home> findByUsuarioAberturaEmail(String email);
}
    /*
    @Query("SELECT o FROM Ocorrencia o WHERE o.usuario.email = :email")
    List<Ocorrencia> buscarPorEmailUsuario(@Param("email") String email);
    */