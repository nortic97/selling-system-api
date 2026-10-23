CREATE TABLE IF NOT EXISTS store
(
    id             UUID PRIMARY KEY,

    owner_id       UUID         NOT NULL,

    tax_type       VARCHAR(50)  NOT NULL,
    tax_id         VARCHAR(100) NOT NULL,

    legal_name     VARCHAR(255),
    name           VARCHAR(255) NOT NULL,

    logo_url       TEXT,
    description    TEXT,
    slogan         VARCHAR(255),

    address        VARCHAR(255),
    country        VARCHAR(100),
    state_province VARCHAR(100),
    city           VARCHAR(100),

    phone_number   VARCHAR(20),
    contact_email  VARCHAR(255),

    currency_code  VARCHAR(3)   NOT NULL DEFAULT 'COP',
    is_active      BOOLEAN      NOT NULL DEFAULT TRUE,

    created_at     TIMESTAMPTZ  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at     TIMESTAMPTZ  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at     TIMESTAMPTZ,

    CONSTRAINT fk_store_owner
        FOREIGN KEY (owner_id)
            REFERENCES "user" (id),

    CONSTRAINT ck_store_tax_type
        CHECK (LENGTH(TRIM(tax_type)) > 0),

    CONSTRAINT ck_store_tax_id
        CHECK (LENGTH(TRIM(tax_id)) > 0),

    CONSTRAINT ck_store_name
        CHECK (LENGTH(TRIM(name)) > 0),

    CONSTRAINT ck_store_currency_code
        CHECK (currency_code ~ '^[A-Z]{3}$')
);

-- Identificación tributaria única entre tiendas activas.
CREATE UNIQUE INDEX IF NOT EXISTS uk_store_tax_active
    ON store (tax_type, tax_id)
    WHERE deleted_at IS NULL;

-- Email de contacto único entre tiendas activas.
CREATE UNIQUE INDEX IF NOT EXISTS uk_store_contact_email_active
    ON store (contact_email)
    WHERE contact_email IS NOT NULL
        AND deleted_at IS NULL;

-- Consultas de tiendas activas por propietario.
CREATE INDEX IF NOT EXISTS idx_store_owner_active
    ON store (owner_id)
    WHERE deleted_at IS NULL;
