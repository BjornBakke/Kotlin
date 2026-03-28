package org.example.functional

// tailrec — kompilatoren optimerer til loop, unngår stack overflow
// Krav: rekursivt kall må være SISTE operasjon (ingen beregning etter kallet)

tailrec fun factorial(n: Long, acc: Long = 1): Long = when {
    n <= 1 -> acc
    else -> factorial(n - 1, acc * n)  // tail position — siste operasjon
}

// Fibonacci med tailrec
tailrec fun fibonacci(n: Int, a: Long = 0, b: Long = 1): Long = when (n) {
    0 -> a
    1 -> b
    else -> fibonacci(n - 1, b, a + b)
}

// Uten tailrec ville dette krasje med StackOverflowError for store n
tailrec fun sumTo(n: Long, acc: Long = 0): Long = when {
    n <= 0 -> acc
    else -> sumTo(n - 1, acc + n)
}

// GCD (Euclids algoritme) — naturlig tail recursive
tailrec fun gcd(a: Int, b: Int): Int = when {
    b == 0 -> a
    else -> gcd(b, a % b)
}

// IKKE tailrec — beregning etter rekursivt kall
fun factorialNonTail(n: Long): Long = when {
    n <= 1 -> 1
    else -> n * factorialNonTail(n - 1)  // multiplikasjon ETTER kall = ikke tail
}

fun main() {
    println("factorial(10) = ${factorial(10)}")       // 3628800
    println("factorial(20) = ${factorial(20)}")       // 2432902008176640000

    println("fibonacci(10) = ${fibonacci(10)}")       // 55
    println("fibonacci(50) = ${fibonacci(50)}")       // 12586269025

    // Stor verdi — fungerer pga tailrec (ellers stack overflow)
    println("sumTo(1_000_000) = ${sumTo(1_000_000)}") // 500000500000

    println("gcd(48, 18) = ${gcd(48, 18)}")           // 6
    println("gcd(100, 75) = ${gcd(100, 75)}")         // 25
}
