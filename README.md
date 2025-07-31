![Thumbnail Challenge Conversor de Moedas](./img/Programação-Challenge%20LiterAlura.png)

# LiterAlura

Neste projeto, construímos nosso próprio catálogo de livros utilizando Java, Spring Boot, PostgreSQL e a API Gutendex. O objetivo é consumir a API Gutendex, que possui dados de mais de 70 mil livros, e praticar a persistência de dados em um banco de dados relacional. Este projeto é desenvolvido na IDE IntelliJ e oferece cinco opções de interação com o usuário via terminal.

## 🔨 Funcionalidades do projeto

- `Buscar livro pelo título:` Realiza a consulta diretamente na API Gutendex e insere o livro no banco de dados.
- `Listar livros registrados:` Lista todos os livros registrados no banco de dados.
- `Listar autores registrados:` Lista todos os autores registrados no banco de dados.
- `Listar autores vivos em um determinado ano:` Lista autores que estavam vivos em um ano especificado pelo usuário.
- `Listar livros em um determinado idioma:` Lista todos os livros registrados no banco de dados em um idioma especificado pelo usuário.

## ✔️ Técnicas e tecnologias utilizadas

- `Java:` Linguagem de programação utilizada para o desenvolvimento do projeto.
- `Spring Boot:` Framework utilizado para criar a aplicação de forma rápida e fácil.
- `PostgreSQL:` Banco de dados relacional utilizado para armazenar os dados.
- `Gutendex API:` API utilizada para obter os dados dos livros.

## 📁 Acesso ao projeto

Você pode acessar o código fonte do projeto [aqui](./src/main).

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

## 🛠️ Passo a passo criação do banco de dados - Configurando o PostgreSQL via pgAdmin

1. **Abra o pgAdmin** e conecte-se ao seu servidor PostgreSQL.

2. **Crie o banco de dados** (se ainda não existir):

   * Clique com o botão direito em **Databases → Create → Database…**
   * Dê o nome `literalura_db` e salve.

3. **Abra a Query Tool** no banco `literalura_db` e cole a SQL abaixo:

   ```sql
   -- 1) Cria usuário de aplicação
   CREATE USER user_teste WITH ENCRYPTED PASSWORD '12345';
   GRANT ALL PRIVILEGES ON DATABASE literalura_db TO user_teste;

   -- 2) Cria tabela de autores
   CREATE TABLE autores (
     id          SERIAL PRIMARY KEY,
     nome        VARCHAR(255) UNIQUE NOT NULL,
     nascimento  INTEGER,
     falecimento INTEGER
   );

   -- 3) Cria tabela de livros
   CREATE TABLE livros (
     id         SERIAL PRIMARY KEY,
     titulo     VARCHAR(255) UNIQUE NOT NULL,
     autor_id   BIGINT,
     nome_autor VARCHAR(255),
     idioma     VARCHAR(50),
     downloads  DOUBLE PRECISION,
     CONSTRAINT fk_autor FOREIGN KEY (autor_id) REFERENCES autores(id)
   );

   -- 4) Ajusta o owner para o usuário da aplicação
   ALTER TABLE public.autores  OWNER TO user_teste;
   ALTER TABLE public.livros   OWNER TO user_teste;
   ```

4. Clique em **Execute (▶️)**. Ao final, confirme em **Schemas → public → Tables** que **autores** e **livros** existem e que **Login/Group Roles** inclui `user_teste`.

5. Defina variáveis de ambiente ou mude as variáveis diretamente no arquivo `.properties:
   
```
DB_HOST=localhost
DB_NAME=literalura_db
DB_USER=user_teste
DB_PASSWORD=12345
```


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
