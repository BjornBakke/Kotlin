package org.example.advanced

/**
 * Varargs — variabelt antall argumenter og spread-operatoren
 *
 * Dekker:
 *  - `vararg` på en parameter — funksjonen kan kalles med 0..N argumenter
 *  - vararg oppfører seg som en Array<T> inne i funksjonen
 *  - `*`-operatoren (spread) — spre en eksisterende array inn som vararg-argumenter
 *  - Kombinere vararg med andre parametere og med trailing lambda
 *  - For Int/Long/Double osv. brukes primitiv-arrays (IntArray, LongArray, ...)
 *
 * Bruk når: du vil ha et ergonomisk kall-sted a la printf/println i stedet for
 *  å tvinge brukeren til å lage en liste eller array.
 *
 * NB: Hvis du har både vararg og andre parametere, må ikke-vararg-parametere
 *  oppgis som navngitte argumenter _hvis_ de kommer etter vararg.
 *  I praksis settes vararg som siste parameter (før en evt. trailing lambda).
 *
 * Docs: https://kotlinlang.org/docs/functions.html#variable-number-of-arguments-varargs
 */

// Enkel vararg: funksjonen kan ta 0..N strenger
private fun skrivAlle(vararg elementer: String) {
    for (e in elementer) println("  $e")
}

// vararg kan kombineres med andre parametere
private fun logg(nivå: String, vararg meldinger: String) {
    meldinger.forEach { println("[$nivå] $it") }
}

// vararg + trailing lambda
private fun <T> byggListe(vararg elementer: T, transform: (T) -> String = { it.toString() }): List<String> =
    elementer.map(transform)

// Inne i funksjonen er `tall` en IntArray
private fun summer(vararg tall: Int): Int = tall.sum()

fun main() {
    println("=== Enkel vararg ===")
    skrivAlle("Hei", "Verden", "Kotlin")

    println("\n=== vararg med annen parameter ===")
    logg("INFO", "Starter app", "Laster konfigurasjon", "Klar")
    logg("FEIL", "Tilkobling feilet")

    println("\n=== Spread-operatoren (*) ===")
    val ord = arrayOf("en", "to", "tre")
    skrivAlle(*ord)  // sprer arrayet til individuelle argumenter

    // Tip: du kan kombinere spread med vanlige argumenter
    skrivAlle("null", *ord, "fire")

    println("\n=== vararg som array i funksjonen ===")
    println("  Sum av 1..5: ${summer(1, 2, 3, 4, 5)}")

    // For primitive typer trengs IntArray (ikke Array<Int>)
    val tall = intArrayOf(10, 20, 30)
    println("  Sum fra IntArray: ${summer(*tall)}")

    println("\n=== vararg + trailing lambda ===")
    val transformert = byggListe(1, 2, 3) { "Element #$it" }
    println("  $transformert")

    val enkelt = byggListe("a", "b", "c")
    println("  $enkelt")
}
