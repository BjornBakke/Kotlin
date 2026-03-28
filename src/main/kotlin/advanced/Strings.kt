package org.example.advanced

fun main() {
    // Triple-quoted (raw) strings — bevarer formatering, ingen escape nødvendig
    val json = """
        {
            "name": "Alice",
            "age": 30,
            "active": true
        }
    """.trimIndent()
    println(json)

    // trimMargin — fjerner ledende whitespace opp til margin-tegn (default |)
    val sql = """
        |SELECT *
        |FROM users
        |WHERE active = true
        |ORDER BY name
    """.trimMargin()
    println("\n$sql")

    // Custom margin-tegn
    val poem = """
        #Roser er røde,
        #Fioler er blå,
        #Kotlin er flott,
        #Og det er du også.
    """.trimMargin("#")
    println("\n$poem")

    // trimIndent — fjerner felles innrykk
    val code = """
        fun hello() {
            println("Hei!")
        }
    """.trimIndent()
    println("\n$code")

    // buildString — effektiv strengbygging med StringBuilder
    val report = buildString {
        appendLine("=== Report ===")
        for (i in 1..3) {
            appendLine("Element $i: ${i * 10} enheter")
        }
        append("Totalt: ${(1..3).sumOf { it * 10 }} enheter")
    }
    println("\n$report")

    // Raw strings med string templates
    val name = "Verden"
    val greeting = """
        Hello, $name!
        I dag er det ${java.time.LocalDate.now()}
        Pris: ${'$'}9.99
    """.trimIndent()
    println("\n$greeting")

    // Regex i raw string — ingen dobbel-escaping nødvendig
    val emailPattern = """[\w.]+@[\w.]+\.\w+""".toRegex()
    val text = "Contact alice@example.com or bob@test.org"
    val emails = emailPattern.findAll(text).map { it.value }.toList()
    println("\nE-poster funnet: $emails")
}


