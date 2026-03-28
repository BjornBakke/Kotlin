package org.example.advanced

// when — Kotlin sin kraftige pattern matching

sealed class Expr {
    data class Num(val value: Double) : Expr()
    data class Sum(val left: Expr, val right: Expr) : Expr()
    data class Mul(val left: Expr, val right: Expr) : Expr()
    data object Undefined : Expr()
}

fun eval(expr: Expr): Double = when (expr) {
    is Expr.Num -> expr.value
    is Expr.Sum -> eval(expr.left) + eval(expr.right)
    is Expr.Mul -> eval(expr.left) * eval(expr.right)
    Expr.Undefined -> Double.NaN
}

enum class HttpStatus(val code: Int) {
    OK(200), CREATED(201), BAD_REQUEST(400), NOT_FOUND(404), SERVER_ERROR(500)
}

fun describeStatus(status: HttpStatus) = when (status) {
    HttpStatus.OK, HttpStatus.CREATED -> "Suksess"
    HttpStatus.BAD_REQUEST -> "Klientfeil"
    HttpStatus.NOT_FOUND -> "Ikke funnet"
    HttpStatus.SERVER_ERROR -> "Serverfeil"
}

fun main() {
    // when med sealed class — exhaustive matching
    val expr = Expr.Sum(Expr.Num(3.0), Expr.Mul(Expr.Num(2.0), Expr.Num(4.0)))
    println("3 + (2 * 4) = ${eval(expr)}")  // 11.0

    // when med enum
    println(describeStatus(HttpStatus.OK))         // Success
    println(describeStatus(HttpStatus.NOT_FOUND))   // Not found

    // when med type-sjekk
    fun classify(obj: Any): String = when (obj) {
        is String -> "Streng: '${obj.uppercase()}'"
        is Int -> "Int: ${obj * 2}"
        is List<*> -> "Liste med størrelse ${obj.size}"
        else -> "Unknown"
    }
    println(classify("hello"))
    println(classify(21))
    println(classify(listOf(1, 2)))

    // when med range
    fun gradeOf(score: Int) = when (score) {
        in 90..100 -> "A"
        in 80..89 -> "B"
        in 70..79 -> "C"
        in 60..69 -> "D"
        else -> "F"
    }
    println("Poeng 85 = ${gradeOf(85)}")
    println("Poeng 42 = ${gradeOf(42)}")

    // when som expression (uten argument)
    val temp = 25
    val weather = when {
        temp < 0 -> "Freezing"
        temp < 15 -> "Cold"
        temp < 25 -> "Nice"
        temp < 35 -> "Hot"
        else -> "Extreme"
    }
    println("${temp}°C = $weather")

    // when med flere verdier i en branch
    val char = 'a'
    val type = when (char) {
        'a', 'e', 'i', 'o', 'u' -> "vowel"
        in 'a'..'z' -> "consonant"
        in 'A'..'Z' -> "stor bokstav"
        else -> "other"
    }
    println("'$char' is a $type")
}


