package org.example.oop

/**
 * Objects — object, companion object, data object
 *
 * Dekker:
 *  - object: singleton (én instans, lazy-initialisert)
 *  - companion object: "statiske" medlemmer i en klasse
 *  - data object: singleton med auto-toString/equals
 *  - Factory-mønster via companion
 *
 * Bruk når:
 *  - Du trenger en singleton (app-config, global registry)
 *  - Du trenger "statiske" hjelpere eller factory-metoder på en klasse
 *  - Du har sealed-hierarki og en konstant-variant uten data
 *
 * NB: Kotlin har ingen 'static' nøkkelord — companion object fyller den
 *     rollen. Access fra Java: ClassName.Companion.method() eller bruk
 *     @JvmStatic på metoden.
 *
 * Docs: https://kotlinlang.org/docs/object-declarations.html
 */

// object — singleton
object AppKonfig {
    var appNavn = "MinApp"
    var versjon = "1.0.0"
    var debug = false
    fun oppsummering() = "$appNavn v$versjon (debug=$debug)"
}

// companion object — factory-mønster
class Bruker private constructor(val navn: String, val rolle: String) {
    companion object Factory {
        fun admin(navn: String) = Bruker(navn, "ADMIN")
        fun gjest() = Bruker("Gjest", "GJEST")
        fun vanlig(navn: String) = Bruker(navn, "BRUKER")
    }

    override fun toString() = "$navn ($rolle)"
}

// data object — singleton med fin toString
sealed class NettverkTilstand {
    data object Frakoblet : NettverkTilstand()
    data object KoblerTil : NettverkTilstand()
    data class Tilkoblet(val hastighet: Int) : NettverkTilstand()
}

fun beskrivNettverk(t: NettverkTilstand) = when (t) {
    NettverkTilstand.Frakoblet -> "Frakoblet"
    NettverkTilstand.KoblerTil -> "Kobler til…"
    is NettverkTilstand.Tilkoblet -> "På nett med ${t.hastighet} Mbps"
}

fun main() {
    // Singleton
    AppKonfig.debug = true
    println(AppKonfig.oppsummering())

    // Factory via companion
    val admin  = Bruker.admin("Alice")
    val gjest  = Bruker.gjest()
    val vanlig = Bruker.vanlig("Bob")
    println(listOf(admin, gjest, vanlig))

    // data object — fin toString i stedet for hash
    println(NettverkTilstand.Frakoblet)

    val tilstander = listOf(
        NettverkTilstand.Frakoblet,
        NettverkTilstand.KoblerTil,
        NettverkTilstand.Tilkoblet(100)
    )
    tilstander.forEach { println(beskrivNettverk(it)) }
}
