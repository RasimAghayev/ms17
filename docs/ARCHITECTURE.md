# Architecture — ms17

## Business purpose
ms17 is a personal Spring Boot learning/demo application focused on JPA entity relationships (one-to-one, one-to-many/many-to-one) and basic CRUD REST APIs. Despite the "ms" prefix, it is a single monolithic service (no service-to-service communication, broker, or discovery).

## Stack (as-is)
- Language/runtime target: Java 17 (`sourceCompatibility = '17'`), Spring Boot 3.5.16 (Gradle plugin) with mixed dependency versions.
- Build: Gradle 8.14.6 wrapper (Groovy DSL), single module `ms17` v1.0.
- Web/API: Spring Web, Spring Validation (3.2.2 skew), springdoc OpenAPI starter UI/API (2.2.0/2.9.1 mismatch).
- Persistence: Spring Data JPA, Hibernate, MySQL connector-j 8.3.0 (runtime). Liquibase config present but disabled and dependency absent.
- Mapping/util: Lombok 1.18.30, MapStruct 1.5.5.Final, ModelMapper 3.2.0.
- Testing: JUnit 4.13.2 + JUnit Jupiter via `spring-boot-starter-test:3.5.16` (vintage excluded), Mockito 5.7.0, Spring Boot test context, MockMvc.
- Containers: `Dockerfile` (deprecated floating `openjdk`, copies prebuilt jar), `docker-compose.yml` v3.9 (MySQL 8 service only; app commented out).
- CI/CD: no `.github/workflows/`; `qodana.yaml` and `.gitpod.yml` present only.
- Docs: README.md, todo.txt, local_history.patch.

## Module/service structure
Layered package layout under `com.example.ms17`:
- `controller`: `ClientController`, `CustomerController`, `PatientController`, `SocialController` (REST endpoints).
- `service` + `service.impl`: per-entity services and `Calculator`/`CalculatorImpl` (standalone exercise).
- `repository`: Spring Data repositories for Customer/Client/Order/Patient/Social.
- `model` (+ `model.onetone`, `model.onetomany`), `dto`: entities and DTOs (entities extend DTOs in some cases; unconventional).
- `mapper`: MapStruct mappers for Client/Customer/Order/Patient.
- `exception`: `NotFoundException`, `ClientNotFound`, `CustomerNotFound`, `PatientNotFound`.
- `config.Config`: beans for RestTemplate, ModelMapper, and unusually entity beans (Customer, Patient).
- `dp.Singleton`: placeholder main method (not a real singleton implementation).

## Data model (from code)
- One-to-one: `Patient` ↔ `PatientDetail` (`@OneToOne`, cascade, mappedBy/join).
- One-to-many/many-to-one: `Client` ↔ `Order` (`@OneToMany`/`@ManyToOne`, lazy + fetch/join query usage, entity graph present).
- Claimed many-to-many not implemented as JPA relation: `Social.customerId` is plain int (no relationship annotations/join table).

## Configuration & environment
- Profiles included from `application.yml`: `common, db, security, logging` (active `local`).
- `application-db.yml`: MySQL datasource with env placeholders/defaults, `ddl-auto: update`, `show-sql: true`, mixed MySQL5/MySQL8 dialect settings, Liquibase changelog configured but `enabled: false`.
- DB name in JDBC (`ms17_demo3`) differs from compose DB (`ms17_demo`); compose has hardcoded credential literals (redacted in reports).
- `application-db.yml` password property references `${DB_CONNECTION_USERNAME:password}` (likely wrong env var/fallback smell) — behavior to be verified in later phases.
- No `.env.example`; security profile effectively empty.

## External integrations / API surface
- REST controllers expose CRUD-style endpoints for customers, clients, patients and partial social operations; CustomerController includes OpenAPI annotations. No outbound integrations, message brokers, or inter-service calls found.
- No auth/security configuration evident beyond empty profile stub.

## Build/test/CI/CD/Docker
- Build/test via Gradle wrapper; tasks configured with JUnit Platform and test logger. No Makefile.
- Tests: context test, controller tests (MockMvc), service/impl tests, duplicate `CalculatorImplTest` in two packages.
- No CI workflows; Qodana config not wired. Docker compose does not start the app service.

## Standing decisions mapping (why here / planned application)
- Latest versions always (decision 1): applies in Phase 6 after baseline — align Boot/plugin/starters/libs/Gradle/base images/CI actions to latest stable with registry verification and one-step-at-a-time migration; document blockers in `docs/TECHNICAL_DEBT.md`.
- Docker-first (decision 2): Phase 5 must provide multi-stage Dockerfile, compose app+db, Makefile (`build/test/verify/up` etc.), and run verification in containers; host runs not verification.
- No new dependency before dependency analysis (decision 3): Phase 2 inventory exists (`projects/info/deps/ms17.md`); any new dep in later phases needs `docs/DEPENDENCY_DECISIONS.md` entry (prefer stdlib/existing).
- Semantic Git (decision 4): Phase 4+ changes via `phase-<n>/<topic>` branches, Conventional Commits, one PR per phase, no `--no-verify`.
- Branch hygiene (decision 5): default branch is non-standard `RasimAghayev/L20` (not `main`/`test`); cleanup/rename decisions require gates where applicable (Gate 4/11 context) — Phase 4 to audit and propose.
- Dead code removal after upgrade (decision 6): Phase 7, proven by tooling+tests; remove obsolete configs/shims only with evidence.
- Design principles (decision 7): apply only where real present problems exist (KISS/YAGNI win). Layering already present; avoid speculative DDD/EDA/microservice patterns for this small demo unless justified.
- Per-phase best practices (decision 8): checklists satisfied per phase before close.
- Skill learning loop (decision 10): follow/create/update `(phase × stack)` skills via curator flow (Java/Gradle/Spring stack).
- Autonomous mode (decision 11): routine assumptions documented; approval gates parked as `needs-approval` in `APPROVALS.md`.
- No AI attribution (decision 12): no trailers/markers in commits/PRs.
- Discussions/Wiki/Dependabot checks (13/14): repo-level verification in later phases as applicable (no CI/Dependabot workflow currently present).

## Assumptions
- Read-only audit of canonical/worktree state at HEAD `6805081` on `RasimAghayev/L20`; no tests/build executed in this phase.
- "Latest stable" targets are not hardcoded here; must be read from registries at execution time (Phase 6).
- Committed credential-like literals in compose/config are treated as potentially sensitive; values redacted in all reports.
