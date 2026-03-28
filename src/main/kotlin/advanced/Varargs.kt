package org.example.advanced

// vararg — variabelt antall argumenter

fun printAll(vararg items: String) {
    for (item in items) {
        println("  $item")
    }
}

// vararg med andre parametere
fun log(level: String, vararg messages: String) {
    messages.forEach { println("[$level] $it") }
}

// vararg med trailing lambda
fun <T> buildList(vararg items: T, transform: (T) -> String = { it.toString() }): List<String> {
    return items.map(transform)
}

// vararg er egentlig en Array<T> inne i funksjonen
fun sumAll(vararg numbers: Int): Int = numbers.sum()

fun main() {
    // Enkel vararg
    println("=== printAll ===")
    printAll("Hei", "Verden", "Kotlin")

    // vararg med navngitt parameter
    println("\n=== logg ===")
    log("INFO", "Starter app", "Laster konfigurasjon", "Ready")
    log("ERROR", "Tilkobling feilet")

    // Spread operator * — sender array som vararg
    println("\n=== spread-operator ===")
    val words = arrayOf("one", "two", "three")
    printAll(*words)  // sprer arrayet til individuelle argumenter

    // Kombinere spread med enkelt-argumenter
    printAll("zero", *words, "four")

    // vararg som Array
    println("\n=== summerAlle ===")
    println("Sum: ${sumAll(1, 2, 3, 4, 5)}")

    val nums = intArrayOf(10, 20, 30)
    println("Sum fra tabell: ${sumAll(*nums)}")

    // buildList med transform
    println("\n=== byggListe ===")
    val result = buildList(1, 2, 3) { "Item #$it" }
    println(result)

    val simple = buildList("a", "b", "c")
    println(simple)
}



