package org.example.intermediate

/**
 * Generics - skriv kode som virker for flere typer
 *
 * Dekker:
 *  - generiske funksjoner med `<T>`
 *  - generiske klasser
 *  - `out` for trygg produksjon av verdier
 *  - `in` for trygg konsumering av verdier
 *
 * Bruk når: du vil gjenbruke samme logikk for flere typer uten å miste
 * typesikkerhet eller falle tilbake til `Any`.
 *
 * NB: bruk `out` når en type bare produserer verdier, og `in` når den
 *     bare tar imot verdier. Da får du tryggere og mer fleksibel kode.
 *
 * Docs: https://kotlinlang.org/docs/generics.html
 */

private data class Boks<T>(val verdi: T)

private interface Kilde<out T> {
    fun hent(): T
}

private interface Sluk<in T> {
    fun motta(verdi: T)
}

private class TekstKilde : Kilde<String> {
    override fun hent(): String = "generisk verdi"
}

private class AnyLogger : Sluk<Any> {
    override fun motta(verdi: Any) {
        println("  Logg: $verdi")
    }
}

private fun <T> førsteEllerNull(liste: List<T>): T? = liste.firstOrNull()

fun main() {
    println("=== Generisk klasse ===")
    val tallBoks = Boks(42)
    val tekstBoks = Boks("Kotlin")
    println("  Tallboks: ${tallBoks.verdi}")
    println("  Tekstboks: ${tekstBoks.verdi}")

    println("\n=== Generisk funksjon ===")
    println("  Første navn: ${førsteEllerNull(listOf("Ada", "Bob"))}")
    println("  Første tall: ${førsteEllerNull(listOf(10, 20, 30))}")

    println("\n=== out: produser verdier ===")
    val kilde: Kilde<String> = TekstKilde()
    println("  Fra kilde: ${kilde.hent()}")

    println("\n=== in: konsumer verdier ===")
    val sluk: Sluk<String> = AnyLogger()
    sluk.motta("Hei fra generics")
}
