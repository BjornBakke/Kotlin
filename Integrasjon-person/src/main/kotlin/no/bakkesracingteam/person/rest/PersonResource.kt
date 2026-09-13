package no.bakkesracingteam.person.rest

import no.bakkesracingteam.person.api.Person
import no.bakkesracingteam.person.service.PersonService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/")
class PersonResource(val personService: PersonService) {

    @GetMapping
    fun index(@RequestParam("term") term: String): List<Person> {
        return personService.getPersoner(term)
    }
}