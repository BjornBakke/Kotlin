package org.example.intermediate

/**
 * with — utfør flere operasjoner på ett objekt
 *
 * Dekker:
 *  - with er en vanlig funksjon (ikke extension): with(obj) { ... }
 *  - Objektet refereres som 'this' i blokken
 *  - Returnerer lambda-resultatet (siste uttrykk)
 *
 * Bruk når: du skal kalle flere metoder på samme objekt uten å gjenta
 * variabelnavnet. Spesielt pent når du ikke eier objektet (API-kall).
 *
 * NB: with tar objektet som første argument (parentes), mens run er
 *     extension (prikk). Teknisk er "obj.run { }" og "with(obj) { }"
 *     nesten identiske, men with leses mer naturlig når objektet er
 *     et midlertidig uttrykk.
 *
 * Docs: https://kotlinlang.org/docs/scope-functions.html#with
 */

private class Canvas {
    fun rect(x: Int, y: Int, w: Int, h: Int) = println("  rect($x, $y, $w, $h)")
    fun circ(x: Int, y: Int, rad: Int) = println("  circ($x, $y, $rad)")
    fun text(x: Int, y: Int, str: String) = println("  text($x, $y, \"$str\")")
}

fun main() {
    println("=== Flere kall på samme objekt ===")
    val canvas = Canvas()
    with(canvas) {
        text(10, 10, "Hei")
        rect(20, 30, 100, 50)
        circ(40, 60, 25)
    }

    println("\n=== with returnerer lambda-resultat ===")
    val person = Triple("Bjørn", 40, "Bergen")
    val oppsummering = with(person) {
        "$first er $second år og bor i $third"
    }
    println("  $oppsummering")

    println("\n=== Bygg en streng med StringBuilder ===")
    val tall = listOf(1, 2, 3, 4, 5)
    val formattert = with(StringBuilder()) {
        append("Tall: ")
        tall.forEachIndexed { i, n ->
            if (i > 0) append(", ")
            append(n)
        }
        toString()
    }
    println("  $formattert")
}
