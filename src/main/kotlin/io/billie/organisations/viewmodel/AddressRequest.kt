package io.billie.organisations.viewmodel

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotBlank

data class AddressRequest(
    @field:NotBlank val street: String,
    val number: Int?,
    @JsonProperty("postal_code") val postalCode: String?,
    @field:NotBlank @JsonProperty("country_code") val countryCode: String,
    @field:NotBlank @JsonProperty("city_id") val cityId: String,
)
