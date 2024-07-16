package br.com.alura.challenge_literalura.model;

import br.com.alura.challenge_literalura.model.Autor;
import br.com.alura.challenge_literalura.model.DadosAutor;
import br.com.alura.challenge_literalura.model.DadosLivro;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @ManyToOne
    private Autor autor;
    private String nomeAutor;
    private String idioma;
    private Double downloads;

    public Livro(){}
//    public Livro(DadosLivro dadosLivro, DadosAutor dadosAutor) {
//        this.titulo = dadosLivro.titulo();
//        this.autor = new Autor(dadosAutor);
//        this.idioma = dadosLivro.idioma().isEmpty() ? "Desconhecido" : dadosLivro.idioma().get(0);;
//        this.downloads = dadosLivro.downloads();
//    }

    public Livro(String titulo, Autor dadosAutor,String nomeAutor, List<String> idioma, Double downloads) {
        this.titulo = titulo;
        this.autor = dadosAutor;
        this.nomeAutor = nomeAutor;
        this.idioma = idioma.isEmpty() ? "Desconhecido" : idioma.get(0);
        this.downloads = downloads;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Double getDownloads() {
        return downloads;
    }

    public void setDownloads(Double downloads) {
        this.downloads = downloads;
    }

    @Override
    public String toString() {
        return "\n ----- Livro ----- " +
                "\nTítulo:" + titulo +
                "\nAutor: " + nomeAutor +
                "\nIdioma: " + idioma +
                "\nNúmero de downloads: " + downloads;
    }
}
