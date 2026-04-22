package org.example.functional

/**
 * TailRecursion — optimert rekursjon som blir til loop
 *
 * Dekker:
 *  - tailrec modifier: kompilatoren optimerer rekursjonen til en løkke
 *  - Krav: rekursivt kall MÅ være siste operasjon (ingen beregning etter)
 *  - Bruk av akkumulator for å gjøre funksjonen tail-recursive
 *
 * Bruk når: du har rekursiv logikk (faktorial, fibonacci, gcd) og vil
 * unngå StackOverflowError for store input.
 *
 * NB: Hvis du ikke får "Recursive call is not a tail call"-advarselen i
 *     IntelliJ etter tailrec, er funksjonen din faktisk tail-recursive.
 *     Omskriv med akkumulator hvis du får advarsel.
 *
 * Docs: https://kotlinlang.org/docs/functions.html#tail-recursive-functions
 */

// Tail-recursive faktorial med akkumulator
tailrec fun faktorial(n: Long, akk: Long = 1): Long = when {
    n <= 1 -> akk
    else   -> faktorial(n - 1, akk * n)  // siste operasjon
}

// Tail-recursive Fibonacci med akkumulator
tailrec fun fibonacci(n: Int, a: Long = 0, b: Long = 1): Long = when (n) {
    0    -> a
    1    -> b
    else -> fibonacci(n - 1, b, a + b)
}

tailrec fun sumTil(n: Long, akk: Long = 0): Long = when {
    n <= 0 -> akk
    else   -> sumTil(n - 1, akk + n)
}

// Euklids algoritme — naturlig tail recursive
tailrec fun gcd(a: Int, b: Int): Int = when {
    b == 0 -> a
    else   -> gcd(b, a % b)
}

// IKKE tail recursive — multiplikasjon skjer ETTER kallet
fun faktorialIkkeTail(n: Long): Long = when {
    n <= 1 -> 1
    else   -> n * faktorialIkkeTail(n - 1)
}

fun main() {
    println("faktorial(10) = ${faktorial(10)}")
    println("faktorial(20) = ${faktorial(20)}")

    println("fibonacci(10) = ${fibonacci(10)}")
    println("fibonacci(50) = ${fibonacci(50)}")

    // Stor verdi — fungerer pga tailrec (ellers stack overflow)
    println("sumTil(1_000_000) = ${sumTil(1_000_000)}")

    println("gcd(48, 18) = ${gcd(48, 18)}")
    println("gcd(100, 75) = ${gcd(100, 75)}")

    println("\nfaktorialIkkeTail(10) = ${faktorialIkkeTail(10)}  (fungerer for små n)")
}
