package com.valko.SpringMusic.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String substr) {
        super("Resource not found " + substr);
    }

    public ResourceNotFoundException(long id) {
        super("Resource not found " + Long.toString(id));
    }

}
