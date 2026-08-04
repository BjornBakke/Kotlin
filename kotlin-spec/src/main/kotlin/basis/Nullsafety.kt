package org.example.basis

/**
 * Nullsafety — null-håndtering i Kotlin
 *
 * Dekker:
 *  - Nullable typer med ? (String?)
 *  - Safe call ?. — returnerer null hvis mottaker er null
 *  - Elvis-operator ?: — default-verdi hvis venstre er null
 *  - Not-null assertion !! — kast NPE hvis null (bruk sparsomt)
 *  - Chaining av ?. gjennom flere steg
 *
 * Bruk når: du må jobbe med verdier som kan mangle. Kotlin tvinger deg
 * til å være eksplisitt om null, slik at du ikke får uventede NPE-er.
 *
 * NB: !! er "gi meg den, ellers krasj" — bruk bare når du VET at verdien
 *     er satt, og du vil fange bug-en heller enn å skjule den.
 *
 * Docs: https://kotlinlang.org/docs/null-safety.html
 */

fun beskrivString(kanskje: String?): String {
    // Smart cast: etter null-sjekk kan 'kanskje' brukes som String
    return if (kanskje != null && kanskje.isNotEmpty()) {
        "Streng med lengde ${kanskje.length}"
    } else {
        "Tom eller null streng"
    }
}

// Safe call returnerer nullable resultat
fun lengde(kanskje: String?): Int? = kanskje?.length

data class Ansatt(val navn: String, var lønn: Int)

fun ansattMedId(id: Int): Ansatt? = when (id) {
    1 -> Ansatt("Mari", 20)
    2 -> null
    3 -> Ansatt("John", 21)
    4 -> Ansatt("Ann", 23)
    else -> null
}

// Safe call + elvis: henter lønn, eller -1 om ansatt ikke finnes
fun lønnForId(id: Int): Int = ansattMedId(id)?.lønn ?: -1

fun main() {
    val nullString: String? = null
    val ekteString: String? = "Hei"

    println(beskrivString(nullString))
    println(beskrivString(ekteString))

    // Safe call
    println("Lengde av null: ${lengde(nullString)}")       // null
    println("Lengde av 'Hei': ${lengde(ekteString)}")      // 3

    // Elvis gir default
    println("Lengde med default: ${nullString?.length ?: 0}")

    // Safe-call-chain
    val tekst: String? = null
    val resultat: String = tekst?.uppercase() ?: "UKJENT"
    println("Resultat: $resultat")

    // Realistisk eksempel
    val totalLønn = (1..5).sumOf { lønnForId(it) }
    println("Total lønn (inkludert -1 for manglende): $totalLønn")

    // !! — not-null assertion
    val navn: String? = "Alice"
    val bekreftet: String = navn!!   // vi hevder den ikke er null
    println("Bekreftet: $bekreftet")

    try {
        val ugyldig: String? = null
        @Suppress("UNUSED_VARIABLE")
        val krasj: String = ugyldig!!  // kaster NullPointerException
    } catch (e: NullPointerException) {
        println("!! på null → NPE: ${e.message}")
    }
}
