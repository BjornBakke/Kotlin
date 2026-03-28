package org.example.basis

fun main() {
    // Read-only map
    val readOnlyJuiceMenu = mapOf("apple" to 100, "kiwi" to 190, "orange" to 100)
    println(readOnlyJuiceMenu)
    // {apple=100, kiwi=190, orange=100}

    // Mutable map with explicit type declaration
    val juiceMenu: MutableMap<String, Int> = mutableMapOf("apple" to 100, "kiwi" to 190, "orange" to 100)
    println(juiceMenu)
    // {apple=100, kiwi=190, orange=100}

    val juiceMenuLocked: Map<String, Int> = juiceMenu

    println("Verdien av eplejuice er: ${readOnlyJuiceMenu["apple"]}")
    // The value of apple juice is: 100

    // Read-only map
    println("Verdien av ananasjuice er: ${readOnlyJuiceMenu["pineapple"]}")
    // The value of pineapple juice is: null

    juiceMenu["coconut"] = 150 // Add key "coconut" with value 150 to the map
    println(juiceMenu)
    // {apple=100, kiwi=190, orange=100, coconut=150}

    juiceMenu.remove("orange")    // Remove key "orange" from the map
    println(juiceMenu)
    // {apple=100, kiwi=190}


    println("Dette kartet har ${readOnlyJuiceMenu.count()} nøkkel-verdi-par")
    // This map has 3 key-value pairs
    println(readOnlyJuiceMenu.containsKey("kiwi"))
    println(readOnlyJuiceMenu.keys)
    // [apple, kiwi, orange]
    println(readOnlyJuiceMenu.values)
    // [100, 190, 100]
    println("orange" in readOnlyJuiceMenu.keys)
    // true

    // Alternatively, you don't need to use the keys property
    println("orange" in readOnlyJuiceMenu)
    // true

    println(200 in readOnlyJuiceMenu.values)
    // false
    val greenNumbers = listOf(1, 4, 23)
    val redNumbers = listOf(17, 2)
    val tot = redNumbers + greenNumbers
    println(tot.count())

    val SUPPORTED = setOf("HTTP", "HTTPS", "FTP")
    val requested = "smtp"
    val isSupported = requested.uppercase() in SUPPORTED
    println("Støtte for $requested: $isSupported")

    val `number 2 word` = mapOf(1 to "one ", 2 to "two", 3 to "tree")
    val n = 2
    println("$n staves som ${`number 2 word`[n]}")
}

