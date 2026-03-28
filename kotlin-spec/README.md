# Kotlin-spec

Praktisk Kotlin-øvingsrepo med eksempler innen basis, OOP, funksjonell programmering og mer avanserte tema.
Prosjektet bygges med Maven og Kotlin Maven Plugin.

## Innhold

- Kotlin-kode i tematiske mapper under `src/main/kotlin`
- Maven-oppsett i `pom.xml`
- Klar for enhetstester i `src/test/kotlin` (JUnit 5 + `kotlin-test-junit5`)

## Prosjektstruktur

```text
src/
  main/
    kotlin/
      basis/
      oop/
      functional/
      advanced/
      Intermediate/
  test/
    kotlin/
pom.xml
```

## Krav

- Git
- JDK (Java 8+)
- Maven 3.9+

Sjekk versjoner:

```powershell
java -version
mvn -version
git --version
```

## Kom i gang

Hvis du allerede står i prosjektmappa:

```powershell
cd  ...\Kotlin\Kotlin-spec
```

Bygg prosjektet:

```powershell
mvn clean compile
```

Kjør tester:

```powershell
mvn test
```

Kjør main-klasse (fra `exec-maven-plugin`):

```powershell
mvn exec:java
```

## Vanlige Maven-kommandoer

- `mvn compile`: kompilerer kildene
- `mvn test`: kjører tester
- `mvn clean test`: ren testrunde
- `mvn exec:java`: kjører konfigurert `mainClass` (`MainKt`)

## Publisere til GitHub (`git@github.com:BjornBakke/Kotlin.git`)

Hvis repoet er ny-initialisert lokalt:

```powershell
git init -b main
git remote add origin git@github.com:BjornBakke/Kotlin.git
git add .
git commit -m "chore(repo): initial project import"
git push -u origin main
```

Hvis du allerede har repo, men mangler upstream:

```powershell
git push -u origin main
```

Sjekk remote:

```powershell
git remote -v
```

## Kodestandard

- Kotlin stil: `official` (satt i `pom.xml`)
- Innrykk: 4 mellomrom
- Navn:
  - typer/klasser: `PascalCase`
  - funksjoner/variabler: `camelCase`
  - konstanter: `UPPER_SNAKE_CASE`

## Testing

- Testfiler legges i `src/test/kotlin`
- Filnavn bør følge `*Test.kt`
- Kjør alltid `mvn test` før push/PR

## Tips

- Ikke rediger `target/` manuelt; regenerer med Maven.
- Hold commits små og tematisk avgrenset.
