package org.example.oop

/**
 * VisibilityModifiers — styring av synlighet
 *
 * Dekker:
 *  - public (default i Kotlin) — synlig overalt
 *  - private — synlig i filen (top-level) eller klassen (medlem)
 *  - protected — synlig i klassen og subklasser (ikke samme pakke!)
 *  - internal — synlig i hele modulen (maven-artefakt)
 *  - Private setter — property kan leses utenfra, men bare settes inni
 *
 * Bruk når: du vil innkapsle detaljer og forhindre uønsket bruk. Default
 * public er vanligvis feil for library-API.
 *
 * NB: protected i Kotlin er strengere enn i Java — ikke synlig for andre
 *     klasser i samme pakke, kun for subklasser.
 *
 * Docs: https://kotlinlang.org/docs/visibility-modifiers.html
 */

open class Konto(private val id: Int, protected var saldo: Double) {
    internal val bank = "MinBank"

    private fun generereUtskrift() = "Konto $id: saldo=$saldo"
    fun oppsummering() = generereUtskrift()

    protected fun leggTilRente(rate: Double) {
        saldo += saldo * rate
    }
}

class Sparekonto(id: Int, saldo: Double, private val rente: Double) : Konto(id, saldo) {
    fun beregnRente() {
        leggTilRente(rente)               // OK — protected
        // generereUtskrift()              // FEIL — private
        println("Saldo etter rente: $saldo")  // OK — protected
    }
}

// Top-level private — kun synlig i denne filen
private fun hemmeligHjelper() = "Jeg er fil-privat"

// Property med private setter
class Teller {
    var antall: Int = 0
        private set

    fun inkrementer() { antall++ }
    fun nullstill()   { antall = 0 }
}

fun main() {
    val konto = Konto(1, 1000.0)
    println(konto.oppsummering())   // OK — public
    println(konto.bank)             // OK — internal (samme modul)
    // konto.saldo                   // FEIL — protected
    // konto.generereUtskrift()      // FEIL — private

    val sparekonto = Sparekonto(2, 5000.0, 0.05)
    sparekonto.beregnRente()
    println(sparekonto.oppsummering())

    println(hemmeligHjelper())      // OK — samme fil

    val teller = Teller()
    teller.inkrementer()
    teller.inkrementer()
    println("Antall: ${teller.antall}")
    // teller.antall = 99  // FEIL — setter er private
}
