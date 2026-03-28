# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Overview

Komplett Kotlin feature-referanse som dekker alle fornuftige språkegenskaper og funksjonell programmering. Hvert eksempel er selvstendig med egen `main()`. Prosjektet er organisert i stigende vanskelighetsgrad.

## Build Commands

Run all commands from `Kotlin-spec/kotlin-spec/`:

```bash
mvn compile                          # Compile only
mvn compile exec:java                # Run Main.kt
mvn test                             # Run tests (JUnit 5)
mvn test -Dtest=MyTestClass          # Run a single test class
```

No Maven wrapper — use system-installed `mvn`. Individual files are designed to be run from IntelliJ (green arrow at `main()`).

## Technical Details

- **Kotlin 2.2.21** with JVM target 1.8
- **Main class**: `MainKt` (configured in `exec-maven-plugin`)
- Each source file has its own `main()` function for standalone execution
- No cross-file imports — every file is self-contained
- New packages use `package org.example.<package>`

## Project Structure

All source under `Kotlin-spec/kotlin-spec/src/main/kotlin/`:

### `basis/` — Grunnleggende (8 filer)
Variabler, string interpolation, klasser, data classes, funksjoner (named/default params, single-expression, higher-order, function types, lambdas), collections (List, Set, Map — mutable og immutable), null safety (safe calls, elvis operator), ranges, kontrollflyt (if-else, when, for-in).

### `Intermediate/` — Mellom (7 filer)
Alle 5 scope functions (let, apply, run, also, with), extension functions, method chaining.

### `oop/` — OOP og typesystem (11 filer)
Arv (open/abstract/override), interfaces (default-metoder, multippel implementering, konfliktløsning), sealed classes, enum classes (med properties/metoder), object declarations, companion objects, data objects, visibility modifiers, primary/secondary constructors, init-blokker, smart casts (is/as/as?), type aliases, nested/inner classes.

### `functional/` — Funksjonell programmering (8 filer)
Sequences (lazy vs eager), avanserte collection-operasjoner (groupBy, partition, associate, zip, windowed, chunked, flatMap, any/all/none, find, reduce, take/drop, distinct, sortedBy), destructuring, inline functions, infix functions, tail recursion, function references (::), closures.

### `advanced/` — Avanserte egenskaper (10 filer)
When avansert (sealed class matching, type-sjekk, range, expression), labels og qualified returns, exception handling (try-catch-finally, try som expression, custom exceptions, use), custom property getters/setters, backing fields, const val, lateinit, delegering (lazy, observable, vetoable, custom delegate, by map), raw strings (trimMargin/trimIndent/buildString), varargs, operator overloading, standard library utilities (require, check, error, TODO, Pair/Triple).

## Not Covered (by design)
Coroutines, reflection, annotations, DSL construction — for komplekst for en feature-referanse.
