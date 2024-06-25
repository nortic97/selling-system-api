package com.nortic.posdash.models.dto;

import java.util.List;
import java.util.UUID;

public class MenuByRoleDTO {
    private UUID menuId;
    private String menuName;
    private String menuIcon;
    private Integer menuPosition;
    private String menuUrl;
    private List<SubMenuByRoleDTO> subMenus;

    public MenuByRoleDTO() {
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public UUID getMenuId() {
        return menuId;
    }

    public void setMenuId(UUID menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getMenuPosition() {
        return menuPosition;
    }

    public void setMenuPosition(Integer menuPosition) {
        this.menuPosition = menuPosition;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public List<SubMenuByRoleDTO> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<SubMenuByRoleDTO> subMenus) {
        this.subMenus = subMenus;
    }
}
