package com.nortic.posdash.models.dto;

import java.util.UUID;

public class SubMenuByRoleDTO {
    private UUID submenuId;
    private String submenuName;
    private Integer submenuPosition;
    private String submenuUrl;

    public SubMenuByRoleDTO() {
    }

    public UUID getSubmenuId() {
        return submenuId;
    }

    public void setSubmenuId(UUID submenuId) {
        this.submenuId = submenuId;
    }

    public String getSubmenuName() {
        return submenuName;
    }

    public void setSubmenuName(String submenuName) {
        this.submenuName = submenuName;
    }

    public Integer getSubmenuPosition() {
        return submenuPosition;
    }

    public void setSubmenuPosition(Integer submenuPosition) {
        this.submenuPosition = submenuPosition;
    }

    public String getSubmenuUrl() {
        return submenuUrl;
    }

    public void setSubmenuUrl(String submenuUrl) {
        this.submenuUrl = submenuUrl;
    }
}
