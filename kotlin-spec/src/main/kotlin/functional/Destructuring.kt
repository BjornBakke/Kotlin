package org.example.functional

/**
 * Destructuring — pakk ut verdier fra data class, Pair, Triple, Map
 *
 * Dekker:
 *  - val (a, b) = dataClass
 *  - _ for å hoppe over komponenter
 *  - Pair og Triple destructuring
 *  - Destructuring i for-løkke over Map
 *  - Destructuring i lambda-parameter
 *
 * Bruk når: du vil trekke ut flere felt fra et objekt og gi dem lokale
 * navn uten å skrive objekt.felt flere ganger.
 *
 * NB: Destructuring bruker componentN()-funksjoner. Data class genererer
 *     disse automatisk; vanlige klasser må implementere dem selv.
 *
 * Docs: https://kotlinlang.org/docs/destructuring-declarations.html
 */

data class Punkt(val x: Double, val y: Double)
data class Person(val navn: String, val alder: Int, val by: String)

private fun hentBruker(): Pair<String, Int> = "Alice" to 30
private fun hentKoordinater(): Triple<Double, Double, Double> = Triple(1.0, 2.0, 3.0)

fun main() {
    println("=== Data class ===")
    val p = Punkt(3.0, 4.0)
    val (x, y) = p
    println("  x=$x, y=$y")

    println("\n=== Hopp over med _ ===")
    val person = Person("Bob", 25, "Oslo")
    val (navn, _, by) = person
    println("  $navn fra $by")

    println("\n=== Pair / Triple ===")
    val (brukerNavn, brukerAlder) = hentBruker()
    println("  $brukerNavn er $brukerAlder")

    val (a, b, c) = hentKoordinater()
    println("  Koordinater: $a, $b, $c")

    println("\n=== For-løkke over Map ===")
    val poeng = mapOf("Alice" to 95, "Bob" to 87, "Clara" to 92)
    for ((elev, p) in poeng) {
        println("  $elev: $p")
    }

    println("\n=== withIndex ===")
    val frukt = listOf("eple", "banan", "kirsebær")
    for ((indeks, f) in frukt.withIndex()) {
        println("  $indeks: $f")
    }

    println("\n=== Lambda-parameter destructuring ===")
    println("  Toppresultater:")
    poeng.filter { (_, p) -> p >= 90 }
         .forEach { (navn, p) -> println("    $navn: $p") }

    println("\n=== Destructuring i map-operasjon ===")
    val par = listOf("a" to 1, "b" to 2, "c" to 3)
    val resultat = par.map { (bokstav, tall) -> "$bokstav=$tall" }
    println("  $resultat")
}
