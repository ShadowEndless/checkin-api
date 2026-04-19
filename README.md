# 🚀 Check-in API (Open Source)

API backend para sincronização de eventos e controle de presença via QR Code.

---

## 🎯 Objetivo

Centralizar dados de eventos e visitantes vindos de múltiplos dispositivos (apps mobile), garantindo:

* deduplicação de eventos
* merge inteligente de visitantes
* histórico completo de entradas e saídas
* base para arquitetura orientada a eventos (Kafka futuramente)

---

## 🧠 Conceito Principal

O sistema recebe dados de múltiplas fontes e aplica:

👉 **Merge determinístico (sem perda de informação)**
👉 **Idempotência (evita duplicação)**
👉 **Histórico completo (auditoria)**

---

## 📱 Contexto

O sistema funciona com dois apps:

### 1. Gerador de QR Code

* gera QR com `id + nome`

### 2. Leitor

* cria eventos
* cadastra visitantes
* registra entrada/saída
* funciona offline
* possui banco local

---

## ⚠️ Importante

Esta API:

* NÃO controla os apps
* NÃO altera dados nos apps
* NÃO exige mudanças imediatas

👉 Apenas recebe e processa dados

---

## 🏗️ Arquitetura (Atual)

```
App → API → PostgreSQL
```

---

## 🧪 Arquitetura (Em evolução)

```
App → API → Kafka → Worker → PostgreSQL
```

---

## 🧱 Stack Tecnológica

* ☕ Java 21
* 🌱 Spring Boot 3.5.x
* 🗄️ PostgreSQL
* 🧪 JUnit + Mockito
* 🐳 Testcontainers
* 🔧 Maven

---

## 📦 Estrutura do Projeto

```
com.shadowendless.checkin.api
 ├── eventos
 │    ├── controller
 │    ├── dto
 │    ├── entity
 │    ├── repository
 │    ├── service
 │
 ├── config
 ├── exception
 └── Application.java
```

---

## 🔌 Endpoint Principal

### POST `/events/sync`

---

## 📤 Payload

```json
{
  "event": {
    "id": 1,
    "name": "Evento X",
    "location": "Local X",
    "startDate": "2026-04-15T10:00:00",
    "endDate": "2026-04-15T18:00:00"
  },
  "visitors": [
    {
      "id": 10,
      "name": "João",
      "entered": true,
      "eventId": 1,
      "eventName": "Evento X",
      "entryDate": "2026-04-15T10:10:00",
      "exitDate": "2026-04-15T12:00:00"
    }
  ]
}
```

---

## 🧠 Regras de Negócio

### 🔹 Eventos

Evento é único por:

* `name`
* `location`
* `startDate`

```
UNIQUE(name, location, start_date)
```

---

### 🔹 Visitantes (merge inteligente)

* `entered`: `true` vence `false`
* `entryDate`: menor data (primeira entrada)
* `exitDate`: maior data (última saída)

👉 Informação positiva nunca é perdida

---

### 🔹 Histórico (checkin_logs)

Cada ação gera um registro:

* ENTRY → entrada
* EXIT → saída

---

## 🔁 Fluxo do Sistema

```
Recebe request
↓
Busca evento
↓
Cria ou reutiliza evento
↓
Para cada visitante:
    aplica merge
    salva
    gera logs
↓
Retorna resposta
```

---

## 🧪 Testes

O projeto possui múltiplos níveis de teste:

### ✔ Controller

* MockMvc
* valida JSON
* valida integração com service

### ✔ Service

* regras de negócio
* merge determinístico

### ✔ Integração

* Testcontainers
* PostgreSQL real em container
* ambiente isolado

---

## 🐳 Testcontainers

* sobe PostgreSQL automaticamente
* não depende do ambiente local
* garante consistência

⚠️ Requer Docker rodando

---

## ⚙️ Configuração

### ⚠️ Recomendação Importante

👉 **NUNCA coloque credenciais diretamente no `application.yml` versionado**

Use:

* variáveis de ambiente (**ENV**) ✅
* ou `application-local.yml` (não versionado) ✅

---

### ✅ Opção 1 — Variáveis de Ambiente (RECOMENDADO)

```yaml
spring:
  datasource:
    url: ${DB_URL}
    username: ${DB_USER}
    password: ${DB_PASSWORD}

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

Exemplo:

```
DB_URL=jdbc:postgresql://localhost:5432/api_checkin
DB_USER=postgres
DB_PASSWORD=senha
```

---

### ✅ Opção 2 — application-local.yml

Crie:

```
src/main/resources/application-local.yml
```

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/api_checkin
    username: postgres
    password: sua_senha
```

Ative:

```yaml
spring:
  profiles:
    active: local
```

---

### 🚫 .gitignore

```
application-local.yml
.env
target/
*.log
.idea/
.vscode/
*.iml
```

---

### 💡 Dica Profissional

* ENV → produção / Docker
* local.yml → desenvolvimento

---

## ▶️ Como rodar

### 1. Criar banco:

```sql
CREATE DATABASE api_checkin;
```

### 2. Rodar aplicação:

```
mvn spring-boot:run
```

---

## 🧪 Rodar testes

```
mvn test
```

---

## 🔐 LGPD (básico)

* apenas nome armazenado
* sem dados sensíveis
* finalidade: controle de presença

---

## 🚧 Status do Projeto

### ✔ Implementado

* [x] API REST
* [x] DTOs
* [x] EventService
* [x] VisitorService (merge)
* [x] PostgreSQL
* [x] Testes
* [x] Testcontainers

### 🔄 Em andamento

* [ ] Checkin logs completos
* [ ] resposta estruturada
* [ ] validações

### 🚀 Futuro

* [ ] Kafka
* [ ] workers
* [ ] autenticação
* [ ] UUID
* [ ] QR seguro

---

## 💬 Como explicar em entrevista

> “Implementei uma API de sincronização com múltiplas fontes que aplica merge determinístico, evita duplicação e mantém histórico completo, com testes de integração usando Testcontainers e preparada para arquitetura com Kafka.”

---

## 🧠 Regra de Ouro

```
Se não roda → não está pronto
```

---

## 📄 Licença

A definir
