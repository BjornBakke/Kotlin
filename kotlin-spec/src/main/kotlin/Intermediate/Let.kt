package org.example.intermediate

/**
 * let — kjør en blokk med ikke-null verdi, returner resultatet
 *
 * Dekker:
 *  - let bruker 'it' som mottaker (eller navngitt parameter)
 *  - let returnerer lambda-resultatet
 *  - Kombinasjonen ?.let { ... } er idiomatisk for null-behandling
 *
 * Bruk når:
 *  1) Du vil utføre noe bare hvis verdien ikke er null.
 *  2) Du vil transformere en verdi i en kjede uten mellomvariabel.
 *  3) Du vil begrense scope til en lokal verdi.
 *
 * NB: `?.let { it.x }` er ikke helt det samme som `?.x` — forskjellen er
 *     at let returnerer lambda-resultatet, og kan kjøre flere linjer.
 *
 * Docs: https://kotlinlang.org/docs/scope-functions.html#let
 */

private fun sendVarsel(mottaker: String): String {
    println("  sender varsel til $mottaker")
    return "sendt"
}

private fun hentNesteMottaker(): String? = "sebastian@jetbrains.com"

fun main() {
    println("=== Null-safe handling ===")
    val mottaker: String? = hentNesteMottaker()
    // Kjører bare hvis mottaker ikke er null
    val status: String? = mottaker?.let { sendVarsel(it) }
    println("  Status: $status")

    println("\n=== Transformasjon uten mellomvariabel ===")
    val navn = "  Alice  "
    val langt = navn
        .trim()
        .takeIf { it.length > 3 }
        ?.let { "lang: $it" }
        ?: "for kort"
    println("  $langt")

    println("\n=== Begrens scope ===")
    val resultat = listOf(1, 2, 3).let { liste ->
        val sum = liste.sum()
        val snitt = sum.toDouble() / liste.size
        "sum=$sum, snitt=$snitt"
    }
    println("  $resultat")

    println("\n=== let med navngitt parameter for klarhet ===")
    val personer: Map<Int, String>? = mapOf(1 to "Ada", 2 to "Bob")
    personer?.let { pers ->
        pers.forEach { (id, n) -> println("  $id -> $n") }
    }
}
