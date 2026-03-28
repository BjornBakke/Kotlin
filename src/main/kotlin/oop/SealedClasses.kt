package org.example.oop

// Sealed class — lukket hierarki, alle subtyper kjent ved compile-time
sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Failure(val error: String) : Result<Nothing>()
    data object Loading : Result<Nothing>()
}

// Exhaustive when — kompilatoren sjekker at alle tilfeller er dekket
fun <T> handleResult(result: Result<T>): String = when (result) {
    is Result.Success -> "Fikk: ${result.data}"
    is Result.Failure -> "Feil: ${result.error}"
    Result.Loading -> "Laster..."
    // Ingen else nødvendig — sealed = alle tilfeller kjent
}

// Enda et eksempel: sealed for UI-state
sealed class UiState {
    data object Idle : UiState()
    data object Loading : UiState()
    data class Content(val items: List<String>) : UiState()
    data class Error(val message: String, val retryable: Boolean) : UiState()
}

fun render(state: UiState) {
    when (state) {
        UiState.Idle -> println("Venter på input...")
        UiState.Loading -> println("Lasteindikator aktiv")
        is UiState.Content -> println("Viser ${state.items.size} elementer")
        is UiState.Error -> {
            println("Feil: ${state.message}")
            if (state.retryable) println("  -> Prøv igjen tilgjengelig")
        }
    }
}

fun main() {
    val results = listOf(
        Result.Loading,
        Result.Success(42),
        Result.Failure("Nettverkstidsavbrudd")
    )
    results.forEach { println(handleResult(it)) }
    // Loading...
    // Got: 42
    // Error: Nettverkstidsavbrudd

    println()
    val states = listOf(
        UiState.Idle,
        UiState.Loading,
        UiState.Content(listOf("A", "B", "C")),
        UiState.Error("Serverfeil 500", retryable = true)
    )
    states.forEach { render(it) }
}



