package org.example.oop

/**
 * SealedClasses — lukket hierarki av kjente subtyper
 *
 * Dekker:
 *  - sealed class / sealed interface — alle subtyper må deklareres i
 *    samme modul (samme pakke for sealed class før Kotlin 1.5)
 *  - Exhaustive when: kompilatoren sjekker at alle grener er dekket
 *  - Typisk mønster: Result<T> (Success/Failure/Loading) eller UiState
 *
 * Bruk når: du har en fast mengde varianter som alle representerer "ett
 * av disse". Gir typesikker pattern matching uten 'else'.
 *
 * NB: sealed + data class-kombinasjon er det Kotlin-idiomatiske
 *     alternativet til abstrakt klasse + mange subklasser.
 *
 * Docs: https://kotlinlang.org/docs/sealed-classes.html
 */

sealed class Resultat<out T> {
    data class Suksess<T>(val data: T) : Resultat<T>()
    data class Feil(val melding: String) : Resultat<Nothing>()
    data object Laster : Resultat<Nothing>()
}

// Exhaustive when — ingen else nødvendig fordi sealed = alle kjent
fun <T> håndterResultat(r: Resultat<T>): String = when (r) {
    is Resultat.Suksess -> "Fikk: ${r.data}"
    is Resultat.Feil    -> "Feil: ${r.melding}"
    Resultat.Laster     -> "Laster..."
}

sealed class UiTilstand {
    data object Inaktiv : UiTilstand()
    data object Laster : UiTilstand()
    data class Innhold(val elementer: List<String>) : UiTilstand()
    data class Feilet(val melding: String, val kanPrøveIgjen: Boolean) : UiTilstand()
}

fun render(t: UiTilstand) {
    when (t) {
        UiTilstand.Inaktiv -> println("Venter på input…")
        UiTilstand.Laster  -> println("Laste-indikator aktiv")
        is UiTilstand.Innhold -> println("Viser ${t.elementer.size} elementer")
        is UiTilstand.Feilet  -> {
            println("Feil: ${t.melding}")
            if (t.kanPrøveIgjen) println("  → kan prøve igjen")
        }
    }
}

fun main() {
    val resultater = listOf(
        Resultat.Laster,
        Resultat.Suksess(42),
        Resultat.Feil("Nettverkstidsavbrudd")
    )
    resultater.forEach { println(håndterResultat(it)) }

    println()

    val tilstander = listOf(
        UiTilstand.Inaktiv,
        UiTilstand.Laster,
        UiTilstand.Innhold(listOf("A", "B", "C")),
        UiTilstand.Feilet("Serverfeil 500", kanPrøveIgjen = true)
    )
    tilstander.forEach { render(it) }
}
