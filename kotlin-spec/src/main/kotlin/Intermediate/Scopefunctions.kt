package org.example.intermediate

/**
 * Scope functions — let, apply, run, also, with
 *
 * Kotlin har fem scope-funksjoner som lar deg kjøre en blokk med kode i
 * konteksten av et objekt. De skiller seg på to ting:
 *   1) Hvordan objektet refereres inni blokken (this vs it)
 *   2) Hva blokken returnerer (objektet selv vs lambda-resultatet)
 *
 *  Funksjon | Referanse | Returnerer      | Typisk bruk
 *  ---------|-----------|-----------------|----------------------------------
 *  let      | it        | lambda-resultat | null-sjekk + transformasjon
 *  apply    | this      | objektet selv   | objekt-initialisering (builder)
 *  also     | it        | objektet selv   | side-effekter (logging, debug)
 *  run      | this      | lambda-resultat | konfigurasjon + returverdi
 *  with     | this      | lambda-resultat | kall flere metoder på objekt
 *
 * Tommelfingerregel:
 *  - Trenger du objektet tilbake? apply / also
 *  - Trenger du et nytt resultat? let / run / with
 *  - Refererer du properties som "this.x"? apply / run / with
 *  - Refererer du objektet med kort navn? let / also
 *
 * Docs: https://kotlinlang.org/docs/scope-functions.html
 */

private data class Person(var navn: String, var alder: Int, var epost: String = "")

fun main() {
    println("=== let: null-sjekk + transformasjon ===")
    val kanskjeNavn: String? = "Alice"
    val lengde: Int? = kanskjeNavn?.let {
        println("  Behandler: $it")
        it.length  // siste uttrykk returneres
    }
    println("  Lengde: $lengde")

    println("\n=== apply: initialiser og returner objektet ===")
    // apply bruker 'this' — ingen prefiks trengs
    val person = Person("", 0).apply {
        navn = "Bjørn"
        alder = 30
        epost = "bjorn@example.no"
    }
    println("  $person")

    println("\n=== also: utfør bieffekt, returner objekt ===")
    // also bruker 'it' — nyttig når 'this' allerede er tatt
    val tall = mutableListOf(1, 2, 3)
        .also { println("  Før: $it") }
        .apply { add(4) }
        .also { println("  Etter add: $it") }
    println("  Resultat: $tall")

    println("\n=== run: initialiser + returner et resultat ===")
    // run er som apply, men returnerer lambda-resultatet
    val oppsummering: String = person.run {
        "$navn ($alder år) — $epost"
    }
    println("  $oppsummering")

    println("\n=== with: kall flere metoder på samme objekt ===")
    // with er en vanlig funksjon (ikke extension), tar objektet som argument
    val melding = with(StringBuilder()) {
        append("Hei, ")
        append(person.navn)
        append("!")
        toString()  // returneres
    }
    println("  $melding")

    println("\n=== Kombinasjoner er vanlig ===")
    val nyPerson = Person("Clara", 25)
        .apply { epost = "clara@example.no" }  // initialiser
        .also { println("  Opprettet: $it") }  // logg
    println("  Ferdig: $nyPerson")
}
