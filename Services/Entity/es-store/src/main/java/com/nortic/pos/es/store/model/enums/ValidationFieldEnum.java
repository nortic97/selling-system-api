package com.nortic.pos.es.store.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ValidationFieldEnum {

    NAME("name", "Name is required and cannot be blank"),
    NAME_BLANK("name", "Name cannot be blank"),
    OWNER_ID("ownerId", "Owner ID is required"),
    TAX_TYPE("taxType", "Tax type is required"),
    TAX_ID("taxId", "Tax ID is required and cannot be blank"),
    TAX_ID_BLANK("taxId", "Tax ID cannot be blank"),
    EMAIL("email", "Email is required and cannot be blank"),
    EMAIL_BLANK("email", "Email cannot be blank"),
    PHONE("phone", "Phone is required and cannot exceed 20 characters"),
    PHONE_BLANK("phone", "Phone cannot be blank");

    private final String field;
    private final String message;
}
