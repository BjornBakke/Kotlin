package org.example.intermediate

/**
 * apply — initialiser et objekt og returner objektet selv
 *
 * Dekker:
 *  - apply bruker 'this' som mottaker — properties settes uten prefiks
 *  - apply returnerer objektet det ble kalt på
 *  - Typisk brukt i "builder-lignende" oppsett
 *
 * Bruk når: du har et objekt du vil konfigurere i én blokk, og så fortsette
 * å bruke det samme objektet etterpå.
 *
 * NB: Sammenlign med 'also' som også returnerer objektet, men bruker 'it'.
 *     Velg apply når du setter properties direkte, also når du vil logge.
 *
 * Docs: https://kotlinlang.org/docs/scope-functions.html#apply
 */

private class HttpKlient {
    var token: String? = null
    fun connect() = println("  tilkoblet!")
    fun authenticate() = println("  autentisert!")
    fun hentData(): String = "mockdata"
}

private data class Bruker(var navn: String = "", var alder: Int = 0)

fun main() {
    println("=== Konfigurer objekt med apply ===")
    val klient = HttpKlient().apply {
        token = "abc123"
        connect()
        authenticate()
    }
    println("  Klient klar, data: ${klient.hentData()}")

    println("\n=== Builder-lignende bruk ===")
    val bruker = Bruker().apply {
        navn = "Ada"
        alder = 42
    }
    println("  $bruker")

    println("\n=== apply kan kjedes ===")
    val liste = mutableListOf<Int>().apply {
        add(1)
        add(2)
        add(3)
    }
    println("  $liste")
}
