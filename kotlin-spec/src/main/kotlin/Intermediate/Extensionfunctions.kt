package org.example.Intermediate


fun String.toLowercaseString () = this.lowercase()




fun main() {
    val arr = arrayOf(10, 20, 30, 40, 50)
    val arr2 = listOf(10, 20, 30, 40, 50)
    println("Det tredje elementet er: " + arr[2])
    println("Hei verden!".toLowercaseString())
}
