package org.example.advanced

/**
 * Strings — raw strings, trimIndent/trimMargin, buildString og regex
 *
 * Dekker:
 *  - Triple-quoted ("raw") strings: bevarer linjeskift og trenger ikke escape
 *  - trimIndent() — fjerner felles innrykk fra alle linjer
 *  - trimMargin(...) — fjerner whitespace frem til marge-tegn (default |)
 *  - String templates inne i raw strings ($x, ${expr}, ${'$'})
 *  - buildString { ... } — bygg strenger effektivt via StringBuilder
 *  - Regex i raw strings slipper dobbel-escaping
 *
 * Bruk når: du trenger flerlinjet tekst (SQL, JSON, HTML, regex) eller
 * skal sette sammen mange fragmenter uten mange `+`-konkateneringer.
 *
 * NB: buildString er ofte raskere enn `s1 + s2 + s3 + ...` fordi hver
 *     `+` på String allokerer en ny String. StringBuilder muterer én buffer.
 *
 * Docs:
 *  - https://kotlinlang.org/docs/strings.html
 *  - https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.text/build-string.html
 */

fun main() {
    println("=== Raw string (triple-quoted) ===")
    // Ingen escape-tegn trengs; linjer bevares som skrevet
    val json = """
        {
            "navn": "Alice",
            "alder": 30,
            "aktiv": true
        }
    """.trimIndent()
    println(json)

    println("\n=== trimMargin med default '|' ===")
    val sql = """
        |SELECT *
        |FROM brukere
        |WHERE aktiv = true
        |ORDER BY navn
    """.trimMargin()
    println(sql)

    println("\n=== trimMargin med egendefinert tegn ===")
    val dikt = """
        #Roser er røde,
        #Fioler er blå,
        #Kotlin er flott,
        #Og det er du også.
    """.trimMargin("#")
    println(dikt)

    println("\n=== trimIndent — fjerner felles innrykk ===")
    val kode = """
        fun hei() {
            println("Hei!")
        }
    """.trimIndent()
    println(kode)

    println("\n=== buildString: effektiv strengbygging ===")
    val rapport = buildString {
        appendLine("=== Rapport ===")
        for (i in 1..3) {
            appendLine("Element $i: ${i * 10} enheter")
        }
        append("Totalt: ${(1..3).sumOf { it * 10 }} enheter")
    }
    println(rapport)

    println("\n=== Templates i raw string ===")
    val navn = "Verden"
    val hilsen = """
        Hei, $navn!
        I dag er det ${java.time.LocalDate.now()}
        Pris: ${'$'}9.99
    """.trimIndent()
    // Tip: ${'$'} er hvordan du får et literal dollartegn i en raw string
    println(hilsen)

    println("\n=== Regex i raw string (ingen dobbel-escape) ===")
    val epostMønster = """[\w.]+@[\w.]+\.\w+""".toRegex()
    val tekst = "Kontakt alice@example.com eller bob@test.org"
    val epostadresser = epostMønster.findAll(tekst).map { it.value }.toList()
    println("  Funnet: $epostadresser")
}
