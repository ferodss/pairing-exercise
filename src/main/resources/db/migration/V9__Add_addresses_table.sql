CREATE TABLE IF NOT EXISTS organisations_schema.addresses
(
    id              UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    organisation_id UUID    NOT NULL REFERENCES organisations_schema.organisations (id),
    street          VARCHAR NOT NULL,
    "number"        NUMERIC,
    postal_code     VARCHAR(20),
    country_code    CHAR(2) NOT NULL,
    city_id         UUID    NOT NULL REFERENCES organisations_schema.cities (id)
);
