package com.nortic.posdash.models.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity(name = "menus")
public class MenuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private Integer position;

    private Boolean hasSubmenu;

    private String url;

    private String icon;

    private String description;

    private Boolean status;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<SubMenuEntity> subMenus;

    @ManyToMany(mappedBy = "menus")
    private List<RoleEntity> roles;

    public MenuEntity() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getHasSubmenu() {
        return hasSubmenu;
    }

    public void setHasSubmenu(Boolean hasSubmenu) {
        this.hasSubmenu = hasSubmenu;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

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

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<SubMenuEntity> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<SubMenuEntity> subMenus) {
        this.subMenus = subMenus;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
