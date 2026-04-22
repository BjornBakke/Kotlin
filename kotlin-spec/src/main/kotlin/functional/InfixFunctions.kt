package org.example.functional

/**
 * InfixFunctions — DSL-aktig syntax uten prikk og parenteser
 *
 * Dekker:
 *  - infix modifier krever: medlem/extension, nøyaktig én parameter
 *  - Standard-biblioteket bruker mye infix: to, until, downTo, step, and, or
 *  - Egne infix for assertion-stil eller lesbar DSL
 *
 * Bruk når: en operasjon er mest naturlig som "a <verb> b" — som "3 times x"
 * eller "42 shouldBe 42".
 *
 * NB: Infix kan ikke kombineres med andre operatorer uten parenteser.
 *     "1 + 1 shouldBe 3" fungerer fordi + har lavere presedens. Vær
 *     bevisst på tvetydighet.
 *
 * Docs: https://kotlinlang.org/docs/functions.html#infix-notation
 */

infix fun Int.ganger(tekst: String) = tekst.repeat(this)
infix fun String.kobletTil(annet: String) = Pair(this, annet)

data class Punkt2D(val x: Int, val y: Int) {
    infix fun distanseTil(annet: Punkt2D): Double {
        val dx = (x - annet.x).toDouble()
        val dy = (y - annet.y).toDouble()
        return kotlin.math.sqrt(dx * dx + dy * dy)
    }
}

// Assertion-stil DSL
infix fun <T> T.skalVære(forventet: T) {
    if (this != forventet) println("  FEIL: $this != $forventet")
    else println("  OK: $this == $forventet")
}

fun main() {
    println("=== Egne infix ===")
    println(3 ganger "Hei ")                 // "Hei Hei Hei "
    println(2.ganger("Ha"))                  // vanlig kall funker også

    val par = "nøkkel" kobletTil "verdi"
    println(par)

    println("\n=== Infix på data class ===")
    val p1 = Punkt2D(0, 0)
    val p2 = Punkt2D(3, 4)
    println("  Avstand: ${p1 distanseTil p2}")

    println("\n=== Assertion-DSL ===")
    42 skalVære 42
    "hei" skalVære "hei"
    1 + 1 skalVære 3

    println("\n=== Standard-bibliotekets infix ===")
    val map = mapOf("a" to 1, "b" to 2)           // 'to' er infix
    val range = 1 until 10                         // 'until' er infix
    val nedover = 10 downTo 1 step 2               // 'downTo' og 'step'
    println("  $map")
    println("  $range -> ${range.toList()}")
    println("  $nedover -> ${nedover.toList()}")
}
