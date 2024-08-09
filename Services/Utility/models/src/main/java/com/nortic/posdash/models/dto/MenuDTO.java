package com.nortic.posdash.models.dto;

import java.util.List;
import java.util.UUID;

public class MenuDTO {
    private UUID id;
    private String name;
    private Integer position;
    private Boolean hasSubmenu;
    private String url;
    private String icon;
    private String description;
    private Boolean status;
    private List<SubMenuDTO> subMenus;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Boolean getHasSubmenu() {
        return hasSubmenu;
    }

    public void setHasSubmenu(Boolean hasSubmenu) {
        this.hasSubmenu = hasSubmenu;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<SubMenuDTO> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<SubMenuDTO> subMenus) {
        this.subMenus = subMenus;
    }
}
