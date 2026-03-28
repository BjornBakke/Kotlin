package org.example.oop

// Basic enum
enum class Direction {
    NORTH, SOUTH, EAST, WEST
}

// Enum med properties og metoder
enum class Planet(val mass: Double, val radius: Double) {
    MERCURY(3.303e+23, 2.4397e6),
    VENUS(4.869e+24, 6.0518e6),
    EARTH(5.976e+24, 6.37814e6),
    MARS(6.421e+23, 3.3972e6);

    // Gravitasjonskonstant
    private val G = 6.67300E-11

    fun surfaceGravity() = G * mass / (radius * radius)
    fun surfaceWeight(otherMass: Double) = otherMass * surfaceGravity()
}

// Enum som implementerer interface
interface Describable {
    fun describe(): String
}

enum class Season : Describable {
    SPRING { override fun describe() = "Blomster blomstrer" },
    SUMMER { override fun describe() = "Sun shines" },
    AUTUMN { override fun describe() = "Løv faller" },
    WINTER { override fun describe() = "Snø faller" }
}

fun main() {
    // Iterere over entries
    println("Retninger: ${Direction.entries.joinToString()}")

    // valueOf — string til enum
    val dir = Direction.valueOf("NORTH")
    println("Tolket: $dir")

    // name og ordinal
    println("${dir.name} har indeks ${dir.ordinal}")

    // when med enum
    val advice = when (dir) {
        Direction.NORTH -> "Gå opp"
        Direction.SOUTH -> "Gå ned"
        Direction.EAST -> "Gå høyre"
        Direction.WEST -> "Gå venstre"
    }
    println(advice)

    // Enum med properties
    val earthWeight = 75.0
    println("\nVekt på andre planeter (hvis du veier $earthWeight kg på jorden):")
    Planet.entries.forEach { planet ->
        println("  ${planet.name}: %.2f kg".format(planet.surfaceWeight(earthWeight)))
    }

    // Enum med interface
    println("\nÅrstider:")
    Season.entries.forEach { println("  ${it.name}: ${it.describe()}") }
}


