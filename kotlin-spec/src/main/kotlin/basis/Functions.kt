package org.example.basis

import kotlin.math.PI

/**
 * Functions — funksjonsdeklarering, parametere og returverdier
 *
 * Dekker:
 *  - Top-level funksjoner uten klasse
 *  - Named arguments og default-verdier
 *  - Single-expression funksjoner (= i stedet for { })
 *  - Unit (tilsvarer void) som implisitt returverdi
 *  - Early return med tidlig utgang fra en funksjon
 *
 * Bruk når: du vil definere gjenbrukbar logikk. I Kotlin kan funksjoner
 * stå på top-level — klasse er ikke påkrevd slik det er i Java.
 *
 * NB: Named arguments gjør kall lesbare når du har flere parametere:
 *     intervallISekunder(timer = 2, sekunder = 30).
 *
 * Docs: https://kotlinlang.org/docs/functions.html
 */

// Unit-returnerende funksjon (print har type Unit)
fun skrivMelding(melding: String) {
    println(melding)
}

// Funksjon med default-parameter
fun skrivMeldingMedPrefix(melding: String, prefix: String = "default") {
    println("[$prefix] $melding")
}

// Single-expression funksjon
fun summer(x: Int, y: Int) = x + y

// Funksjon med returverdi og early return
fun registrerBruker(
    brukernavn: String,
    epost: String,
    eksisterendeBrukernavn: Set<String>,
    eksisterendeEposter: Set<String>
): String {
    if (brukernavn in eksisterendeBrukernavn) {
        return "Brukernavn er opptatt. Velg et annet."
    }
    if (epost in eksisterendeEposter) {
        return "E-post er allerede registrert."
    }
    return "Bruker registrert: $brukernavn"
}

// Single-expression med type-slutning
fun sirkelAreal(radius: Int): Double = PI * radius * radius

// Flere default-verdier
fun intervallISekunder(timer: Int = 0, minutter: Int = 0, sekunder: Int = 0) =
    ((timer * 60) + minutter) * 60 + sekunder

fun main() {
    skrivMelding("Hei, verden!")

    // Named arguments lar deg hoppe over parametere og gjør kall lesbare
    skrivMeldingMedPrefix(prefix = "Log", melding = "Sjekker tilkobling")
    skrivMeldingMedPrefix("Uten prefix")  // bruker default

    // Enkel funksjon med returverdi
    println("1 + 2 = ${summer(1, 2)}")

    // Registrering (tester begge return-grener)
    val brukernavn = setOf("john_doe", "jane_smith")
    val eposter = setOf("john@example.com", "jane@example.com")
    println(registrerBruker("john_doe", "ny@example.com", brukernavn, eposter))
    println(registrerBruker("ny_bruker", "ny@example.com", brukernavn, eposter))

    println("Areal av sirkel med r=2: ${sirkelAreal(2)}")

    // Default + named argument-kombinasjoner
    println(intervallISekunder(1, 20, 15))               // 4815
    println(intervallISekunder(minutter = 1, sekunder = 25))  // 85
    println(intervallISekunder(timer = 2))                // 7200
}
