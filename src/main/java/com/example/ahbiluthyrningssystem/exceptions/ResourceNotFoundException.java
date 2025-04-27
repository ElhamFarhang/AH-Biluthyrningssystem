package com.example.ahbiluthyrningssystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


//--------------------- Elham - ResourceNotFoundException --------------
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String object, String field, Object value) {
        super(String.format("%s with %s '%s' not found", object, field, value));
    }
}