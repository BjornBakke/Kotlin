package org.example.oop

// public   — synlig overalt (default i Kotlin)
// private  — kun synlig i filen/klassen
// protected — synlig i klassen + subklasser
// internal — synlig i hele modulen (hele prosjektet)

open class Account(private val id: Int, protected var balance: Double) {
    internal val bank = "MyBank"

    // private — kun tilgjengelig inni Account
    private fun generateStatement() = "Konto $id: saldo=$balance"

    // public (default) — tilgjengelig overalt
    fun summary() = generateStatement()

    // protected — tilgjengelig i subklasser
    protected fun addInterest(rate: Double) {
        balance += balance * rate
    }
}

class SavingsAccount(id: Int, balance: Double, private val interestRate: Double) : Account(id, balance) {
    fun applyInterest() {
        addInterest(interestRate)  // OK — protected er tilgjengelig i subklasse
        // println(generateStatement())  // FEIL — private er ikke tilgjengelig
        println("Saldo etter rente: $balance")  // OK — protected
    }
}

// Top-level private — kun synlig i denne filen
private fun secretHelper() = "Jeg er fil-privat"

fun main() {
    val account = Account(1, 1000.0)
    println(account.summary())       // OK — public
    println(account.bank)            // OK — internal (same module)
    // account.balance                // FEIL — protected
    // account.generateStatement()    // FEIL — private

    val savings = SavingsAccount(2, 5000.0, 0.05)
    savings.applyInterest()
    println(savings.summary())

    println(secretHelper())  // OK — same file
}

