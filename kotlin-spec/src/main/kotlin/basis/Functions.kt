package org.example.basis

import kotlin.math.PI

fun hello() {
    return println("Hei, verden!")
}

fun main() {
    hello()
    printMessageWithPrefix(prefix = "Log", message = "Hei")
    printMessageWithPrefix("Hei")
    val unit = printMessage("Hei")
    println(unit)
    // Hello, world!
    println(sum(1, 2))

    println(registerUser("john_doe", "newjohn@example.com"))
    // Brukernavn er opptatt. Velg et annet brukernavn.
    println(registerUser("new_user", "newuser@example.com"))
    // User registered successfully: new_user
    println(circleArea(2))
    main22()
}

fun printMessageWithPrefix(message: String, prefix: String = "default") {
    println("[$prefix] $message")
}

fun printMessage(message: String) {
    println(message)
    // `return Unit` or `return` is optional
}

fun sum(x: Int, y: Int) = x + y


// A list of registered usernames
val registeredUsernames = mutableListOf("john_doe", "jane_smith")

// A list of registered emails
val registeredEmails = mutableListOf("john@example.com", "jane@example.com")

fun registerUser(username: String, email: String): String {
    // Early return if the username is already taken
    if (username in registeredUsernames) {
        return "Brukernavn er opptatt. Velg et annet brukernavn."
    }

    // Early return if the email is already registered
    if (email in registeredEmails) {
        return "E-post er allerede registrert. Bruk en annen e-postadresse."
    }

    // Proceed with the registration if the username and email are not taken
    registeredUsernames.add(username)
    registeredEmails.add(email)

    return "Bruker registrert: $username"
}

fun circleArea(radius: Int): Double {

    return PI * radius * radius
}

fun intervalInSeconds(hours: Int = 0, minutes: Int = 0, seconds: Int = 0) =
    ((hours * 60) + minutes) * 60 + seconds

fun main22() {
    println(intervalInSeconds(1, 20, 15))
    println(intervalInSeconds(minutes = 1, seconds = 25))
    println(intervalInSeconds(hours = 2))
    println(intervalInSeconds(minutes = 10))
    println(intervalInSeconds(hours = 1, seconds = 1))
}


