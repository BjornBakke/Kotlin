package org.example.basis

fun toSeconds(time: String): (Int) -> Int = when (time) {
    "hour" -> { value -> value * 60 * 60 }
    "minute" -> { value -> value * 60 }
    "second" -> { value -> value }
    else -> { value -> value }
}

fun main() {
    val timesInMinutes = listOf(2, 10, 15, 1)
    val min2sec = toSeconds("minute")
    val totalTimeInSeconds = timesInMinutes.map(min2sec).sum()
    println("Total tid er $totalTimeInSeconds sek")
    // Total time is 1680 secs
    val action = { text: String -> text.uppercase() }
    println(action("hello"))
    println({ text: String -> text.uppercase() }("hello"))

    // The initial value is zero.
// The operation sums the initial value with every item in the list cumulatively.
    println(listOf(1, 2, 3).fold(0, { x, item -> x + item })) // 6

// Alternatively, in the form of a trailing lambda
    println(listOf(1, 2, 3).fold(0) { x, item -> x + item })  // 6

    val actions = listOf("title", "year", "author")
    val prefix = "https://example.com/book-info"
    val id = 5

    val urls = actions.map { input -> "$prefix/$id/$input" }

    println(urls)
    c()
}

fun repeatN(n: Int, action: () -> Unit) {
    repeat(List(n) { it + 1 }.size) { action() }
    repeat(n) { action() }

}

fun c() {
    // Write your code here
    val action = { -> println("hello") }
    repeatN(5, action)
    repeatN(5){  println("test") }

}
