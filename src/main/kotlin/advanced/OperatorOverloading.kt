package org.example.advanced

// operator fun — overload operatorer for egne typer

data class Vector(val x: Double, val y: Double) {
    // + operator
    operator fun plus(other: Vector) = Vector(x + other.x, y + other.y)

    // - operator
    operator fun minus(other: Vector) = Vector(x - other.x, y - other.y)

    // * operator (skalar)
    operator fun times(scalar: Double) = Vector(x * scalar, y * scalar)

    // Unary minus
    operator fun unaryMinus() = Vector(-x, -y)

    // compareTo — lar deg bruke <, >, <=, >=
    operator fun compareTo(other: Vector): Int {
        val thisMag = Math.sqrt(x * x + y * y)
        val otherMag = Math.sqrt(other.x * other.x + other.y * other.y)
        return thisMag.compareTo(otherMag)
    }

    val magnitude: Double get() = Math.sqrt(x * x + y * y)
}

// Indexering med get/set
class Matrix(private val rows: Int, private val cols: Int) {
    private val data = Array(rows) { DoubleArray(cols) }

    operator fun get(row: Int, col: Int) = data[row][col]
    operator fun set(row: Int, col: Int, value: Double) { data[row][col] = value }

    override fun toString(): String = data.joinToString("\n") { row ->
        row.joinToString(", ") { "%.1f".format(it) }
    }
}

// contains — lar deg bruke 'in' operator
data class BoundingBox(val minX: Double, val minY: Double, val maxX: Double, val maxY: Double) {
    operator fun contains(point: Vector) =
        point.x in minX..maxX && point.y in minY..maxY
}

fun main() {
    val a = Vector(1.0, 2.0)
    val b = Vector(3.0, 4.0)

    println("a + b = ${a + b}")      // Vector(4.0, 6.0)
    println("a - b = ${a - b}")      // Vector(-2.0, -2.0)
    println("a * 3 = ${a * 3.0}")    // Vector(3.0, 6.0)
    println("-a = ${-a}")             // Vector(-1.0, -2.0)

    // compareTo
    println("a < b: ${a < b}")       // true (magnitude)
    println("|a| = ${"%.2f".format(a.magnitude)}, |b| = ${"%.2f".format(b.magnitude)}")

    // Matrix indexering
    println()
    val m = Matrix(2, 2)
    m[0, 0] = 1.0
    m[0, 1] = 2.0
    m[1, 0] = 3.0
    m[1, 1] = 4.0
    println("Matrix:\n$m")

    // contains / in
    println()
    val box = BoundingBox(0.0, 0.0, 10.0, 10.0)
    val inside = Vector(5.0, 5.0)
    val outside = Vector(15.0, 5.0)
    println("$inside in box: ${inside in box}")   // true
    println("$outside in box: ${outside in box}") // false
}
