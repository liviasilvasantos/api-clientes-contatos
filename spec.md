# Visão Geral

Construa uma API REST para gerenciamento de clientes e seus contatos. Cada cliente pode ter um ou mais contatos associados.

## Requisitos Técnicos

A aplicação deve conter:

- Cadastro de Cliente: POST /clientes
- Cadastro de Contato associado a um cliente existente: POST /contatos
- Listagem de todos os clientes com seus contatos: GET /clientes
- Listagem de contatos de um cliente específico: GET /clientes/{id}/contatos
- Uso do Spring Boot + Spring Data JPA
- Banco de Dados PostgreSQL
- Entidades Cliente e Contato com relacionamento @OneToMany / @ManyToOne

## Requisitos de Código

Esperamos que o código siga boas práticas de desenvolvimento, incluindo:

- Separação de responsabilidades (controller, service, repository)
- Utilize uma arquitetura baseada em clean arch.
- Uso de DTOs para entrada e saída de dados
- Tratamento adequado de erros
- Uso de Lombok
- Gere um arquivo para ser executado no Bruno, para testar todas as chamadas das apis


### Diferenciais (Não obrigatórios)

- Uso de Docker para subir o PostgreSQL
- Testes automatizados
- Documentação com Swagger/OpenAPI

## Tecnologias Usadas

- Java 21 
- Spring Boot  
- Spring Data JPA  
- PostgreSQL  
- Lombok  
- Docker 
