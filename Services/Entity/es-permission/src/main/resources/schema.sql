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

-- Permiso principal del sistema
insert into permission (id, code, "module", name, description, is_active, created_at, updated_at, deleted_at)
values
    ('550e8400-e29b-41d4-a716-446655440001', 'system.all', 'system', 'Acceso total al sistema', 'Permiso para acceder a todas las funcionalidades del sistema sin restricciones', true, now(), now(), null)
on conflict (code) do nothing;

-- Permisos para es-branch (Sucursales)
insert into permission (id, code, "module", name, description, is_active, created_at, updated_at, deleted_at)
values
    ('550e8400-e29b-41d4-a716-446655440002', 'branch.all', 'branch', 'Acceso completo a sucursales', 'Permiso para realizar todas las operaciones sobre sucursales', true, now(), now(), null),
    ('550e8400-e29b-41d4-a716-446655440003', 'branch.read', 'branch', 'Leer sucursales', 'Permiso para consultar información de sucursales', true, now(), now(), null),
    ('550e8400-e29b-41d4-a716-446655440004', 'branch.create', 'branch', 'Crear sucursales', 'Permiso para crear nuevas sucursales', true, now(), now(), null),
    ('550e8400-e29b-41d4-a716-446655440005', 'branch.update', 'branch', 'Actualizar sucursales', 'Permiso para modificar información de sucursales existentes', true, now(), now(), null),
    ('550e8400-e29b-41d4-a716-446655440006', 'branch.delete', 'branch', 'Eliminar sucursales', 'Permiso para eliminar sucursales del sistema', true, now(), now(), null)
on conflict (code) do nothing;

-- Permisos para es-employee (Empleados)
insert into permission (id, code, "module", name, description, is_active, created_at, updated_at, deleted_at)
values
    ('550e8400-e29b-41d4-a716-446655440007', 'employee.all', 'employee', 'Acceso completo a empleados', 'Permiso para realizar todas las operaciones sobre empleados', true, now(), now(), null),
    ('550e8400-e29b-41d4-a716-446655440008', 'employee.read', 'employee', 'Leer empleados', 'Permiso para consultar información de empleados', true, now(), now(), null),
    ('550e8400-e29b-41d4-a716-446655440009', 'employee.create', 'employee', 'Crear empleados', 'Permiso para crear nuevos empleados', true, now(), now(), null),
    ('550e8400-e29b-41d4-a716-446655440010', 'employee.update', 'employee', 'Actualizar empleados', 'Permiso para modificar información de empleados existentes', true, now(), now(), null),
    ('550e8400-e29b-41d4-a716-446655440011', 'employee.delete', 'employee', 'Eliminar empleados', 'Permiso para eliminar empleados del sistema', true, now(), now(), null)
on conflict (code) do nothing;

-- Permisos para es-employee-workspace (Espacios de trabajo de empleados)
insert into permission (id, code, "module", name, description, is_active, created_at, updated_at, deleted_at)
values
    ('550e8400-e29b-41d4-a716-446655440012', 'employee-workspace.all', 'employee-workspace', 'Acceso completo a espacios de trabajo', 'Permiso para realizar todas las operaciones sobre espacios de trabajo de empleados', true, now(), now(), null),
    ('550e8400-e29b-41d4-a716-446655440013', 'employee-workspace.read', 'employee-workspace', 'Leer espacios de trabajo', 'Permiso para consultar información de espacios de trabajo', true, now(), now(), null),
    ('550e8400-e29b-41d4-a716-446655440014', 'employee-workspace.create', 'employee-workspace', 'Crear espacios de trabajo', 'Permiso para crear nuevos espacios de trabajo', true, now(), now(), null),
    ('550e8400-e29b-41d4-a716-446655440015', 'employee-workspace.update', 'employee-workspace', 'Actualizar espacios de trabajo', 'Permiso para modificar espacios de trabajo existentes', true, now(), now(), null),
    ('550e8400-e29b-41d4-a716-446655440016', 'employee-workspace.delete', 'employee-workspace', 'Eliminar espacios de trabajo', 'Permiso para eliminar espacios de trabajo del sistema', true, now(), now(), null)
on conflict (code) do nothing;

-- Permisos para es-feature (Características)
insert into permission (id, code, "module", name, description, is_active, created_at, updated_at, deleted_at)
values
    ('550e8400-e29b-41d4-a716-446655440017', 'feature.all', 'feature', 'Acceso completo a características', 'Permiso para realizar todas las operaciones sobre características del sistema', true, now(), now(), null),
    ('550e8400-e29b-41d4-a716-446655440018', 'feature.read', 'feature', 'Leer características', 'Permiso para consultar información de características', true, now(), now(), null),
    ('550e8400-e29b-41d4-a716-446655440019', 'feature.create', 'feature', 'Crear características', 'Permiso para crear nuevas características', true, now(), now(), null),
    ('550e8400-e29b-41d4-a716-446655440020', 'feature.update', 'feature', 'Actualizar características', 'Permiso para modificar características existentes', true, now(), now(), null),
    ('550e8400-e29b-41d4-a716-446655440021', 'feature.delete', 'feature', 'Eliminar características', 'Permiso para eliminar características del sistema', true, now(), now(), null)
on conflict (code) do nothing;

-- Permisos para es-permission (Permisos)
insert into permission (id, code, "module", name, description, is_active, created_at, updated_at, deleted_at)
values
    ('550e8400-e29b-41d4-a716-446655440022', 'permission.all', 'permission', 'Acceso completo a permisos', 'Permiso para realizar todas las operaciones sobre permisos del sistema', true, now(), now(), null),
    ('550e8400-e29b-41d4-a716-446655440023', 'permission.read', 'permission', 'Leer permisos', 'Permiso para consultar información de permisos', true, now(), now(), null),
    ('550e8400-e29b-41d4-a716-446655440024', 'permission.create', 'permission', 'Crear permisos', 'Permiso para crear nuevos permisos', true, now(), now(), null),
    ('550e8400-e29b-41d4-a716-446655440025', 'permission.update', 'permission', 'Actualizar permisos', 'Permiso para modificar permisos existentes', true, now(), now(), null),
    ('550e8400-e29b-41d4-a716-446655440026', 'permission.delete', 'permission', 'Eliminar permisos', 'Permiso para eliminar permisos del sistema', true, now(), now(), null)
on conflict (code) do nothing;

-- Permisos para es-plan (Planes)
insert into permission (id, code, "module", name, description, is_active, created_at, updated_at, deleted_at)
values
    ('550e8400-e29b-41d4-a716-446655440027', 'plan.all', 'plan', 'Acceso completo a planes', 'Permiso para realizar todas las operaciones sobre planes', true, now(), now(), null),
    ('550e8400-e29b-41d4-a716-446655440028', 'plan.read', 'plan', 'Leer planes', 'Permiso para consultar información de planes', true, now(), now(), null),
    ('550e8400-e29b-41d4-a716-446655440029', 'plan.create', 'plan', 'Crear planes', 'Permiso para crear nuevos planes', true, now(), now(), null),
    ('550e8400-e29b-41d4-a716-446655440030', 'plan.update', 'plan', 'Actualizar planes', 'Permiso para modificar planes existentes', true, now(), now(), null),
    ('550e8400-e29b-41d4-a716-446655440031', 'plan.delete', 'plan', 'Eliminar planes', 'Permiso para eliminar planes del sistema', true, now(), now(), null)
on conflict (code) do nothing;

-- Permisos para es-role (Roles)
insert into permission (id, code, "module", name, description, is_active, created_at, updated_at, deleted_at)
values
    ('550e8400-e29b-41d4-a716-446655440032', 'role.all', 'role', 'Acceso completo a roles', 'Permiso para realizar todas las operaciones sobre roles', true, now(), now(), null),
    ('550e8400-e29b-41d4-a716-446655440033', 'role.read', 'role', 'Leer roles', 'Permiso para consultar información de roles', true, now(), now(), null),
    ('550e8400-e29b-41d4-a716-446655440034', 'role.create', 'role', 'Crear roles', 'Permiso para crear nuevos roles', true, now(), now(), null),
    ('550e8400-e29b-41d4-a716-446655440035', 'role.update', 'role', 'Actualizar roles', 'Permiso para modificar roles existentes', true, now(), now(), null),
    ('550e8400-e29b-41d4-a716-446655440036', 'role.delete', 'role', 'Eliminar roles', 'Permiso para eliminar roles del sistema', true, now(), now(), null)
on conflict (code) do nothing;

-- Permisos para es-store (Tiendas)
insert into permission (id, code, "module", name, description, is_active, created_at, updated_at, deleted_at)
values
    ('550e8400-e29b-41d4-a716-446655440037', 'store.all', 'store', 'Acceso completo a tiendas', 'Permiso para realizar todas las operaciones sobre tiendas', true, now(), now(), null),
    ('550e8400-e29b-41d4-a716-446655440038', 'store.read', 'store', 'Leer tiendas', 'Permiso para consultar información de tiendas', true, now(), now(), null),
    ('550e8400-e29b-41d4-a716-446655440039', 'store.create', 'store', 'Crear tiendas', 'Permiso para crear nuevas tiendas', true, now(), now(), null),
    ('550e8400-e29b-41d4-a716-446655440040', 'store.update', 'store', 'Actualizar tiendas', 'Permiso para modificar tiendas existentes', true, now(), now(), null),
    ('550e8400-e29b-41d4-a716-446655440041', 'store.delete', 'store', 'Eliminar tiendas', 'Permiso para eliminar tiendas del sistema', true, now(), now(), null)
on conflict (code) do nothing;

-- Permisos para es-user (Usuarios)
insert into permission (id, code, "module", name, description, is_active, created_at, updated_at, deleted_at)
values
    ('550e8400-e29b-41d4-a716-446655440042', 'user.all', 'user', 'Acceso completo a usuarios', 'Permiso para realizar todas las operaciones sobre usuarios', true, now(), now(), null),
    ('550e8400-e29b-41d4-a716-446655440043', 'user.read', 'user', 'Leer usuarios', 'Permiso para consultar información de usuarios', true, now(), now(), null),
    ('550e8400-e29b-41d4-a716-446655440044', 'user.create', 'user', 'Crear usuarios', 'Permiso para crear nuevos usuarios', true, now(), now(), null),
    ('550e8400-e29b-41d4-a716-446655440045', 'user.update', 'user', 'Actualizar usuarios', 'Permiso para modificar usuarios existentes', true, now(), now(), null),
    ('550e8400-e29b-41d4-a716-446655440046', 'user.delete', 'user', 'Eliminar usuarios', 'Permiso para eliminar usuarios del sistema', true, now(), now(), null)
on conflict (code) do nothing;