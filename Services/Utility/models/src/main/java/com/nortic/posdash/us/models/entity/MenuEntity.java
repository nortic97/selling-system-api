package com.nortic.posdash.us.models.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity(name = "menus")
public class MenuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private int Position;

    private boolean hasSubmenu;

    private String url;

    private String icon;

    private String description;

    private boolean status;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<RoleEntity> roles;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<SubMenuEntity> subMenus;

    public MenuEntity() {
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

    public int getPosition() {
        return Position;
    }

    public void setPosition(int position) {
        Position = position;
    }

    public boolean isHasSubmenu() {
        return hasSubmenu;
    }

    public void setHasSubmenu(boolean hasSubmenu) {
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

    public List<SubMenuEntity> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<SubMenuEntity> subMenus) {
        this.subMenus = subMenus;
    }
}
