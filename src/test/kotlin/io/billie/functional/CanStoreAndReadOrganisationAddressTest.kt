package io.billie.functional

import com.fasterxml.jackson.databind.ObjectMapper
import io.billie.functional.data.Fixtures
import io.billie.organisations.viewmodel.Entity
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsEqual
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.jdbc.core.JdbcTemplate
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

    @Autowired
    private lateinit var template: JdbcTemplate

    @Autowired
    private lateinit var mapper: ObjectMapper

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

    @Test
    fun cannotStoreAddressWhenCityIdIsMissing() {
        val orgId = UUID.randomUUID()

        mockMvc.perform(
            put("/organisations/$orgId/address")
                .contentType(APPLICATION_JSON)
                .content(Fixtures.addressRequestJsonNoCityId())
        )
            .andExpect(status().isBadRequest)
    }

    @Test
    fun cannotStoreAddressWhenCityIsNotRecognised() {
        val orgId = UUID.randomUUID()

        mockMvc.perform(
            put("/organisations/$orgId/address")
                .contentType(APPLICATION_JSON)
                .content(Fixtures.addressRequestJsonCityIncorrect())
        )
            .andExpect(status().isBadRequest)
    }

    @Test
    fun canStoreOrgAddress() {
        val orgId = UUID.randomUUID()
        val cityId = UUID.randomUUID()

        givenCityExists(cityId, "GB")

        val result = mockMvc.perform(
            put("/organisations/$orgId/address")
                .contentType(APPLICATION_JSON)
                .content(Fixtures.addressRequestJson(cityId))
        )
            .andExpect(status().isOk)
            .andReturn()

        val response = mapper.readValue(result.response.contentAsString, Entity::class.java)

        val address: Map<String, Any> = addressFromDatabase(response.id)
        assertDataMatches(address, Fixtures.bbcAddressFixture(response.id, cityId))
    }

    fun assertDataMatches(reply: Map<String, Any>, assertions: Map<String, Any>) {
        for (key in assertions.keys) {
            MatcherAssert.assertThat(reply[key], IsEqual.equalTo(assertions[key]))
        }
    }

    private fun givenCityExists(cityId: UUID, countryCode: String) =
        template.update(
            "INSERT into organisations_schema.cities (\"id\", \"name\", \"country_code\") VALUES (?, ?, ?);",
            cityId,
            "Random City",
            countryCode
        )

    private fun addressFromDatabase(id: UUID): MutableMap<String, Any> =
        template.queryForMap("select * from organisations_schema.cities where id = ?", id)
}
