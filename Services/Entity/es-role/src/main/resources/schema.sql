CREATE TABLE IF NOT EXISTS role
(
    id          UUID PRIMARY KEY,
    store_id    UUID         NOT NULL,
    name        VARCHAR(100) NOT NULL,
    description TEXT,
    is_active   BOOLEAN      NOT NULL DEFAULT true,
    created_at  TIMESTAMPTZ  NOT NULL DEFAULT now(),
    updated_at  TIMESTAMPTZ  NOT NULL DEFAULT now(),
    deleted_at  TIMESTAMPTZ,

    CONSTRAINT fk_role_store
        FOREIGN KEY (store_id)
            REFERENCES store (id)
);

CREATE UNIQUE INDEX IF NOT EXISTS uidx_role_store_name_active
    ON role (store_id, name)
    WHERE deleted_at IS NULL;