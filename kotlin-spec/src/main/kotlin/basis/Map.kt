package org.example.basis

/**
 * Map — nøkkel/verdi-oppslag
 *
 * Dekker:
 *  - mapOf(...) og mutableMapOf(...)
 *  - "nøkkel" to verdi — infix funksjon som lager Pair
 *  - Oppslag med [nøkkel] (returnerer nullable verdi)
 *  - containsKey, keys, values
 *  - in på Map sjekker nøkler som default
 *
 * Bruk når: du vil slå opp en verdi basert på en nøkkel (f.eks. prisliste,
 * konfigurasjon, konverterings-tabell).
 *
 * NB: map["finnesIkke"] returnerer null — bruk Map.getValue() hvis du
 *     heller vil ha NoSuchElementException, eller .getOrDefault().
 *
 * Docs: https://kotlinlang.org/docs/map-operations.html
 */

fun main() {
    // Read-only map
    val jusMeny = mapOf("eple" to 100, "kiwi" to 190, "appelsin" to 100)
    println("Meny: $jusMeny")

    // Mutable map med eksplisitt type
    val meny: MutableMap<String, Int> = mutableMapOf(
        "eple" to 100,
        "kiwi" to 190,
        "appelsin" to 100
    )
    println("Mutable meny: $meny")

    // Oppslag
    println("Pris på eple: ${jusMeny["eple"]}")          // 100
    println("Pris på ananas: ${jusMeny["ananas"]}")       // null — finnes ikke

    // Legg til og fjern
    meny["kokosnøtt"] = 150
    println("Etter add: $meny")

    meny.remove("appelsin")
    println("Etter remove: $meny")

    // Inspeksjon
    println("Antall par: ${jusMeny.count()}")
    println("Har 'kiwi'? ${jusMeny.containsKey("kiwi")}")
    println("Nøkler: ${jusMeny.keys}")
    println("Verdier: ${jusMeny.values}")

    // 'in' på map sjekker nøkler
    println("'appelsin' in jusMeny? ${"appelsin" in jusMeny}")  // true
    println("200 in jusMeny.values? ${200 in jusMeny.values}")   // false

    // Backtick-navngitt variabel (med mellomrom) — kan gjøres, men uvanlig
    val `tall til ord` = mapOf(1 to "en", 2 to "to", 3 to "tre")
    val n = 2
    println("$n staves '${`tall til ord`[n]}'")
}
