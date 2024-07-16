package br.com.alura.challenge_literalura;

import br.com.alura.challenge_literalura.repository.RepositoryAutor;
import br.com.alura.challenge_literalura.repository.RepositoryLivro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeLiteraluraApplication implements CommandLineRunner {

	@Autowired
	private RepositoryLivro repositoryLivro;

	@Autowired
	private RepositoryAutor repositoryAutor;

	public static void main(String[] args) {
		SpringApplication.run(ChallengeLiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositoryLivro, repositoryAutor);
		principal.exibirMenu();
	}
}
