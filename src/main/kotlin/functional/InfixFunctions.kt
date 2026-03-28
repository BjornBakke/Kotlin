package org.example.functional

// infix fun — kan kalles uten prikk og parenteser: a funName b

// Må: være member function eller extension function
// Må: ha nøyaktig en parameter

infix fun Int.times(str: String) = str.repeat(this)

infix fun String.onto(other: String) = Pair(this, other)

data class Point2D(val x: Int, val y: Int) {
    infix fun distanceTo(other: Point2D): Double {
        val dx = (x - other.x).toDouble()
        val dy = (y - other.y).toDouble()
        return Math.sqrt(dx * dx + dy * dy)
    }
}

// Bygge assertions-lignende syntax
infix fun <T> T.shouldBe(expected: T) {
    if (this != expected) println("FEIL: $this != $expected")
    else println("OK: $this == $expected")
}

fun main() {
    // Infix kall — uten prikk og parenteser
    println(3 times "Hei ")  // Hei Hei Hello

    // Normalt kall fungerer også
    println(2.times("Ha"))     // HaHa

    val pair = "key" onto "value"
    println(pair)  // (key, value)

    // Infix på data class
    val p1 = Point2D(0, 0)
    val p2 = Point2D(3, 4)
    println("Avstand: ${p1 distanceTo p2}")  // 5.0

    // Assertion-stil
    42 shouldBe 42     // PASS
    "hello" shouldBe "hello"  // PASS
    1 + 1 shouldBe 3  // FAIL

    // Standardbiblioteket bruker infix: to, until, downTo, step
    val map = mapOf("a" to 1, "b" to 2)  // "to" er infix
    val range = 1 until 10               // "until" er infix
    val countdown = 10 downTo 1 step 2   // "downTo" og "step" er infix
    println(map)
    println(range.toList())
    println(countdown.toList())
}


