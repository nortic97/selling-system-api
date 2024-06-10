package com.nortic.posdash.us.models.enums;

public enum StatusEnum {
    SUCCESS("success"),
    ERROR("error");

    private final String message;

    StatusEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
