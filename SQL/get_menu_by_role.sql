DROP FUNCTION IF EXISTS get_menu_by_role(UUID);

CREATE OR REPLACE FUNCTION get_menu_by_role(p_role_id UUID)
RETURNS TABLE(
    menu_id UUID,
    menu_name VARCHAR,
	menu_icon TEXT,
	menu_position INT,
    menu_url VARCHAR,
    submenu_id UUID,
    submenu_name VARCHAR,
	submenu_position INT,
    submenu_url VARCHAR
) AS $$
BEGIN
    RETURN QUERY
    SELECT
        m.id AS menu_id,
        m.name AS menu_name,
		m.icon AS menu_icon,
		m.position AS menu_position,
        m.url AS menu_url,
        sm.id AS submenu_id,
        sm.name AS submenu_name,
		sm.position AS submenu_position,
        sm.url AS submenu_url
    FROM
        role_menus rm
    JOIN
        menus m ON rm.menu_id = m.id
    LEFT JOIN
        submenus sm ON m.id = sm.menu_id
    WHERE
        rm.role_id = p_role_id
	ORDER BY
		m.position, sm.position;
END;
$$ LANGUAGE plpgsql;