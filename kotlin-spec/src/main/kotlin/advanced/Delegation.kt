package org.example.advanced

import kotlin.properties.Delegates

/**
 * Delegation — property delegation i Kotlin
 *
 * Dekker:
 *  - by lazy { } — beregn én gang, cache deretter
 *  - Delegates.observable — callback ved hver endring
 *  - Delegates.vetoable — callback som kan avvise endring
 *  - Egen delegate — implementer getValue/setValue
 *  - by map — lesing/skriving via en Map
 *
 * Bruk når: du vil gjenbruke property-logikk uten å kopiere. Vanlige
 * mønstre: lazy init, logging, validering, binding til ekstern kilde.
 *
 * NB: lazy er trådsikker som default (SYNCHRONIZED). For single-threaded
 *     bruk: by lazy(LazyThreadSafetyMode.NONE) { ... } er litt raskere.
 *
 * Docs: https://kotlinlang.org/docs/delegated-properties.html
 */

// lazy: beregnes ved første tilgang, caches
class DyrRessurs {
    val data: String by lazy {
        println("  beregner data… (kun én gang)")
        "dyrt resultat"
    }
}

// observable: kjører callback ved hver endring
class BrukerProfil {
    var navn: String by Delegates.observable("uten navn") { _, gammel, ny ->
        println("  navn endret: '$gammel' -> '$ny'")
    }
}

// vetoable: kan avvise endring
class AvgrensetVerdi {
    var poeng: Int by Delegates.vetoable(0) { _, _, ny ->
        ny in 0..100  // false = avvises
    }
}

// Custom delegate med getValue/setValue
class LoggingDelegate<T>(private var verdi: T) {
    operator fun getValue(ref: Any?, prop: kotlin.reflect.KProperty<*>): T {
        println("  GET ${prop.name} = $verdi")
        return verdi
    }
    operator fun setValue(ref: Any?, prop: kotlin.reflect.KProperty<*>, ny: T) {
        println("  SET ${prop.name}: $verdi -> $ny")
        verdi = ny
    }
}

class Konfig {
    var host: String by LoggingDelegate("localhost")
    var port: Int by LoggingDelegate(8080)
}

// by map — delegere til en Map (praktisk for JSON-lignende data)
class MapBruker(kart: Map<String, Any?>) {
    val navn: String  by kart
    val alder: Int    by kart
    val epost: String by kart
}

fun main() {
    println("=== lazy ===")
    val r = DyrRessurs()
    println("  før tilgang")
    println("  ${r.data}")  // beregner her
    println("  ${r.data}")  // cached

    println("\n=== observable ===")
    val p = BrukerProfil()
    p.navn = "Alice"
    p.navn = "Bob"

    println("\n=== vetoable ===")
    val a = AvgrensetVerdi()
    a.poeng = 85
    println("  poeng: ${a.poeng}")
    a.poeng = 150  // avvises
    println("  etter avvist 150: ${a.poeng}")
    a.poeng = -10  // avvises
    println("  etter avvist -10: ${a.poeng}")

    println("\n=== custom delegate ===")
    val k = Konfig()
    println("  host: ${k.host}")
    k.host = "api.example.com"
    k.port = 443

    println("\n=== by map ===")
    val kart = mapOf("navn" to "Clara", "alder" to 30, "epost" to "clara@test.no")
    val mb = MapBruker(kart)
    println("  ${mb.navn}, ${mb.alder}, ${mb.epost}")
}
