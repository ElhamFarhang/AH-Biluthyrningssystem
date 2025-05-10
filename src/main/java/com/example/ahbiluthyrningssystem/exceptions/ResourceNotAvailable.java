package com.example.ahbiluthyrningssystem.exceptions;

public class ResourceNotAvailable extends RuntimeException {
    public ResourceNotAvailable(String object, String field) {
        super(String.format("%s not available for this %s", object, field));
    }
}
