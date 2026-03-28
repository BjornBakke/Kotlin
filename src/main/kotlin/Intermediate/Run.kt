package Intermediate.run


class Client() {
    var token: String? = null
    fun connect() = println("tilkoblet!")
    fun authenticate() = println("autentisert!")
    fun getData(): String = "Mokkdata"
}

val client: Client = Client().apply {
    token = "asdf"
}

fun main() {
    val result: String = client.run {
        connect()
        authenticate()
        getData()

    }
    println(result)

}


