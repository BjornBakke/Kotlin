package Intermediate;

//Kotlin has five scope functions in total: let, apply, run, also, and with.

//Let
//Use the let scope function when you want to perform null checks in your code and later perform further actions with the returned object.

//Consider the example:

fun sendNotification(recipientAddress: String): String {
    println("Hei $recipientAddress!")
    return "Varsel sendt!"
}

fun getNextAddress(): String {
    return "sebastian@jetbrains.com"
}

fun main() {
    val address: String? = getNextAddress()
    //sendNotification(address)
    address?.let { sendNotification(it) }
}

