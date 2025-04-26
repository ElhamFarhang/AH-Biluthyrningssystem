package com.example.ahbiluthyrningssystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


//--------------------- Elham - BadRequestException --------------
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    public BadRequestException(String field) {
        super( String.format("%s are required", field));
    }
}
