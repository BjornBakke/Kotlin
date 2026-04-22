package org.example.basis

/**
 * Klasser — vanlige klasser og data classes
 *
 * Dekker:
 *  - Primary constructor med val / var-parametere som blir properties
 *  - Default-verdier i constructor
 *  - data class — genererer equals, hashCode, toString, copy, componentN
 *  - copy() for å lage en modifisert kopi
 *  - Destructuring via componentN (se Destructuring.kt for mer)
 *
 * Bruk når: du trenger en verdibærende klasse (DTO, record). Velg data
 * class hvis objektet i hovedsak er en ren dataholder.
 *
 * NB: data class krever minst én parameter i primary constructor og
 *     sammenligner med equals() basert på alle primary-parametere.
 *
 * Docs: https://kotlinlang.org/docs/classes.html
 *       https://kotlinlang.org/docs/data-classes.html
 */

// Vanlig klasse med primary constructor
class Kontakt(val id: Int, var epost: String)

// Klasse med default-verdi og egen property
class Kontakt2(
    val id: Int,
    var epost: String = "eksempel@gmail.com"
) {
    val kategori: String = "jobb"
}

// data class — automatiske equals/hashCode/toString/copy/componentN
data class Bruker(val navn: String, val id: Int)

fun main() {
    val k = Kontakt(1, "mari@gmail.com")
    println("Kontakt: id=${k.id}, epost=${k.epost}")

    val k2 = Kontakt2(2)                  // bruker default-epost
    println("Kontakt2: ${k2.id}, ${k2.epost}, kategori=${k2.kategori}")

    val bruker = Bruker("Alex", 1)
    println("Bruker (toString auto): $bruker")

    val maks = bruker.copy(navn = "Maks") // lag ny versjon med ett endret felt
    println("Etter copy: $maks")

    val (navn, id) = bruker               // destructuring via componentN
    println("Destruktur: navn=$navn, id=$id")

    // Equals er automatisk for data class
    val duplikat = Bruker("Alex", 1)
    println("bruker == duplikat? ${bruker == duplikat}")
}
