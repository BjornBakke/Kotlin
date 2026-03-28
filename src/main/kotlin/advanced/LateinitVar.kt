package org.example.advanced

// lateinit var — deklarerer variabel uten å initialisere den med en gang
// Brukes når initialisering skjer senere (f.eks. dependency injection, setup)
// Kun: var (ikke val), ikke-null type, ikke primitiv (Int, Boolean, etc.)

class Service {
    lateinit var repository: String  // initialiseres senere

    fun setup(repo: String) {
        repository = repo
    }

    fun doWork(): String {
        // Sjekk om initialisert med ::property.isInitialized
        if (!::repository.isInitialized) {
            return "Ikke initialisert!"
        }
        return "Jobber med $repository"
    }
}

class TestFramework {
    lateinit var testName: String
    lateinit var testData: List<String>

    fun beforeEach(name: String) {
        testName = name
        testData = listOf("a", "b", "c")
    }

    fun runTest() {
        println("Kjører '$testName' med data: $testData")
    }
}

fun main() {
    val service = Service()

    // Før initialisering
    println(service.doWork())  // Not initialized!

    // Etter initialisering
    service.setup("PostgreSQL")
    println(service.doWork())  // Working with PostgreSQL

    // Aksess uten init kaster UninitializedPropertyAccessException
    val bad = Service()
    try {
        println(bad.repository)  // krasjer!
    } catch (e: UninitializedPropertyAccessException) {
        println("Feil: ${e.message}")
    }

    // Test-lignende bruk
    val test = TestFramework()
    test.beforeEach("my test")
    test.runTest()
}

