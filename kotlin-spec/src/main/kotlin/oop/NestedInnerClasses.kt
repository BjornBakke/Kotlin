package org.example.oop

/**
 * NestedInnerClasses — nøstede og indre klasser
 *
 * Dekker:
 *  - Nested class: ingen referanse til ytre instans
 *  - Inner class (med 'inner'): har referanse til ytre instans (this@Outer)
 *  - Builder-mønster som nøstet klasse
 *
 * Bruk når: en klasse bare gir mening i kontekst av en annen klasse (f.eks.
 * en intern Builder, Node i et linked list, eller en Comparator).
 *
 * NB: Som default er nøstede klasser i Kotlin statiske (motsatt av Java).
 *     Du må eksplisitt skrive 'inner' hvis klassen skal ha tilgang til
 *     ytre instans.
 *
 * Docs: https://kotlinlang.org/docs/nested-classes.html
 */

class Ytre(private val navn: String) {
    private val hemmelighet = "ytre-hemmelighet"

    // Nested — ingen tilgang til Ytre-instansen
    class Nøstet {
        fun hils() = "Hei fra Nøstet"
        // fun feil() = navn  // FEIL — har ingen referanse til Ytre
    }

    // Inner — har tilgang til Ytre-instansen via this@Ytre
    inner class Indre {
        fun hils() = "Hei fra Indre, eier=$navn"
        fun hentHemmelighet() = hemmelighet
        fun ytreRef() = this@Ytre
    }

    fun lagIndre() = Indre()
}

// Praktisk eksempel: Builder-mønster
class Pizza(val størrelse: String, val topping: List<String>, val ekstraOst: Boolean) {
    class Builder(private val størrelse: String) {
        private val topping = mutableListOf<String>()
        private var ekstraOst = false

        fun leggTilTopping(t: String) = apply { topping.add(t) }
        fun medEkstraOst() = apply { ekstraOst = true }
        fun bygg() = Pizza(størrelse, topping.toList(), ekstraOst)
    }

    override fun toString() =
        "$størrelse pizza med $topping${if (ekstraOst) " + ekstra ost" else ""}"
}

fun main() {
    // Nested — ikke behov for Ytre-instans
    val n = Ytre.Nøstet()
    println(n.hils())

    // Inner — trenger Ytre-instans
    val ytre = Ytre("Alice")
    val indre = ytre.Indre()
    println(indre.hils())
    println("Hemmelighet: ${indre.hentHemmelighet()}")

    // Via factory-metode
    val indre2 = ytre.lagIndre()
    println(indre2.hils())

    // Builder-mønster
    val pizza = Pizza.Builder("Stor")
        .leggTilTopping("Pepperoni")
        .leggTilTopping("Sopp")
        .medEkstraOst()
        .bygg()
    println(pizza)
}
