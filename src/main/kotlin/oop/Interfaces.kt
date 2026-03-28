package org.example.oop

// Interface med default-metode og abstrakt property
interface Printable {
    val label: String  // abstrakt property — må implementeres
    fun print() = println("Skriver ut: $label")  // default implementering
}

interface Loggable {
    fun log() = println("Logget ved ${System.currentTimeMillis()}")
    fun print() = println("Logg-utskrift")  // bevisst navnekonflikt med Printable
}

// Multippel implementering — løser konflikt med super<I>
class Report(override val label: String) : Printable, Loggable {
    // Må override print() fordi begge interfaces har den
    override fun print() {
        super<Printable>.print()  // velger Printable sin versjon
        super<Loggable>.log()     // kaller Loggable sin log() i tillegg
    }
}

// Interface med abstrakt metode
interface Repository<T> {
    fun findById(id: Int): T?
    fun findAll(): List<T>
    fun save(item: T)
}

data class Product(val id: Int, val name: String, val price: Double)

class InMemoryProductRepo : Repository<Product> {
    private val store = mutableMapOf<Int, Product>()

    override fun findById(id: Int) = store[id]
    override fun findAll() = store.values.toList()
    override fun save(item: Product) { store[item.id] = item }
}

fun main() {
    val report = Report("Q4 Sales")
    report.print()
    // Printing: Q4 Sales
    // Logged at ...

    // Interface som type
    val repo: Repository<Product> = InMemoryProductRepo()
    repo.save(Product(1, "Keyboard", 99.90))
    repo.save(Product(2, "Mouse", 49.90))
    println(repo.findAll())
    println(repo.findById(1))
    println(repo.findById(99))  // null
}

