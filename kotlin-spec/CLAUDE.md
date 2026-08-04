# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Overview

Komplett Kotlin feature-referanse som dekker alle fornuftige språkegenskaper og
funksjonell programmering. Hvert eksempel er selvstendig med egen `main()`.
Prosjektet er organisert i stigende vanskelighetsgrad.

Alle kildefiler starter med en blokk-kommentar på norsk som beskriver tema,
typisk bruk, en "NB"/"Tip", og en lenke til offisiell Kotlin-dokumentasjon.
All println-output er også på norsk for konsistens.

## Build Commands

Run all commands from `kotlin-spec/`:

```bash
mvn compile                          # Compile only
mvn compile exec:java                # Run basis.MainKt
mvn test                             # Run smoke tests (JUnit 5)
mvn test -Dtest=BasisMainSmokeTest   # Run a single smoke test class
```

No Maven wrapper — use system-installed `mvn`. Individual files are designed to
be run from IntelliJ (green arrow at `main()`).

## Technical Details

- **Kotlin 2.3.20** with JVM target 25
- **Main class**: `basis.MainKt` (configured in `exec-maven-plugin`)
- Each source file has its own `main()` function for standalone execution
- No cross-file imports — every file is self-contained
- All packages use `package org.example.<package>` (all lowercase, even though
  the `Intermediate/` directory keeps capital I due to case-insensitive FS)
- Helper classes used only inside one file are marked `private` at top level
  to avoid collisions with same-named classes in other files (e.g. `Person`, `Bruker`)

## Project Structure

All source under `kotlin-spec/src/main/kotlin/`:

### `basis/` — Grunnleggende
Minste mulige program, val/var, string templates, klasser og data classes,
collections (List/Set/Map — mutable og immutable), null safety (`?`, `?.`, `?:`, `!!`),
funksjoner, kontrollflyt (if/when/for/while, ranges).

### `oop/` — OOP og typesystem
Arv (`open`/`abstract`/`override`), interfaces med default-metoder og konfliktløsning,
sealed classes, enum classes (med properties og interface-implementasjon),
`object` (singleton + companion + data object), visibility (public/internal/protected/private,
`private set`), primary/secondary konstruktører med init-blokker, smart casts,
type aliases, nested vs. inner classes.

### `Intermediate/` — Mellom
(Pakke: `org.example.intermediate`, mappenavn kapitalisert pga. FS.)
Alle 5 scope-funksjoner (`let`, `apply`, `run`, `also`, `with`) med sammenligning
og dedikerte eksempler pr. fil, extension functions (String, generisk, nullable
receiver, på egen klasse).

### `functional/` — Funksjonell programmering
Sequences (lazy vs eager), avanserte collection-operasjoner (`groupBy`/`partition`/
`associate`/`zip`/`windowed`/`chunked`/`flatMap`/`any`/`all`/`none`/`find`/`reduce`/`fold`/
`take`/`drop`/`distinct`/`sortedBy`/`sumOf`/`maxBy`), destructuring, inline functions
(+ `reified`), infix functions, tail recursion, function references (`::`), closures.

### `advanced/` — Avanserte egenskaper
`when` avansert (sealed matching, enum, range), labels og qualified returns,
exception handling (try-catch-finally, try som uttrykk, custom exceptions, `Nothing`),
custom property getters/setters, manuell lazy, `private set`, `const val`, `lateinit`,
delegation (`by lazy`, `observable`, `vetoable`, custom delegate, `by Map`),
raw strings (`trimMargin`/`trimIndent`/`buildString`/regex), varargs og spread (`*`),
operator overloading (`plus`/`minus`/`times`/`get`/`set`/`compareTo`/`contains`),
standard library utilities (`require`/`check`/`error`/`TODO`/`Pair`/`Triple`/`takeIf`/`repeat`).

## Smoke Tests

Smoke tests live in `src/test/kotlin/org/example/smoke/` and are the primary safety
net. `BaseMainSmokeTest` reflects over the classpath to find every `*Kt` class
under a given package prefix and invokes its `main()`. A file passes if it runs
without throwing an unhandled exception. To add coverage for a new package,
subclass `BaseMainSmokeTest` with the new prefix.

Current subclasses:
- `BasisMainSmokeTest` — `org.example.basis.`
- `OopMainSmokeTest` — `org.example.oop.`
- `IntermediateMainSmokeTest` — `org.example.intermediate.`
- `FunctionalMainSmokeTest` — `org.example.functional.`
- `AdvancedMainSmokeTest` — `org.example.advanced.`

## Not Covered (by design)
Coroutines, reflection, annotations, DSL construction — for komplekst for en
feature-referanse. Disse hører hjemme i egne, større prosjekter.
