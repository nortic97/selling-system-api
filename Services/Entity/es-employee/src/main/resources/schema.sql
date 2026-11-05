CREATE TABLE IF NOT EXISTS employee
(
    id                UUID PRIMARY KEY,

    store_id          UUID NOT NULL,

    first_name        VARCHAR(100) NOT NULL,
    last_name         VARCHAR(100) NOT NULL,

    document_type     VARCHAR(50) NOT NULL,
    document_number   VARCHAR(100) NOT NULL,

    username          VARCHAR(100) NOT NULL,
    email             VARCHAR(255) NOT NULL,
    password          VARCHAR(255) NOT NULL,

    phone             VARCHAR(20) NOT NULL,
    address           VARCHAR(255),
    date_of_birth     DATE,
    photo_url         TEXT,

    is_active         BOOLEAN NOT NULL DEFAULT FALSE,

    created_at        TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at        TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at        TIMESTAMPTZ,

    CONSTRAINT fk_employee_store
    FOREIGN KEY (store_id)
    REFERENCES store(id),

    CONSTRAINT ck_employee_document_type
    CHECK (LENGTH(TRIM(document_type)) > 0),

    CONSTRAINT ck_employee_document_number
    CHECK (LENGTH(TRIM(document_number)) > 0),

    CONSTRAINT ck_employee_username
    CHECK (LENGTH(TRIM(username)) > 0),

    CONSTRAINT ck_employee_phone
    CHECK (LENGTH(TRIM(phone)) > 0),

    CONSTRAINT ck_employee_email
    CHECK (LENGTH(TRIM(email)) > 0)
    );

-- Un documento solo puede existir una vez por tienda para empleados activos.
CREATE UNIQUE INDEX IF NOT EXISTS uidx_employee_document_active
    ON employee (store_id, document_type, document_number)
    WHERE deleted_at IS NULL;

-- Un username solo puede existir una vez por tienda para empleados activos.
CREATE UNIQUE INDEX IF NOT EXISTS uidx_employee_username_active
    ON employee (store_id, username)
    WHERE deleted_at IS NULL;

-- Un email solo puede existir una vez por tienda para empleados activos.
CREATE UNIQUE INDEX IF NOT EXISTS uidx_employee_email_active
    ON employee (store_id, email)
    WHERE deleted_at IS NULL;

-- Un telefono solo puede existir una vez por tienda para empleados activos.
CREATE UNIQUE INDEX IF NOT EXISTS uidx_employee_phone_active
    ON employee (store_id, phone)
    WHERE deleted_at IS NULL;

-- Índices auxiliares.
CREATE INDEX IF NOT EXISTS idx_employee_store
    ON employee (store_id)
    WHERE deleted_at IS NULL;

CREATE INDEX IF NOT EXISTS idx_employee_active
    ON employee (is_active)
    WHERE deleted_at IS NULL;

CREATE INDEX IF NOT EXISTS idx_employee_created_at
    ON employee (created_at);