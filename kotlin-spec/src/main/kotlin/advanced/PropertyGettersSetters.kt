package org.example.advanced

/**
 * PropertyGettersSetters — custom getters/setters og computed properties
 *
 * Dekker:
 *  - Custom setter med validering (field = backing field)
 *  - Read-only computed property (ingen backing field)
 *  - Property med private setter
 *  - const val — compile-time konstant
 *
 * Bruk når: du vil legge logikk inn i property-tilgang uten å bryte
 * kallsyntaxen (obj.verdi i stedet for obj.getVerdi()).
 *
 * NB: 'field' er et spesielt nøkkelord som refererer til backing-feltet
 *     inni getter/setter. Det er det eneste stedet du kan bruke det.
 *
 * Docs: https://kotlinlang.org/docs/properties.html
 */

// const val = kompileringstidskonstant (bare top-level eller i companion/object)
const val MAX_NAVN_LENGDE = 50

class Temperatur(celsius: Double) {
    // Custom setter med validering
    var celsius: Double = celsius
        set(value) {
            require(value >= -273.15) { "Under absolutt nullpunkt!" }
            field = value  // field = backing field
        }

    // Read-only computed property — ingen backing field
    val fahrenheit: Double
        get() = celsius * 9.0 / 5.0 + 32

    val kelvin: Double
        get() = celsius + 273.15

    val beskrivelse: String
        get() = when {
            celsius < 0  -> "frysende"
            celsius < 20 -> "kaldt"
            celsius < 30 -> "behagelig"
            else         -> "varmt"
        }
}

class Bruker(val navn: String) {
    // Setter med logging og normalisering
    var epost: String = ""
        set(value) {
            println("  e-post endret: '$field' -> '$value'")
            field = value.lowercase().trim()
        }

    // Manuell lazy via backing field
    private var _visningsNavn: String? = null
    val visningsNavn: String
        get() {
            if (_visningsNavn == null) {
                _visningsNavn = navn.replaceFirstChar { it.uppercase() }
                println("  beregnet visningsNavn: $_visningsNavn")
            }
            return _visningsNavn!!
        }
}

// Private setter — lesbar utenfra, skrivbar kun innenfra
class Teller {
    var antall: Int = 0
        private set
    fun øk() { antall++ }
    fun nullstill() { antall = 0 }
}

fun main() {
    val t = Temperatur(20.0)
    println("=== Computed properties ===")
    println("  ${t.celsius}°C = ${t.fahrenheit}°F = ${t.kelvin}K")
    println("  beskrivelse: ${t.beskrivelse}")

    t.celsius = 35.0
    println("  ${t.celsius}°C — ${t.beskrivelse}")

    println("\n=== Setter-validering ===")
    try {
        t.celsius = -300.0
    } catch (e: IllegalArgumentException) {
        println("  avvist: ${e.message}")
    }

    println("\n=== Setter med logging + normalisering ===")
    val b = Bruker("alice")
    b.epost = "  ALICE@Example.COM  "
    println("  e-post: ${b.epost}")

    println("\n=== Manuell lazy ===")
    println("  ${b.visningsNavn}")  // beregnes
    println("  ${b.visningsNavn}")  // cached

    println("\n=== Private setter ===")
    val teller = Teller()
    teller.øk(); teller.øk()
    println("  antall: ${teller.antall}")
    // teller.antall = 99  // FEIL — private set

    println("\nMAX_NAVN_LENGDE (const val) = $MAX_NAVN_LENGDE")
}
