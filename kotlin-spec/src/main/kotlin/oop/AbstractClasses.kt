package org.example.oop

/**
 * AbstractClasses — abstrakte klasser
 *
 * Dekker:
 *  - abstract class kan ikke instansieres direkte
 *  - Blanding av abstrakte og konkrete medlemmer
 *  - Abstrakt property som må overrides
 *  - Forskjell fra interface: kan ha constructor og tilstand
 *
 * Bruk når: du har felles tilstand OG atferd for en gruppe typer, og
 * minst én ting må implementeres av subklasser.
 *
 * NB: Abstrakte klasser støtter kun én-til-én arv. Trenger du multippel,
 *     bruk interface. Trenger du både tilstand og multippel, må du
 *     komponere med delegation eller interface-properties.
 *
 * Docs: https://kotlinlang.org/docs/classes.html#abstract-classes
 */

abstract class Dyr(val navn: String) {
    abstract fun lyd(): String                      // må implementeres
    fun beskriv() = "$navn sier ${lyd()}"           // konkret — arves som den er
}

class Hund(navn: String) : Dyr(navn) {
    override fun lyd() = "Voff!"
}

class Katt(navn: String) : Dyr(navn) {
    override fun lyd() = "Mjau!"
}

// Abstrakt klasse med abstrakt property
abstract class Varsel {
    abstract val kanal: String
    abstract val melding: String
    fun send() = println("[$kanal] $melding")
}

class EpostVarsel(override val melding: String) : Varsel() {
    override val kanal = "E-POST"
}

class SmsVarsel(override val melding: String) : Varsel() {
    override val kanal = "SMS"
}

fun main() {
    val dyr = listOf(Hund("Rex"), Katt("Pusen"))
    dyr.forEach { println(it.beskriv()) }
    // Rex sier Voff!
    // Pusen sier Mjau!

    println()

    val varsler = listOf(
        EpostVarsel("Velkommen!"),
        SmsVarsel("Koden din er 1234")
    )
    varsler.forEach { it.send() }
}
