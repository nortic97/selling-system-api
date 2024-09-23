CREATE OR REPLACE VIEW public.menus_with_submenus AS
SELECT 
    m.id AS menu_id,
    m.name AS menu_name,
    m.description AS menu_description,
    m.has_submenu AS menu_has_submenu,
    m.icon AS menu_icon,
    m.position AS menu_position,
    m.status AS menu_status,
    m.url AS menu_url,
    s.id AS submenu_id,
    s.name AS submenu_name,
    s.description AS submenu_description,
    s.position AS submenu_position,
    s.status AS submenu_status,
    s.url AS submenu_url,
    s.menu_id AS submenu_menu_id
FROM 
    public.menus m
LEFT JOIN 
    public.submenus s ON m.id = s.menu_id
ORDER BY 
    m.position, s.position;
