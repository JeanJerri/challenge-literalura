package br.com.alura.challenge_literalura;

import br.com.alura.challenge_literalura.model.Autor;
import br.com.alura.challenge_literalura.model.DadosLivro;
import br.com.alura.challenge_literalura.model.Livro;
import br.com.alura.challenge_literalura.model.ResultadoBusca;
import br.com.alura.challenge_literalura.repository.RepositoryAutor;
import br.com.alura.challenge_literalura.repository.RepositoryLivro;
import br.com.alura.challenge_literalura.service.ConsumoApi;
import br.com.alura.challenge_literalura.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private String endereco = "https://gutendex.com/books/?search=";

    private Scanner entrada = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    @Autowired
    private RepositoryLivro repositorioLivro;
    @Autowired
    private RepositoryAutor repositorioAutor;

    public Principal(RepositoryLivro rLivro, RepositoryAutor rAutor){
        this.repositorioLivro = rLivro;
        this.repositorioAutor = rAutor;
    }

    public void exibirMenu() {

        var menu = """
                \nEscolha o número de sua opção:
                1 - Buscar livro pelo título
                2 - Listar livros registrados
                3 - Listar autores registrados
                4 - Listar autores vivos em um determinado ano
                5 - Listar livros em um determinado idioma
                0 - Sair
                """;
        int escolha;

        do {
            try {
                System.out.println(menu);
                escolha = entrada.nextInt();
                entrada.nextLine();

                switch (escolha) {
                    case 1:
                        buscarLivro();
                        break;
                    case 2:
                        listarLivrosRegistrados();
                        break;
                    case 3:
                        listarAutoresRegistrados();
                        break;
                    case 4:
                        listarAutoresVivosEmDeterminadoAno();
                        break;
                    case 5:
                        listarLivrosEmDeterminadoIdioma();
                        break;
                    case 0:
                        System.out.println("Encerrando...");
                        break;
                    default:
                        escolhaErrada();
                        break;
                }
            } catch (InputMismatchException e) {
                entrada.nextLine();
                escolhaErrada();
                escolha = -1;
            }
        } while (escolha != 0);
    }

    private void escolhaErrada() {
        System.out.println("Opção inválida.");
    }

    @Transactional
    private void buscarLivro() {
        DadosLivro dadosLivro = getDadosLivro();

        if (dadosLivro != null) {
            var dadosAutor = dadosLivro.autor().get(0);

            try {
                Optional<Livro> livroRepositorio = repositorioLivro.findByTituloContainingIgnoreCase(
                        dadosLivro.titulo());

                if (livroRepositorio.isEmpty()) {
                    Optional<Autor> autorRepositorio = repositorioAutor.findByNomeContainingIgnoreCase(
                            dadosAutor.nome());
                    Autor autor;

                    if (autorRepositorio.isEmpty()) {
                        autor = new Autor(dadosAutor.nome(), dadosAutor.nascimento(), dadosAutor.falecimento());
                        repositorioAutor.save(autor);
                    } else {
                        autor = autorRepositorio.get();
                    }

                    Livro livro = new Livro(dadosLivro.titulo(), autor, dadosAutor.nome(), dadosLivro.idioma(),
                            dadosLivro.downloads());
                    repositorioLivro.save(livro);

                    autor.adicionarLivro(livro);
                     repositorioAutor.save(autor);

                    System.out.println("\nLivro salvo com sucesso:");
                    System.out.println(livro);
                    System.out.println("\nAutor salvo com sucesso:");
                    System.out.println(autor);

                } else {
                    System.out.println("\nLivro já se encontra no banco de dados!");
                }
            } catch (Exception e) {
                System.out.println("Erro ao salvar o livro: " + e.getMessage());
            }
        }
    }

    private DadosLivro getDadosLivro() {

        System.out.println("Insira o nome do livro que você deseja procurar:");
        var nomeLivro = entrada.nextLine();
        var enderecoCompleto = endereco + nomeLivro.replace(" ", "+");
        var json = consumoApi.obterDados(enderecoCompleto);

        var resultadoBusca = conversor.obterDados(json, ResultadoBusca.class);

        if (resultadoBusca != null && resultadoBusca.resultadoJson() != null
                && !resultadoBusca.resultadoJson().isEmpty()) {
            return resultadoBusca.resultadoJson().get(0);
        } else {
            System.out.println("Nenhum livro encontrado para a busca: " + nomeLivro);
            return null;
        }
    }

    private void listarLivrosRegistrados() {
        var livros = repositorioLivro.findAll();
        livros.forEach(System.out::println);
    }

    private void listarAutoresRegistrados() {
        var autores = repositorioAutor.findAll();
        autores.forEach(System.out::println);
    }

    private void listarAutoresVivosEmDeterminadoAno() {
        System.out.println("Informe o ano que você deseja consultar:");
        var ano = entrada.nextInt();
        entrada.nextLine();

        var autoresVivos = repositorioAutor.findAutoresVivos(ano);

        if (autoresVivos.isEmpty()) {
            System.out.println("\nNão possui autores que estivessem vivos nesse determinado ano no banco de dados.");
        } else {
            autoresVivos.forEach(System.out::println);
        }
    }

    private void listarLivrosEmDeterminadoIdioma() {
        System.out.println("""
        Escolha entre as opções abaixo (digite apenas a sigla):
        es - espanhol
        en - inglês
        fr - francês
        pt - português
        """);
        var idioma = entrada.nextLine();
        int quantidadeLivros = 0;

        var livrosEmDeterminadoIdioma = repositorioLivro.findIdioma(idioma);
        if (livrosEmDeterminadoIdioma.isEmpty()) {
            System.out.println("Não existem livros nesse idioma no banco de dados.");
        } else {
            for (int i = 0; i < livrosEmDeterminadoIdioma.size(); i++) {
                System.out.println(livrosEmDeterminadoIdioma.get(i));
                quantidadeLivros++;
            }
            System.out.println("\nQuantidade de livros em " + idioma + ": " + quantidadeLivros);
        }
    }
}
