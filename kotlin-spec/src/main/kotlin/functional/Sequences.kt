package org.example.functional

// Sequences = lazy evaluering — elementer beregnes ett om gangen
// Collections = eager — hele listen prosesseres i hvert steg

fun main() {
    // Eager (List) — lager mellomliggende lister for hvert steg
    val eagerResult = listOf(1, 2, 3, 4, 5)
        .map { println("  ivrig map $it"); it * 2 }
        .filter { println("  ivrig filter $it"); it > 4 }
    println("Ivrig resultat: $eagerResult\n")

    // Lazy (Sequence) — prosesserer element for element
    val lazyResult = listOf(1, 2, 3, 4, 5)
        .asSequence()
        .map { println("  lat map $it"); it * 2 }
        .filter { println("  lat filter $it"); it > 4 }
        .toList()  // terminal operasjon — trigger evaluering
    println("Lazy result: $lazyResult\n")

    // sequenceOf
    val seq = sequenceOf(1, 2, 3)
    println("sequenceOf sum: ${seq.sum()}")

    // generateSequence — uendelig sekvens
    val powers = generateSequence(1) { it * 2 }  // 1, 2, 4, 8, 16...
    println("Første 10 potenser av 2: ${powers.take(10).toList()}")

    // generateSequence med null-terminering
    val countDown = generateSequence(10) { if (it > 0) it - 1 else null }
    println("Nedtelling: ${countDown.toList()}")

    // Fibonacci med generateSequence
    val fibonacci = generateSequence(Pair(0, 1)) { Pair(it.second, it.first + it.second) }
        .map { it.first }
    println("Fibonacci(10): ${fibonacci.take(10).toList()}")

    // Ytelsesforsksell: sequence unngår mellomliggende lister
    val largeRange = (1..1_000_000)
    val firstBigSquare = largeRange.asSequence()
        .map { it.toLong() * it }
        .first { it > 1_000_000 }  // stopper etter den finner en
    println("Første kvadrat > 1M: $firstBigSquare")
}

