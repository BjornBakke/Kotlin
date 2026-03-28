package org.example.oop

// typealias gir nytt navn til eksisterende typer — gjor koden mer lesbar

// For collections
typealias StudentGrades = Map<String, List<Int>>
typealias MutableStudentGrades = MutableMap<String, MutableList<Int>>

// For function types
typealias Predicate<T> = (T) -> Boolean
typealias Transformer<T, R> = (T) -> R
typealias EventHandler = (String, Int) -> Unit

// For generics
typealias StringList = List<String>
typealias Pair2<T> = Pair<T, T>

fun <T> filterWith(items: List<T>, predicate: Predicate<T>): List<T> {
    return items.filter(predicate)
}

fun main() {
    // Collection typealias
    val grades: StudentGrades = mapOf(
        "Alice" to listOf(90, 85, 92),
        "Bob" to listOf(78, 88, 75)
    )
    grades.forEach { (name, scores) ->
        println("$name: avg=${scores.average()}")
    }

    // Function type alias
    val isEven: Predicate<Int> = { it % 2 == 0 }
    val toUpper: Transformer<String, String> = { it.uppercase() }

    println(filterWith(listOf(1, 2, 3, 4, 5), isEven))  // [2, 4]
    println(toUpper("hello"))  // HELLO

    // Event handler
    val onClick: EventHandler = { event, x ->
        println("Hendelse '$event' på posisjon $x")
    }
    onClick("click", 42)

    // Generic alias
    val names: StringList = listOf("A", "B", "C")
    val point: Pair2<Double> = Pair(1.0, 2.0)
    println(names)
    println(point)
}

