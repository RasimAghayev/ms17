# Technical Debt — ms17

## Debt register (prioritized)
| ID | Area | Description | Severity | Planned phase | Notes / blocker |
|---|---|---|---|---|---|
| TD-01 | Secrets/config | Hardcoded DB credential literals in `docker-compose.yml` and default/miswired password in `application-db.yml` (redacted). Treat committed secrets as potentially compromised. | P0 | 5/9/4 | Rotation/remediation requires careful handling; history rewrite only under Gate 1 if needed. |
| TD-02 | Build toolchain | Gradle 8.14.6 wrapper; README notes JDK 22 incompatibility with older context; toolchain/JDK pinning unclear for reproducible builds. | P1 | 5/6 | Upgrade to latest stable Gradle/JDK strategy with verification. |
| TD-03 | Dependencies | Spring Boot/version skew (validation 3.2.2, config-processor 3.1.5 vs 3.5.16), springdoc UI/API mismatch, outdated libs, legacy JUnit4, overlapping ModelMapper/MapStruct. | P1 | 6/7 | One logical upgrade group at a time; registry-verified latest; blockers recorded if unresolved. |
| TD-04 | Docker | Deprecated floating `FROM openjdk`, no multi-stage build, copies host-built jar, runs as root, no healthcheck, no digest pinning. | P1 | 5/6/9 | Must meet Docker standard and `make verify` gate. |
| TD-05 | Compose/runtime | App service commented out; only MySQL runs; DB name mismatch with app config; floating `mysql:8`; no app health dependency wiring. | P1 | 5 | Align service names/env/ports with reserved range. |
| TD-06 | Database/migrations | `ddl-auto: update` with Liquibase disabled and `liquibase-core` absent; duplicate placeholder changelogs; mixed MySQL5/MySQL8 dialect config. | P1 | 5/6/8 | Migration strategy decision required (enable real Liquibase or remove dead config) with evidence. |
| TD-07 | CI/CD | No GitHub Actions workflows; no automated lint/test/scan/build gates; Qodana not integrated. | P1 | 10 | Runner-time and security standards apply. |
| TD-08 | Security hardening | No evident authz/authn, empty security profile, no rate limiting/CORS review, logging/PII review pending. | P2 | 9 | Apply proportionate to demo scope; document residual risk. |
| TD-09 | API correctness/quality | Partial implementations and error handling gaps (`SocialService.findById` `.get()`, `findByAll` null, edit/delete edge cases, commented endpoints/code). | P2 | 6-8/11 | Fix with tests and baseline awareness. |
| TD-10 | Tests | Duplicate `CalculatorImplTest`; context/integration tests may need containerized DB; coverage/edge cases unknown. | P2 | 5/11 | Establish baseline first; tester does not edit production code. |
| TD-11 | Repo hygiene | `local_history.patch`, `todo.txt`, commented code, non-standard default branch, no LICENSE/CHANGELOG/.env.example/Makefile. | P3 | 4/15 | Branch/default-branch changes may require gates. |
| TD-12 | Docs drift | README claims (Redis/Liquibase/MtM/microservices naming) partially inaccurate vs code. | P3 | 3/15 | Keep docs aligned with real implementation. |
| TD-13 | Unused/suspected components | ModelMapper vs MapStruct overlap, JUnit4, placeholder `dp.Singleton`, entity beans in config — removal requires proof. | P3 | 7 | No guess-based deletion. |

## Dependency decision log (pending)
- No new dependencies added in Phase 3. Any addition in later phases must be recorded in `docs/DEPENDENCY_DECISIONS.md` per standing decision 3 (existing deps/stdlib coverage, maintenance, license, vulns, size, latest stable).

## Standing-decision exceptions / blockers (current)
- None approved yet. If a latest-stable upgrade cannot be completed in Phase 6, record: component, attempted target, blocker (peer conflict/upstream bug), highest compatible version used, and follow-up plan.
