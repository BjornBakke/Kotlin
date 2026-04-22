package org.example.oop

/**
 * SmartCasts — automatisk typecasting etter is/null-sjekk
 *
 * Dekker:
 *  - is-sjekk gir automatisk cast i resten av blokken
 *  - !is-sjekk + tidlig return fungerer også
 *  - Safe cast (as?) returnerer null ved feil type
 *  - Unsafe cast (as) kaster ClassCastException
 *
 * Bruk når: du jobber med 'Any' eller en supertype og vil tilgang til
 * spesifikke medlemmer på undertypen uten å skrive eksplisitt cast.
 *
 * NB: Smart cast virker KUN på var hvis kompilatoren kan garantere at
 *     verdien ikke endret seg mellom sjekken og bruken (ikke tilgjengelig
 *     på mutable properties fra åpne klasser, for eksempel).
 *
 * Docs: https://kotlinlang.org/docs/typecasts.html
 */

fun beskriv(obj: Any): String = when (obj) {
    is String   -> "Streng med lengde ${obj.length}"   // smart cast til String
    is Int      -> "Heltall: ${obj * 2}"                // smart cast til Int
    is List<*>  -> "Liste med ${obj.size} elementer"    // smart cast til List
    is Boolean  -> if (obj) "SANN" else "USANN"
    else        -> "Ukjent: $obj"
}

fun behandleInput(input: Any) {
    if (input is String) {
        // input er automatisk String her
        println("  Uppercase: ${input.uppercase()}")
        println("  Første tegn: ${input.first()}")
    }

    if (input is String && input.length > 5) {
        println("  Lang streng: $input")
    }

    if (input !is String) {
        println("  Ikke en streng: $input")
        return
    }
    // input er String fra dette punktet (etter return guard)
    println("  Trimmet: ${input.trim()}")
}

// Safe cast med as?
fun sikkerCast(verdi: Any): Int? {
    val tall = verdi as? Int
    return tall?.let { it * 10 }
}

// Unsafe cast med as — krasj ved feil type
fun unsafeCast(verdi: Any): String = verdi as String

fun main() {
    println(beskriv("Hei"))
    println(beskriv(42))
    println(beskriv(listOf(1, 2)))
    println(beskriv(true))
    println(beskriv(3.14))

    println()

    behandleInput("  Kotlin er kult  ")
    behandleInput(42)

    println()

    println("sikkerCast(5) = ${sikkerCast(5)}")          // 50
    println("sikkerCast(\"nei\") = ${sikkerCast("nei")}")  // null

    println(unsafeCast("OK"))
    try {
        unsafeCast(123)
    } catch (e: ClassCastException) {
        println("Cast feilet: ${e.message}")
    }
}
