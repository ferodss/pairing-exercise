package io.billie.organisations.service

import io.billie.countries.data.CountryRepository
import io.billie.organisations.data.UnableToFindCountry
import io.billie.organisations.viewmodel.AddressRequest
import io.billie.organisations.viewmodel.AddressResponse
import org.springframework.stereotype.Service
import java.util.*

@Service
class AddressService(val countries: CountryRepository) {

    fun findOrganisationAddress(orgId: UUID): AddressResponse? {
        return null
    }

    fun setOrganisationAddress(orgId: UUID, address: AddressRequest): UUID {
        val countryExists = countries.countryExists(address.countryCode)
        if (!countryExists) {
            throw UnableToFindCountry(address.countryCode)
        }

        return UUID.randomUUID()
    }
}
