package org.example.functional

// Data class destructuring — component1(), component2(), etc. genereres automatisk
data class Point(val x: Double, val y: Double)
data class Person(val name: String, val age: Int, val city: String)

fun getUser(): Pair<String, Int> = "Alice" to 30
fun getCoordinates(): Triple<Double, Double, Double> = Triple(1.0, 2.0, 3.0)

fun main() {
    // Data class destructuring
    val point = Point(3.0, 4.0)
    val (x, y) = point
    println("x=$x, y=$y")

    // Skip med _
    val person = Person("Bob", 25, "Oslo")
    val (name, _, city) = person  // skipper age
    println("$name fra $city")

    // Pair destructuring
    val (userName, userAge) = getUser()
    println("$userName er $userAge")

    // Triple destructuring
    val (a, b, c) = getCoordinates()
    println("Coords: $a, $b, $c")

    // For-loop destructuring med Map
    val scores = mapOf("Alice" to 95, "Bob" to 87, "Carol" to 92)
    for ((student, score) in scores) {
        println("$student: $score")
    }

    // For-loop med withIndex
    val fruits = listOf("Apple", "Banana", "Cherry")
    for ((index, fruit) in fruits.withIndex()) {
        println("$index: $fruit")
    }

    // Lambda parameter destructuring
    println("\nToppresultater:")
    scores.filter { (_, score) -> score >= 90 }
        .forEach { (name, score) -> println("  $name: $score") }

    // Destructuring i map-operasjoner
    val pairs = listOf("a" to 1, "b" to 2, "c" to 3)
    val result = pairs.map { (letter, number) -> "$letter=$number" }
    println(result)
}

