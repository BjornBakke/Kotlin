package org.example.advanced

/**
 * OperatorOverloading — overstyr operatorer for egne typer
 *
 * Dekker:
 *  - plus, minus, times, unaryMinus
 *  - compareTo (gjør <, >, <=, >= tilgjengelig)
 *  - get, set (indexing: obj[i, j])
 *  - contains (gjør 'in' tilgjengelig)
 *
 * Bruk når: operasjonene på typen er matematiske eller samling-lignende
 * og leses klarere med operator enn med metodenavn.
 *
 * NB: Ikke misbruk. "a + b" bør faktisk føles som addisjon — ellers er
 *     en vanlig metode tydeligere.
 *
 * Docs: https://kotlinlang.org/docs/operator-overloading.html
 */

data class Vektor(val x: Double, val y: Double) {
    operator fun plus(annen: Vektor) = Vektor(x + annen.x, y + annen.y)
    operator fun minus(annen: Vektor) = Vektor(x - annen.x, y - annen.y)
    operator fun times(skalar: Double) = Vektor(x * skalar, y * skalar)
    operator fun unaryMinus() = Vektor(-x, -y)

    // compareTo gir deg <, >, <=, >= for fri
    operator fun compareTo(annen: Vektor): Int =
        magnitude.compareTo(annen.magnitude)

    val magnitude: Double
        get() = kotlin.math.sqrt(x * x + y * y)
}

// get/set — indexering
class Matrise(private val rader: Int, private val kolonner: Int) {
    private val data = Array(rader) { DoubleArray(kolonner) }

    operator fun get(rad: Int, kol: Int) = data[rad][kol]
    operator fun set(rad: Int, kol: Int, verdi: Double) { data[rad][kol] = verdi }

    override fun toString(): String = data.joinToString("\n") { rad ->
        rad.joinToString(", ") { "%.1f".format(it) }
    }
}

// contains — 'in' operator
data class Rektangel(val minX: Double, val minY: Double, val maxX: Double, val maxY: Double) {
    operator fun contains(p: Vektor) = p.x in minX..maxX && p.y in minY..maxY
}

fun main() {
    val a = Vektor(1.0, 2.0)
    val b = Vektor(3.0, 4.0)

    println("=== Matematiske operatorer ===")
    println("  a + b = ${a + b}")
    println("  a - b = ${a - b}")
    println("  a * 3 = ${a * 3.0}")
    println("  -a   = ${-a}")

    println("\n=== compareTo ===")
    println("  a < b: ${a < b}")
    println("  |a| = %.2f, |b| = %.2f".format(a.magnitude, b.magnitude))

    println("\n=== Indexering ===")
    val m = Matrise(2, 2)
    m[0, 0] = 1.0; m[0, 1] = 2.0
    m[1, 0] = 3.0; m[1, 1] = 4.0
    println(m)

    println("\n=== contains med 'in' ===")
    val boks = Rektangel(0.0, 0.0, 10.0, 10.0)
    val inni = Vektor(5.0, 5.0)
    val utenfor = Vektor(15.0, 5.0)
    println("  $inni in boks:    ${inni in boks}")
    println("  $utenfor in boks: ${utenfor in boks}")
}
