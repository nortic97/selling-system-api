CREATE TABLE IF NOT EXISTS "user"
(
    id              UUID PRIMARY KEY,

    first_name      VARCHAR(100) NOT NULL,
    last_name       VARCHAR(100) NOT NULL,

    document_type   VARCHAR(50)  NOT NULL,
    document_number VARCHAR(100) NOT NULL,

    username        VARCHAR(100) NOT NULL,
    email           VARCHAR(255) NOT NULL,
    password        VARCHAR(255) NOT NULL,

    phone           VARCHAR(20) NOT NULL,
    address         VARCHAR(255),
    date_of_birth   DATE,
    photo_url       TEXT,

    is_active       BOOLEAN      NOT NULL DEFAULT FALSE,

    created_at      TIMESTAMPTZ  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMPTZ  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at      TIMESTAMPTZ,

    CONSTRAINT ck_user_document_type
        CHECK (LENGTH(TRIM(document_type)) > 0),

    CONSTRAINT ck_user_document_number
        CHECK (LENGTH(TRIM(document_number)) > 0),

    CONSTRAINT ck_user_username
        CHECK (LENGTH(TRIM(username)) > 0),

    CONSTRAINT ck_user_email
        CHECK (LENGTH(TRIM(email)) > 0)
);

-- Un documento solo puede pertenecer a un usuario activo.
CREATE UNIQUE INDEX IF NOT EXISTS uk_user_document_active
    ON "user" (document_type, document_number)
    WHERE deleted_at IS NULL;

-- Un username solo puede pertenecer a un usuario activo.
CREATE UNIQUE INDEX IF NOT EXISTS uk_user_username_active
    ON "user" (username)
    WHERE deleted_at IS NULL;

-- Un email solo puede pertenecer a un usuario activo.
CREATE UNIQUE INDEX IF NOT EXISTS uk_user_email_active
    ON "user" (email)
    WHERE deleted_at IS NULL;

-- Un teléfono solo puede pertenecer a un usuario activo.
CREATE UNIQUE INDEX IF NOT EXISTS uk_user_phone_active
    ON "user" (phone)
    WHERE deleted_at IS NULL;

-- Índices auxiliares para consultas habituales.
CREATE INDEX IF NOT EXISTS idx_user_active
    ON "user" (is_active)
    WHERE deleted_at IS NULL;

CREATE INDEX IF NOT EXISTS idx_user_created_at
    ON "user" (created_at);
