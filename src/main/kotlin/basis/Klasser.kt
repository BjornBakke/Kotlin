package org.example.basis

class Contact(val id: Int, var email: String)
class Contact2(val id: Int, var email: String = "example@gmail.com") {
    val category: String = "work"
}
data class User(val name: String, val id: Int)

fun main() {
    val contact = Contact(1, "mary@gmail.com")
    val user = User("Alex", 1)
    println(user.copy(name = "Max"))
}