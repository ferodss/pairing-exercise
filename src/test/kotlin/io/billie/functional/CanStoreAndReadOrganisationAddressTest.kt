package io.billie.functional

import io.billie.functional.data.Fixtures
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.util.*

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = RANDOM_PORT)
class CanStoreAndReadOrganisationAddressTest {

    @LocalServerPort
    private val port = 0

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun orgHasNoAddressByDefault() {
        val orgId = UUID.randomUUID()

        mockMvc.perform(
            get("/organisations/$orgId/address").contentType(APPLICATION_JSON)
        )
        .andExpect(status().isOk)
        .andExpect(content().contentType(APPLICATION_JSON))
        .andExpect(content().string("{}"))
    }

    @Test
    fun cannotStoreAddressWhenStreetIsBlank() {
        val orgId = UUID.randomUUID()

        mockMvc.perform(
            put("/organisations/$orgId/address")
                .contentType(APPLICATION_JSON)
                .content(Fixtures.addressRequestJsonStreetBlank())
        )
            .andExpect(status().isBadRequest)
    }

    @Test
    fun cannotStoreAddressWhenStreetIsMissing() {
        val orgId = UUID.randomUUID()

        mockMvc.perform(
            put("/organisations/$orgId/address")
                .contentType(APPLICATION_JSON)
                .content(Fixtures.addressRequestJsonNoStreet())
        )
            .andExpect(status().isBadRequest)
    }

    @Test
    fun cannotStoreAddressWhenCountryCodeIsBlank() {
        val orgId = UUID.randomUUID()

        mockMvc.perform(
            put("/organisations/$orgId/address")
                .contentType(APPLICATION_JSON)
                .content(Fixtures.addressRequestJsonCountryCodeBlank())
        )
            .andExpect(status().isBadRequest)
    }

    @Test
    fun cannotStoreAddressWhenCountryCodeIsMissing() {
        val orgId = UUID.randomUUID()

        mockMvc.perform(
            put("/organisations/$orgId/address")
                .contentType(APPLICATION_JSON)
                .content(Fixtures.addressRequestJsonNoCountryCode())
        )
            .andExpect(status().isBadRequest)
    }

    @Test
    fun cannotStoreAddressWhenCountryCodeIsNotRecognised() {
        val orgId = UUID.randomUUID()

        mockMvc.perform(
            put("/organisations/$orgId/address")
                .contentType(APPLICATION_JSON)
                .content(Fixtures.addressRequestJsonCountryCodeIncorrect())
        )
            .andExpect(status().isBadRequest)
    }

    @Test
    fun cannotStoreAddressWhenCityIdIsBlank() {
        val orgId = UUID.randomUUID()

        mockMvc.perform(
            put("/organisations/$orgId/address")
                .contentType(APPLICATION_JSON)
                .content(Fixtures.addressRequestJsonCityIdBlank())
        )
            .andExpect(status().isBadRequest)
    }

}
