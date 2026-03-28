package org.example.advanced

// const val — kompileringstidskonstant (kun top-level eller i companion/object)
const val MAX_NAME_LENGTH = 50

class Temperature(celsius: Double) {
    // Custom getter — computed property (lagrer ikke verdi)
    var celsius: Double = celsius
        set(value) {
            require(value >= -273.15) { "Under absolutt nullpunkt!" }
            field = value  // 'field' = backing field
        }

    // Read-only computed property — ingen backing field
    val fahrenheit: Double
        get() = celsius * 9.0 / 5.0 + 32

    val kelvin: Double
        get() = celsius + 273.15

    val description: String
        get() = when {
            celsius < 0 -> "Freezing"
            celsius < 20 -> "Cold"
            celsius < 30 -> "Pleasant"
            else -> "Hot"
        }
}

class User(val name: String) {
    // Property med custom setter og logging
    var email: String = ""
        set(value) {
            println("  E-post endret: '$field' -> '$value'")
            field = value.lowercase().trim()
        }

    // Lazy-lignende manuelt (se Delegation.kt for ekte lazy)
    private var _displayName: String? = null
    val displayName: String
        get() {
            if (_displayName == null) {
                _displayName = name.replaceFirstChar { it.uppercase() }
                println("  Computed displayName: $_displayName")
            }
            return _displayName!!
        }
}

// Private setter — kan leses utenfor, men kun settes internt
class Counter {
    var count: Int = 0
        private set

    fun increment() { count++ }
    fun reset() { count = 0 }
}

fun main() {
    val temp = Temperature(20.0)
    println("${temp.celsius}°C = ${temp.fahrenheit}°F = ${temp.kelvin}K")
    println("Beskrivelse: ${temp.description}")

    temp.celsius = 35.0
    println("${temp.celsius}°C = ${temp.fahrenheit}°F — ${temp.description}")

    // Custom setter validering
    try {
        temp.celsius = -300.0  // under absolutt nullpunkt
    } catch (e: IllegalArgumentException) {
        println("Feil: ${e.message}")
    }

    println()

    // Custom setter med logging
    val user = User("alice")
    user.email = "  ALICE@Example.COM  "
    println("Email: ${user.email}")  // alice@example.com

    // Computed property (lazy-manuelt)
    println("Visning: ${user.displayName}")  // beregnes første gang
    println("Visning: ${user.displayName}")  // cached

    println()

    // Private setter
    val counter = Counter()
    counter.increment()
    counter.increment()
    println("Antall: ${counter.count}")
    // counter.count = 99  // FEIL — setter er private

    println("MAX_NAME_LENGTH = $MAX_NAME_LENGTH")
}


