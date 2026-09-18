# ms17

A personal Spring Boot learning/demo project exploring JPA entity
relationships and a couple of core design patterns. Despite the "ms"
name, this is a **single Spring Boot application**, not a microservices
system — there's no service-to-service communication, message broker, or
service discovery anywhere in the code.

The repository's own GitHub description lists "Java - SpringBoot -
Docker - Redis - Liqubase - OtO - OtM - MtM". Reading the actual source
(this README's basis), some of that is accurate and some isn't — see
"Known limitations" below for exactly which.

This README documents the default branch (`RasimAghayev/L20`) as it
actually is; no source files were changed to produce it.

## Architecture style

A conventional **layered Spring Boot REST service** — `Controller →
Service → Repository`, with MapStruct/ModelMapper-based DTO mapping in
between. No CQRS, no event-driven messaging, no microservice boundaries;
one application, one MySQL database. (Per the portfolio's `[R112]` rule:
this is the honest label — "ms17" reads like a microservice name, but
nothing in the code establishes microservice architecture.)

## What's actually here

### Domain (JPA relationship demos)

| Relationship | Entities | Notes |
|---|---|---|
| One-to-one | `Patient` ↔ `PatientDetail` | Real `@OneToOne` mapping (`Patient` owns the `mappedBy` side). Both entities extend their own DTO class (`PatientDto`/`PatientDetailDto`) — an inheritance direction, DTO→entity, that's the reverse of the usual convention. |
| One-to-many / many-to-one | `Client` ↔ `Order` | Real `@OneToMany`/`@ManyToOne` mapping, with a `@NamedEntityGraph` and explicit `FetchMode.JOIN`. |
| (claimed) many-to-many | `Customer`, `Social` | **Not actually a JPA relationship.** `Social.customerId` is a plain `int` field with no `@ManyToMany`/`@ManyToOne` annotation or `@JoinTable` — just an application-level foreign key by convention. `SocialController` only exposes `save`/`findById`, no list/update/delete. The description's "MtM" isn't implemented anywhere in this codebase. |

### Everything else

| Path | Purpose |
|---|---|
| `src/main/java/.../controller/*` | Four REST controllers (`Patient`, `Client`, `Customer`, `Social`) — standard CRUD, `Customer`'s alone has OpenAPI/Swagger annotations. |
| `src/main/java/.../service`, `service/impl` | Service interfaces + impls per entity, plus a standalone `Calculator` (`sum`/`sub`/`multiple`/`divide`/`isOdd`) unrelated to the rest of the app — reads as a separate exercise, not part of the domain. |
| `src/main/java/.../dp/Singleton.java` | Intended as a Singleton design-pattern example (it's in a package named `dp`), but **doesn't implement the Singleton pattern** — it's just a `public static void main` that prints a placeholder string (`"Sdflksdf"`). |
| `src/main/java/.../config/Config.java` | Declares `RestTemplate` and `ModelMapper` beans — plus `@Bean public Customer customer()` and `@Bean public Patient patient()`, which registers two `@Entity` classes as Spring singleton beans. Unusual and easy to misuse (a single shared entity instance across the whole app context), not something any controller/service actually consumes. |
| `src/main/java/.../Ms17Application.java` | The `CommandLineRunner.run()` body is almost entirely commented-out scratch code (manual repository calls, native-query examples) — left over from earlier exploration, not exercised on startup. |
| `src/main/resources/application*.yml` | Split into `common` (server port), `db` (datasource + JPA + Liquibase), `security` (empty stub), `logging` (log level) profiles, all included from the base `application.yml` (`profiles.active: local`). |
| `src/main/resources/v-0.1/20230809-01-DB.yaml`, `v1/v12.yml` | Two **identical** Liquibase changelog files, both containing the same placeholder `test_table`/`test_column` definition — not real schema. See "Known limitations": Liquibase isn't actually wired up to run these. |
| `docker-compose.yml` | Defines only a `db` service (MySQL 8). **The application service itself is commented out** (`# ms: image: 1234agil/ms17:1.1`) — `docker compose up` starts the database only, not the app. |
| `Dockerfile` | Copies a pre-built `build/libs/ms17-1.0.jar` into `openjdk` and runs it. `FROM openjdk` uses Docker Hub's now-deprecated, no-longer-updated `openjdk` image. |
| `qodana.yaml` | JetBrains Qodana static-analysis config — present, but there's no `.github/workflows` (or any CI) that actually invokes it. |
| `local_history.patch` | An accidental commit of an IntelliJ "Local History" diff snapshot (IDE-internal metadata, not real source) — reads as something that should never have been added to git. |
| `todo.txt` | Personal Azerbaijani study notes about Java reference types (strong/weak/soft/phantom) — developer scratch notes, not a project roadmap. |
| `src/test/java/.../*Test.java` | 5 test classes (JUnit 5 + Mockito) covering `Customer`/`Patient` controllers and services, plus `Calculator` (twice — `service/CalculatorImplTest.java` and `service/impl/CalculatorImplTest.java` both exist, same class name in two packages). |

## Branches

The default branch is `RasimAghayev/L20` (not `main`/`master`) — this
repo is organized as a sequence of course/lecture branches. One other
branch, `RasimAghayev/L22-23` ("Lecture 22-23 ACID & @Transactional"),
adds an `Account`/`TransferService` transaction demo and is **not merged**
into the default branch (verified via `git merge-base --is-ancestor`) —
so a checkout of the default branch won't include it.

## Setup

Requires JDK 17 (this repo's `sourceCompatibility`) and the Gradle
wrapper's pinned Gradle 8.4.

```bash
docker compose up -d          # starts only the MySQL database
./gradlew bootRun             # runs the app locally against it
```

There is no compose service for the application itself — see "Known
limitations".

## Known limitations (disclosed, not fixed by this pass)

- **The description's "Redis" isn't implemented anywhere** — no Redis
  dependency in `build.gradle`, no Redis client code, no Redis service in
  `docker-compose.yml`.
- **The description's "Liquibase" is present but inert.** `liquibase-core`
  is not a declared dependency in `build.gradle`, and
  `application-db.yml` sets `spring.liquibase.enabled: false` explicitly
  — even if the dependency were added, Liquibase would still be switched
  off. The two changelog files under `v-0.1/`/`v1/` are identical
  placeholder content (`test_table`), not real schema.
- **The description's "MtM" (many-to-many) isn't implemented** — see the
  domain table above; `Customer`/`Social` use a plain integer field, not
  a JPA relationship.
- **`docker compose up` doesn't run the application** — only the MySQL
  database service is defined; the app service is commented out.
- **`Dockerfile` uses the deprecated `openjdk` Docker Hub image.**
- **`application-db.yml`'s password property reads the wrong environment
  variable**: `password: ${DB_CONNECTION_USERNAME:password}` — setting
  `DB_CONNECTION_PASSWORD` (the name that would be expected) has no
  effect; only `DB_CONNECTION_USERNAME` or the `password` literal
  fallback is ever used.
- **Database name mismatch**: `docker-compose.yml` creates
  `MYSQL_DATABASE: ms17_demo`, but the JDBC URL in `application-db.yml`
  points at `ms17_demo3` (mitigated only by `createDatabaseIfNotExist=true`,
  which creates a second, separate database rather than using the one
  compose provisioned).
- **Contradictory Hibernate dialect configuration**: `spring.jpa.database-platform`
  is set to `MySQL5Dialect` while `spring.jpa.properties.hibernate.dialect`
  is set to `MySQL8Dialect` in the same file.
- **Mixed Spring Boot dependency versions**: the Spring Boot Gradle
  plugin is pinned to `3.1.5`, but several `spring-boot-starter-*`
  dependencies have been bumped to `3.2.2` by merged Renovate PRs — a
  partially-applied upgrade, not a consistent version set.
- **Two `CalculatorImplTest` classes exist** in different packages
  (`service` and `service/impl`), duplicating coverage of the same
  `CalculatorImpl` class.
- **Not verified via a real build in this environment**: `./gradlew test`
  was attempted, but the pinned Gradle 8.4 (`gradle-wrapper.properties`)
  doesn't support this machine's installed JDK 22 (`Unsupported class
  file major version 66`) — Gradle 8.4 supports up to JDK 21. Not fixed
  here (bumping the Gradle wrapper or changing the JDK are both real
  changes, out of scope for a documentation pass) — disclosed as
  unverified rather than assumed working.
- **No `LICENSE` file.**
