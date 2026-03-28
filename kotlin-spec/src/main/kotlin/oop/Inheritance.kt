package org.example.oop

// open = kan arves fra (klasser er final by default i Kotlin)
open class Shape(val name: String) {
    open fun area(): Double = 0.0
    override fun toString() = "$name: area=${area()}"
}

class Circle(val radius: Double) : Shape("Circle") {
    override fun area() = Math.PI * radius * radius
}

class Rectangle(val width: Double, val height: Double) : Shape("Rectangle") {
    override fun area() = width * height
}

class Triangle(val base: Double, val height: Double) : Shape("Triangle") {
    override fun area() = 0.5 * base * height
}

// Abstrakt klasse — kan ikke instansieres direkte
abstract class Vehicle(val brand: String) {
    abstract fun fuelType(): String
    fun description() = "$brand går på ${fuelType()}"
}

class ElectricCar(brand: String) : Vehicle(brand) {
    override fun fuelType() = "electricity"
}

class DieselTruck(brand: String) : Vehicle(brand) {
    override fun fuelType() = "diesel"
}

fun main() {
    // Polymorfisme — forskjellige typer i samme liste
    val shapes: List<Shape> = listOf(
        Circle(5.0),
        Rectangle(4.0, 6.0),
        Triangle(3.0, 8.0)
    )
    shapes.forEach { println(it) }
    // Circle: area=78.53981633974483
    // Rectangle: area=24.0
    // Triangle: area=12.0

    println("\nTotal area: ${shapes.sumOf { it.area() }}")

    // Abstrakt klasse
    val vehicles = listOf(ElectricCar("Tesla"), DieselTruck("Volvo"))
    vehicles.forEach { println(it.description()) }
    // Tesla runs on electricity
    // Volvo runs on diesel
}

