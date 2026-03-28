package org.example.functional

// inline — kompilatoren kopierer funksjonsinnholdet inn på call-site
// Fordel: unngår allokering av lambda-objekt + ekstra funksjonskall

inline fun measureTime(block: () -> Unit): Long {
    val start = System.nanoTime()
    block()  // denne lambdaen inlines — ingen objektallokering
    return System.nanoTime() - start
}

// noinline — parameter som IKKE skal inlines
// Nødvendig hvis lambdaen skal lagres/returneres (inline lambdaer kan ikke det)
inline fun runAndStore(
    action: () -> Unit,
    noinline callback: () -> Unit  // kan lagres fordi den ikke er inlinet
): () -> Unit {
    action()
    return callback  // kan returnere noinline lambda
}

// inline med reified type — gjor generisk type tilgjengelig ved runtime
inline fun <reified T> isInstanceOf(value: Any): Boolean {
    return value is T  // Normalt umulig med generics pga type erasure — reified fikser dette
}

inline fun <reified T> filterByType(items: List<Any>): List<T> {
    return items.filterIsInstance<T>()
}

// Uten inline ville lambda-parametere blitt anonyme klasser (Java)
// Med inline kopieres lambdaens kode direkte inn — mer effektivt for korte lambdaer

fun main() {
    // measureTime — lambda inlines, ingen allokering
    val elapsed = measureTime {
        val sum = (1..1_000_000).sum()
        println("Sum: $sum")
    }
    println("Tok ${elapsed / 1_000_000}ms\n")

    // noinline — callback kan returneres og lagres
    val stored = runAndStore(
        action = { println("Kjører handling") },
        callback = { println("Lagret callback kjørt") }
    )
    stored()  // kaller lagret callback

    // reified — type-sjekk ved runtime
    println("\n42 er String: ${isInstanceOf<String>(42)}")
    println("42 er Int: ${isInstanceOf<Int>(42)}")

    val mixed = listOf(1, "hello", 2.0, "world", 3)
    println("Strenger: ${filterByType<String>(mixed)}")
    println("Ints: ${filterByType<Int>(mixed)}")
}



