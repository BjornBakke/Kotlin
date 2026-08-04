package org.example.functional

/**
 * Closures — lambdaer som fanger variabler fra sitt scope
 *
 * Dekker:
 *  - Lambda fanger variabler fra omgivende scope
 *  - I Kotlin kan closures også MUTERE fangede variabler
 *  - Counter-factory-mønsteret
 *  - Closures som event handlers
 *  - Akkumulator-mønster
 *
 * Bruk når: du vil ha en liten funksjon som husker kontekst (tilstand,
 * konfigurasjon) uten å bygge en hel klasse.
 *
 * NB: I Java må fangede variabler være effectively final. Kotlin tillater
 *     mutasjon — men vær forsiktig i concurrent kode (deling av tilstand).
 *
 * Docs: https://kotlinlang.org/docs/lambdas.html#closures
 */

fun main() {
    println("=== Closure fanger og muterer ===")
    var teller = 0
    val inkrementer = { teller++ }
    val hent = { teller }

    inkrementer(); inkrementer(); inkrementer()
    println("  Teller: ${hent()}")  // 3

    println("\n=== Counter-factory ===")
    fun lagTeller(start: Int = 0): Pair<() -> Int, () -> Unit> {
        var antall = start
        return ({ antall }) to ({ antall++; Unit })
    }

    val (hentA, incA) = lagTeller()
    val (hentB, incB) = lagTeller(100)
    incA(); incA(); incA()
    incB()
    println("  A=${hentA()}, B=${hentB()}")  // A=3, B=101

    println("\n=== Closure i collection-operasjon ===")
    var total = 0
    listOf(10, 20, 30).forEach { total += it }
    println("  Total: $total")

    println("\n=== Closure fanger 'threshold' ===")
    val terskel = 15
    val over = listOf(5, 10, 15, 20, 25).filter { it > terskel }
    println("  Over $terskel: $over")

    println("\n=== Closure som event handler ===")
    fun påKlikk(handler: (String) -> Unit) = handler("knapp_klikket")

    var klikkTeller = 0
    påKlikk { hendelse ->
        klikkTeller++
        println("  Hendelse: $hendelse (klikk #$klikkTeller)")
    }
    påKlikk { hendelse ->
        klikkTeller++
        println("  Hendelse: $hendelse (klikk #$klikkTeller)")
    }

    println("\n=== Akkumulator ===")
    fun akkumulator(start: Int): (Int) -> Int {
        var sum = start
        return { verdi -> sum += verdi; sum }
    }

    val akk = akkumulator(0)
    println("  ${akk(5)}")   // 5
    println("  ${akk(10)}")  // 15
    println("  ${akk(3)}")   // 18
}
