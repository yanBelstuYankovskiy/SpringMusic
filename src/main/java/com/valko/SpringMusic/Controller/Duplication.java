package com.valko.SpringMusic.Controller;

import com.valko.SpringMusic.Exception.DuplicateException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class Duplication {
    @ResponseBody
    @ExceptionHandler(DuplicateException.class)
    @ResponseStatus(HttpStatus.NOT_MODIFIED)
    String duplicateHandler(DuplicateException ex) {
        return ex.getMessage();
    }
}
