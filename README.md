#Link do Desafio: https://drive.google.com/file/d/1GkY5ZNsxDoiVcdDNCNDxYVd4nrvK_U0x/view

Informações do Desafio


```markdown
# API de Agendamento de Comunicações - Magalu

API para agendamento, consulta e cancelamento de comunicações (via email, SMS, push e WhatsApp)- Futuro Processo para Venda de Maquinetas, para um próximo projeto.

## Tecnologias Utilizadas
- **Java 22** (Linguagem principal)
- **Spring Boot 3.4.2** (Framework)
- **PostgreSQL** (Banco de dados principal)
- **H2** (Banco de dados para testes)
- **Flyway** (Controle de versão de banco de dados)
- **MapStruct** (Mapeamento de DTOs)
- **Lombok** (Redução de boilerplate)
- **SpringDoc OpenAPI** (Documentação da API)

## Funcionalidades
- ✅ Agendamento de comunicações
- ✅ Consulta de status de agendamentos
- ✅ Validação de dados de entrada
- ✅ Documentação automática da API

## Modelo de Dados
create table magalu.agendamento(

    id_agendamento uuid default gen_random_uuid(),

    nome_destinatario_agendamento varchar,

    data_cadastramento_agendamento timestamp,

    data_cancelamento_agendamento timestamp,

    data_hora_agendamento timestamp,

    mensagem_de_entrega_agendamento varchar,

    status_mensagem_agendamento varchar,

    primary key (id_agendamento)
);
```

## Endpoints

### POST /api/scheduling/v1/insert
Agendar nova comunicação
```json
{
  "nomeDestinatario": "matheusbsi1992gmail.com",
  "dataHoraAgendamento": "19/02/2025 22:35:16",
  "mensagem": "Olá existe um novo agendamento",
  "status": "Enviado"
}
```

### GET /api/scheduling/v1/returnAll
Consultar todos os agendamentos

### GET /api/v1/communications/{status}
Consultar status de um/mais agendamento(s)

### GET /api/scheduling/v1/returnSchedulingEmail/{email}
Consultar email de um/mais agendamento(s)

### PUT /api/scheduling/v1/update
Atualiza um agendamento (Não Cancelar e/ou Reangedar) com datas diferentes
```json
{
  "nomeDestinatario": "tina.melisa@gmail.com",
  "dataHoraAgendamento": "17/02/2025 17:15:16"
}
```
## Pré-requisitos
- Java 22+
- Maven 3.9+
- PostgreSQL 15+
- Docker (opcional)

## Configuração
1. Clone o repositório:
```bash
git clone https://github.com/seu-usuario/api-notificacao-agendamento.git
```

2. Configure o banco de dados:
```yaml
# application.yaml
server:
  port: 9293
spring:
  profiles:
    active: dev
  datasource:
    driver-class-name: org.postgresql.Driver
    url: jdbc:postgresql://localhost:5432/api_magazine_luiza_agendamento
    username: postgres
    password: postgres
jpa:
  open-in-view: false
  hibernate:
    ddl-auto: none
    show-sql: true
    format-sql: true
flyway:
  enabled: true
  locations:
    classpath: db/migration
  baseline-on-migrate: true
  schemas: magalu
spring-doc:
  pathsToMatch:
    - /api/**/v1/**
swagger-ui:
  user-root-path: true
```


4. Configure o banco de dados de teste:
```properties
# application-test.properties
# Configuração do banco de dados H2 para testes
spring.datasource.url=jdbc:h2:mem:testdb;MODE=PostgreSQL;INIT=CREATE SCHEMA IF NOT EXISTS MAGALU
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# Configuração do console H2 (opcional)
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Configuração do JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Configuração do Flyway (se estiver usando)
spring.flyway.enabled=false
```

A aplicação estará disponível em: http://localhost:9293

## Documentação da API
Acesse a documentação interativa:
- Swagger UI: http://localhost:9293/swagger-ui.html
- OpenAPI: http://localhost:9293/api/scheduling/v1/...

```

### Explicações importantes:

1. **Escolha Tecnológica**:
- Java + Spring Boot foi escolhido por sua maturidade, ecossistema robusto e adequação para sistemas empresariais
- PostgreSQL pela confiabilidade e recursos avançados
- Flyway para versionamento de banco de dados
- MapStruct para mapeamento seguro entre DTOs e entidades

2. **Estrutura do Projeto**:
- Controller: `SchedulingController`
- Service: `SchedulingService`
- Repository: `SchedulingRepository`
- DTOs: `SchedulingDTO`
- Model: `Scheduling`
- Mapper: `SchedulingMapper`

3. **Validações**:
- Data de agendamento deve ser futura ou atual
- Canal deve ser um dos valores permitidos
- Status segue fluxo definido (SCHEDULED → SENT/SCHEDULING/CANCELED)

4. **Testes**:
- Testes unitários para repositório
- Testes de comportamento usando H2

5. **Melhorias Futuras**:
- Implementar envio real das comunicações (Email e WhatsAPP)
- Adicionar autenticação/autorização
- Implementar fila para processamento assíncrono (De envio de emails e WhatsAPP)
