package org.example.intermediate

/**
 * Extension functions — legg til metoder på eksisterende typer
 *
 * Dekker:
 *  - Extension-syntax: fun Type.navn(...) { }
 *  - Extension properties: val Type.navn: ... get() = ...
 *  - Extensions er statiske — løses opp ved compile-time
 *  - Nullable receiver: fun Type?.navn()
 *
 * Bruk når: du vil utvide en type (egen eller fra bibliotek) uten å arve.
 * Gjor kode lesbar som "tekst.formater()" i stedet for "format(tekst)".
 *
 * NB: Extensions overstyrer ikke virkelige member-funksjoner — hvis
 *     typen allerede har en metode med samme navn, vinner member-funksjonen.
 *     Ekstensjoner er også statisk bundet, så polymorfisme gjelder ikke.
 *
 * Docs: https://kotlinlang.org/docs/extensions.html
 */

// Extension function på String
fun String.tilSnake(): String = this.lowercase().replace(' ', '_')

// Extension property på String
val String.sisteIndeks: Int get() = this.length - 1

// Extension function med nullable receiver
fun String?.erTom(): Boolean = this.isNullOrEmpty()

// Extension på generisk type
fun <T> List<T>.andreElement(): T? = if (size >= 2) this[1] else null

// Extension på egen klasse
private data class Bok(val tittel: String, val forfatter: String)

private fun Bok.visning(): String = "\"$tittel\" av $forfatter"

fun main() {
    println("=== Extension function ===")
    println("  " + "Hei Kotlin Verden".tilSnake())  // hei_kotlin_verden

    println("\n=== Extension property ===")
    val ord = "Kotlin"
    println("  '$ord' siste indeks = ${ord.sisteIndeks}")

    println("\n=== Nullable receiver ===")
    val tekst: String? = null
    println("  null er tom? ${tekst.erTom()}")  // true
    println("  'Hei' er tom? ${"Hei".erTom()}")  // false

    println("\n=== Generisk extension ===")
    println("  andreElement av [10, 20, 30] = ${listOf(10, 20, 30).andreElement()}")

    println("\n=== Extension på egen klasse ===")
    val bok = Bok("Kotlin in Action", "Jemerov")
    println("  ${bok.visning()}")
}
