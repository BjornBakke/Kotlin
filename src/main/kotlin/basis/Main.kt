package org.example.basis

interface  NumberWrapper<Int>
interface IntWrapper : NumberWrapper<Int>

class StringWrapper : NumberWrapper<String>{
    constructor()
}

//TIP For å <b>kjøre</b> kode, trykk <shortcut actionId="Run"/> eller
// klikk på <icon src="AllIcons.Actions.Execute"/>-ikonet i margen.
fun main() {
    val name = "Kotlin"
    //TIP Trykk <shortcut actionId="ShowIntentionActions"/> med markøren på den uthevede teksten
    // for å se hvordan IntelliJ IDEA foreslår å fikse det.
    println("Hei, " + name + "!")

    for (i in 1..5) {
        //TIP Trykk <shortcut actionId="Debug"/> for å starte feilsøking. Ett <icon src="AllIcons.Debugger.Db_set_breakpoint"/>-breakpoint er allerede satt
        // men du kan legge til flere ved å trykke <shortcut actionId="ToggleLineBreakpoint"/>.
        println("i = $i")
    }
}

