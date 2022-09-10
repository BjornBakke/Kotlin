package no.bakkesracingteram.person.rest

import no.bakkesracingteram.person.api.Person
import no.bakkesracingteram.person.service.PersonService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/")
class PersonResource(val personService: PersonService) {

    @GetMapping
    fun index(): List<Person> {
        return personService.getPersoner("1")
    }
}