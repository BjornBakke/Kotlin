# Kotlin-spec

Praktisk Kotlin-øvingsrepo med eksempler innen basis, OOP, funksjonell programmering
og mer avanserte tema. Hver fil har en egen `main()` slik at den kan kjøres selvstendig,
og hver fil starter med en blokk-kommentar som forklarer tema, typisk bruk og lenker
til offisiell Kotlin-dokumentasjon.

Prosjektet bygges med Maven og Kotlin Maven Plugin.

## Innhold

- [Start her](#start-her)
- [Anbefalt læringssti](#anbefalt-læringssti)
- [Prosjektstruktur](#prosjektstruktur)
- [Krav](#krav)
- [Kom i gang](#kom-i-gang)
- [Innholdsfortegnelse (tema → fil)](#innholdsfortegnelse-tema--fil)
- [Vanlige Maven-kommandoer](#vanlige-maven-kommandoer)
- [Kodestandard](#kodestandard)
- [Testing](#testing)
- [Publisere til GitHub](#publisere-til-github)
- [Tips](#tips)

## Start her

Dette repoet er laget for to ting samtidig:

- å lære Kotlin steg for steg
- å fungere som et personlig oppslagsverk når du trenger et konkret eksempel senere

Hvis du er ny i Kotlin, ikke hopp tilfeldig mellom filer. Start i `basis/`, kjør filene
enkeltvis, og les toppkommentaren før du ser på implementasjonen. Når et tema sitter,
går du videre til neste nivå.

## Anbefalt læringssti

`basis -> oop -> intermediate -> functional -> advanced`

- `basis`: lær syntaks, kontrollflyt, nullsafety, collections og enkle klasser
- `oop`: bygg forståelse for arv, interfaces, sealed classes, objekter og synlighet
- `intermediate`: lær scope-funksjoner, extension functions og generics
- `functional`: jobb med funksjonsreferanser, sekvenser, inline-funksjoner og samlingsoperasjoner
- `advanced`: bruk delegasjon, operatorer, egenskaper og mer avansert Kotlin-standardbibliotek

Praktisk arbeidsmåte:

- velg én fil
- kjør `main()`
- les utskriften
- endre litt i eksemplet selv
- gå videre først når du kan forklare hvorfor koden virker

## Prosjektstruktur

```text
src/
  main/
    kotlin/
      basis/          — grunnleggende syntaks, typer, kontrollflyt
      oop/            — objektorientert programmering
      Intermediate/   — scope-funksjoner, extensions (pakke: org.example.intermediate)
      functional/     — funksjonell programmering, lambdaer, sekvenser
      advanced/       — delegation, operator overloading, lateinit m.m.
  test/
    kotlin/           — smoke-tester som kjører alle main()-funksjoner
pom.xml
```

## Krav

- Git
- JDK 21 eller nyere (Maven er konfigurert til å bruke `jvmTarget = 25`)
- Maven 3.9+

Sjekk versjoner:

```powershell
java -version
mvn -version
git --version
```

## Kom i gang

Fra prosjektmappa:

```powershell
cd kotlin-spec
mvn clean compile
```

Kjør alle smoke-tester (kaller `main()` i hver fil og fanger exceptions):

```powershell
mvn test
```

Kjør en enkelt fil i IntelliJ ved å trykke på grønn pil ved siden av `fun main()`.

## Innholdsfortegnelse (tema → fil)

### basis — grunnleggende syntaks

| Tema | Fil | Beskrivelse |
|------|-----|-------------|
| Minste mulige program | `basis/Main.kt` | `fun main()`, val/var, string templates, enkel for-løkke |
| Kontrollflyt | `basis/ControlFlow.kt` | if/when/for/while, ranges (`..`, `downTo`, `step`, `withIndex`) |
| String templates | `basis/StringTemplates.kt` | `$var`, `${expr}`, escape av `$`, trimIndent/trimMargin |
| Klasser | `basis/Klasser.kt` | primærkonstruktør, mutable properties, vanlige klasser og identitet |
| Data classes | `basis/DataClasses.kt` | `data class`, `copy`, `equals`, `toString`, destructuring |
| Collections (List) | `basis/Collections.kt` | read-only vs mutable, `listOf`/`mutableListOf` |
| Set | `basis/Set.kt` | `setOf`, unikhet, allowliste-eksempel |
| Map | `basis/Map.kt` | `mapOf`, `to`-infix, iterasjon, backtick-navn |
| Funksjoner | `basis/Functions.kt` | enkle funksjoner, parameter-typer, returverdier |
| Nullsafety | `basis/Nullsafety.kt` | `?`, `?.`, `?:`, `!!` |
| Funksjoner som returnerer andre | `basis/FunctionReturns.kt` | funksjon som tar lambda og returnerer en handling |

### oop — objektorientert programmering

| Tema | Fil | Beskrivelse |
|------|-----|-------------|
| Arv | `oop/Inheritance.kt` | `open`/`override`, Shape-hierarki, Kjøretøy/Elbil |
| Interfaces | `oop/Interfaces.kt` | default-implementasjoner, konfliktløsning, generisk `Repository<T>` |
| Abstrakte klasser | `oop/AbstractClasses.kt` | abstract method vs. concrete, Dyr/Varsel-eksempler |
| Konstruktører | `oop/Constructors.kt` | primær, sekundær, `init`-blokker, init-rekkefølge |
| Enum classes | `oop/EnumClasses.kt` | enum med felt, metoder, interface-implementasjon |
| Sealed classes | `oop/SealedClasses.kt` | `Resultat<T>` (Suksess/Feil/Laster), UiTilstand |
| Nested/inner | `oop/NestedInnerClasses.kt` | nøstede vs. indre klasser, Builder-mønster |
| Objects | `oop/Objects.kt` | singleton (`object`), companion object factory |
| Smart casts | `oop/SmartCasts.kt` | `is`, `!is`, `as`, `as?` |
| Type aliases | `oop/TypeAliases.kt` | navngi komplekse typer for lesbarhet |
| Visibility | `oop/VisibilityModifiers.kt` | public/internal/protected/private, `private set` |

### Intermediate — scope-funksjoner og extensions

(Pakke: `org.example.intermediate`. Mappenavn begynner med stor I pga. filsystemet.)

| Tema | Fil | Beskrivelse |
|------|-----|-------------|
| Scope-funksjoner (oversikt) | `Intermediate/Scopefunctions.kt` | Sammenligning av `let`, `apply`, `run`, `also`, `with` |
| `apply` | `Intermediate/Apply.kt` | initialisering; `this` inni, objekt returneres |
| `also` | `Intermediate/Also.kt` | side-effekter; `it` inni, objekt returneres |
| `let` | `Intermediate/Let.kt` | null-sjekk + transformasjon |
| `run` | `Intermediate/Run.kt` | konfigurering + returverdi |
| `with` | `Intermediate/With.kt` | kall flere metoder på samme objekt |
| Extension functions | `Intermediate/Extensionfunctions.kt` | utvid eksisterende typer (String, egne klasser, generisk, nullable) |
| Generics | `Intermediate/Generics.kt` | generiske funksjoner, klasser og `in`/`out` |

### functional — funksjonell programmering

| Tema | Fil | Beskrivelse |
|------|-----|-------------|
| Lukkinger (closures) | `functional/Closures.kt` | lambdaer som fanger variabler fra scope |
| Destructuring | `functional/Destructuring.kt` | `val (a, b) = ...` på Pair/Triple/Map/data class |
| Funksjonsreferanser | `functional/FunctionReferences.kt` | `::funksjon`, `Klasse::metode`, bundet referanse |
| Infix-funksjoner | `functional/InfixFunctions.kt` | definer og bruk `a ganger b`-syntaks |
| Inline + reified | `functional/InlineFunctions.kt` | `inline`, `noinline`, `reified T` med `filterPåType` |
| Sekvenser | `functional/Sequences.kt` | eager vs lazy, `generateSequence`, ytelse |
| Tail recursion | `functional/TailRecursion.kt` | `tailrec` — stakk-sikker rekursjon |
| Collection-operasjoner | `functional/AdvancedCollections.kt` | `groupBy`/`partition`/`zip`/`fold`/`chunked` m.fl. |

### advanced — avanserte språkfunksjoner

| Tema | Fil | Beskrivelse |
|------|-----|-------------|
| Delegation | `advanced/Delegation.kt` | `by lazy`, `observable`, `vetoable`, `by Map` |
| Exception handling | `advanced/ExceptionHandling.kt` | try/catch/finally, try som uttrykk, `Nothing` |
| Labels og returns | `advanced/LabelsAndReturns.kt` | `return@forEach`, `break@ytre`, egendefinerte labels |
| lateinit | `advanced/LateinitVar.kt` | `lateinit var`, `::prop.isInitialized` |
| Operator overloading | `advanced/OperatorOverloading.kt` | `plus`/`minus`/`times`/`get`/`set`/`compareTo`/`contains` |
| Properties med getters/setters | `advanced/PropertyGettersSetters.kt` | custom setter-validering, manuell lazy, `private set`, `const val` |
| Standard lib utilities | `advanced/StandardLibUtils.kt` | `require`/`check`/`error`/`TODO`/`Pair`/`Triple`/`takeIf`/`repeat` |
| Strings (avansert) | `advanced/Strings.kt` | raw strings, trimMargin, trimIndent, buildString, regex |
| Varargs | `advanced/Varargs.kt` | `vararg`, spread-operator `*`, `intArrayOf` |
| When (avansert) | `advanced/WhenAdvanced.kt` | sealed match, enum, range, when uten argument |

## Vanlige Maven-kommandoer

- `mvn compile` — kompilerer kildene
- `mvn test` — kjører smoke-testene (hver `main()` kalles)
- `mvn clean test` — ren testrunde
- `mvn exec:java` — kjører konfigurert `mainClass` (`basis.MainKt`)

## Kodestandard

- Kotlin stil: `official` (satt i `pom.xml`)
- Innrykk: 4 mellomrom
- Navn:
  - typer/klasser: `PascalCase`
  - funksjoner/variabler: `camelCase`
  - konstanter: `UPPER_SNAKE_CASE`
- Alle pedagogiske filer skal ha blokk-kommentar på toppen som dekker:
  - Hva filen dekker
  - Typisk bruk (når skal jeg nå etter dette?)
  - En "NB" eller "Tip" med en vanlig fallgruve
  - Lenke til offisiell Kotlin-dokumentasjon
- Hjelpe-klasser som bare brukes i én fil (f.eks. `Person`, `Bruker`) markeres
  `private` på toppnivå for å unngå navnekollisjoner mellom filer.

## Testing

- Smoke-tester ligger i `src/test/kotlin/org/example/smoke/`
- `BaseMainSmokeTest` finner alle `*Kt`-klasser under en gitt pakke-prefiks via
  refleksjon, og kaller `main()` på hver. En test er bestått hvis programmet
  kjører uten å kaste unntak (noen filer demonstrerer eksepsjoner og fanger dem selv).
- Pakke-prefikser som testes: `org.example.basis.`, `org.example.oop.`,
  `org.example.intermediate.`, `org.example.functional.`, `org.example.advanced.`
- Legg til nye smoke-tester ved å arve fra `BaseMainSmokeTest` med ny prefiks
- Kjør alltid `mvn test` før push/PR

## Publisere til GitHub

Repo: `git@github.com:BjornBakke/Kotlin.git`

```powershell
git push -u origin main
```

## Tips

- Ikke rediger `target/` manuelt; regenerer med Maven
- Hold commits små og tematisk avgrenset
- Start alltid med header-kommentaren i en fil — den gir kontekst før koden
- Bruk IntelliJ sin grønne "run"-pil ved siden av `fun main()` for å kjøre én fil
- Når du står fast: slå opp i README først og følg læringsstien i stedet for å hoppe tilfeldig
