package com.example.exceptions;

public class PropertiesNotLoadedException extends RuntimeException {

    public PropertiesNotLoadedException(String message, Throwable cause) {
        super(message, cause);
    }

    public PropertiesNotLoadedException(String message) {
        super(message);
    }
}
