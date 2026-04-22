package org.example.oop

/**
 * EnumClasses — oppregning av en lukket mengde verdier
 *
 * Dekker:
 *  - Enkel enum med konstanter
 *  - Enum med properties og metoder
 *  - Enum som implementerer interface
 *  - entries, name, ordinal, valueOf
 *  - when-uttrykk over enum (exhaustive)
 *
 * Bruk når: du har en fast mengde typer (retninger, statuskoder, dager).
 *
 * NB: entries (ny i Kotlin 1.9+) erstatter den eldre values(). entries
 *     returnerer en List (ikke Array) og er litt mer effektiv.
 *
 * Docs: https://kotlinlang.org/docs/enum-classes.html
 */

enum class Retning {
    NORD, SØR, ØST, VEST
}

// Enum med properties og metoder
enum class Planet(val masse: Double, val radius: Double) {
    MERKUR(3.303e+23,  2.4397e6),
    VENUS (4.869e+24,  6.0518e6),
    JORDEN(5.976e+24,  6.37814e6),
    MARS  (6.421e+23,  3.3972e6);  // semikolon før metoder

    private val G = 6.67300E-11
    fun overflateGravitasjon() = G * masse / (radius * radius)
    fun overflateVekt(masse: Double) = masse * overflateGravitasjon()
}

// Enum som implementerer interface (hver konstant overstyrer)
interface Beskrivelse {
    fun beskriv(): String
}

enum class Årstid : Beskrivelse {
    VÅR    { override fun beskriv() = "Blomster blomstrer" },
    SOMMER { override fun beskriv() = "Sola skinner" },
    HØST   { override fun beskriv() = "Løv faller" },
    VINTER { override fun beskriv() = "Snø faller" }
}

fun main() {
    println("Retninger: ${Retning.entries.joinToString()}")

    val d = Retning.valueOf("NORD")
    println("Tolket: $d (indeks ${d.ordinal})")

    val råd = when (d) {
        Retning.NORD -> "Gå opp"
        Retning.SØR  -> "Gå ned"
        Retning.ØST  -> "Gå høyre"
        Retning.VEST -> "Gå venstre"
    }
    println("Råd: $råd")

    val jordvekt = 75.0
    println("\nVekt på andre planeter (du veier $jordvekt kg på jorda):")
    Planet.entries.forEach { p ->
        println("  ${p.name}: %.2f kg".format(p.overflateVekt(jordvekt)))
    }

    println("\nÅrstider:")
    Årstid.entries.forEach { println("  ${it.name}: ${it.beskriv()}") }
}
