package org.example.oop

/**
 * Interfaces — kontrakter som klasser kan implementere
 *
 * Dekker:
 *  - Interface med abstrakte og default-metoder
 *  - Abstrakte properties i interface
 *  - Multippel implementering
 *  - Konfliktløsning med super<Type>.metode()
 *  - Generisk interface (Repository<T>)
 *
 * Bruk når: du vil definere hva en type KAN gjøre uten å binde deg til
 * implementasjon. Interfaces støtter multippel arv av oppførsel.
 *
 * NB: Interfaces kan ikke holde tilstand (ingen backing fields) — bare
 *     abstrakte properties eller properties med getter/setter-logikk.
 *
 * Docs: https://kotlinlang.org/docs/interfaces.html
 */

interface Skrivbar {
    val etikett: String                       // abstrakt property
    fun skriv() = println("Skriver ut: $etikett")  // default-implementasjon
}

interface Loggbar {
    fun logg() = println("Logget ved ${System.currentTimeMillis()}")
    fun skriv() = println("Logg-utskrift")    // bevisst navnekollisjon
}

// Multippel implementering — konflikt løses med super<I>
class Rapport(override val etikett: String) : Skrivbar, Loggbar {
    // Må override når flere interfaces har samme default-metode
    override fun skriv() {
        super<Skrivbar>.skriv()  // kall Skrivbar sin versjon
        super<Loggbar>.logg()    // og Loggbar sin logg
    }
}

// Generisk interface
interface Repository<T> {
    fun finnMedId(id: Int): T?
    fun finnAlle(): List<T>
    fun lagre(element: T)
}

data class Produkt(val id: Int, val navn: String, val pris: Double)

class InMemoryProduktRepo : Repository<Produkt> {
    private val lager = mutableMapOf<Int, Produkt>()

    override fun finnMedId(id: Int) = lager[id]
    override fun finnAlle() = lager.values.toList()
    override fun lagre(element: Produkt) { lager[element.id] = element }
}

fun main() {
    val rapport = Rapport("Q4 salg")
    rapport.skriv()
    // Skriver ut: Q4 salg
    // Logget ved ...

    println()

    val repo: Repository<Produkt> = InMemoryProduktRepo()
    repo.lagre(Produkt(1, "Tastatur", 99.90))
    repo.lagre(Produkt(2, "Mus", 49.90))
    println("Alle: ${repo.finnAlle()}")
    println("id=1:  ${repo.finnMedId(1)}")
    println("id=99: ${repo.finnMedId(99)}")
}
