CREATE OR REPLACE FUNCTION role_has_permission(role_id UUID, method VARCHAR, url VARCHAR) 
RETURNS BOOLEAN AS $$
BEGIN
    RETURN EXISTS (
        SELECT 1
        FROM roles r
        JOIN role_permissions rp ON r.id = rp.role_id
        JOIN permissions p ON rp.permission_id = p.id
        WHERE r.id = role_has_permission.role_id 
        AND p.method ILIKE role_has_permission.method 
        AND p.url ILIKE role_has_permission.url
    );
END;
$$ LANGUAGE plpgsql;
