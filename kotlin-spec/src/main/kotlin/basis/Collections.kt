package org.example.basis

fun main() {
    `Oppgave 3 Collections`().main()
}

class `Oppgave 3 Collections` {
    fun main() {
        // Read only list
        val readOnlyShapes = listOf("triangle", "square", "circle")
        println(readOnlyShapes)
        // [triangle, square, circle]

        // Mutable list with explicit type declaration
        val shapes: MutableList<String> = mutableListOf("triangle", "square", "circle")
        println(shapes)
        // [triangle, square, circle]


        val shapesLocked: List<String> = shapes

        println("Første element i listen er: ${readOnlyShapes.first()}")
        println("Denne listen har ${readOnlyShapes.count()} elementer")
        println("circle" in readOnlyShapes)

        // Add "pentagon" to the list
        shapes.add("pentagon")
        println(shapes)
        // [triangle, square, circle, pentagon]

        // Remove the first "pentagon" from the list
        shapes.remove("pentagon")
        println(shapes)
        // [triangle, square, circle]


    }
}
