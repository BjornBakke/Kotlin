package Intermediate

data class User(val id: Int, var email: String)

fun updateEmail(user: User, newEmail: String): User =
    user.apply {
        email = newEmail
    }.also { println("oppatett mne denne $newEmail med id:${it.id}/ ${it.email} ") }

fun main() {
    val user = User(1, "old_email@example.com")
    val updatedUser = updateEmail(user, "new_email@example.com")
    // Updating email for user with ID: 1

    println("Oppdatert bruker: $updatedUser")
    // Updated User: User(id=1, email=new_email@example.com)
}
