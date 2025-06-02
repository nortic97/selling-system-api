package com.nortic.pos.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TypeOfPlanEnum {
    FREE("Free"),
    BASIC("Basic"),
    PREMIUM("Premium");

    private String value;

    TypeOfPlanEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static TypeOfPlanEnum fromValue(String value) {
        for (TypeOfPlanEnum b : TypeOfPlanEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
