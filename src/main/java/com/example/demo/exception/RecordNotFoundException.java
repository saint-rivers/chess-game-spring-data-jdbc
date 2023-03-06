package com.example.demo.exception;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(String message, Object resourceId) {
        super(message + " " + resourceId);
    }
}
