package org.example.oop

/**
 * Constructors — primary og secondary constructors
 *
 * Dekker:
 *  - Primary constructor i klasse-signaturen
 *  - init-blokker for logikk som må kjøre under initialisering
 *  - Secondary constructors med delegering via this(...)
 *  - Default-verdier — ofte erstatter flere constructor-overloads
 *  - Initialiseringsrekkefølge
 *
 * Bruk når: du vil styre hvordan objekter opprettes. Primary constructor
 * er idiomatisk i Kotlin — secondary trengs sjelden.
 *
 * NB: Property-initializers og init-blokker kjøres i rekkefølgen de står
 *     i kildekoden. Secondary constructor kjører ETTER primary + alle init.
 *
 * Docs: https://kotlinlang.org/docs/classes.html#constructors
 */

// Primary constructor + init-blokker
class Person(val navn: String, var alder: Int) {
    init {
        println("Person opprettet: $navn, alder $alder")
        require(alder >= 0) { "Alder kan ikke være negativ" }
    }

    // Flere init-blokker kjøres i rekkefølge
    init {
        println("  (init-blokk 2)")
    }
}

// Secondary constructor
class Ansatt(val navn: String, val avdeling: String) {
    var tittel: String = "Associate"

    // Secondary må delegere til primary via this()
    constructor(navn: String, avdeling: String, tittel: String) : this(navn, avdeling) {
        this.tittel = tittel
    }

    override fun toString() = "$navn — $tittel @ $avdeling"
}

// Initialiseringsrekkefølge
class InitRekkefølge(val input: String) {
    val første = "Første: $input".also(::println)

    init {
        println("Init-blokk: $input")
    }

    val andre = "Andre: $input".also(::println)

    init {
        println("Init-blokk 2: $input")
    }
}

// Default-verdier — erstatter flere overloads
class Konfig(
    val host: String = "localhost",
    val port: Int = 8080,
    val sikker: Boolean = false
) {
    override fun toString() = "${if (sikker) "https" else "http"}://$host:$port"
}

fun main() {
    Person("Alice", 30)
    println()

    val a1 = Ansatt("Bob", "Teknisk")
    val a2 = Ansatt("Clara", "Teknisk", "Senioringeniør")
    println(a1)
    println(a2)
    println()

    println("--- Initialiseringsrekkefølge ---")
    InitRekkefølge("test")
    println()

    // Default-verdier: tre lovlige kall
    println(Konfig())                          // http://localhost:8080
    println(Konfig("api.example.com", 443, true))
    println(Konfig(port = 9090))
}
