package no.bakkesracingteam.person.service

import no.bakkesracingteam.person.api.Person
import no.bakkesracingteam.person.backend.Customer
import no.bakkesracingteam.person.backend.PersonRegisterEndpoint
import org.springframework.stereotype.Service

@Service
class PersonService(val endpoint: PersonRegisterEndpoint) {

    fun getPersoner(term: String): List<Person> {
        return toPerson(endpoint.searchPerson(term))
    }

    private fun toPerson(customers: List<Customer>): List<Person> {
        return customers.map { Person(it.ssn, it.customerName) }
    }
}
