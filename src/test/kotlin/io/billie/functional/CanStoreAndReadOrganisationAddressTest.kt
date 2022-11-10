package io.billie.functional

import com.ninjasquad.springmockk.MockkBean
import io.billie.organisations.viewmodel.Entity
import io.billie.organisations.service.OrganisationService
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.util.*

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = RANDOM_PORT)
class CanStoreAndReadOrganisationAddressTest {

    @LocalServerPort
    private val port = 0

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var organisationService: OrganisationService

    @Test
    fun orgHasNoAddressByDefault() {
        val orgId = UUID.randomUUID()

        every { organisationService.findOrganisationById(orgId) } returns Entity(orgId)

        mockMvc.perform(
            get("/organisations/$orgId/address").contentType(APPLICATION_JSON)
        )
        .andExpect(status().isOk)
        .andExpect(content().contentType(APPLICATION_JSON))
        .andExpect(content().string("{}"))
    }

}
