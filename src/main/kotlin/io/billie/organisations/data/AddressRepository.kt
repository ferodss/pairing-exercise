package io.billie.organisations.data

import io.billie.organisations.viewmodel.AddressRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.jdbc.support.KeyHolder
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
class AddressRepository {

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    @Transactional
    fun create(orgId: UUID, address: AddressRequest): UUID {
        val keyHolder: KeyHolder = GeneratedKeyHolder()
        jdbcTemplate.update({ connection ->
            val ps = connection.prepareStatement(
                "INSERT INTO organisations_schema.addresses (" +
                        "organisation_id," +
                        "street," +
                        "number," +
                        "postal_code," +
                        "country_code," +
                        "city_id" +
                        ") VALUES (?, ?, ?, ?, ?, ?)",
                arrayOf("id")
            )
            ps.setObject(1, orgId)
            ps.setString(2, address.street)
            address.number?.let { ps.setInt(3, it) }
            ps.setString(4, address.postalCode)
            ps.setString(5, address.countryCode)
            ps.setObject(6, UUID.fromString(address.cityId))
            ps
        }, keyHolder)

        return keyHolder.getKeyAs(UUID::class.java)!!
    }
}
