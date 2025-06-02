package com.nortic.pos.es.branch.util;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private final int errorCode;

    public CustomException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

}
