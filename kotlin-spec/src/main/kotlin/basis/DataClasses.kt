package org.example.basis

/**
 * DataClasses - verdibærende modeller med nyttige standardmetoder
 *
 * Dekker:
 *  - `data class` for rene dataobjekter
 *  - automatisk `toString`, `equals` og `hashCode`
 *  - `copy(...)` for å lage en ny variant
 *  - destructuring av verdier i riktig rekkefølge
 *
 * Bruk når: objektet først og fremst representerer data, ikke kompleks
 * oppførsel eller mye intern tilstand.
 *
 * NB: bare felter i primary constructor er med i `equals`, `copy` og
 *     destructuring. Vanlige properties inne i klassen blir ikke tatt med.
 *
 * Docs: https://kotlinlang.org/docs/data-classes.html
 */

private data class Student(val navn: String, val kull: Int, val aktiv: Boolean = true)

fun main() {
    val ada = Student("Ada", 2026)
    println("Student: $ada")

    val pause = ada.copy(aktiv = false)
    println("Kopi med endring: $pause")

    val (navn, kull, aktiv) = ada
    println("Destrukturert: navn=$navn, kull=$kull, aktiv=$aktiv")

    println("Likhet: ${ada == Student("Ada", 2026)}")
}
