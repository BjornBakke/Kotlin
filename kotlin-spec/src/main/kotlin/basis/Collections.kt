package org.example.basis

/**
 * Collections — List (ordnet sekvens av elementer)
 *
 * Dekker:
 *  - listOf(...) gir read-only List<T>
 *  - mutableListOf(...) gir MutableList<T> (kan add/remove)
 *  - Referanse-casting: en MutableList kan tilordnes en List-variabel for
 *    å skjule muteringsmuligheten (skrivebeskyttet utad)
 *  - first(), count(), contains (in)
 *
 * Bruk når: du vil holde orden på en sekvens med elementer der posisjon
 * kan ha betydning, eller der du trenger å kjøre transformasjoner (map/filter).
 *
 * NB: Read-only (List) betyr at VISNINGEN er read-only — det underliggende
 *     objektet kan fortsatt være mutable via en annen referanse.
 *
 * Docs: https://kotlinlang.org/docs/collections-overview.html
 */

fun main() {
    // Read-only List
    val kunLes: List<String> = listOf("trekant", "kvadrat", "sirkel")
    println("kunLes:  $kunLes")

    // Mutable List
    val former: MutableList<String> = mutableListOf("trekant", "kvadrat", "sirkel")
    println("former (mutable): $former")

    // Skjul muteringsmuligheten utad ved å gi ut som List<T>
    val formerLest: List<String> = former

    println("Første element: ${kunLes.first()}")
    println("Antall elementer: ${kunLes.count()}")
    println("Inneholder 'sirkel'? ${"sirkel" in kunLes}")

    // Legg til og fjern
    former.add("femkant")
    println("Etter add: $former")

    former.remove("femkant")
    println("Etter remove: $former")

    // formerLest reflekterer endringer på underliggende liste (samme objekt)
    println("formerLest ser også samme endringer: $formerLest")
}
