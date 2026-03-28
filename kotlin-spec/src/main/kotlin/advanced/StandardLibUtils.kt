package org.example.advanced

// Standard library utility-funksjoner

fun main() {
    // require — kaster IllegalArgumentException hvis false (precondition)
    fun createUser(name: String, age: Int): String {
        require(name.isNotBlank()) { "Navn kan ikke være tomt" }
        require(age in 0..150) { "Alder må være 0-150, fikk $age" }
        return "$name ($age)"
    }

    println(createUser("Alice", 30))
    try {
        createUser("", 25)
    } catch (e: IllegalArgumentException) {
        println("require feilet: ${e.message}")
    }

    // check — kaster IllegalStateException (state validation)
    class Connection {
        var connected = false
        fun connect() { connected = true }
        fun query(sql: String): String {
            check(connected) { "Må være tilkoblet før spørring" }
            return "Resultat av: $sql"
        }
    }

    val conn = Connection()
    try {
        conn.query("SELECT 1")
    } catch (e: IllegalStateException) {
        println("check feilet: ${e.message}")
    }
    conn.connect()
    println(conn.query("SELECT 1"))

    // error — shorthand for throw IllegalStateException
    fun getEnv(key: String): String =
        System.getenv(key) ?: error("Miljøvariabel $key er ikke satt")

    try {
        getEnv("NONEXISTENT_VAR_12345")
    } catch (e: IllegalStateException) {
        println("error(): ${e.message}")
    }

    // TODO — kaster NotImplementedError, markerer ufullstendig kode
    fun futureFeature(): String = TODO("Implementer i v2.0")
    try {
        futureFeature()
    } catch (e: NotImplementedError) {
        println("GJØREMÅL: ${e.message}")
    }

    // Pair og Triple
    val pair = Pair("key", 42)
    val (k, v) = pair
    println("Pair: $k=$v")

    val triple = Triple("Alice", 30, "Oslo")
    val (name, age, city) = triple
    println("Trippel: $name, $age, $city")

    // 'to' infix — lager Pair
    val map = mapOf("a" to 1, "b" to 2)
    println("Map: $map")

    // repeat — kjør kode N ganger
    print("Gjenta: ")
    repeat(5) { i -> print("${i + 1} ") }
    println()

    // takeIf / takeUnless
    val number = 42
    val even = number.takeIf { it % 2 == 0 }     // 42 (fordi det er partall)
    val odd = number.takeUnless { it % 2 == 0 }   // null (fordi det ER partall)
    println("takeIf partall: $even, takeUnless partall: $odd")

    // Kjede med takeIf
    val input = "  hallo  "
    val trimmed = input.trim().takeIf { it.isNotEmpty() }?.uppercase()
    println("takeIf-kjede: $trimmed")  // HELLO
}



