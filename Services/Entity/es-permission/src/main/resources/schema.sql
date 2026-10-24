CREATE TABLE IF NOT EXISTS permission
(
    id          UUID PRIMARY KEY,
    code        VARCHAR(100) UNIQUE NOT NULL,
    module      VARCHAR(100)        NOT NULL,
    name        VARCHAR(150)        NOT NULL,
    description TEXT,
    is_active   BOOLEAN             NOT NULL DEFAULT true,
    created_at  TIMESTAMPTZ         NOT NULL DEFAULT now(),
    updated_at  TIMESTAMPTZ         NOT NULL DEFAULT now(),
    deleted_at  TIMESTAMPTZ
);