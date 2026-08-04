package org.example.oop

/**
 * Inheritance — arv i Kotlin
 *
 * Dekker:
 *  - Klasser er 'final' by default — må merkes 'open' for å kunne arves
 *  - Metoder må også være 'open' før de kan overstyres
 *  - 'override' er påkrevd (kontra Java der det er valgfritt)
 *  - Abstrakte klasser (kan ikke instansieres, kan ha abstrakte medlemmer)
 *  - Polymorfisme over felles basisklasse
 *
 * Bruk når: du har et "er-et"-forhold mellom typer (Circle er-en Shape) og
 * delt atferd. For "kan-gjøre"-forhold, bruk heller interfaces.
 *
 * NB: I Kotlin er alt lukket for arv som default for å unngå "fragile base
 *     class"-problemet. Du må bevisst åpne med 'open'.
 *
 * Docs: https://kotlinlang.org/docs/inheritance.html
 */

// open = kan arves fra
open class Shape(val navn: String) {
    open fun areal(): Double = 0.0
    override fun toString() = "$navn: areal=${areal()}"
}

class Sirkel(val radius: Double) : Shape("Sirkel") {
    override fun areal() = Math.PI * radius * radius
}

class Rektangel(val bredde: Double, val høyde: Double) : Shape("Rektangel") {
    override fun areal() = bredde * høyde
}

class Trekant(val grunnlinje: Double, val høyde: Double) : Shape("Trekant") {
    override fun areal() = 0.5 * grunnlinje * høyde
}

// Abstrakt klasse — kan ikke instansieres direkte, kan ha abstrakte medlemmer
abstract class Kjøretøy(val merke: String) {
    abstract fun drivstoff(): String
    fun beskrivelse() = "$merke går på ${drivstoff()}"
}

class Elbil(merke: String) : Kjøretøy(merke) {
    override fun drivstoff() = "strøm"
}

class Dieseltruck(merke: String) : Kjøretøy(merke) {
    override fun drivstoff() = "diesel"
}

fun main() {
    // Polymorfisme — flere typer i samme liste
    val figurer: List<Shape> = listOf(
        Sirkel(5.0),
        Rektangel(4.0, 6.0),
        Trekant(3.0, 8.0)
    )
    figurer.forEach { println(it) }
    println("Totalt areal: ${figurer.sumOf { it.areal() }}")

    println()

    val kjøretøyer = listOf(Elbil("Tesla"), Dieseltruck("Volvo"))
    kjøretøyer.forEach { println(it.beskrivelse()) }
}
