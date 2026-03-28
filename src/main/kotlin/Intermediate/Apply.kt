package Intermediate

class Client() {
    var token: String? = null
    fun connect() = println("tilkoblet!")
    fun authenticate() = println("autentisert!")
    fun getData(): String = "Mokkdata"
}

val client = Client().apply {
    token = "asdf"
    connect()
    authenticate()
}

fun main() {
    val res= client.getData()
    println(res)

}


