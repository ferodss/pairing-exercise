package io.billie.functional.data

import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

object Fixtures {

    fun orgRequestJsonNameBlank(): String {
        return "{\n" +
                "  \"name\": \"\",\n" +
                "  \"date_founded\": \"18/10/1922\",\n" +
                "  \"country_code\": \"GB\",\n" +
                "  \"vat_number\": \"333289454\",\n" +
                "  \"registration_number\": \"3686147\",\n" +
                "  \"legal_entity_type\": \"NONPROFIT_ORGANIZATION\",\n" +
                "  \"contact_details\": {\n" +
                "    \"phone_number\": \"+443700100222\",\n" +
                "    \"fax\": \"\",\n" +
                "    \"email\": \"yourquestions@bbc.co.uk\"\n" +
                "  }\n" +
                "}"
    }

    fun orgRequestJsonNoName(): String {
        return "{\n" +
                "  \"date_founded\": \"18/10/1922\",\n" +
                "  \"country_code\": \"GB\",\n" +
                "  \"vat_number\": \"333289454\",\n" +
                "  \"registration_number\": \"3686147\",\n" +
                "  \"legal_entity_type\": \"NONPROFIT_ORGANIZATION\",\n" +
                "  \"contact_details\": {\n" +
                "    \"phone_number\": \"+443700100222\",\n" +
                "    \"fax\": \"\",\n" +
                "    \"email\": \"yourquestions@bbc.co.uk\"\n" +
                "  }\n" +
                "}"
    }

    fun orgRequestJsonNoLegalEntityType(): String {
        return "{\n" +
                "  \"name\": \"BBC\",\n" +
                "  \"date_founded\": \"18/10/1922\",\n" +
                "  \"country_code\": \"GB\",\n" +
                "  \"vat_number\": \"333289454\",\n" +
                "  \"registration_number\": \"3686147\",\n" +
                "  \"contact_details\": {\n" +
                "    \"phone_number\": \"+443700100222\",\n" +
                "    \"fax\": \"\",\n" +
                "    \"email\": \"yourquestions@bbc.co.uk\"\n" +
                "  }\n" +
                "}"
    }

    fun orgRequestJsonNoContactDetails(): String {
        return "{\n" +
                "  \"name\": \"BBC\",\n" +
                "  \"date_founded\": \"18/10/1922\",\n" +
                "  \"country_code\": \"GB\",\n" +
                "  \"vat_number\": \"333289454\",\n" +
                "  \"registration_number\": \"3686147\",\n" +
                "  \"legal_entity_type\": \"NONPROFIT_ORGANIZATION\"\n" +
                "}"
    }

    fun orgRequestJson(): String {
        return "{\n" +
                "  \"name\": \"BBC\",\n" +
                "  \"date_founded\": \"18/10/1922\",\n" +
                "  \"country_code\": \"GB\",\n" +
                "  \"vat_number\": \"333289454\",\n" +
                "  \"registration_number\": \"3686147\",\n" +
                "  \"legal_entity_type\": \"NONPROFIT_ORGANIZATION\",\n" +
                "  \"contact_details\": {\n" +
                "    \"phone_number\": \"+443700100222\",\n" +
                "    \"fax\": \"\",\n" +
                "    \"email\": \"yourquestions@bbc.co.uk\"\n" +
                "  }\n" +
                "}"
    }

    fun orgRequestJsonCountryCodeBlank(): String {
        return "{\n" +
                "  \"name\": \"BBC\",\n" +
                "  \"date_founded\": \"18/10/1922\",\n" +
                "  \"country_code\": \"\",\n" +
                "  \"vat_number\": \"333289454\",\n" +
                "  \"registration_number\": \"3686147\",\n" +
                "  \"legal_entity_type\": \"NONPROFIT_ORGANIZATION\",\n" +
                "  \"contact_details\": {\n" +
                "    \"phone_number\": \"+443700100222\",\n" +
                "    \"fax\": \"\",\n" +
                "    \"email\": \"yourquestions@bbc.co.uk\"\n" +
                "  }\n" +
                "}"
    }

    fun orgRequestJsonNoCountryCode(): String {
        return "{\n" +
                "  \"name\": \"BBC\",\n" +
                "  \"date_founded\": \"18/10/1922\",\n" +
                "  \"vat_number\": \"333289454\",\n" +
                "  \"registration_number\": \"3686147\",\n" +
                "  \"legal_entity_type\": \"NONPROFIT_ORGANIZATION\",\n" +
                "  \"contact_details\": {\n" +
                "    \"phone_number\": \"+443700100222\",\n" +
                "    \"fax\": \"\",\n" +
                "    \"email\": \"yourquestions@bbc.co.uk\"\n" +
                "  }\n" +
                "}"
    }

    fun orgRequestJsonCountryCodeIncorrect(): String {
        return "{\n" +
                "  \"name\": \"BBC\",\n" +
                "  \"date_founded\": \"18/10/1922\",\n" +
                "  \"country_code\": \"XX\",\n" +
                "  \"vat_number\": \"333289454\",\n" +
                "  \"registration_number\": \"3686147\",\n" +
                "  \"legal_entity_type\": \"NONPROFIT_ORGANIZATION\",\n" +
                "  \"contact_details\": {\n" +
                "    \"phone_number\": \"+443700100222\",\n" +
                "    \"fax\": \"\",\n" +
                "    \"email\": \"yourquestions@bbc.co.uk\"\n" +
                "  }\n" +
                "}"
    }

    fun bbcFixture(id: UUID): Map<String, Any> {
        val data = HashMap<String, Any>()
        data["id"] = id
        data["name"] = "BBC"
        data["date_founded"] = SimpleDateFormat("yyyy-MM-dd").parse("1922-10-18")
        data["country_code"] = "GB"
        data["vat_number"] = "333289454"
        data["registration_number"] = "3686147"
        data["legal_entity_type"] = "NONPROFIT_ORGANIZATION"
        return data
    }

    fun bbcContactFixture(id: UUID): Map<String, Any> {
        val data = HashMap<String, Any>()
        data["id"] = id
        data["phone_number"] = "+443700100222"
        data["fax"] = ""
        data["email"] = "yourquestions@bbc.co.uk"
        return data
    }


    fun addressRequestJsonStreetBlank(): String {
        return "{\n" +
                "  \"street\": \"\",\n" +
                "  \"number\": \"1234\",\n" +
                "  \"postal_code\": \"10558\",\n" +
                "  \"country_code\": \"GB\",\n" +
                "  \"city_id\": \"ca1a7992-613c-11ed-9b6a-0242ac120002\"\n" +
                "}"
    }

    fun addressRequestJsonNoStreet(): String {
        return "{\n" +
                "  \"number\": \"1234\",\n" +
                "  \"postal_code\": \"10558\",\n" +
                "  \"country_code\": \"GB\",\n" +
                "  \"city_id\": \"ca1a7992-613c-11ed-9b6a-0242ac120002\"\n" +
                "}"
    }

    fun addressRequestJsonCountryCodeBlank(): String {
        return "{\n" +
                "  \"street\": \"Somestrasse\",\n" +
                "  \"number\": \"1234\",\n" +
                "  \"postal_code\": \"10558\",\n" +
                "  \"country_code\": \"\",\n" +
                "  \"city_id\": \"ca1a7992-613c-11ed-9b6a-0242ac120002\"\n" +
                "}"
    }

    fun addressRequestJsonNoCountryCode(): String {
        return "{\n" +
                "  \"street\": \"Somestrasse\",\n" +
                "  \"number\": \"1234\",\n" +
                "  \"postal_code\": \"10558\",\n" +
                "  \"city_id\": \"ca1a7992-613c-11ed-9b6a-0242ac120002\"\n" +
                "}"
    }

    fun addressRequestJsonCountryCodeIncorrect(): String {
        return "{\n" +
                "  \"street\": \"Somestrasse\",\n" +
                "  \"number\": \"1234\",\n" +
                "  \"postal_code\": \"10558\",\n" +
                "  \"country_code\": \"AA\",\n" +
                "  \"city_id\": \"ca1a7992-613c-11ed-9b6a-0242ac120002\"\n" +
                "}"
    }

    fun addressRequestJsonCityIdBlank(): String {
        return "{\n" +
                "  \"street\": \"Somestrasse\",\n" +
                "  \"number\": \"1234\",\n" +
                "  \"postal_code\": \"10558\",\n" +
                "  \"country_code\": \"GB\",\n" +
                "  \"city_id\": \"\"\n" +
                "}"
    }

    fun addressRequestJsonNoCityId(): String {
        return "{\n" +
                "  \"street\": \"Somestrasse\",\n" +
                "  \"number\": \"1234\",\n" +
                "  \"postal_code\": \"10558\",\n" +
                "  \"country_code\": \"GB\"\n" +
                "}"
    }

    fun addressRequestJsonCityIncorrect(): String {
        return "{\n" +
                "  \"street\": \"Somestrasse\",\n" +
                "  \"number\": \"1234\",\n" +
                "  \"postal_code\": \"10558\",\n" +
                "  \"country_code\": \"GB\",\n" +
                "  \"city_id\": \"ca1a7992-613c-11ed-9b6a-0242ac120002\"\n" +
                "}"
    }

    fun addressRequestJson(cityId: UUID): String {
        return "{\n" +
                "  \"street\": \"Somestrasse\",\n" +
                "  \"number\": 1234,\n" +
                "  \"postal_code\": \"10558\",\n" +
                "  \"country_code\": \"GB\",\n" +
                "  \"city_id\": \"$cityId\"\n" +
                "}"
    }

    fun bbcAddressFixture(id: UUID, cityId: UUID): Map<String, Any> {
        val data = HashMap<String, Any>()
        data["id"] = id
        data["street"] = "Somestrasse"
        data["number"] = 1234.toBigDecimal()
        data["postal_code"] = "10558"
        data["country_code"] = "GB"
        data["city_id"] = cityId
        return data
    }
}
