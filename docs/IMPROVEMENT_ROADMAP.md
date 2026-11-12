# Improvement Roadmap — ms17

## Objective
Bring `ms17` (Java 17 / Spring Boot / Gradle, single-module demo REST app with MySQL/JPA) to a reproducible, Docker-first, secure, and maintainable baseline while preserving existing business behavior unless an intentional, approved change is required.

## Phase 4–11 plan (applicability)

| Phase | Agent | Applicability | Plan for ms17 | Key risks / notes |
|---|---|---|---|---|
| 4. Git hygiene | `git-steward` | Applicable | Full-history secret scan (gitleaks), audit branches/PRs, add/verify `.gitignore`/`.gitattributes`/pre-commit secret scan, assess default branch `RasimAghayev/L20` vs `main/test` policy, clean merged/stale branches per rules. Do not merge dependency-bot PRs (Phase 6). | Committed DB credential literals exist (redacted) — treat as compromised, report and request rotation; history rewrite/force-push only with Gate 1. Default-branch rename is Gate 4; unmergeable abandoned branches Gate 11. |
| 5. Container baseline | `container-engineer` | Applicable | Create/replace Dockerfile with multi-stage `base/deps/build/test/runtime` (pinned supported JDK base image+digest, non-root, healthcheck), add `.dockerignore`, working `docker-compose.yml` (app + MySQL, healthchecks, depends_on healthy), `Makefile` (`build/test/verify/up/run/down` etc.), `.env.example` placeholders, `docs/BASELINE.md`. Run existing tests in Docker test stage and record results. | Current Dockerfile/compose are non-functional for app; DB config mismatches/miswired password var may block startup; JDK/Gradle compatibility must be stabilized for `make verify`. No host-run verification. |
| 6. Upgrade to latest | `upgrader` | Applicable | Follow dependency-map order; resolve version skew, upgrade Gradle/plugins/Spring Boot/libs/MySQL connector/base images/CI actions to latest stable (registry-verified) one logical step at a time with Docker `make verify` per step. Add `docs/DEPENDENCY_DECISIONS.md` for any new dependency. Handle bot PRs per policy. | Major Boot/framework jumps (3.5 → 4.x line if chosen) likely breaking; springdoc/Lombok/MapStruct/Hibernate/Jakarta compatibility; Gate 5 if repo public contract breaks. Unresolved blockers documented in `docs/TECHNICAL_DEBT.md`. |
| 7. Dead code & restructure | `cleaner` | Applicable | Remove proven dead/duplicate code and configs (e.g. duplicate `CalculatorImplTest`, dead Liquibase/config paths if decisioned, commented/unused code, placeholder `dp.Singleton` if unused) using tooling+tests; align layout to upgraded conventions. | Do not guess: reflection/framework registration/dynamic refs checked. Public API removal requires Gate 5. Preserve baseline behavior. |
| 8. Architecture | `architect` | Applicable (limited) | Apply DRY/KISS/YAGNI/SOLID only where real present problems exist; document "why here" and ADRs for non-trivial choices. Keep solution simple for a small demo (avoid speculative DDD/EDA/microservices). Fix config/bean anti-patterns if evidenced (e.g. entity beans) and API/service correctness issues within scope. | Avoid over-engineering; KISS/YAGNI win conflicts. |
| 9. Hardening | `hardener` | Applicable | Security review (validation, authn/authz as appropriate, OWASP Top 10 floor), secret handling (env/secret mounts only), dependency/image scanning, structured logging without secrets/PII, health endpoints, 12-factor config, dual-mode considerations where relevant, `openapi.yaml` alignment if maintained. | Demo app may have limited auth requirements — document residual risk honestly. No real secrets/environments. |
| 10. CI/CD | `ci-engineer` | Applicable | Add GitHub Actions pipeline: parallel fast checks (format/lint/secret scan) → Docker test/build → scans; cache deps/layers; pin actions by SHA; least privilege; timeouts/concurrency; measure and record timings. | No existing CI baseline; runner-time budget and real green run required (or overrun documented). |
| 11. Tests | `tester` | Applicable | Improve/extend unit, integration, API, edge/error tests run in Docker; regression against `docs/BASELINE.md`; produce `docs/TESTING_STRATEGY.md` and `docs/TEST_REPORT.md` with real results. Do not edit production code. | Some existing tests may be environment-dependent; `Ms17ApplicationTests` context load needs DB strategy in containers. Report defects to Orchestrator (R8). |

## Phases 12–16 (ecosystem context, not executed in this phase)
- Phase 12 (merge small repos): n/a for this repo alone unless selected as merge candidate later (Gate 3).
- Phase 13 (shared library): evaluate duplication only after cross-repo evidence; no extraction without Gate 7.
- Phase 14 (rename): repo naming change only under Gate 2; map in `RENAMES.md` if approved.
- Phase 15 (docs/manifest/diagrams): applicable later; docs must match real implementation.
- Phase 16 (platform repo): n/a for single app; local kind/compose verification only unless Gate 9.

## Risks and assumptions
- **Secret exposure risk:** committed DB credentials/defaults detected (redacted). Must be reported for rotation; removal from git history is not a fix and needs Gate 1 if pursued.
- **Verification risk:** no build/tests executed in Phase 3; baseline may be red or environment-sensitive (JDK/DB). Phase 5 must establish evidence in Docker.
- **Upgrade risk:** major Spring/Gradle/library jumps and config bugs (password env mapping, DB name/dialect mismatch) can break startup/tests; migrate incrementally.
- **Scope risk:** README claims features (Redis/Liquibase/MtM) not present — later phases must not invent functionality (YAGNI) and must document intentional behavior changes.
- **Branch/gate risk:** non-standard default branch and potential abandoned work require careful gate handling (Gates 1/4/11 as applicable).

## Gate 10 status
Phase 3 plan prepared for `ms17`. **Needs approval (Gate 10)** — recorded in `projects/info/state/PHASES.md` and pending in `projects/info/state/APPROVALS.md` unless explicitly pre-approved by run command.
