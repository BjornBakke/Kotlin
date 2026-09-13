package no.bakkesracingteam.person.backend

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class PersonRegisterEndpointTest {

    private val endpoint = PersonRegisterEndpoint()

    @Test
    fun `søker case-insensitivt i navn`() {
        val result = endpoint.searchPerson("BJØRN")

        assertEquals(listOf("Bjørn Best"), result.map { it.customerName })
    }

    @Test
    fun `søker på deler av fødselsnummer`() {
        val result = endpoint.searchPerson("45334")

        assertEquals(listOf("Lisa Mona"), result.map { it.customerName })
    }

    @Test
    fun `tom søketerm gir tom liste`() {
        assertTrue(endpoint.searchPerson("   ").isEmpty())
    }
}
