# Repository Guidelines

## Prosjektstruktur og modulorganisering
Dette repoet er sentrert rundt Maven-modulen i `kotlin-spec/`.
- `src/main/kotlin`: Kotlin-eksempler gruppert etter tema (`basis`, `oop`, `functional`, `advanced`, `Intermediate`). Pakkenavn er små bokstaver (`org.example.basis`, `org.example.oop`, `org.example.intermediate`, `org.example.functional`, `org.example.advanced`) selv om `Intermediate/`-katalogen beholder stor I pga. case-insensitive filsystem.
- `src/test/kotlin`: smoke-tester per pakke (se `BaseMainSmokeTest`). Legg nye tester her.
- `pom.xml`: konfigurasjon for bygg, test og kjøring (Kotlin 2.3.20, JVM target 25).
- `target/`: genererte byggeartefakter; skal ikke redigeres manuelt.

Legg nye filer i riktig temapakke, og utvid eksisterende temamapper før du oppretter nye toppnivå-grupper.

## Bygg-, test- og utviklingskommandoer
Kjør kommandoer fra `kotlin-spec/`.
- `mvn compile`: kompilerer Kotlin-kilder.
- `mvn test`: kjører smoke-testene (hver `main()` kalles via refleksjon).
- `mvn clean test`: sletter gamle artefakter og kjører en ren testrunde.
- `mvn exec:java`: kjører konfigurert main-klasse (`basis.MainKt`).

Eksempel:
```powershell
cd kotlin-spec
mvn clean compile
```

## Kodestil og navnekonvensjoner
- Følg Kotlin sin offisielle stil (`kotlin.code.style=official` i `pom.xml`).
- Bruk 4 mellomrom for innrykk, ikke tabulator.
- Typer/grensesnitt: `PascalCase`; funksjoner/variabler: `camelCase`; konstanter: `UPPER_SNAKE_CASE`.
- Navngi filer etter hovedkonsept/klasse (for eksempel `SealedClasses.kt`, `InlineFunctions.kt`).
- Hold pakkenavn i små bokstaver (`org.example.oop`, `org.example.intermediate`).
- Alle pedagogiske filer skal ha en blokk-kommentar på toppen som dekker:
  - Hva filen dekker (kort liste med `-`)
  - Typisk bruk ("Bruk når: ...")
  - Minst én "NB" eller "Tip" med en vanlig fallgruve
  - Lenke til offisiell Kotlin-dokumentasjon
- Kommentarer og `println`-output skal være på norsk for konsistens.
- Hjelpe-klasser som bare brukes i én fil (f.eks. `Person`, `Bruker`) markeres
  `private` på toppnivå for å unngå navnekollisjoner mellom pedagogiske filer.

## Retningslinjer for testing
- Teststakk: JUnit Jupiter + `kotlin-test-junit5`.
- Smoke-tester finner `*Kt`-klasser via refleksjon og kaller `main()`; en fil består hvis den kjører uten ufanget exception.
- Plasser nye tester i tilsvarende pakker under `src/test/kotlin`.
- Navngi testfiler som `*Test.kt`, og bruk beskrivende testmetoder (gjerne med backticks).
- Kjør `mvn test` før du åpner en PR.

## Retningslinjer for commit og pull request
- Commit-format: `type(scope): kort sammendrag` (for eksempel `feat(oop): legg til sealed class-oppgave`).
- Hold commits avgrenset til ett tema eller én atferdsendring.
- PR-er skal inneholde: formål, sentrale filer endret, hvordan verifisere, og eventuelt oppfølgingsarbeid.
- Hvis kjøreoutput endres, legg ved et kort konsollutdrag i PR-beskrivelsen.

## Sikkerhet og konfigurasjon
- Bruk kun avhengigheter fra Maven Central med mindre annet er eksplisitt godkjent.
- Ikke commit lokal IDE-tilstand utover delte prosjektinnstillinger.
- Unngå endringer i `target/`; regenerer heller med Maven.
