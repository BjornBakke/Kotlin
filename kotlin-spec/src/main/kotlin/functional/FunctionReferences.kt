package org.example.functional

/**
 * FunctionReferences — referanse til funksjon i stedet for lambda
 *
 * Dekker:
 *  - ::funksjon for top-level funksjonsreferanse
 *  - ::Constructor for referanse til constructor
 *  - Bundet referanse: instans::metode
 *  - Klasse::metode (ubundet, tar instans som første arg)
 *
 * Bruk når: du allerede har en funksjon med riktig signatur og vil sende
 * den som parameter uten å pakke inn i { arg -> funksjon(arg) }.
 *
 * NB: ::funksjon og { funksjon(it) } er nesten identiske, men
 *     funksjonsreferanse er litt raskere (ingen ekstra wrapping).
 *
 * Docs: https://kotlinlang.org/docs/reflection.html#function-references
 */

private fun erPartall(n: Int) = n % 2 == 0
private fun kvadrer(n: Int) = n * n
private fun hils(navn: String) = "Hei, $navn!"

data class Profil(val navn: String, val alder: Int) {
    fun presenter() = "Jeg er $navn, $alder år gammel"
}

fun main() {
    val tall = listOf(1, 2, 3, 4, 5, 6)

    println("=== ::funksjon — top-level referanse ===")
    println("  partall: ${tall.filter(::erPartall)}")
    println("  kvadrat: ${tall.map(::kvadrer)}")

    val navn = listOf("Alice", "Bob")
    println("  ${navn.map(::hils)}")

    println("\n=== ::Constructor ===")
    val par = listOf("Alice" to 30, "Bob" to 25)
    val profiler = par.map { (n, a) -> Profil(n, a) }
    println("  $profiler")

    println("\n=== Bundet referanse (instans::metode) ===")
    val profil = Profil("Clara", 28)
    val presenter = profil::presenter
    println("  ${presenter()}")

    println("\n=== Metoder på eksisterende typer ===")
    val ord = listOf("  hallo  ", "  verden  ")
    println("  trim:      ${ord.map(String::trim)}")
    println("  uppercase: ${ord.map(String::uppercase)}")

    println("\n=== Sammenligning: lambda vs referanse ===")
    println("  lambda:    ${tall.filter { erPartall(it) }}")
    println("  referanse: ${tall.filter(::erPartall)}")
}
