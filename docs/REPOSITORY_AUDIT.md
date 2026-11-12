# Repository Audit — ms17

Audit date (UTC): 2026-11-11  
Scope: Phase 3 discovery from code/config/docs in worktree (HEAD `6805081`, branch `phase-3/audit-plan` / default `RasimAghayev/L20`). No production code changes, no secrets exposed.

## Discovery summary
- **Type:** Single Spring Boot (Java 17 target) learning/demo REST app; not a microservices system.
- **Modules:** Controllers (Client/Customer/Patient/Social), Services+impls, Repositories, Entities/DTOs, MapStruct mappers, Exceptions, Config, placeholder `dp.Singleton`, `Calculator` utility exercise.
- **Config/env:** `application.yml` includes common/db/security/logging; DB uses MySQL with env placeholders and defaults. `SERVER_PORT`, `DB_CONNECTION_IP`, `DB_CONNECTION_PORT`, `DB_CONNECTION_USERNAME`, `LOG_LEVEL` referenced. Security profile empty.
- **API:** CRUD-style REST endpoints; limited OpenAPI annotations (Customer). No external service integrations found.
- **DB/migrations:** Spring Data JPA/Hibernate with `ddl-auto: update`. Liquibase changelog files exist (`v-0.1/20230809-01-DB.yaml`, `v1/v12.yml`, identical placeholder content) but Liquibase disabled and `liquibase-core` not declared.
- **Tests:** JUnit 5 platform with Spring Boot test/MockMvc/Mockito tests present; JUnit 4 dependency also present (vintage excluded). Duplicate `CalculatorImplTest` classes in two packages. `Ms17ApplicationTests` context test exists. (Execution/coverage not measured in this phase.)
- **CI/CD:** No GitHub Actions workflows. `qodana.yaml` configured but not wired; `.gitpod.yml` runs `./gradlew build`.
- **Docker:** `Dockerfile` copies prebuilt jar into floating deprecated `openjdk` image (no multi-stage/non-root/healthcheck/pinning). `docker-compose.yml` defines MySQL 8 only; application service commented out.
- **Docs:** README (with disclosed limitations), todo.txt, local_history.patch; no LICENSE, no CHANGELOG, no ADRs.

## Key issues (P0–P3)

### P0 (critical / security-correctness risks)
- **Committed credential-like literals in compose/config (redacted):** `docker-compose.yml` contains DB password literals; `application-db.yml` contains a default password token and uses `${DB_CONNECTION_USERNAME:password}` for `password` (likely miswired env/default). Treat as potentially compromised per policy; values not reproduced. (Secret scan: matches at `docker-compose.yml:13,16` for password-assignment pattern; no private keys/tokens found by patterns used.)

### P1 (high)
- **Build/runtime reproducibility gaps:** Gradle wrapper 8.14.6 vs README-noted local JDK 22 incompatibility history; Dockerfile expects prebuilt `build/libs/ms17-1.0.jar` and uses deprecated floating base image; compose does not run app.
- **Dependency/version skew and drift:** Spring Boot plugin 3.5.16 but `spring-boot-starter-validation:3.2.2` and `spring-boot-configuration-processor:3.1.5`; springdoc UI/API mismatch (2.2.0 vs 2.9.1); overlapping mappers (MapStruct + ModelMapper) and legacy JUnit4 present — upgrade/alignment needed (Phase 6/7) with compatibility verification.
- **DB/migration inconsistency:** `ddl-auto: update` in app while Liquibase files exist but disabled/unusable; DB name mismatch (`ms17_demo3` URL vs compose `ms17_demo`); mixed Hibernate dialect settings (MySQL5/MySQL8).
- **No CI gates:** no automated build/test/lint/scan pipeline.

### P2 (medium)
- **Security hardening absent:** no auth/RBAC configuration evident, no rate limiting/CORS policy review, security profile empty, Docker runs as root by default in current image, no image scanning in CI.
- **API/behavior/documentation mismatches:** README claims Redis/Liquibase/MtM not fully reflected in implementation; Social "relationship" not a JPA relation; `CustomerServiceImpl.edit` returns `null` in some paths; `SocialService.findById` uses `.get()` without not-found handling; some endpoints/methods partially implemented (e.g. commented code, `findByAll` returns null).
- **Test quality signals:** duplicate test classes for `CalculatorImplTest`; `Ms17ApplicationTests` may require DB/profile setup to pass in isolation (unverified here); limited coverage of error/edge cases.
- **Docker/compose smells:** floating `mysql:8` tag, no healthchecks/depends_on conditions for app (when added), no `.dockerignore` reviewed here.

### P3 (low / hygiene)
- **Repository hygiene:** `local_history.patch`, `todo.txt`, commented-out code in multiple files, placeholder singleton, IDE/internal artifacts risk; default branch non-standard (`RasimAghayev/L20`).
- **Missing governance/docs artifacts:** no `LICENSE`, `.env.example`, `CHANGELOG`, `docs/DEPENDENCY_DECISIONS.md`, `Makefile`, ADRs.
- **Unused/suspected deps/config:** ModelMapper vs MapStruct overlap, JUnit4 legacy, devtools/config-processor skew — prove before removal (Phase 7).

## Duplication observations
- Duplicate `CalculatorImplTest` implementations in `service` and `service/impl` test packages.
- Two identical placeholder Liquibase changelogs (`v-0.1/20230809-01-DB.yaml`, `v1/v12.yml`).
- Repeated mapping/DTO patterns and some commented duplicate dependency lines in `build.gradle`.

## Baseline/verification status
- No build/test execution performed in Phase 3 (audit only). Existing behavior/green status is **unverified** here; Phase 5 must establish Docker-based baseline/characterization as needed.
