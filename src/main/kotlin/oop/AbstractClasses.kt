package org.example.oop

// Abstrakt klasse: kan ha tilstand (properties med verdier) + abstrakte members
abstract class Animal(val name: String) {
    // Abstrakt — må overrides
    abstract fun sound(): String

    // Konkret — arves som den er
    fun describe() = "$name sier ${sound()}"
}

class Dog(name: String) : Animal(name) {
    override fun sound() = "Voff!"
}

class Cat(name: String) : Animal(name) {
    override fun sound() = "Meow!"
}

// Abstrakt klasse med abstrakt property
abstract class Notification {
    abstract val channel: String
    abstract val message: String

    fun send() = println("[$channel] $message")
}

class EmailNotification(override val message: String) : Notification() {
    override val channel = "EMAIL"
}

class SmsNotification(override val message: String) : Notification() {
    override val channel = "SMS"
}

// Forskjell fra interface: abstrakt klasse kan ha constructor og tilstand
// Interface kan IKKE ha constructor eller lagre tilstand

fun main() {
    val animals = listOf(Dog("Rex"), Cat("Whiskers"))
    animals.forEach { println(it.describe()) }
    // Rex says Voff!
    // Whiskers says Meow!

    val notifications = listOf(
        EmailNotification("Velkommen!"),
        SmsNotification("Koden din er 1234")
    )
    notifications.forEach { it.send() }
    // [EMAIL] Welcome!
    // [SMS] Koden din er 1234
}


