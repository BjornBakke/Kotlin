package no.bakkesracingteram.person.service

import no.bakkesracingteram.person.api.Person
import no.bakkesracingteram.person.backend.Customer
import no.bakkesracingteram.person.backend.PersonRegisterEndpoint
import org.springframework.stereotype.Service

@Service
class PersonService(val endpoint: PersonRegisterEndpoint) {

    fun getPersoner(term: String): List<Person> {
        return toPerson(endpoint.searchPerson(term));
    }

    private fun toPerson(customers: List<Customer>): List<Person> {
        return customers.map { Person(it.ssn, it.customerName) }
    }
}

