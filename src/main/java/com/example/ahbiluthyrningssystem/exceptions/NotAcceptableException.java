package com.example.ahbiluthyrningssystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


//--------------------- Elham - NotAcceptableException --------------
@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class NotAcceptableException extends RuntimeException {

    public NotAcceptableException(String field) {
        super(String.format("personal_number %s does not match", field));
    }
}
