# Atividade2-DAC

1. Pré-requisitos
Para executar este projeto, você precisará ter instalado em sua máquina:

Java JDK 17 ou superior.

Apache Maven 3.6 ou superior.

2. Instruções de Instalação e Execução
Siga os passos abaixo para executar a aplicação localmente.

Passo 1: Clone o repositório (ou use o projeto já criado)

# Se estivesse em um repositório git
git clone <URL_DO_REPOSITORIO>
cd biblioteca-api


Passo 2: Execute a aplicação com Maven

Abra um terminal na raiz do projeto e execute o seguinte comando:

mvn spring-boot:run


O Maven irá baixar as dependências e iniciar o servidor de aplicação. Ao final do processo, você verá uma mensagem indicando que a aplicação foi iniciada na porta 8080.

3. Acessando o Banco de Dados e a Documentação
Com a aplicação rodando, você pode acessar:

Console do Banco H2:

Abra seu navegador e acesse: http://localhost:8080/h2-console

Use a URL JDBC padrão: jdbc:h2:mem:testdb

Usuário: sa

Senha: (deixe em branco)

Isso permite que você visualize e manipule os dados diretamente no banco de memória.

Documentação Interativa (Swagger/OpenAPI):

Para uma documentação mais completa e interativa, você pode adicionar a dependência springdoc-openapi-starter-webmvc-ui ao pom.xml.

Após adicioná-la, a documentação estará disponível em: http://localhost:8080/swagger-ui.html

4. Resumo dos Endpoints
A URL base para a API é http://localhost:8080.

Verbo HTTP

URI

Descrição

GET

/livros

Lista todos os livros (com paginação e ordenação)

GET

/livros/{id}

Busca um livro específico pelo seu ID

POST

/livros

Cria um novo livro

PUT

/livros/{id}

Atualiza um livro existente

DELETE

/livros/{id}

Remove um livro
