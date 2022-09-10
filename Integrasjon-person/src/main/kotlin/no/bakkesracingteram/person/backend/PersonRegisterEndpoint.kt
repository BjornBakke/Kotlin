package no.bakkesracingteram.person.backend

import org.springframework.stereotype.Component

@Component
class PersonRegisterEndpoint {
    fun searchPerson(customerNumber: String): List<Customer> {
        return listOf(
            Customer("11105645332", "Bjørn Best"),
            Customer("21105645333", "Ole i Dole"),
            Customer("31105645334", "Lisa Mona"),
            Customer("01105645335", "My Ran"),
        )
    }
}

data class Customer(val ssn: String?, val customerName: String)
