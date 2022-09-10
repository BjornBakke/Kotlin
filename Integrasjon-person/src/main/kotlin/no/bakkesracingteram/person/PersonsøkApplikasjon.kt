package no.bakkesracingteram.person

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PersonsøkApplikasjon

fun main(args: Array<String>) {
    runApplication<PersonsøkApplikasjon>(*args)
}

