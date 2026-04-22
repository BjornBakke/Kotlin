package org.example.basis

/**
 * Main — innstegspunktet for basis-pakken
 *
 * Dekker:
 *  - Hvordan en Kotlin-app starter: fun main() som top-level funksjon
 *  - val (read-only) vs var (muterbar)
 *  - Enkel string template: "Hei, $navn"
 *  - En enkel for-løkke over et range
 *
 * Bruk når: du vil se det minste mulige kjørbare Kotlin-programmet.
 * Alle andre filer i dette prosjektet har også sin egen main(), slik at
 * hver fil kan kjøres selvstendig.
 *
 * Docs: https://kotlinlang.org/docs/basic-syntax.html
 */

fun main() {
    val navn: String = "Kotlin"      // val = uforanderlig, må settes én gang
    var teller: Int = 0              // var = kan endres senere
    println("Hei, $navn!")           // string template med $variabel

    for (i in 1..5) {
        teller += i                  // akkumulerer 1+2+3+4+5 = 15
        println("  i = $i (teller = $teller)")
    }

    println("Summen fra 1 til 5 er $teller")
}
