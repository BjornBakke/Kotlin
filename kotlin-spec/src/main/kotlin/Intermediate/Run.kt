package org.example.intermediate

/**
 * run — kombiner initialisering og beregning, returner resultatet
 *
 * Dekker:
 *  - run som extension: objekt.run { ... } bruker 'this'
 *  - run som vanlig funksjon: run { ... } kjører bare en blokk
 *  - run returnerer siste uttrykk i blokken
 *
 * Bruk når: du vil konfigurere noe OG beregne en returverdi basert på det
 * i samme blokk. Også nyttig for å avgrense scope til lokale variabler.
 *
 * NB: Forskjellen fra apply er returverdien — apply gir deg objektet,
 *     run gir deg lambda-resultatet.
 *
 * Docs: https://kotlinlang.org/docs/scope-functions.html#run
 */

private class DbTjener {
    var token: String? = null
    fun connect() = println("  tilkoblet!")
    fun authenticate() = println("  autentisert!")
    fun hentData(): String = "mockdata"
}

fun main() {
    println("=== run som extension: oppsett + returverdi ===")
    val klient = DbTjener().apply { token = "abc" }
    val data: String = klient.run {
        connect()
        authenticate()
        hentData()  // siste uttrykk returneres
    }
    println("  Mottatt: $data")

    println("\n=== run som vanlig funksjon: scoper lokale variabler ===")
    val resultat = run {
        val x = 10
        val y = 20
        x + y  // verdien av blokken
    }
    println("  Sum: $resultat")
    // x og y er ikke tilgjengelig her — scope er avgrenset

    println("\n=== run på nullable med safe-call ===")
    val tekst: String? = "Hei verden"
    val lengde = tekst?.run {
        println("  Behandler: $this")
        length * 2  // returneres
    } ?: 0
    println("  Dobbel lengde: $lengde")
}
