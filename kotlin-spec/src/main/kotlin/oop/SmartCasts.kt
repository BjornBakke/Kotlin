package org.example.oop

// Smart cast — etter is-sjekk kan du bruke typen direkte
fun describe(obj: Any): String = when (obj) {
    is String -> "Streng med lengde ${obj.length}"  // smart cast til String
    is Int -> "Heltall: ${obj * 2}"                 // smart cast til Int
    is List<*> -> "Liste med ${obj.size} elementer"  // smart cast til List
    is Boolean -> if (obj) "TRUE" else "FALSE"
    else -> "Ukjent: $obj"
}

// Smart cast i if-blokk
fun processInput(input: Any) {
    if (input is String) {
        // input er automatisk String her
        println(input.uppercase())
        println("Første tegn: ${input.first()}")
    }

    if (input is String && input.length > 5) {
        // Begge smart casts gjelder
        println("Lang streng: $input")
    }

    if (input !is String) {
        println("Ikke en streng: $input")
        return
    }
    // input er String herfra (etter return guard)
    println("Trimmet: ${input.trim()}")
}

// Safe cast med as? — returnerer null hvis feil type
fun safeCast(value: Any): Int? {
    val number = value as? Int  // null hvis value ikke er Int
    return number?.let { it * 10 }
}

// Unsafe cast med as — kaster ClassCastException hvis feil type
fun unsafeCast(value: Any): String {
    return value as String  // krasjer hvis value ikke er String!
}

fun main() {
    // when med smart cast
    println(describe("Hei"))       // String of length 5
    println(describe(42))            // Integer: 84
    println(describe(listOf(1, 2)))  // List with 2 elements
    println(describe(true))          // TRUE
    println(describe(3.14))          // Unknown: 3.14

    println()

    // Safe cast
    println(safeCast(5))       // 50
    println(safeCast("nope"))  // null

    // Unsafe cast
    println(unsafeCast("OK"))  // OK
    try {
        unsafeCast(123)  // ClassCastException!
    } catch (e: ClassCastException) {
        println("Casting feilet: ${e.message}")
    }
}


