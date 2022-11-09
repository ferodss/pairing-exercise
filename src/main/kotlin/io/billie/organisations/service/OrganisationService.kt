package io.billie.organisations.service

import io.billie.organisations.data.OrganisationRepository
import io.billie.organisations.viewmodel.Entity
import io.billie.organisations.viewmodel.OrganisationRequest
import io.billie.organisations.viewmodel.OrganisationResponse
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrganisationService(val db: OrganisationRepository) {

    fun findOrganisations(): List<OrganisationResponse> = db.findOrganisations()

    fun createOrganisation(organisation: OrganisationRequest): UUID {
        return db.create(organisation)
    }

    fun findOrganisationById(id: UUID): Entity {
        return Entity(id)
    }

}
