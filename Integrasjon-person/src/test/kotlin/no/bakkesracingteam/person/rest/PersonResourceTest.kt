package no.bakkesracingteam.person.rest

import no.bakkesracingteam.person.api.Person
import no.bakkesracingteam.person.service.PersonService
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class PersonResourceTest {

    private val personService = mock(PersonService::class.java)
    private val mockMvc = MockMvcBuilders.standaloneSetup(PersonResource(personService)).build()

    @Test
    fun `videresender request-parameter og returnerer personer`() {
        `when`(personService.getPersoner("Bjørn")).thenReturn(
            listOf(Person("11105645332", "Bjørn Best")),
        )

        mockMvc.get("/api/") {
            param("term", "Bjørn")
        }.andExpect {
            status { isOk() }
            jsonPath("$[0].fnr") { value("11105645332") }
            jsonPath("$[0].navn") { value("Bjørn Best") }
        }

        verify(personService).getPersoner("Bjørn")
    }

    @Test
    fun `avviser kall uten søketerm`() {
        mockMvc.get("/api/").andExpect {
            status { isBadRequest() }
        }
    }
}
