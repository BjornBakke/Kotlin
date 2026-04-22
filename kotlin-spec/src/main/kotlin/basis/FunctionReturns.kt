package org.example.basis

/**
 * FunctionReturns — funksjoner som returnerer funksjoner
 *
 * Dekker:
 *  - Function types: (Int) -> Int, (String, Int) -> String
 *  - Lambdaer: { x -> x + 1 }
 *  - Higher-order functions — tar eller returnerer funksjoner
 *  - fold og map med lambdaer
 *  - Trailing lambda syntax
 *  - repeat {}
 *
 * Bruk når: du vil abstrahere en "strategi" eller "konverterings-regel"
 * som en verdi du kan sende rundt.
 *
 * NB: Lambdaer fanger variabler fra omgivende scope (closures). Se
 *     functional/Closures.kt for detaljer.
 *
 * Docs: https://kotlinlang.org/docs/lambdas.html
 */

// Funksjon som returnerer en annen funksjon
fun tilSekunder(enhet: String): (Int) -> Int = when (enhet) {
    "time"   -> { v -> v * 60 * 60 }
    "minutt" -> { v -> v * 60 }
    "sekund" -> { v -> v }
    else     -> { v -> v }
}

// Higher-order: tar en lambda som parameter
fun gjentaNGanger(n: Int, handling: () -> Unit) {
    repeat(n) { handling() }
}

fun main() {
    // Bruk returnert funksjon
    val min2sek: (Int) -> Int = tilSekunder("minutt")
    val tiderIMinutter = listOf(2, 10, 15, 1)
    val totaltISekunder = tiderIMinutter.map(min2sek).sum()
    println("Totalt sekunder: $totaltISekunder")  // 1680

    // Lambda tilordnet til variabel
    val stortBokstav = { tekst: String -> tekst.uppercase() }
    println(stortBokstav("hello"))

    // Umiddelbart kall av lambda
    println({ tekst: String -> tekst.uppercase() }("hello"))

    // fold — akkumulerer fra en startverdi
    val sum = listOf(1, 2, 3).fold(0) { akk, tall -> akk + tall }
    println("fold-sum: $sum")

    // map med lambda som bygger URLer
    val felt = listOf("tittel", "år", "forfatter")
    val prefix = "https://example.com/book-info"
    val id = 5
    val urler = felt.map { input -> "$prefix/$id/$input" }
    println(urler)

    // Higher-order function som tar lambda
    print("Gjenta: ")
    gjentaNGanger(3) { print("ha ") }
    println()
}
