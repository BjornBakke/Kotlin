package org.example.advanced

/**
 * ExceptionHandling — try, catch, finally, throw
 *
 * Dekker:
 *  - try-catch-finally
 *  - try som uttrykk (returnerer verdi)
 *  - Flere catch-blokker for ulike exceptions
 *  - Custom exception (arver Exception)
 *  - throw som uttrykk, Nothing-type
 *
 * Bruk når: du må håndtere feil fra IO, parsing, eller egen validering.
 *
 * NB: Kotlin har ingen "checked exceptions" som Java. Alle unntak er
 *     runtime — så kompilatoren tvinger deg ikke til å fange dem.
 *
 * Docs: https://kotlinlang.org/docs/exceptions.html
 */

class ValideringsException(val felt: String, melding: String) : Exception(melding)

// Nothing = funksjon returnerer aldri normalt
fun feil(melding: String): Nothing = throw IllegalStateException(melding)

fun main() {
    println("=== try-catch-finally ===")
    try {
        val r = "abc".toInt()
        println("  $r")
    } catch (e: NumberFormatException) {
        println("  fanget: ${e.message}")
    } finally {
        println("  finally kjøres alltid")
    }

    println("\n=== try som uttrykk ===")
    val tall = try {
        "42".toInt()
    } catch (e: NumberFormatException) {
        -1
    }
    println("  tolket: $tall")

    val ugyldig = try {
        "nope".toInt()
    } catch (e: NumberFormatException) {
        -1
    }
    println("  ugyldig: $ugyldig")

    println("\n=== Flere catch ===")
    fun risikabelOp(input: String): String = try {
        val i = input.toInt()
        val elementer = listOf("a", "b", "c")
        elementer[i]
    } catch (e: NumberFormatException) {
        "Ikke et tall: $input"
    } catch (e: IndexOutOfBoundsException) {
        "Indeks utenfor gyldig område: $input"
    }
    println("  ${risikabelOp("1")}")
    println("  ${risikabelOp("abc")}")
    println("  ${risikabelOp("99")}")

    println("\n=== Custom exception ===")
    fun valider(alder: Int) {
        if (alder < 0 || alder > 150) {
            throw ValideringsException("alder", "Alder må være 0-150, fikk $alder")
        }
    }
    try {
        valider(200)
    } catch (e: ValideringsException) {
        println("  validering feilet for '${e.felt}': ${e.message}")
    }

    println("\n=== Nothing med elvis ===")
    val navn: String? = null
    try {
        val bekreftet = navn ?: feil("Navn er påkrevd")
        println("  aldri her: $bekreftet")
    } catch (e: IllegalStateException) {
        println("  elvis + feil(): ${e.message}")
    }
}
