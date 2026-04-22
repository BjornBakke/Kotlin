package org.example.basis

/**
 * ControlFlow — if, when, for, while, ranges
 *
 * Dekker:
 *  - if/else som uttrykk (returnerer en verdi)
 *  - when som uttrykk — Kotlin sin "switch på steroider"
 *  - for med range og medIndeks
 *  - while og do-while
 *  - Ranges: 1..10, 1 until 10, 10 downTo 1, step 2
 *
 * Bruk når: du trenger å styre programflyten. I Kotlin er både if og when
 * uttrykk — de kan returnere verdi, så du slipper ofte mellomvariabler.
 *
 * NB: 1..10 inkluderer 10, mens 1 until 10 stopper på 9 (eksklusivt).
 *
 * Docs: https://kotlinlang.org/docs/control-flow.html
 *       https://kotlinlang.org/docs/ranges.html
 */

fun main() {
    println("=== if som uttrykk ===")
    val a = 7
    val b = 12
    val største = if (a > b) a else b
    println("  Største: $største")

    println("\n=== when som uttrykk ===")
    val poeng = 78
    val karakter = when {
        poeng >= 90 -> "A"
        poeng >= 80 -> "B"
        poeng >= 70 -> "C"
        poeng >= 60 -> "D"
        else        -> "F"
    }
    println("  $poeng poeng gir $karakter")

    println("\n=== when med argument ===")
    val tegn: Any = 42
    val beskrivelse = when (tegn) {
        0          -> "null"
        in 1..9    -> "lite positivt tall"
        in 10..99  -> "to-sifret"
        is String  -> "tekst: $tegn"
        else       -> "noe annet"
    }
    println("  Beskrivelse: $beskrivelse")

    println("\n=== for over range ===")
    for (i in 1..5) print("  $i")
    println()

    println("\n=== for over range eksklusivt ===")
    for (i in 1 until 5) print("  $i")
    println("  (stopper før 5)")

    println("\n=== for nedover med step ===")
    for (i in 10 downTo 0 step 2) print("  $i")
    println()

    println("\n=== for med indeks ===")
    val frukter = listOf("eple", "banan", "kirsebær")
    for ((indeks, frukt) in frukter.withIndex()) {
        println("  $indeks: $frukt")
    }

    println("\n=== while ===")
    var n = 3
    while (n > 0) {
        println("  n = $n")
        n--
    }

    println("\n=== do-while ===")
    var m = 0
    do {
        println("  m = $m")
        m++
    } while (m < 2)

    println("\n=== Range som liste ===")
    val tall = (1..10).toList()
    println("  $tall")
    val partall = (1..10).filter { it % 2 == 0 }
    println("  Partall: $partall")
}
