package org.example.basis

/**
 * Klasser — vanlige klasser med constructor og properties
 *
 * Dekker:
 *  - Primary constructor med val / var-parametere som blir properties
 *  - Default-verdier i constructor
 *  - Egne properties i klassekroppen
 *  - Forskjellen på immutable og mutable felt
 *  - Når en vanlig klasse er et bedre valg enn en data class
 *
 * Bruk når: du vil modellere et objekt med litt mer identitet eller
 * egen tilstand enn en ren dataholder. Se DataClasses.kt for data class.
 *
 * NB: en vanlig klasse får ikke automatisk `copy`, `equals` eller
 *     destructuring. Velg vanlig klasse når det er et poeng at objektet
 *     ikke bare er "en pose data".
 *
 * Docs: https://kotlinlang.org/docs/classes.html
 */

// Vanlig klasse med primary constructor
private class Kontakt(val id: Int, var epost: String)

// Klasse med default-verdi og egen property
private class Kontakt2(
    val id: Int,
    var epost: String = "eksempel@gmail.com"
) {
    val kategori: String = "jobb"
}

fun main() {
    val k = Kontakt(1, "mari@gmail.com")
    println("Kontakt: id=${k.id}, epost=${k.epost}")

    val k2 = Kontakt2(2)                  // bruker default-epost
    println("Kontakt2: ${k2.id}, ${k2.epost}, kategori=${k2.kategori}")

    k.epost = "ny.epost@gmail.com"
    println("Oppdatert kontakt: id=${k.id}, epost=${k.epost}")

    println("Vanlige klasser sammenlignes på referanse som standard:")
    val første = Kontakt(3, "alex@gmail.com")
    val andre = Kontakt(3, "alex@gmail.com")
    println("første == andre? ${første == andre}")

    println("For verdibaserte modeller er DataClasses.kt et bedre valg.")
}
