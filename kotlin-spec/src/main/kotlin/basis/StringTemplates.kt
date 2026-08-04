package org.example.basis

/**
 * StringTemplates — string interpolation i Kotlin
 *
 * Dekker:
 *  - Enkel variabel-innsetting: "Hei $navn"
 *  - Uttrykk: "Sum: ${a + b}"
 *  - Escape av dollar: "${'$'}"
 *  - Multi-linje strings med """..."""
 *  - trimIndent() og trimMargin() for pene blokker
 *
 * Bruk når: du bygger tekst med dynamiske verdier. Unngå + for
 * konkatenering når templates er mer lesbart.
 *
 * NB: $variabel virker bare for enkle navn. For uttrykk (f.eks. "a.b"
 *     eller "a + b") må du bruke ${...}.
 *
 * Docs: https://kotlinlang.org/docs/strings.html#string-templates
 */

fun main() {
    val navn = "Bjørn"
    val alder = 40

    println("=== Enkel interpolasjon ===")
    println("  Hei $navn, du er $alder år gammel")

    println("\n=== Uttrykk i ${'$'}{...} ===")
    val a = 10
    val b = 4
    println("  $a + $b = ${a + b}")
    println("  Navn (stor): ${navn.uppercase()}")

    println("\n=== Escape dollar-tegn ===")
    val pris = 99
    println("  Prisen er ${'$'}$pris")   // $99

    println("\n=== Multi-linje string ===")
    val melding = """
        Hei $navn,
        Du har $alder år bak deg.
        Hilsen: Kotlin
    """.trimIndent()
    println(melding)

    println("\n=== trimMargin for egen marg ===")
    val sql = """
        |SELECT *
        |FROM brukere
        |WHERE navn = '$navn'
    """.trimMargin()
    println(sql)
}
