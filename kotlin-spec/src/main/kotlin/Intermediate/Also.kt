package org.example.intermediate

/**
 * also — utfør en bieffekt og returner objektet
 *
 * Dekker:
 *  - also bruker 'it' som mottaker (eller navngitt parameter)
 *  - also returnerer objektet uendret
 *  - Typisk: logging, debug, validering underveis i en kjede
 *
 * Bruk når: du vil "tape inn" et sidesteg (log/sjekk) i en pipeline
 * uten å endre verdien som flyter gjennom.
 *
 * NB: apply vs also — begge returnerer objektet, men apply bruker 'this'
 *     (kortere for property-setting), mens also bruker 'it' (tydeligere
 *     når du kaller funksjoner eller har navnekollisjoner).
 *
 * Docs: https://kotlinlang.org/docs/scope-functions.html#also
 */

fun main() {
    println("=== Logging underveis i pipeline ===")
    val medaljer = listOf("Gold", "Silver", "Bronze")
    val resultat = medaljer
        .map { it.uppercase() }
        .also { println("  Etter map: $it") }
        .filter { it.length > 4 }
        .also { println("  Etter filter: $it") }
        .reversed()
        .also { println("  Etter reversed: $it") }
    println("  Sluttresultat: $resultat")

    println("\n=== Debug en verdi uten å endre flyten ===")
    val tall = (1..5)
        .map { it * it }
        .also { println("  Debug kvadrater: $it") }
        .sum()
    println("  Sum: $tall")

    println("\n=== Validering i kjede ===")
    val liste = mutableListOf(1, 2, 3)
        .also { require(it.isNotEmpty()) { "Listen må ha elementer" } }
        .also { it.add(4) }
    println("  $liste")
}
