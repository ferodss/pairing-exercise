package io.billie.organisations.service

import io.billie.countries.data.CityRepository
import io.billie.countries.data.CountryRepository
import io.billie.organisations.data.AddressRepository
import io.billie.organisations.data.UnableToFindCity
import io.billie.organisations.data.UnableToFindCountry
import io.billie.organisations.viewmodel.AddressRequest
import io.billie.organisations.viewmodel.AddressResponse
import org.springframework.stereotype.Service
import java.util.*

@Service
class AddressService(
    val db: AddressRepository,
    val countries: CountryRepository,
    val cities: CityRepository
) {

    fun findOrganisationAddress(orgId: UUID): AddressResponse? {
        return null
    }

    fun setOrganisationAddress(orgId: UUID, address: AddressRequest): UUID {
        val countryExists = countries.countryExists(address.countryCode)
        if (!countryExists) {
            throw UnableToFindCountry(address.countryCode)
        }

        val cityId = UUID.fromString(address.cityId)
        val cityExistsInCountry = cities.cityExistsInCountry(cityId, address.countryCode)
        if (!cityExistsInCountry) {
            throw UnableToFindCity(address.cityId, address.countryCode)
        }

        return db.create(orgId, address)
    }
}
