package org.example.advanced

import kotlin.properties.Delegates

// lazy {} — beregnes ved første tilgang, caches deretter
class ExpensiveResource {
    val data: String by lazy {
        println("  Beregner data... (kun én gang)")
        "dyrt resultat"
    }
}

// Delegates.observable — kjører callback ved endring
class UserProfile {
    var name: String by Delegates.observable("unnamed") { _, old, new ->
        println("  Navn endret: '$old' -> '$new'")
    }
}

// Delegates.vetoable — kan avvise endring
class BoundedValue {
    var score: Int by Delegates.vetoable(0) { _, _, new ->
        new in 0..100  // returnerer false = avviser endringen
    }
}

// Custom delegate — implementer getValue/setValue
class LoggingDelegate<T>(private var value: T) {
    operator fun getValue(thisRef: Any?, property: kotlin.reflect.KProperty<*>): T {
        println("  GET ${property.name} = $value")
        return value
    }

    operator fun setValue(thisRef: Any?, property: kotlin.reflect.KProperty<*>, newValue: T) {
        println("  SET ${property.name}: $value -> $newValue")
        value = newValue
    }
}

class Config {
    var host: String by LoggingDelegate("localhost")
    var port: Int by LoggingDelegate(8080)
}

// by map — delegere til Map (nyttig for JSON-lignende data)
class MapUser(map: Map<String, Any?>) {
    val name: String by map
    val age: Int by map
    val email: String by map
}

fun main() {
    // lazy
    println("=== lazy ===")
    val resource = ExpensiveResource()
    println("Før tilgang")
    println(resource.data)  // beregner her
    println(resource.data)  // returnerer cached verdi

    // observable
    println("\n=== observerbar ===")
    val profile = UserProfile()
    profile.name = "Alice"
    profile.name = "Bob"

    // vetoable
    println("\n=== kan-avvises ===")
    val bounded = BoundedValue()
    bounded.score = 85
    println("Poeng: ${bounded.score}")  // 85
    bounded.score = 150  // avvist!
    println("Poeng etter avvist endring: ${bounded.score}")  // fortsatt 85
    bounded.score = -10  // avvist!
    println("Poeng etter avvist endring: ${bounded.score}")  // fortsatt 85

    // Custom delegate
    println("\n=== tilpasset delegat ===")
    val config = Config()
    println("Host: ${config.host}")
    config.host = "api.example.com"
    config.port = 443

    // by map
    println("\n=== via map ===")
    val userMap = mapOf("name" to "Charlie", "age" to 30, "email" to "charlie@test.com")
    val user = MapUser(userMap)
    println("${user.name}, ${user.age}, ${user.email}")
}


