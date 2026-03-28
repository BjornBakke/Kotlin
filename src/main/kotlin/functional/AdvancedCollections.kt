package org.example.functional

data class Employee(val name: String, val dept: String, val salary: Int)

fun main() {
    val people = listOf("Alice", "Bob", "Charlie", "Anna", "Brian")
    val numbers = listOf(5, 3, 8, 1, 9, 2, 7, 4, 6)
    val employees = listOf(
        Employee("Alice", "Dev", 80000),
        Employee("Bob", "Dev", 75000),
        Employee("Carol", "Sales", 70000),
        Employee("Dave", "Sales", 65000),
        Employee("Eve", "HR", 60000)
    )

    // groupBy — grupper etter nøkkel
    val byDept = employees.groupBy { it.dept }
    byDept.forEach { (dept, emps) -> println("$dept: ${emps.map { it.name }}") }

    // partition — split i to lister basert på predikat
    val (devs, others) = employees.partition { it.dept == "Dev" }
    println("\nUtviklere: ${devs.map { it.name }}, Andre: ${others.map { it.name }}")

    // associate — lag Map fra liste
    val salaryMap = employees.associate { it.name to it.salary }
    println("Lønninger: $salaryMap")

    val byName = employees.associateBy { it.name }
    println("Bob sin avdeling: ${byName["Bob"]?.dept}")

    // zip — kombiner to lister
    val names = listOf("A", "B", "C")
    val scores = listOf(90, 85, 78)
    println("\nZip: ${names.zip(scores)}")
    println("Zip-transform: ${names.zip(scores) { n, s -> "$n=$s" }}")

    // windowed og chunked
    println("\nWindowed(3): ${numbers.windowed(3)}")
    println("Windowed(3, step=2): ${numbers.windowed(3, step = 2)}")
    println("Chunked(3): ${numbers.chunked(3)}")

    // flatMap
    val nested = listOf(listOf(1, 2), listOf(3, 4), listOf(5))
    println("\nFlatMap: ${nested.flatMap { it }}")
    println("Flatten: ${nested.flatten()}")

    // any, all, none
    println("\nNoen lønn > 70k: ${employees.any { it.salary > 70000 }}")
    println("Alle lønninger > 50k: ${employees.all { it.salary > 50000 }}")
    println("Ingen i Marked: ${employees.none { it.dept == "Marketing" }}")

    // find / firstOrNull
    val richest = employees.find { it.salary > 75000 }
    println("Første med lønn > 75k: ${richest?.name}")

    // reduce / fold
    println("\nSum via reduce: ${numbers.reduce { acc, n -> acc + n }}")
    println("Product via fold: ${listOf(1, 2, 3, 4).fold(1) { acc, n -> acc * n }}")

    // take / drop
    println("\nTake 3: ${numbers.take(3)}")
    println("Slipp 3: ${numbers.drop(3)}")
    println("TakeWhile < 5: ${listOf(1, 3, 5, 2, 4).takeWhile { it < 5 }}")

    // distinct / sortedBy
    println("\nDistinct: ${listOf(1, 2, 2, 3, 3, 3).distinct()}")
    println("SortertEtter lønn: ${employees.sortedBy { it.salary }.map { it.name }}")
    println("SortertEtterSynkende lønn: ${employees.sortedByDescending { it.salary }.map { it.name }}")

    // sumOf / maxByOrNull / minByOrNull
    println("\nTotal salary: ${employees.sumOf { it.salary }}")
    println("Høyest lønnet: ${employees.maxByOrNull { it.salary }?.name}")
    println("Lavest lønnet: ${employees.minByOrNull { it.salary }?.name}")
}

