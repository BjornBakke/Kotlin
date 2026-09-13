package no.bakkesracingteam.person.service

import no.bakkesracingteam.person.api.Person
import no.bakkesracingteam.person.backend.Customer
import no.bakkesracingteam.person.backend.PersonRegisterEndpoint
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class PersonServiceTest {

    private val endpoint = mock(PersonRegisterEndpoint::class.java)
    private val service = PersonService(endpoint)

    @Test
    fun `sender søketermen til backend og mapper resultatet`() {
        `when`(endpoint.searchPerson("Lisa")).thenReturn(
            listOf(Customer("31105645334", "Lisa Mona")),
        )

        val result = service.getPersoner("Lisa")

        assertEquals(listOf(Person("31105645334", "Lisa Mona")), result)
        verify(endpoint).searchPerson("Lisa")
    }
}
