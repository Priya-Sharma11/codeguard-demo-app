# CodeGuard Demo App

A minimal Spring Boot 3.x application used to demonstrate [CodeGuard](https://github.com) automated PR code review.

**This is not production code.** The `feature/bad-code` branch intentionally violates common Java/Spring coding standards so CodeGuard can flag them during PR review.

## Stack

- Java 21
- Spring Boot 3.3.x
- Spring Data JPA + H2 in-memory database
- Maven

## Run locally

```bash
mvn spring-boot:run
```

The API listens on **http://localhost:8081**.

### Sample requests

```bash
# List users
curl http://localhost:8081/api/users

# Create user
curl -X POST http://localhost:8081/api/users \
  -H "Content-Type: application/json" \
  -d '{"name":"Ada Lovelace","email":"ada@example.com"}'
```

H2 console: http://localhost:8081/h2-console (JDBC URL: `jdbc:h2:mem:usersdb`)

## Branches

| Branch | Purpose |
|--------|---------|
| `main` | Clean code — service layer, SLF4J logging, `@ControllerAdvice` |
| `feature/bad-code` | Intentional violations for CodeGuard testing |

Open a PR from `feature/bad-code` → `main` and review it with CodeGuard.

## Intended violations on `feature/bad-code`

1. `System.out.println` instead of SLF4J
2. Controller calls `JpaRepository` directly (no service layer)
3. Missing Javadoc on public API
4. Poor exception handling (generic `RuntimeException`, swallowed errors)
5. Field injection with `@Autowired`
6. Missing `@Valid` on request body
7. Wrong HTTP status for create (`200` instead of `201`)
