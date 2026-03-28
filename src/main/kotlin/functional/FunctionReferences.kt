package org.example.functional

// :: operator — referanse til funksjon (i stedet for lambda)

fun isEven(n: Int) = n % 2 == 0
fun square(n: Int) = n * n
fun greet(name: String) = "Hei, $name!"

data class User(val name: String, val age: Int) {
    fun introduce() = "Jeg er $name, $age år gammel"
}

fun main() {
    val numbers = listOf(1, 2, 3, 4, 5, 6)

    // ::funksjon — referanse til top-level funksjon
    println(numbers.filter(::isEven))    // [2, 4, 6]
    println(numbers.map(::square))       // [1, 4, 9, 16, 25, 36]

    val names = listOf("Alice", "Bob")
    println(names.map(::greet))  // [Hello, Alice!, Hello, Bob!]

    // ::Constructor — referanse til constructor
    val pairs = listOf("Alice" to 30, "Bob" to 25)
    val users = pairs.map { (name, age) -> User(name, age) }
    println(users)

    // Bound reference — referanse bundet til en spesifikk instans
    val user = User("Carol", 28)
    val intro = user::introduce  // bundet til 'user'
    println(intro())  // I'm Carol, 28 years old

    // Bound reference til property
    val getName = user::name  // KProperty referanse
    // println(getName.get())

    // Bruke String metoder som referanse
    val words = listOf("  hallo  ", "  verden  ")
    println(words.map(String::trim))  // [hello, world]
    println(words.map(String::uppercase))

    // Referanse til extension function
    fun String.exclaim() = "$this!"
    val items = listOf("wow", "cool")
    // Bruk lambda i stedet for referanse for local extensions
    println(items.map { it.exclaim() })

    // Sammenligning: lambda vs function reference
    println(numbers.filter { isEven(it) })   // lambda
    println(numbers.filter(::isEven))         // function reference — kortere
}


