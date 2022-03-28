package com.chekk.santahelper.exceptions;


public class CustomException extends Exception {

    String message;

    public CustomException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
