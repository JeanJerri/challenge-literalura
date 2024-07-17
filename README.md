![Thumbnail Challenge Conversor de Moedas](./img/Programação-Challenge%20Conversor%20de%20Moedas.png)

# LiterAlura

Neste projeto, construímos nosso próprio catálogo de livros utilizando Java, Spring Boot, PostgreSQL e a API Gutendex. O objetivo é consumir a API Gutendex, que possui dados de mais de 70 mil livros, e praticar a persistência de dados em um banco de dados relacional. Este projeto é desenvolvido na IDE IntelliJ e oferece cinco opções de interação com o usuário via terminal.

## 🔨 Funcionalidades do projeto

- `Buscar livro pelo título:` Realiza a consulta diretamente na API Gutendex e insere o livro no banco de dados.
- `Listar livros registrados:` Lista todos os livros registrados no banco de dados.
- `istar autores registrados:` Lista todos os autores registrados no banco de dados.
- `Listar autores vivos em um determinado ano:` Lista autores que estavam vivos em um ano especificado pelo usuário.
- `Listar livros em um determinado idioma:` Lista todos os livros registrados no banco de dados em um idioma especificado pelo usuário.

## ✔️ Técnicas e tecnologias utilizadas

- `Java:` Linguagem de programação utilizada para o desenvolvimento do projeto.
- `Spring Boot:` Framework utilizado para criar a aplicação de forma rápida e fácil.
- `PostgreSQL:` Banco de dados relacional utilizado para armazenar os dados.
- `Gutendex API:` API utilizada para obter os dados dos livros.

## 📁 Acesso ao projeto

Você pode acessar o código fonte do projeto [aqui](./src).

## 🛠️ Abrir e rodar o projeto

Para executar o projeto, siga as instruções abaixo:

1. Clone o repositório para sua máquina.
2. Abra o projeto em sua IDE Java de preferência (recomendo IntelliJ IDEA).
3. Configure o banco de dados PostgreSQL:
    - Baixe e instale o PostgreSQL.
    - Crie um banco de dados para o projeto.
4. Configure as propriedades do banco de dados em `application.properties`:

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/seu_banco_de_dados
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
    ```
5. Execute a classe `ChallengeLiteraluraApplication` para iniciar o programa.

## 👩‍💻 Uso

Após iniciar o projeto, as opções de interação serão exibidas no terminal. Selecione a opção desejada digitando o número correspondente e seguindo as instruções.

### Exemplo de Uso

1. Digite `1` e, em seguida, o título do livro (por exemplo, "Dom Casmurro").
   O sistema buscará o livro na API Gutendex e o registrará no banco de dados.

2. Digite `2` para listar todos os livros registrados no banco de dados.

3. Digite `3` para listar todos os autores registrados no banco de dados.

4. Digite `4` e, em seguida, o ano desejado (por exemplo, 1800) para listar os autores que estavam vivos naquele ano.

5. Digite `5` e, em seguida, a sigla do idioma desejado (`es` para espanhol, `en` para inglês, `fr` para francês, `pt` para português) para listar os livros naquele idioma.

0. Digite `0` para encerrar o programa.

## 🤖 Estrutura do Projeto

- `ChallengeLiteraluraApplication.java`: Classe principal que inicia o projeto Spring Boot.
- `Principal.java`: Classe que exibe o menu e gerencia as interações do usuário.
- `ConsumoApi.java`: Classe que consome a API Gutendex.
- `ConverteDados.java`: Classe que converte os dados JSON retornados pela API Gutendex.
- `Autor.java`: Entidade que representa um autor.
- `Livro.java`: Entidade que representa um livro.
- `DadosAutor.java`: Classe que mapeia os dados do autor retornados pela API Gutendex.
- `DadosLivro.java`: Classe que mapeia os dados do livro retornados pela API Gutendex.
- `ResultadoBusca.java`: Classe que mapeia o resultado da busca na API Gutendex.
- `RepositoryAutor.java`: Repositório JPA para a entidade `Autor`.
- `RepositoryLivro.java`: Repositório JPA para a entidade `Livro`.

## ⚙️ API Gutendex

A API Gutendex é gratuita e fornece dados de mais de 70 mil livros. Para mais informações, visite o [site da Gutendex](https://gutendex.com/).

## 📚 Mais informações do curso

Para mais informações e detalhes sobre este projeto, confira o challenge da Alura [aqui](https://cursos.alura.com.br/course/spring-boot-challenge-literalura).