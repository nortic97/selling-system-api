CREATE TABLE IF NOT EXISTS branch
(
    id           UUID PRIMARY KEY,
    store_id     UUID         NOT NULL,
    name         VARCHAR(255) NOT NULL,
    address      VARCHAR(255),
    phone_number VARCHAR(20),
    email        VARCHAR(255),
    is_active    BOOLEAN      NOT NULL DEFAULT true,
    created_at   TIMESTAMPTZ  NOT NULL DEFAULT now(),
    updated_at   TIMESTAMPTZ  NOT NULL DEFAULT now(),
    deleted_at   TIMESTAMPTZ,

    CONSTRAINT fk_branch_store FOREIGN KEY (store_id) REFERENCES store (id)
);

-- Índice único parcial que ignora los registros eliminados (soft deletes)
CREATE UNIQUE INDEX IF NOT EXISTS uidx_branch_store_name_active
    ON branch (store_id, name)
    WHERE deleted_at IS NULL;