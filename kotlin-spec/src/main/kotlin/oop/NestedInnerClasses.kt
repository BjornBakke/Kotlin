package org.example.oop

class Outer(private val name: String) {
    private val secret = "outer-secret"

    // Nested class — har IKKE tilgang til outer instans
    class Nested {
        fun greet() = "Hei fra Nested"
        // fun test() = name  // FEIL — kan ikke aksessere Outer sin tilstand
    }

    // Inner class — HAR tilgang til outer instans via this@Outer
    inner class Inner {
        fun greet() = "Hei fra Inner, eier=$name"
        fun getSecret() = secret  // OK — har tilgang
        fun outerRef() = this@Outer  // eksplisitt referanse til outer
    }

    fun createInner() = Inner()
}

// Praktisk eksempel: Builder pattern med inner class
class Pizza(val size: String, val toppings: List<String>, val extraCheese: Boolean) {
    // Inner Builder har tilgang til Pizza sin constructor
    class Builder(private val size: String) {
        private val toppings = mutableListOf<String>()
        private var extraCheese = false

        fun addTopping(topping: String) = apply { toppings.add(topping) }
        fun withExtraCheese() = apply { extraCheese = true }
        fun build() = Pizza(size, toppings.toList(), extraCheese)
    }

    override fun toString() = "$size pizza med $toppings${if (extraCheese) " + extra cheese" else ""}"
}

fun main() {
    // Nested class — trenger ikke outer instans
    val nested = Outer.Nested()
    println(nested.greet())  // Hei from Nested

    // Inner class — trenger outer instans
    val outer = Outer("Alice")
    val inner = outer.Inner()
    println(inner.greet())      // Hei from Inner, owner=Alice
    println(inner.getSecret())  // outer-secret

    // Via factory method
    val inner2 = outer.createInner()
    println(inner2.greet())

    // Builder pattern
    val pizza = Pizza.Builder("Large")
        .addTopping("Pepperoni")
        .addTopping("Mushrooms")
        .withExtraCheese()
        .build()
    println(pizza)  // Large pizza with [Pepperoni, Mushrooms] + extra cheese
}


