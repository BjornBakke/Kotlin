package org.example.advanced

/**
 * when — Kotlin sitt kraftigste "pattern matching"-verktøy
 *
 * Dekker:
 *  - when med sealed class — kompilator sjekker at _alle_ subklasser er håndtert
 *  - when med enum — samme exhaustive-fordel, flere verdier pr. gren
 *  - when med type-sjekk (is T) og smart cast
 *  - when med range (in 1..10) og samling (in setOf(...))
 *  - when _uten_ argument — som en kortere if/else-if-kjede
 *  - when som uttrykk (returnerer verdi) vs. som statement
 *
 * Bruk når: du har mer enn ett if/else-nivå, eller når du vil at
 *  kompilatoren skal tvinge deg til å håndtere alle tilfeller.
 *
 * Tip: Når `when` brukes som _uttrykk_ (verdi brukes), må alle tilfeller
 *  dekkes. Med sealed class/enum blir `else`-grenen overflødig, noe som
 *  gjør det umulig å glemme et tilfelle når du legger til en variant.
 *
 * Docs: https://kotlinlang.org/docs/control-flow.html#when-expression
 */

// Sealed class-hierarki representerer et aritmetisk uttrykk
private sealed class Uttrykk {
    data class Tall(val verdi: Double) : Uttrykk()
    data class Sum(val venstre: Uttrykk, val høyre: Uttrykk) : Uttrykk()
    data class Produkt(val venstre: Uttrykk, val høyre: Uttrykk) : Uttrykk()
    data object Udefinert : Uttrykk()
}

// Exhaustive when — ingen `else` nødvendig fordi alle varianter er dekket
private fun evaluer(u: Uttrykk): Double = when (u) {
    is Uttrykk.Tall -> u.verdi
    is Uttrykk.Sum -> evaluer(u.venstre) + evaluer(u.høyre)
    is Uttrykk.Produkt -> evaluer(u.venstre) * evaluer(u.høyre)
    Uttrykk.Udefinert -> Double.NaN
}

private enum class HttpStatus(val kode: Int) {
    OK(200), OPPRETTET(201), UGYLDIG_FORESPØRSEL(400), IKKE_FUNNET(404), SERVERFEIL(500)
}

private fun beskrivStatus(status: HttpStatus): String = when (status) {
    HttpStatus.OK, HttpStatus.OPPRETTET -> "Suksess"
    HttpStatus.UGYLDIG_FORESPØRSEL -> "Klientfeil"
    HttpStatus.IKKE_FUNNET -> "Ikke funnet"
    HttpStatus.SERVERFEIL -> "Serverfeil"
}

fun main() {
    println("=== when + sealed class (exhaustive) ===")
    val uttrykk = Uttrykk.Sum(Uttrykk.Tall(3.0), Uttrykk.Produkt(Uttrykk.Tall(2.0), Uttrykk.Tall(4.0)))
    println("  3 + (2 * 4) = ${evaluer(uttrykk)}")

    println("\n=== when + enum (flere verdier pr. gren) ===")
    println("  OK         -> ${beskrivStatus(HttpStatus.OK)}")
    println("  IKKE_FUNNET -> ${beskrivStatus(HttpStatus.IKKE_FUNNET)}")

    println("\n=== when med type-sjekk (is) og smart cast ===")
    fun klassifiser(obj: Any): String = when (obj) {
        is String -> "Streng: '${obj.uppercase()}'"      // smart cast til String
        is Int -> "Int: ${obj * 2}"                      // smart cast til Int
        is List<*> -> "Liste med størrelse ${obj.size}"  // smart cast til List
        else -> "Ukjent"
    }
    println("  ${klassifiser("hei")}")
    println("  ${klassifiser(21)}")
    println("  ${klassifiser(listOf(1, 2, 3))}")

    println("\n=== when med range (in 1..10) ===")
    fun karakterFor(poeng: Int): String = when (poeng) {
        in 90..100 -> "A"
        in 80..89 -> "B"
        in 70..79 -> "C"
        in 60..69 -> "D"
        else -> "F"
    }
    println("  85 poeng -> ${karakterFor(85)}")
    println("  42 poeng -> ${karakterFor(42)}")

    println("\n=== when uten argument (erstatter if/else-if-kjeder) ===")
    val temp = 25
    val vær = when {
        temp < 0 -> "Iskaldt"
        temp < 15 -> "Kaldt"
        temp < 25 -> "Behagelig"
        temp < 35 -> "Varmt"
        else -> "Ekstremt"
    }
    println("  ${temp}°C -> $vær")

    println("\n=== when med flere verdier og ranges blandet ===")
    val tegn = 'a'
    val type = when (tegn) {
        'a', 'e', 'i', 'o', 'u', 'y' -> "vokal"
        in 'a'..'z' -> "liten konsonant"
        in 'A'..'Z' -> "stor bokstav"
        else -> "annet"
    }
    println("  '$tegn' er en $type")
}
