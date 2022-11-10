package io.billie.organisations.viewmodel

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID
import javax.validation.constraints.NotBlank

data class AddressRequest(
    @field:NotBlank val street: String,
    val number: Int,
    @JsonProperty("postal_code") val postalCode: Int,
    @field:NotBlank @JsonProperty("country_code") val countryCode: String,
    @JsonProperty("city_id") val cityId: UUID,
)
