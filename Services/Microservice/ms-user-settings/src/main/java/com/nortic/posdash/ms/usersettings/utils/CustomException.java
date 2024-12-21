package com.nortic.posdash.ms.usersettings.utils;

public class CustomException extends Exception {

    public CustomException() {
        super("Internal Server Error");
    }

    public CustomException(String message) {
        super(message);
    }

}
