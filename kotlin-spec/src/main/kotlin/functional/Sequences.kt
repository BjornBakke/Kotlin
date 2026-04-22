package org.example.functional

/**
 * Sequences — lat evaluering av sekvensoperasjoner
 *
 * Dekker:
 *  - Sequence vs List: lat (lazy) vs umiddelbar (eager) evaluering
 *  - asSequence() konverterer en collection til Sequence
 *  - generateSequence for uendelige/beregnede sekvenser
 *  - Terminale operasjoner trigger evaluering (toList, sum, first)
 *
 * Bruk når: datasettet er stort eller uendelig, eller du har mange
 * sammenkoblede map/filter og vil unngå mellomliggende lister.
 *
 * NB: For små samlinger er List ofte raskere (mindre overhead). Lat
 *     evaluering vinner først når kjeden er lang eller datasettet stort.
 *
 * Docs: https://kotlinlang.org/docs/sequences.html
 */

fun main() {
    println("=== Eager (List) — mellomliggende lister ===")
    val eager = listOf(1, 2, 3, 4, 5)
        .map { println("  eager map $it"); it * 2 }
        .filter { println("  eager filter $it"); it > 4 }
    println("  Resultat: $eager")

    println("\n=== Lazy (Sequence) — element for element ===")
    val lazy = listOf(1, 2, 3, 4, 5)
        .asSequence()
        .map { println("  lazy map $it"); it * 2 }
        .filter { println("  lazy filter $it"); it > 4 }
        .toList()  // terminal operasjon — trigger evaluering
    println("  Resultat: $lazy")

    println("\n=== sequenceOf + terminal ===")
    val seq = sequenceOf(1, 2, 3)
    println("  sum: ${seq.sum()}")

    println("\n=== generateSequence — uendelig sekvens ===")
    val toerpotenser = generateSequence(1) { it * 2 }  // 1, 2, 4, 8, 16…
    println("  Første 10: ${toerpotenser.take(10).toList()}")

    println("\n=== generateSequence med null-terminering ===")
    val nedtelling = generateSequence(10) { if (it > 0) it - 1 else null }
    println("  ${nedtelling.toList()}")

    println("\n=== Fibonacci med generateSequence ===")
    val fib = generateSequence(Pair(0, 1)) { Pair(it.second, it.first + it.second) }
        .map { it.first }
    println("  Første 10: ${fib.take(10).toList()}")

    println("\n=== Ytelsesforskjell: stopp tidlig uten å lage mellomliste ===")
    val stort = 1..1_000_000
    val førsteStoreKvadrat = stort.asSequence()
        .map { it.toLong() * it }
        .first { it > 1_000_000 }
    println("  Første kvadrat > 1M: $førsteStoreKvadrat")
}
