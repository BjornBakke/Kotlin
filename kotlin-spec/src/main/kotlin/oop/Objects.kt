package org.example.oop

// object — singleton, bare en instans
object AppConfig {
    var appName = "MyApp"
    var version = "1.0.0"
    var debug = false

    fun summary() = "$appName v$version (debug=$debug)"
}

// companion object — factory pattern
class User private constructor(val name: String, val role: String) {
    companion object Factory {
        fun admin(name: String) = User(name, "ADMIN")
        fun guest() = User("Guest", "GUEST")
        fun regular(name: String) = User(name, "USER")
    }

    override fun toString() = "$name ($role)"
}

// data object — singleton med auto toString
data object NoConnection : Exception("Ingen internettforbindelse")

sealed class NetworkState {
    data object Disconnected : NetworkState()
    data object Connecting : NetworkState()
    data class Connected(val speed: Int) : NetworkState()
}

fun describeNetwork(state: NetworkState) = when (state) {
    NetworkState.Disconnected -> "Frakoblet"
    NetworkState.Connecting -> "Kobler til..."
    is NetworkState.Connected -> "På nett med ${state.speed} Mbps"
}

fun main() {
    // Singleton
    AppConfig.debug = true
    println(AppConfig.summary())  // MyApp v1.0.0 (debug=true)

    // Factory via companion object
    val admin = User.admin("Alice")
    val guest = User.guest()
    val user = User.regular("Bob")
    println(listOf(admin, guest, user))

    // data object — fin toString()
    println(NetworkState.Disconnected)  // Disconnected (ikke hash-kode)

    val states = listOf(
        NetworkState.Disconnected,
        NetworkState.Connecting,
        NetworkState.Connected(100)
    )
    states.forEach { println(describeNetwork(it)) }
}


