package org.example.functional

/**
 * InlineFunctions — kopier lambda-koden inn på call-site
 *
 * Dekker:
 *  - inline: kompilatoren kopierer funksjonsinnholdet der den kalles
 *  - noinline: en spesifikk lambda som IKKE skal inlines
 *  - reified: generisk type-parameter tilgjengelig ved runtime
 *  - filterIsInstance med reified T
 *
 * Bruk når:
 *  - Du har en HOF som brukes ofte med korte lambdaer (unngå allokering)
 *  - Du trenger "reified T" (f.eks. T::class) i en generisk funksjon
 *
 * NB: inline gir ingen fordel om lambda er kompleks eller stor — da blåser
 *     den opp call-site. Reserver til korte HOF-er (map, filter-lignende).
 *
 * Docs: https://kotlinlang.org/docs/inline-functions.html
 */

inline fun mål(blokk: () -> Unit): Long {
    val start = System.nanoTime()
    blokk()
    return System.nanoTime() - start
}

// noinline tillater at lambdaen kan lagres/returneres
inline fun kjørOgLagre(
    handling: () -> Unit,
    noinline tilbakekall: () -> Unit
): () -> Unit {
    handling()
    return tilbakekall  // kan returnere fordi noinline
}

// reified lar oss sjekke typen T ved runtime
inline fun <reified T> erInstansAv(verdi: Any): Boolean = verdi is T

inline fun <reified T> filterPåType(elementer: List<Any>): List<T> =
    elementer.filterIsInstance<T>()

fun main() {
    println("=== måler tid (inline) ===")
    val tid = mål {
        val sum = (1..1_000_000).sum()
        println("  Sum: $sum")
    }
    println("  Tok ${tid / 1_000_000}ms")

    println("\n=== noinline lambda kan returneres ===")
    val lagret = kjørOgLagre(
        handling = { println("  kjører handling") },
        tilbakekall = { println("  lagret callback kjørt") }
    )
    lagret()

    println("\n=== reified — type tilgjengelig ved runtime ===")
    println("  42 er String? ${erInstansAv<String>(42)}")
    println("  42 er Int?    ${erInstansAv<Int>(42)}")

    val blandet: List<Any> = listOf(1, "hei", 2.0, "verden", 3)
    println("  Strenger: ${filterPåType<String>(blandet)}")
    println("  Ints:     ${filterPåType<Int>(blandet)}")
}
