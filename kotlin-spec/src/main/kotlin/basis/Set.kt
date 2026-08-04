package org.example.basis

/**
 * Set — uordnet samling av unike elementer
 *
 * Dekker:
 *  - setOf(...) gir read-only Set<T>
 *  - mutableSetOf(...) gir MutableSet<T>
 *  - Duplikater fjernes automatisk
 *  - in-operator for medlemssjekk
 *
 * Bruk når: du vil sjekke tilhørighet raskt og ikke trenger rekkefølge
 * (f.eks. "hvilke roller har brukeren", "støttede protokoller").
 *
 * NB: Set er implementert som LinkedHashSet som default — iterasjon skjer
 *     i innsettingsrekkefølge, men kontrakten garanterer ikke det.
 *
 * Docs: https://kotlinlang.org/docs/collections-overview.html#set
 */

fun main() {
    // Read-only set — duplikater fjernes automatisk
    val kunLesFrukt = setOf("eple", "banan", "kirsebær", "kirsebær")
    println("kunLesFrukt: $kunLesFrukt  (duplikater fjernes)")

    // Mutable set
    val frukt: MutableSet<String> = mutableSetOf("eple", "banan", "kirsebær")
    println("Antall elementer: ${kunLesFrukt.count()}")
    println("Inneholder 'banan'? ${"banan" in kunLesFrukt}")

    frukt.add("dragefrukt")
    println("Etter add: $frukt")

    frukt.remove("dragefrukt")
    println("Etter remove: $frukt")

    // Typisk bruk: allowliste
    val støttet = setOf("HTTP", "HTTPS", "FTP")
    val forespurt = "smtp"
    println("Støttes '$forespurt'? ${forespurt.uppercase() in støttet}")
}
