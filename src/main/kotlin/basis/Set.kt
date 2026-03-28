package org.example.basis

fun main() {
    // Read-only set
    val readOnlyFruit = setOf("apple", "banana", "cherry", "cherry")
    // Mutable set with explicit type declaration
    val fruit: MutableSet<String> = mutableSetOf("apple", "banana", "cherry", "cherry")

    println(readOnlyFruit)
    // [apple, banana, cherry]

    val fruitLocked: Set<String> = fruit
    println("Dette settet har ${readOnlyFruit.count()} elementer")
    // This set has 3 items
    println("banana" in readOnlyFruit)
    // true
    fruit.add("dragonfruit")    // Add "dragonfruit" to the set
    println(fruit)              // [apple, banana, cherry, dragonfruit]

    fruit.remove("dragonfruit") // Remove "dragonfruit" from the set
    println(fruit)              // [apple, banana, cherry]
}

fun main2() {
    val tall = 1..100
    for (t in tall)
        when {
            t % 15 == 0 -> print("buss-fiss")
            t % 3 == 0 -> print("fiss")
            t % 5 == 0 -> print("buss")
            else -> print("$t")
        }

}

fun main3() {
    val words = listOf("dinosaur", "limousine", "magazine", "language")
    for (word in words) {
        if ( word.startsWith("l"))  print("$word")
    }
}
