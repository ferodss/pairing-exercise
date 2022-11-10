package io.billie.organisations.resource

import io.billie.organisations.data.UnableToFindCountry
import io.billie.organisations.service.AddressService
import io.billie.organisations.viewmodel.AddressRequest
import io.billie.organisations.viewmodel.EmptyJsonResponse
import io.billie.organisations.viewmodel.Entity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("organisations/{orgId}/address")
class AddressResource(val service: AddressService) {

    @GetMapping
    fun index(@PathVariable("orgId") orgId: UUID): ResponseEntity<Any> {
        val response = service.findOrganisationAddress(orgId) ?: EmptyJsonResponse()

        return ResponseEntity.ok(response)
    }

    @PutMapping
    fun put(@PathVariable orgId: UUID, @Valid @RequestBody address: AddressRequest): Entity {
        try {
            val id = service.setOrganisationAddress(orgId, address)

            return Entity(id)
        } catch (e: UnableToFindCountry) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
    }
}
