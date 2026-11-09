CREATE TABLE IF NOT EXISTS employee_workspace
(
    id              UUID PRIMARY KEY,

    store_id        UUID         NOT NULL,
    employee_id     UUID         NOT NULL,
    branch_id       UUID         NOT NULL,
    role_id         UUID         NOT NULL,

    is_default      BOOLEAN      NOT NULL DEFAULT FALSE,
    last_access_at  TIMESTAMPTZ,
    is_active       BOOLEAN      NOT NULL DEFAULT TRUE,

    created_at      TIMESTAMPTZ  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMPTZ  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at      TIMESTAMPTZ,

    CONSTRAINT fk_employee_workspace_store
        FOREIGN KEY (store_id)
            REFERENCES store (id),

    CONSTRAINT fk_employee_workspace_employee
        FOREIGN KEY (employee_id)
            REFERENCES employee (id),

    CONSTRAINT fk_employee_workspace_branch
        FOREIGN KEY (branch_id)
            REFERENCES branch (id),

    CONSTRAINT fk_employee_workspace_role
        FOREIGN KEY (role_id)
            REFERENCES role (id)
);

-- Un empleado solo puede tener una asignación activa por sucursal.
CREATE UNIQUE INDEX IF NOT EXISTS uidx_employee_workspace_employee_branch_active
    ON employee_workspace (employee_id, branch_id)
    WHERE deleted_at IS NULL;

-- Un empleado solo puede tener una sucursal por defecto por tienda.
CREATE UNIQUE INDEX IF NOT EXISTS uidx_employee_workspace_store_default
    ON employee_workspace (employee_id, store_id)
    WHERE is_default = TRUE
        AND deleted_at IS NULL;

-- Índices auxiliares para consultas habituales.
CREATE INDEX IF NOT EXISTS idx_employee_workspace_employee_id
    ON employee_workspace (employee_id)
    WHERE deleted_at IS NULL;

CREATE INDEX IF NOT EXISTS idx_employee_workspace_store_id
    ON employee_workspace (store_id)
    WHERE deleted_at IS NULL;

CREATE INDEX IF NOT EXISTS idx_employee_workspace_branch_id
    ON employee_workspace (branch_id)
    WHERE deleted_at IS NULL;

CREATE INDEX IF NOT EXISTS idx_employee_workspace_role_id
    ON employee_workspace (role_id)
    WHERE deleted_at IS NULL;

CREATE INDEX IF NOT EXISTS idx_employee_workspace_active
    ON employee_workspace (is_active)
    WHERE deleted_at IS NULL;

CREATE INDEX IF NOT EXISTS idx_employee_workspace_default
    ON employee_workspace (is_default)
    WHERE deleted_at IS NULL;

CREATE INDEX IF NOT EXISTS idx_employee_workspace_created_at
    ON employee_workspace (created_at);