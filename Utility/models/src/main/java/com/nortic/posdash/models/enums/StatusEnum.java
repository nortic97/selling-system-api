package com.nortic.posdash.models.enums;

/**
 * Enum for status of response body
 */
public enum StatusEnum {
    SUCCESS("success"),
    ERROR("error"),
    NOT_FOUND("not found");

    private final String message;

    StatusEnum(String message) {
        this.message = message;
    }

    /**
     * Return String assigned to enum
     * @return
     */
    public String getMessage() {
        return message;
    }
}
