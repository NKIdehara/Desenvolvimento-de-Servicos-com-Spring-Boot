# Desenvolvimento de Serviços com Spring Boot

## TP2

###	1. Cadastro de um novo produto:
**[POST]**
http://localhost:8080/produto

_JSON_
{
  "id": int,
  "nome": "string",
  "preco": float
}

###	2. Consulta de todos os produtos cadastrados:
**[GET]**
http://localhost:8080/produto

###	3. Consulta de um Produto específico por ID:
**[GET]**
http://localhost:8080/produto/{id}

###	4. Atualização dos dados de um Produto:
**[PUT]**
http://localhost:8080/produto/{id}

_JSON_
{
  "id": int,
  "nome": "string",
  "preco": float
}

###	5. Remoção de um Produto da lista:
**[DELETE]**
http://localhost:8080/produto/{id}

### Swagger
http://localhost:8080/swagger-ui/index.html
