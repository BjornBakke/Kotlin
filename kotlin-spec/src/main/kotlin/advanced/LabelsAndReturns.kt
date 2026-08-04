package org.example.advanced

/**
 * LabelsAndReturns — labels og qualified returns
 *
 * Dekker:
 *  - return@lambdaName for å returnere fra en lambda (ikke ytre funksjon)
 *  - Custom labels (myLoop@)
 *  - break@ og continue@ i nøstede løkker
 *  - return@run, return@let
 *
 * Bruk når: du har nøstede lambdaer eller løkker og trenger å være
 * spesifikk om hvilken du returnerer fra.
 *
 * NB: "Bare return" i en lambda betyr return fra den YTRE funksjonen,
 *     ikke fra lambda. Bruk return@lambdaName for lokal retur.
 *
 * Docs: https://kotlinlang.org/docs/returns.html
 */

fun main() {
    println("=== return@forEach (hopper over 3) ===")
    listOf(1, 2, 3, 4, 5).forEach {
        if (it == 3) return@forEach  // hopper bare over 3
        println("  $it")
    }
    println("  Etter forEach")

    println("\n=== Custom label ===")
    listOf(1, 2, 3, 4, 5).forEach myLoop@{
        if (it == 3) return@myLoop
        println("  $it")
    }

    println("\n=== break@ytre i nøstet løkke ===")
    ytre@ for (i in 1..3) {
        for (j in 1..3) {
            if (i == 2 && j == 2) break@ytre
            println("  $i,$j")
        }
    }

    println("\n=== continue@ytre ===")
    ytre@ for (i in 1..3) {
        for (j in 1..3) {
            if (j == 2) continue@ytre
            println("  $i,$j")
        }
    }

    println("\n=== return@run ===")
    val resultat = run {
        val x = 10
        if (x > 5) return@run "stor"
        "liten"
    }
    println("  $resultat")

    println("\n=== return@let ===")
    val verdi: String? = "hei"
    val behandlet = verdi?.let {
        if (it.length < 3) return@let "for kort"
        it.uppercase()
    }
    println("  $behandlet")
}
