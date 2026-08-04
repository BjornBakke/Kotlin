# Kotlin-spec forbedringsplan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Gjøre `kotlin-spec` tydeligere som læringsrepo og oppslagsverk ved å rydde svake filer, styrke README, og legge til noen få sentrale Kotlin-temaer som mangler.

**Architecture:** Endringene holdes små og pedagogiske. Først legges det til repo-kvalitetstester som beskriver ønsket sluttstatus, deretter ryddes intermediate-området og nytt innhold legges til i små, verifiserbare steg. README oppdateres til slutt slik at læringsløpet og nye temaer faktisk blir synlige.

**Tech Stack:** Kotlin 2.3.20, Maven, JUnit Jupiter, kotlin-test-junit5

---

### Task 1: Legg til repo-kvalitetstester for læringsstruktur

**Files:**
- Create: `src/test/kotlin/org/example/repo/RepositoryQualityTest.kt`
- Test: `src/test/kotlin/org/example/repo/RepositoryQualityTest.kt`

- [ ] **Step 1: Write the failing test**

```kotlin
package org.example.repo

import java.nio.file.Files
import java.nio.file.Path
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RepositoryQualityTest {

    private val projectRoot: Path = Path.of("").toAbsolutePath()

    @Test
    fun `intermediate should not contain placeholder or malformed filenames`() {
        val names = Files.list(projectRoot.resolve("src/main/kotlin/Intermediate"))
            .use { stream -> stream.map { it.fileName.toString() }.toList() }

        assertFalse("exer.kt" in names)
        assertFalse(names.any { it.contains('\uFEFF') })
        assertTrue("Also.kt" in names)
        assertTrue("Apply.kt" in names)
    }

    @Test
    fun `readme should describe start here and learning path`() {
        val readme = Files.readString(projectRoot.resolve("README.md"))

        assertTrue(readme.contains("## Start her"))
        assertTrue(readme.contains("Anbefalt læringssti"))
        assertTrue(readme.contains("basis -> oop -> intermediate -> functional -> advanced"))
    }

    @Test
    fun `repo should expose dedicated files for data classes and generics`() {
        assertTrue(Files.exists(projectRoot.resolve("src/main/kotlin/basis/DataClasses.kt")))
        assertTrue(Files.exists(projectRoot.resolve("src/main/kotlin/Intermediate/Generics.kt")))
    }
}
```

- [ ] **Step 2: Run test to verify it fails**

Run: `mvn -Dtest=RepositoryQualityTest test`
Expected: FAIL fordi `exer.kt` og BOM-fil fortsatt finnes, README mangler nye seksjoner, og nye filer ikke finnes ennå.

- [ ] **Step 3: Write minimal implementation**

```text
Planlagt implementasjon i neste tasks:
- fjerne/erstatte placeholder-filer i Intermediate
- legge til DataClasses.kt og Generics.kt
- oppdatere README med tydelig læringssti
```

- [ ] **Step 4: Run test to verify it still fails for the expected reasons**

Run: `mvn -Dtest=RepositoryQualityTest test`
Expected: FAIL med konkrete treff på manglende filer/README-seksjoner, ikke på kompilasjonsfeil i testen.

- [ ] **Step 5: Commit**

```bash
git add src/test/kotlin/org/example/repo/RepositoryQualityTest.kt
git commit -m "test(repo): legg til kvalitetskrav for læringsrepoet"
```

### Task 2: Rydd Intermediate og legg til Generics

**Files:**
- Modify: `src/main/kotlin/Intermediate/Scopefunctions.kt`
- Modify: `src/main/kotlin/Intermediate/Apply.kt`
- Modify: `src/main/kotlin/Intermediate/Also.kt`
- Modify: `src/main/kotlin/Intermediate/Extensionfunctions.kt`
- Delete: `src/main/kotlin/Intermediate/exer.kt`
- Delete: `src/main/kotlin/Intermediate/Also﻿.kt`
- Create: `src/main/kotlin/Intermediate/Generics.kt`
- Test: `src/test/kotlin/org/example/repo/RepositoryQualityTest.kt`

- [ ] **Step 1: Write the failing test**

```kotlin
@Test
fun `repo should expose dedicated files for data classes and generics`() {
    assertTrue(Files.exists(projectRoot.resolve("src/main/kotlin/basis/DataClasses.kt")))
    assertTrue(Files.exists(projectRoot.resolve("src/main/kotlin/Intermediate/Generics.kt")))
}
```

- [ ] **Step 2: Run test to verify it fails**

Run: `mvn -Dtest=RepositoryQualityTest#repo\ should\ expose\ dedicated\ files\ for\ data\ classes\ and\ generics test`
Expected: FAIL fordi `Generics.kt` mangler.

- [ ] **Step 3: Write minimal implementation**

```kotlin
package org.example.intermediate

/**
 * Generics - skriv kode som virker for flere typer
 *
 * Dekker:
 *  - generiske funksjoner med `<T>`
 *  - generiske klasser
 *  - `out` for trygg produksjon av verdier
 *  - `in` for trygg konsumering av verdier
 *
 * Bruk når: du vil gjenbruke samme logikk for flere typer uten å miste typesikkerhet.
 *
 * NB: bruk `out` når du bare returnerer T, og `in` når du bare tar imot T.
 *
 * Docs: https://kotlinlang.org/docs/generics.html
 */

private data class Boks<T>(val verdi: T)

private interface Kilde<out T> {
    fun hent(): T
}

private interface Sluk<in T> {
    fun motta(verdi: T)
}

private class TekstKilde : Kilde<String> {
    override fun hent(): String = "generisk verdi"
}

private class AnyLogger : Sluk<Any> {
    override fun motta(verdi: Any) {
        println("  logg: $verdi")
    }
}

private fun <T> førsteEllerNull(liste: List<T>): T? = liste.firstOrNull()

fun main() {
    val tallBoks = Boks(42)
    println("Boks: ${tallBoks.verdi}")
    println("Første navn: ${førsteEllerNull(listOf("Ada", "Bob"))}")

    val kilde: Kilde<String> = TekstKilde()
    println("Fra kilde: ${kilde.hent()}")

    val sluk: Sluk<String> = AnyLogger()
    sluk.motta("Hei fra in/out")
}
```

- [ ] **Step 4: Run test to verify it passes**

Run: `mvn -Dtest=RepositoryQualityTest test`
Expected: én test kan fortsatt feile på README/data classes, men `Generics.kt`-kravet og filnavnkravet skal være nærmere grønt.

- [ ] **Step 5: Commit**

```bash
git add src/main/kotlin/Intermediate src/test/kotlin/org/example/repo/RepositoryQualityTest.kt
git commit -m "feat(intermediate): rydd scope-filer og legg til generics"
```

### Task 3: Skill ut DataClasses som eget læringstema

**Files:**
- Create: `src/main/kotlin/basis/DataClasses.kt`
- Modify: `src/main/kotlin/basis/Klasser.kt`
- Test: `src/test/kotlin/org/example/repo/RepositoryQualityTest.kt`

- [ ] **Step 1: Write the failing test**

```kotlin
@Test
fun `repo should expose dedicated files for data classes and generics`() {
    assertTrue(Files.exists(projectRoot.resolve("src/main/kotlin/basis/DataClasses.kt")))
    assertTrue(Files.exists(projectRoot.resolve("src/main/kotlin/Intermediate/Generics.kt")))
}
```

- [ ] **Step 2: Run test to verify it fails**

Run: `mvn -Dtest=RepositoryQualityTest#repo\ should\ expose\ dedicated\ files\ for\ data\ classes\ and\ generics test`
Expected: FAIL fordi `DataClasses.kt` mangler.

- [ ] **Step 3: Write minimal implementation**

```kotlin
package org.example.basis

/**
 * DataClasses - verdibærende modeller med nyttige standardmetoder
 *
 * Dekker:
 *  - `data class`
 *  - automatisk `toString`, `equals`, `hashCode`
 *  - `copy(...)`
 *  - destructuring
 *
 * Bruk når: objektet hovedsakelig representerer data og ikke kompleks oppførsel.
 *
 * NB: bare properties i primary constructor er med i `equals`, `copy` og destructuring.
 *
 * Docs: https://kotlinlang.org/docs/data-classes.html
 */

private data class Student(val navn: String, val kull: Int, val aktiv: Boolean = true)

fun main() {
    val ada = Student("Ada", 2026)
    println("Student: $ada")

    val kopi = ada.copy(aktiv = false)
    println("Kopi: $kopi")

    val (navn, kull, aktiv) = ada
    println("Destrukturert: navn=$navn, kull=$kull, aktiv=$aktiv")

    println("Likhet: ${ada == Student("Ada", 2026)}")
}
```

- [ ] **Step 4: Run test to verify it passes**

Run: `mvn -Dtest=RepositoryQualityTest test`
Expected: data class/generics-kravet passerer, mens README-kravet kan fortsatt feile.

- [ ] **Step 5: Commit**

```bash
git add src/main/kotlin/basis/DataClasses.kt src/main/kotlin/basis/Klasser.kt src/test/kotlin/org/example/repo/RepositoryQualityTest.kt
git commit -m "feat(basis): skill ut data classes som eget tema"
```

### Task 4: Forbedre README som læringsguide og oppslagskart

**Files:**
- Modify: `README.md`
- Test: `src/test/kotlin/org/example/repo/RepositoryQualityTest.kt`

- [ ] **Step 1: Write the failing test**

```kotlin
@Test
fun `readme should describe start here and learning path`() {
    val readme = Files.readString(projectRoot.resolve("README.md"))

    assertTrue(readme.contains("## Start her"))
    assertTrue(readme.contains("Anbefalt læringssti"))
    assertTrue(readme.contains("basis -> oop -> intermediate -> functional -> advanced"))
}
```

- [ ] **Step 2: Run test to verify it fails**

Run: `mvn -Dtest=RepositoryQualityTest#readme\ should\ describe\ start\ here\ and\ learning\ path test`
Expected: FAIL fordi seksjonene ikke finnes ennå.

- [ ] **Step 3: Write minimal implementation**

```markdown
## Start her

Hvis du er ny i Kotlin, start i `basis/` og jobb deg videre i anbefalt rekkefølge.

## Anbefalt læringssti

`basis -> oop -> intermediate -> functional -> advanced`

- `basis`: grunnleggende syntaks, kontrollflyt, nullsafety og enkle klasser
- `oop`: arv, interfaces, sealed classes og objekter
- `intermediate`: scope-funksjoner, extensions og generics
- `functional`: funksjonsreferanser, inline, sekvenser og samlinger
- `advanced`: delegasjon, operatorer, egenskaper og mer avansert standardbibliotek
```

- [ ] **Step 4: Run test to verify it passes**

Run: `mvn -Dtest=RepositoryQualityTest test`
Expected: PASS for repo-kvalitetstesten.

- [ ] **Step 5: Commit**

```bash
git add README.md src/test/kotlin/org/example/repo/RepositoryQualityTest.kt
git commit -m "docs(readme): legg til læringssti og startguide"
```

### Task 5: Full verifikasjon og pedagogisk egenkontroll

**Files:**
- Verify: `README.md`
- Verify: `src/main/kotlin/basis/DataClasses.kt`
- Verify: `src/main/kotlin/Intermediate/Generics.kt`
- Verify: `src/main/kotlin/Intermediate/*`
- Verify: `src/test/kotlin/org/example/repo/RepositoryQualityTest.kt`

- [ ] **Step 1: Run full test suite**

Run: `mvn test`
Expected: PASS med grønne smoke-tester og grønn repo-kvalitetstest.

- [ ] **Step 2: Run git diff review**

Run: `git diff -- src/main/kotlin src/test/kotlin README.md docs/superpowers`
Expected: diff viser reelle pedagogiske forbedringer og ingen meningsløse filler-endringer.

- [ ] **Step 3: Perform explicit self-review**

```text
Sjekk:
- Har hver ny eller endret fil et tydelig læringsmål?
- Er det lagt til få, men viktige emner?
- Er uferdige eller rare filer faktisk ryddet bort?
- Er README mer nyttig som både læringsguide og oppslagskart?
- Finnes det kode som bare øker volum uten å lære bort noe?
```

- [ ] **Step 4: Stage and commit**

```bash
git add README.md src/main/kotlin src/test/kotlin docs/superpowers
git commit -m "feat(kotlin-spec): styrk læringsløp og oppslagsverdi"
```
