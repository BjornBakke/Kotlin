package no.bakkesracingteam.person.backend

import org.springframework.stereotype.Component

@Component
class PersonRegisterEndpoint {
    private val customers = listOf(
        Customer("11105645332", "Bjørn Best"),
        Customer("21105645333", "Ole i Dole"),
        Customer("31105645334", "Lisa Mona"),
        Customer("01105645335", "My Ran"),
    )

    fun searchPerson(term: String): List<Customer> {
        val normalizedTerm = term.trim()
        if (normalizedTerm.isEmpty()) {
            return emptyList()
        }

        return customers.filter { customer ->
            customer.customerName.contains(normalizedTerm, ignoreCase = true) ||
                customer.ssn.orEmpty().contains(normalizedTerm)
        }
    }
}

data class Customer(val ssn: String?, val customerName: String)
