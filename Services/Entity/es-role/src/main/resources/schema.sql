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

CREATE TABLE IF NOT EXISTS role_permission
(
    role_id       UUID NOT NULL,
    permission_id UUID NOT NULL,

    CONSTRAINT pk_role_permission
        PRIMARY KEY (role_id, permission_id),

    CONSTRAINT fk_role_permission_role
        FOREIGN KEY (role_id)
            REFERENCES role (id)
            ON DELETE CASCADE,

    CONSTRAINT fk_role_permission_permission
        FOREIGN KEY (permission_id)
            REFERENCES permission (id)
            ON DELETE CASCADE
);