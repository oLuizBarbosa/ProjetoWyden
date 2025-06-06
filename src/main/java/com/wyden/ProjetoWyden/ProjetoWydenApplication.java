package com.wyden.ProjetoWyden;

import com.wyden.ProjetoWyden.models.Cadastro;
import com.wyden.ProjetoWyden.repository.CadastroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ProjetoWydenApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoWydenApplication.class, args);
	}
	/*@Bean
	CommandLineRunner init(CadastroRepository repo, PasswordEncoder encoder) {
		return args -> {
			if (repo.findByEmail("admin@admin.com").isEmpty()) {
				Cadastro admin = new Cadastro();
				admin.setNome("Admin");
				admin.setEmail("admin@gmail.com");
				admin.setSenha(encoder.encode("admin123"));
				admin.setGrupo(Cadastro.Role.ADMIN);
				admin.setTelefone("71955244196");
				repo.save(admin);
			}
		};
	}*/
}
