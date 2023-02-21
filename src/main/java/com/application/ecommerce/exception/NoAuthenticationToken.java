package com.application.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NoAuthenticationToken extends RuntimeException{
    public NoAuthenticationToken(String message) {
        super(message);
    }
}
