package com.nortic.pos.es.role.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ValidationFieldEnum {

    STORE_ID("storeId", "Store ID is required"),
    NAME("name", "Name is required, cannot be blank and must be between 3 and 100 characters"),
    NAME_BLANK("name", "Name cannot be blank"),
    NAME_TOO_SHORT("name", "Name must be at least 3 characters"),
    NAME_TOO_LONG("name", "Name must not exceed 100 characters");

    private final String field;
    private final String message;
}
