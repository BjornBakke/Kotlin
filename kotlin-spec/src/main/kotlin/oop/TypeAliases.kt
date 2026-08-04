package org.example.oop

/**
 * TypeAliases — gi eksisterende typer kortere/mer meningsfulle navn
 *
 * Dekker:
 *  - typealias for collections
 *  - typealias for function types
 *  - typealias for generiske typer
 *  - typealias reduserer bare synlig støy — ingen ny type!
 *
 * Bruk når: signaturer eller dokumentasjon blir lett lesbar med et kortere
 * navn (f.eks. "Handler" i stedet for "(Event, Int) -> Unit").
 *
 * NB: typealias er BARE et alias — det lages ingen ny type. Du kan sende
 *     en String til en typealias av String og motsatt uten cast.
 *
 * Docs: https://kotlinlang.org/docs/type-aliases.html
 */

typealias ElevKarakterer = Map<String, List<Int>>
typealias Predikat<T>    = (T) -> Boolean
typealias Transform<T,R> = (T) -> R
typealias Handler        = (String, Int) -> Unit
typealias StringListe    = List<String>
typealias Par2<T>        = Pair<T, T>

fun <T> filtrer(elementer: List<T>, pred: Predikat<T>): List<T> =
    elementer.filter(pred)

fun main() {
    val karakterer: ElevKarakterer = mapOf(
        "Alice" to listOf(90, 85, 92),
        "Bob"   to listOf(78, 88, 75)
    )
    karakterer.forEach { (navn, poeng) ->
        println("$navn: snitt=${poeng.average()}")
    }

    val erPartall: Predikat<Int> = { it % 2 == 0 }
    val tilStort: Transform<String, String> = { it.uppercase() }

    println(filtrer(listOf(1, 2, 3, 4, 5), erPartall))
    println(tilStort("hallo"))

    val klikk: Handler = { hendelse, x -> println("'$hendelse' ved x=$x") }
    klikk("klikk", 42)

    val navn: StringListe = listOf("A", "B", "C")
    val punkt: Par2<Double> = Pair(1.0, 2.0)
    println(navn)
    println(punkt)
}
