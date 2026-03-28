package org.example.functional

// Closure = lambda som fanger (lukker over) variabler fra omgivende scope
// I Kotlin kan closures fange OG mutere variabler (i motsetning til Java)

fun main() {
    // Closure fanger mutable variabel
    var counter = 0
    val increment = { counter++ }
    val getCount = { counter }

    increment()
    increment()
    increment()
    println("Teller: ${getCount()}")  // 3

    // Counter-factory — returnerer closures som deler tilstand
    fun makeCounter(start: Int = 0): Pair<() -> Int, () -> Unit> {
        var count = start
        val get = { count }
        val inc = { count++ ; Unit }
        return get to inc
    }

    val (getA, incA) = makeCounter()
    val (getB, incB) = makeCounter(100)
    incA(); incA(); incA()
    incB()
    println("A: ${getA()}, B: ${getB()}")  // A: 3, B: 101

    // Closure i collection-operasjoner
    var total = 0
    listOf(10, 20, 30).forEach { total += it }  // lambda fanger 'total'
    println("Total: $total")  // 60

    // Closure med filter — fanger threshold
    val threshold = 15
    val aboveThreshold = listOf(5, 10, 15, 20, 25).filter { it > threshold }
    println("Over $threshold: $aboveThreshold")

    // Closure som event handler
    fun onClick(handler: (String) -> Unit) = handler("button_clicked")

    var clickCount = 0
    onClick { event ->
        clickCount++
        println("Hendelse: $event (klikk #$clickCount)")
    }
    onClick { event ->
        clickCount++
        println("Hendelse: $event (klikk #$clickCount)")
    }

    // Akkumulator-mønster
    fun accumulator(initial: Int): (Int) -> Int {
        var sum = initial
        return { value -> sum += value; sum }
    }

    val acc = accumulator(0)
    println(acc(5))   // 5
    println(acc(10))  // 15
    println(acc(3))   // 18
}

