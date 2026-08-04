package org.example.advanced

/**
 * Standard library utilities — require, check, error, TODO, Pair/Triple, takeIf, repeat
 *
 * Dekker:
 *  - require(...) — kaster IllegalArgumentException; brukes til å validere _argumenter_
 *  - check(...) — kaster IllegalStateException; brukes til å validere _tilstand_
 *  - error(...) — kortform for `throw IllegalStateException(...)`
 *  - TODO(...) — kaster NotImplementedError; marker ufullstendig kode uten å måtte stubbe returtype
 *  - Pair, Triple, `to`-infix — enkle tuple-typer
 *  - repeat(n) { ... } — kjør en blokk n ganger
 *  - takeIf / takeUnless — returner objektet kun hvis predikatet passer (ellers null)
 *
 * Bruk når:
 *  - du vil feile tidlig og tydelig med gode feilmeldinger
 *  - du vil kjede sammen transformasjoner uten if/else
 *  - du trenger lett-vekts tupler uten å lage en data class
 *
 * NB: require/check/error/TODO har kontrakter som lar smart-cast fungere etterpå.
 *     F.eks. etter `require(x != null)` kan kompilatoren behandle x som ikke-null.
 *
 * Docs:
 *  - https://kotlinlang.org/docs/scope-functions.html#takeif-and-takeunless
 *  - https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/require.html
 *  - https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/check.html
 */

fun main() {
    println("=== require: valider argumenter ===")
    fun opprettBruker(navn: String, alder: Int): String {
        require(navn.isNotBlank()) { "Navn kan ikke være tomt" }
        require(alder in 0..150) { "Alder må være 0..150, fikk $alder" }
        return "$navn ($alder)"
    }
    println("  ${opprettBruker("Alice", 30)}")
    try {
        opprettBruker("", 25)
    } catch (e: IllegalArgumentException) {
        println("  require feilet: ${e.message}")
    }

    println("\n=== check: valider tilstand ===")
    class Tilkobling {
        private var tilkoblet = false
        fun koble() { tilkoblet = true }
        fun spør(sql: String): String {
            check(tilkoblet) { "Må være tilkoblet før spørring" }
            return "Resultat av: $sql"
        }
    }

    val conn = Tilkobling()
    try {
        conn.spør("SELECT 1")
    } catch (e: IllegalStateException) {
        println("  check feilet: ${e.message}")
    }
    conn.koble()
    println("  ${conn.spør("SELECT 1")}")

    println("\n=== error: kast IllegalStateException ===")
    fun hentMiljø(nøkkel: String): String =
        System.getenv(nøkkel) ?: error("Miljøvariabel $nøkkel er ikke satt")

    try {
        hentMiljø("IKKE_SATT_12345")
    } catch (e: IllegalStateException) {
        println("  error(): ${e.message}")
    }

    println("\n=== TODO: marker uferdig kode ===")
    fun fremtidigFunksjon(): String = TODO("Implementeres i v2.0")
    try {
        fremtidigFunksjon()
    } catch (e: NotImplementedError) {
        println("  ${e.message}")
    }

    println("\n=== Pair og Triple ===")
    val par = Pair("nøkkel", 42)
    val (k, v) = par
    println("  Pair: $k=$v")

    val trippel = Triple("Alice", 30, "Oslo")
    val (navn, alder, by) = trippel
    println("  Triple: $navn, $alder, $by")

    // 'to' infix-funksjon lager en Pair
    val kart = mapOf("a" to 1, "b" to 2)
    println("  Map via 'to': $kart")

    println("\n=== repeat: kjør N ganger ===")
    print("  ")
    repeat(5) { i -> print("${i + 1} ") }
    println()

    println("\n=== takeIf / takeUnless ===")
    val tall = 42
    val partall = tall.takeIf { it % 2 == 0 }       // 42 (fordi det ER partall)
    val oddetall = tall.takeUnless { it % 2 == 0 }  // null (fordi det ER partall)
    println("  takeIf partall: $partall, takeUnless partall: $oddetall")

    // Tip: takeIf + safe-call (?.) er en kraftig kombinasjon for kjeding
    val input = "  hallo  "
    val resultat = input.trim().takeIf { it.isNotEmpty() }?.uppercase()
    println("  Kjede: '$input' -> $resultat")
}
