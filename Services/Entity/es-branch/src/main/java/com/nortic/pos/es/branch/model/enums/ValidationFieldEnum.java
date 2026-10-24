package com.nortic.pos.es.branch.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ValidationFieldEnum {

    STORE_ID("storeId", "Store ID is required"),
    NAME("name", "Name is required and cannot be blank"),
    NAME_BLANK("name", "Name cannot be blank");

    private final String field;
    private final String message;
}
