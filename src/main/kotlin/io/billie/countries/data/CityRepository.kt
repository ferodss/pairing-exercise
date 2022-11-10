package io.billie.countries.data

import io.billie.countries.model.CityResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.ResultSetExtractor
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.sql.ResultSet
import java.util.*

@Repository
class CityRepository {


    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    @Transactional(readOnly = true)
    fun findByCountryCode(countryCode: String): List<CityResponse> {
        return jdbcTemplate.query(
            "select id, name, country_code from organisations_schema.cities where country_code = ?",
            cityResponseMapper(),
            countryCode
        )
    }

    fun cityExistsInCountry(cityId: UUID, countryCode: String): Boolean {
        val reply: Int? = jdbcTemplate.query(
            "select count(country_code) from organisations_schema.cities c WHERE id = ? AND country_code = ?",
            ResultSetExtractor {
                it.next()
                it.getInt(1)
            },
            cityId,
            countryCode
        )
        return (reply != null) && (reply > 0)
    }

    private fun cityResponseMapper() = RowMapper<CityResponse> { it: ResultSet, _: Int ->
        CityResponse(
            it.getObject("id", UUID::class.java),
            it.getString("name"),
            it.getString("country_code")
        )
    }
}
