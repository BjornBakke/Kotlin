package org.example.basis

fun describeString(maybeString: String?): String {
    if (maybeString != null && maybeString.length > 0) {
        return "Streng med lengde ${maybeString.length}"
    } else {
        return "Tom eller null streng"
    }
}

fun lengthString(maybeString: String?): Int? = maybeString?.length
fun main() {
    val nullString: String? = null
    println(describeString(nullString))
    // Tom eller null streng
    println(lengthString(nullString))
    println(nullString?.length ?: 0)
    println(nullString?.uppercase()?: "s")
    println((1..5).sumOf { id -> salaryById(id) })

}

data class Employee (val name: String, var salary: Int)

fun employeeById(id: Int) = when(id) {
    1 -> Employee("Mary", 20)
    2 -> null
    3 -> Employee("John", 21)
    4 -> Employee("Ann", 23)
    else -> null
}

fun salaryById(id: Int) = employeeById(id)?.salary ?: -20





