package com.example.ahbiluthyrningssystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//  Wille
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ResourceMissingDataException extends RuntimeException {
    public ResourceMissingDataException(String object, String field) {
        super(String.format("Object '%s' has no field '%s'", object, field));
    }
}
