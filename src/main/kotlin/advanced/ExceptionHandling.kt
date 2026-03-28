package org.example.advanced

// Custom exception
class ValidationException(val field: String, message: String) : Exception(message)

// throw som Nothing — uttrykk som aldri returnerer
fun fail(message: String): Nothing {
    throw IllegalStateException(message)
}

fun main() {
    // try-catch-finally
    try {
        val result = "abc".toInt()
        println(result)
    } catch (e: NumberFormatException) {
        println("Feil fanget: ${e.message}")
    } finally {
        println("Finally kjøres alltid")
    }

    // try som expression — returnerer verdi
    val number = try {
        "42".toInt()
    } catch (e: NumberFormatException) {
        -1
    }
    println("Tolket: $number")

    val invalid = try {
        "nope".toInt()
    } catch (e: NumberFormatException) {
        -1
    }
    println("Ugyldig: $invalid")

    // Flere catch-blokker
    fun riskyOperation(input: String): String = try {
        val index = input.toInt()
        val items = listOf("a", "b", "c")
        items[index]
    } catch (e: NumberFormatException) {
        "Ikke et tall: $input"
    } catch (e: IndexOutOfBoundsException) {
        "Indeks utenfor gyldig område: $input"
    }

    println(riskyOperation("1"))     // b
    println(riskyOperation("abc"))   // Not a number: abc
    println(riskyOperation("99"))    // Index out of range: 99

    // Custom exception
    fun validateAge(age: Int) {
        if (age < 0 || age > 150) {
            throw ValidationException("age", "Alder må være 0-150, fikk $age")
        }
    }

    try {
        validateAge(200)
    } catch (e: ValidationException) {
        println("Validering feilet for '${e.field}': ${e.message}")
    }

    // throw som Nothing — kan brukes i elvis operator
    val name: String? = null
    val safeName = name ?: fail("Navn er påkrevd")  // krasjer her
    // Linje under nås aldri, men kompilatoren vet det pga Nothing
    println(safeName)
}


