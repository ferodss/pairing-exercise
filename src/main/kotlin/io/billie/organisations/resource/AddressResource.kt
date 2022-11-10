package io.billie.organisations.resource

import io.billie.organisations.service.AddressService
import io.billie.organisations.viewmodel.EmptyJsonResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("organisations/{orgId}/address")
class AddressResource(val service: AddressService) {

    @GetMapping
    fun index(@PathVariable("orgId") orgId: UUID): ResponseEntity<Any> {
        val response = service.findOrganisationAddress(orgId) ?: EmptyJsonResponse()

        return ResponseEntity.ok(response)
    }
}
