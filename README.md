# CodeGuard Demo App

A minimal Spring Boot 3.x application used to demonstrate CodeGuard automated PR code review.

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

## Push to your personal GitHub

Use your own GitHub account — no `gh` CLI required.

### 1. Create an empty repo on GitHub

1. Sign in at https://github.com
2. Go to https://github.com/new
3. Repository name: **`codeguard-demo-app`**
4. Visibility: **Public** (simpler token scopes) or **Private** (requires `repo` scope on your token)
5. Do **not** check "Add a README" — this project already has one
6. Click **Create repository**

### 2. Push both branches

Replace `YOUR_USERNAME` with your GitHub username:

```bash
cd /home/priya-sharma/Documents/codeguard-demo-app

git remote add origin https://github.com/YOUR_USERNAME/codeguard-demo-app.git

git checkout main
git push -u origin main

git checkout feature/bad-code
git push -u origin feature/bad-code
```

If `origin` already exists, update it:

```bash
git remote set-url origin https://github.com/YOUR_USERNAME/codeguard-demo-app.git
```

### 3. Open a pull request

1. Go to `https://github.com/YOUR_USERNAME/codeguard-demo-app`
2. Click **Pull requests** → **New pull request**
3. Base: **`main`** ← Compare: **`feature/bad-code`**
4. Title: `Add intentional coding violations for CodeGuard demo`
5. Click **Create pull request**
6. Note the **PR number** in the URL (e.g. `.../pull/1` → PR number is `1`)

### 4. Review in CodeGuard

In the CodeGuard UI (http://localhost:5173) or via API, use **your** values:

| Field | Value |
|-------|-------|
| Owner | `YOUR_USERNAME` |
| Repository | `codeguard-demo-app` |
| PR Number | e.g. `1` |

See the CodeGuard README for token setup (`GITHUB_TOKEN`) and backend configuration.

## Intended violations on `feature/bad-code`

1. `System.out.println` instead of SLF4J
2. Controller calls `JpaRepository` directly (no service layer)
3. Missing Javadoc on public API
4. Poor exception handling (generic `RuntimeException`, swallowed errors)
5. Field injection with `@Autowired`
6. Missing `@Valid` on request body
7. Wrong HTTP status for create (`200` instead of `201`)
