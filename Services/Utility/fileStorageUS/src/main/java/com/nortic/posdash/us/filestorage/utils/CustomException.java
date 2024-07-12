package com.nortic.posdash.us.filestorage.utils;

public class CustomException extends Exception {

    public CustomException() {
        super("Internal Server Error");
    }

    public CustomException(String message) {
        super(message);
    }

}
