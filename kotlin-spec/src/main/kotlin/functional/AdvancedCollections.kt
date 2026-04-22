package org.example.functional

/**
 * AdvancedCollections — en "cheat sheet" for collection-operasjoner
 *
 * Dekker:
 *  - groupBy, partition
 *  - associate, associateBy
 *  - zip, windowed, chunked
 *  - flatMap, flatten
 *  - any/all/none, find
 *  - reduce, fold
 *  - take, drop, takeWhile
 *  - distinct, sortedBy, sortedByDescending
 *  - sumOf, maxByOrNull, minByOrNull
 *
 * Bruk når: du er usikker på hvilken operasjon du trenger. Denne filen
 * er oppslagsverk-delen — kjør den og se hva hver operasjon gjør.
 *
 * NB: De fleste av disse har en Sequence-variant hvis du jobber med
 *     store datasett (se Sequences.kt).
 *
 * Docs: https://kotlinlang.org/docs/collection-operations.html
 */

private data class Ansatt(val navn: String, val avdeling: String, val lønn: Int)

fun main() {
    val tall = listOf(5, 3, 8, 1, 9, 2, 7, 4, 6)
    val ansatte = listOf(
        Ansatt("Alice", "Teknisk", 80000),
        Ansatt("Bob",   "Teknisk", 75000),
        Ansatt("Clara", "Salg",    70000),
        Ansatt("Dave",  "Salg",    65000),
        Ansatt("Eva",   "HR",      60000)
    )

    println("=== groupBy ===")
    val etterAvdeling = ansatte.groupBy { it.avdeling }
    etterAvdeling.forEach { (avd, a) ->
        println("  $avd: ${a.map { it.navn }}")
    }

    println("\n=== partition ===")
    val (utviklere, andre) = ansatte.partition { it.avdeling == "Teknisk" }
    println("  Utviklere: ${utviklere.map { it.navn }}")
    println("  Andre:     ${andre.map { it.navn }}")

    println("\n=== associate / associateBy ===")
    val lønnsMap = ansatte.associate { it.navn to it.lønn }
    println("  Lønnsmap: $lønnsMap")
    val etterNavn = ansatte.associateBy { it.navn }
    println("  Bob sin avdeling: ${etterNavn["Bob"]?.avdeling}")

    println("\n=== zip ===")
    val navn = listOf("A", "B", "C")
    val poeng = listOf(90, 85, 78)
    println("  ${navn.zip(poeng)}")
    println("  ${navn.zip(poeng) { n, p -> "$n=$p" }}")

    println("\n=== windowed / chunked ===")
    println("  windowed(3):        ${tall.windowed(3)}")
    println("  windowed(3, step=2): ${tall.windowed(3, step = 2)}")
    println("  chunked(3):          ${tall.chunked(3)}")

    println("\n=== flatMap / flatten ===")
    val nøstet = listOf(listOf(1, 2), listOf(3, 4), listOf(5))
    println("  flatMap: ${nøstet.flatMap { it }}")
    println("  flatten: ${nøstet.flatten()}")

    println("\n=== any / all / none ===")
    println("  Noen > 70k: ${ansatte.any { it.lønn > 70000 }}")
    println("  Alle > 50k: ${ansatte.all { it.lønn > 50000 }}")
    println("  Ingen i 'Marked': ${ansatte.none { it.avdeling == "Marked" }}")

    println("\n=== find / firstOrNull ===")
    val rikest = ansatte.find { it.lønn > 75000 }
    println("  Første med lønn > 75k: ${rikest?.navn}")

    println("\n=== reduce / fold ===")
    println("  Sum via reduce: ${tall.reduce { akk, n -> akk + n }}")
    println("  Produkt via fold: ${listOf(1, 2, 3, 4).fold(1) { akk, n -> akk * n }}")

    println("\n=== take / drop / takeWhile ===")
    println("  take(3):       ${tall.take(3)}")
    println("  drop(3):       ${tall.drop(3)}")
    println("  takeWhile < 5: ${listOf(1, 3, 5, 2, 4).takeWhile { it < 5 }}")

    println("\n=== distinct / sortedBy ===")
    println("  distinct:             ${listOf(1, 2, 2, 3, 3, 3).distinct()}")
    println("  sortedBy lønn:        ${ansatte.sortedBy { it.lønn }.map { it.navn }}")
    println("  sortedByDescending:   ${ansatte.sortedByDescending { it.lønn }.map { it.navn }}")

    println("\n=== sumOf / maxByOrNull / minByOrNull ===")
    println("  Total lønn:  ${ansatte.sumOf { it.lønn }}")
    println("  Høyest lønn: ${ansatte.maxByOrNull { it.lønn }?.navn}")
    println("  Lavest lønn: ${ansatte.minByOrNull { it.lønn }?.navn}")
}
