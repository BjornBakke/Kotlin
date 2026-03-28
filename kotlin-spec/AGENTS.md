# Repository Guidelines

## Prosjektstruktur og modulorganisering
Dette repoet er sentrert rundt Maven-modulen i `Kotlin-spec/kotlin-spec`.
- `src/main/kotlin`: Kotlin-eksempler gruppert etter tema (`basis`, `oop`, `functional`, `advanced`, `Intermediate`).
- `src/test/kotlin`: testkilder (for tiden lite/ingen innhold; legg nye tester her).
- `pom.xml`: konfigurasjon for bygg, test og kjøring.
- `target/`: genererte byggeartefakter; skal ikke redigeres manuelt.

Legg nye filer i riktig temapakke, og utvid eksisterende temamapper før du oppretter nye toppnivå-grupper.

## Bygg-, test- og utviklingskommandoer
Kjør kommandoer fra `Kotlin-spec/kotlin-spec`.
- `mvn compile`: kompilerer Kotlin-kilder.
- `mvn test`: kjører enhetstester med JUnit 5 + Kotlin test.
- `mvn clean test`: sletter gamle artefakter og kjører en ren testrunde.
- `mvn exec:java`: kjører konfigurert main-klasse (`MainKt`).

Eksempel:
```powershell
cd Kotlin-spec/kotlin-spec
mvn clean compile
```

## Kodestil og navnekonvensjoner
- Følg Kotlin sin offisielle stil (`kotlin.code.style=official` i `pom.xml`).
- Bruk 4 mellomrom for innrykk, ikke tabulator.
- Typer/grensesnitt: `PascalCase`; funksjoner/variabler: `camelCase`; konstanter: `UPPER_SNAKE_CASE`.
- Navngi filer etter hovedkonsept/klasse (for eksempel `SealedClasses.kt`, `InlineFunctions.kt`).
- Hold pakkenavn i små bokstaver (for eksempel `org.example.oop`).

## Retningslinjer for testing
- Foretrukket teststakk: JUnit Jupiter + `kotlin-test-junit5`.
- Plasser tester i tilsvarende pakker under `src/test/kotlin`.
- Navngi testfiler som `*Test.kt`, og bruk beskrivende testmetoder (gjerne med backticks ved behov).
- Kjør `mvn test` før du åpner en PR.

## Retningslinjer for commit og pull request
Lokal `.git`-historikk er ikke tilgjengelig i dette workspace-snapshotet, så bruk denne standarden:
- Commit-format: `type(scope): kort sammendrag` (for eksempel `feat(oop): legg til sealed class-oppgave`).
- Hold commits avgrenset til ett tema eller én atferdsendring.
- PR-er skal inneholde: formål, sentrale filer endret, hvordan verifisere, og eventuelt oppfølgingsarbeid.
- Hvis kjøreoutput endres, legg ved et kort konsollutdrag i PR-beskrivelsen.

## Sikkerhet og konfigurasjon
- Bruk kun avhengigheter fra Maven Central med mindre annet er eksplisitt godkjent.
- Ikke commit lokal IDE-tilstand utover delte prosjektinnstillinger.
- Unngå endringer i `target/`; regenerer heller med Maven.
