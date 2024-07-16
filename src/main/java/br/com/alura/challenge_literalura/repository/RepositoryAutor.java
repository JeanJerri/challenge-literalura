package br.com.alura.challenge_literalura.repository;

import br.com.alura.challenge_literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RepositoryAutor extends JpaRepository<Autor, Long> {

    @Query("SELECT a FROM Autor a WHERE a.nascimento <= :ano AND (a.falecimento IS NULL OR a.falecimento >= :ano)")
    List<Autor> findAutoresVivos(Integer ano);

    Optional<Autor> findByNomeContainingIgnoreCase(String nome);
}
