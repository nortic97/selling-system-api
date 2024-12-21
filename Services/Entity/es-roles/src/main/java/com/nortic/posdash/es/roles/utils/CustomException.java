package com.nortic.posdash.es.roles.utils;

public class CustomException extends Exception{

    public CustomException(){
        super("Internal Server Error");
    }

    public CustomException(String message){
        super(message);
    }

}
