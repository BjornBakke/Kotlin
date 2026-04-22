package org.example.advanced

/**
 * LateinitVar — sen initialisering av non-null property
 *
 * Dekker:
 *  - lateinit var — ikke initialisert med en gang, ikke-null type
 *  - ::property.isInitialized — sjekk om satt
 *  - UninitializedPropertyAccessException hvis lest før sett
 *
 * Bruk når: et felt må initialiseres via dependency injection eller test-
 * setup (f.eks. @Autowired, @BeforeEach), men du vet det er satt før bruk.
 *
 * NB: lateinit virker IKKE for:
 *   - val (bare var)
 *   - nullable typer (bruk null-initialisering)
 *   - primitives (Int, Boolean, Double) — bruk Delegates.notNull()
 *
 * Docs: https://kotlinlang.org/docs/properties.html#late-initialized-properties-and-variables
 */

class Tjeneste {
    lateinit var repo: String

    fun konfigurer(verdi: String) {
        repo = verdi
    }

    fun gjørJobb(): String {
        // Idiomatisk sjekk før bruk
        if (!::repo.isInitialized) {
            return "Ikke initialisert!"
        }
        return "Jobber med $repo"
    }
}

class TestRammeverk {
    lateinit var testNavn: String
    lateinit var testData: List<String>

    fun førHver(navn: String) {
        testNavn = navn
        testData = listOf("a", "b", "c")
    }

    fun kjørTest() {
        println("  Kjører '$testNavn' med data: $testData")
    }
}

fun main() {
    val tjeneste = Tjeneste()

    println("=== Før init ===")
    println("  ${tjeneste.gjørJobb()}")

    println("\n=== Etter init ===")
    tjeneste.konfigurer("PostgreSQL")
    println("  ${tjeneste.gjørJobb()}")

    println("\n=== Tilgang uten init kaster exception ===")
    val dårlig = Tjeneste()
    try {
        println(dårlig.repo)
    } catch (e: UninitializedPropertyAccessException) {
        println("  Feil: ${e.message}")
    }

    println("\n=== Typisk test-setup-bruk ===")
    val test = TestRammeverk()
    test.førHver("min test")
    test.kjørTest()
}
