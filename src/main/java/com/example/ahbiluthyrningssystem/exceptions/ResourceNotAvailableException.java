package com.example.ahbiluthyrningssystem.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)                  //Anna
public class ResourceNotAvailableException extends RuntimeException {
    public ResourceNotAvailableException(String object, String field) {
        super(String.format("%s not available for this %s", object, field));
    }
}
