# API de Gerenciamento de Clientes e Contatos

Esta é uma API REST desenvolvida com Spring Boot para gerenciar clientes e seus respectivos contatos. Cada cliente possui informações básicas (nome, e-mail, telefone) e pode ter um ou mais contatos associados.

---

## 🛠️ Tecnologias Utilizadas

*   **Java 21**
*   **Spring Boot 3.x / 4.x** (Spring Web, Spring Data JPA, Validation)
*   **PostgreSQL** (Banco de dados relacional em produção/desenvolvimento)
*   **H2 Database** (Banco de dados em memória para testes)
*   **Lombok** (Redução de boilerplate de código)
*   **Docker & Docker Compose** (Instanciação rápida do PostgreSQL)
*   **Swagger / OpenAPI 3** (Documentação interativa da API)
*   **JUnit 5 & MockMvc** (Testes automatizados de integração)

---

## 🏗️ Arquitetura e Boas Práticas

A API foi desenvolvida seguindo os padrões de boas práticas do ecossistema Spring:

*   **Separação de Responsabilidades:** Divisão em camadas de Controle (`Controller`), Regras de Negócio (`Service`) e Acesso a Dados (`Repository`).
*   **Segurança de Tipos com DTOs:** Uso de objetos de transferência de dados (DTOs) específicos para entradas (validações via `@Valid`) e saídas de dados, evitando exposição direta das entidades de banco de dados.
*   **Tratamento Global de Erros:** Captura e tratamento centralizado de exceções (`ResourceNotFoundException`, `EmailAlreadyExistsException`, validações inválidas de formato) com payloads JSON padronizados.
*   **Performance:** Uso de `@EntityGraph` no repositório de clientes para buscar dados com relacionamento de contatos em uma única consulta, evitando o clássico problema do *N+1 Select*.

---

## 🚀 Como Executar o Projeto

### Pró-requisitos
*   **Java 21** ou superior instalado
*   **Maven** instalado (ou utilize o `./mvnw` incluído no projeto)
*   **Docker** e **Docker Compose** instalados (opcional, mas recomendado)

### 1. Subir o Banco de Dados PostgreSQL (via Docker)
Na raiz do projeto, execute o comando abaixo para iniciar o contêiner do PostgreSQL:
```bash
docker-compose up -d
```

### 2. Rodar a Aplicação
Com o banco ativo, execute a aplicação Spring Boot:
```bash
mvn spring-boot:run
```
O servidor estará disponível no endereço: `http://localhost:8080`

---

## 🧪 Como Executar os Testes Automatizados

Os testes de integração utilizam o banco de dados em memória **H2** (configurado sob o perfil de teste). Para executá-los, rode:
```bash
mvn clean test
```

---

## 📖 Documentação da API (Swagger / OpenAPI)

A API possui documentação interativa gerada automaticamente. Com o projeto em execução, acesse:
👉 [Swagger UI - http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## 📡 Principais Endpoints

### 👤 Clientes

*   **Cadastrar Cliente**
    *   **POST** `/clientes`
    *   *Payload:*
        ```json
        {
          "nome": "João da Silva",
          "email": "joao.silva@email.com",
          "telefone": "11988887777"
        }
        ```
*   **Listar Clientes (com Contatos)**
    *   **GET** `/clientes`
*   **Listar Contatos de um Cliente específico**
    *   **GET** `/clientes/{id}/contatos`

### 📞 Contatos

*   **Cadastrar Contato**
    *   **POST** `/contatos`
    *   *Payload:*
        ```json
        {
          "nome": "Filho do João",
          "email": "filho.joao@email.com",
          "telefone": "11977776666",
          "clienteId": 1
        }
        ```
