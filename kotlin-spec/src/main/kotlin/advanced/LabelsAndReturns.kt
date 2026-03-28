package org.example.advanced

// Labels og qualified returns — kontroll over nøstede løkker og lambdaer

fun main() {
    // return@forEach — returner fra lambda, ikke fra main
    println("=== return@forEach ===")
    listOf(1, 2, 3, 4, 5).forEach {
        if (it == 3) return@forEach  // hopper over 3, fortsetter med 4
        println(it)
    }
    println("Etter forEach")  // denne kjøres

    // return (uten label) i forEach ville returnert fra main!
    // listOf(1,2,3).forEach { if (it == 2) return }  // returnerer fra main

    // return@label — custom label
    println("\n=== tilpasset label ===")
    listOf(1, 2, 3, 4, 5).forEach myLoop@{
        if (it == 3) return@myLoop
        println(it)
    }

    // break/continue med labels i nøstede løkker
    println("\n=== break@outer ===")
    outer@ for (i in 1..3) {
        for (j in 1..3) {
            if (i == 2 && j == 2) break@outer  // bryter ut av YTRE løkke
            println("$i,$j")
        }
    }

    println("\n=== continue@outer ===")
    outer@ for (i in 1..3) {
        for (j in 1..3) {
            if (j == 2) continue@outer  // hopper til neste iterasjon av YTRE løkke
            println("$i,$j")
        }
    }

    // Forskjell: run med lambda — return@run returnerer verdien
    println("\n=== return@run ===")
    val result = run {
        val x = 10
        if (x > 5) return@run "big"
        "small"  // dette er siste uttrykk, men return@run "big" kjører
    }
    println("Result: $result")

    // let med return@let
    val value: String? = "hello"
    val processed = value?.let {
        if (it.length < 3) return@let "for kort"
        it.uppercase()
    }
    println("Processed: $processed")
}


