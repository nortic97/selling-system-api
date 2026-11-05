package com.nortic.pos.es.employee.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ValidationFieldEnum {

    USERNAME("username", "Username is required and cannot be blank"),
    EMAIL("email", "Email is required and cannot be blank"),
    DOCUMENT_NUMBER("documentNumber", "Document number is required and cannot be blank"),
    DOCUMENT_TYPE("documentType", "Document type is required"),
    FIRST_NAME("firstName", "First name is required and cannot be blank"),
    LAST_NAME("lastName", "Last name is required and cannot be blank"),
    PHONE("phone", "Phone is required and cannot be blank"),
    STORE_ID("storeId", "Store ID is required");

    private final String field;
    private final String message;
}
