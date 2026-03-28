package org.example.oop

// Primary constructor — i klasse-signaturen
class Person(val name: String, var age: Int) {
    // init-blokk — kjøres etter primary constructor
    init {
        println("Person opprettet: $name, alder $age")
        require(age >= 0) { "Alder kan ikke være negativ" }
    }

    // Flere init-blokker kjøres i rekkefølge
    init {
        println("  (init block 2)")
    }
}

// Secondary constructor — bruker constructor keyword
class Employee(val name: String, val department: String) {
    var title: String = "Associate"

    // Secondary constructor må delegere til primary via this()
    constructor(name: String, department: String, title: String) : this(name, department) {
        this.title = title
    }

    override fun toString() = "$name - $title @ $department"
}

// Initialiseringsrekkefølge: primary constructor → property-initializers/init-blokker (i kode-rekkefølge)
class InitOrder(val input: String) {
    val first = "Første: $input".also(::println)

    init {
        println("Init-blokk: $input")
    }

    val second = "Andre: $input".also(::println)

    init {
        println("Init-blokk 2: $input")
    }
}

// Default-verdier i primary constructor
class Config(
    val host: String = "localhost",
    val port: Int = 8080,
    val secure: Boolean = false
) {
    override fun toString() = "${if (secure) "https" else "http"}://$host:$port"
}

fun main() {
    val person = Person("Alice", 30)
    println()

    // Secondary constructor
    val emp1 = Employee("Bob", "Engineering")
    val emp2 = Employee("Carol", "Engineering", "Senioringeniør")
    println(emp1)  // Bob - Associate @ Engineering
    println(emp2)  // Carol - Senioringeniør @ Engineering
    println()

    // Initialiseringsrekkefølge
    println("--- Init order ---")
    InitOrder("test")
    println()

    // Default-verdier — kan utelate parametere
    println(Config())                          // http://localhost:8080
    println(Config("api.example.com", 443, true))  // https://api.example.com:443
    println(Config(port = 9090))               // http://localhost:9090
}

