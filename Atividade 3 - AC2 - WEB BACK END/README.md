# AC2 – Controle de Projetos (Spring Boot)

## Rodar
Requer JDK 25 (LTS) e Maven.

```
mvn spring-boot:run
```
- Frontend: http://localhost:8080
- Console H2: http://localhost:8080/h2-console (JDBC URL `jdbc:h2:mem:testdb`, user `sa`, senha `password`)

## Endpoints
| Método | URL | Descrição |
|---|---|---|
| POST | `/api/setores` `{"nome":"TI"}` | adicionar setor |
| GET | `/api/setores/{id}` | setor + funcionários |
| GET | `/api/setores` | todos os setores + funcionários (query d) |
| POST | `/api/funcionarios` `{"nome":"Ana","setorId":1}` | adicionar funcionário |
| GET | `/api/funcionarios/{id}/projetos` | projetos do funcionário (query c) |
| POST | `/api/projetos` `{"descricao":"X","dataInicio":"2026-01-10","dataFim":"2026-06-30"}` | adicionar projeto |
| GET | `/api/projetos/{id}` | projeto + funcionários (query a) |
| PUT | `/api/projetos/{idProjeto}/funcionarios/{idFuncionario}` | vincular funcionário |
| GET | `/api/projetos` | listar todos (frontend) |
| GET | `/api/projetos?inicio=2026-01-01&fim=2026-12-31` | projetos por data de início (query b) |

Erros retornam JSON `{"errors": ["..."]}` (400 validação, 404 não encontrado).

## Teste rápido (curl)
```
curl -X POST localhost:8080/api/setores -H "Content-Type: application/json" -d '{"nome":"TI"}'
curl -X POST localhost:8080/api/funcionarios -H "Content-Type: application/json" -d '{"nome":"Ana","setorId":1}'
curl -X POST localhost:8080/api/projetos -H "Content-Type: application/json" -d '{"descricao":"Portal","dataInicio":"2026-03-01"}'
curl -X PUT localhost:8080/api/projetos/1/funcionarios/1
curl localhost:8080/api/projetos/1
curl localhost:8080/api/funcionarios/1/projetos
```
