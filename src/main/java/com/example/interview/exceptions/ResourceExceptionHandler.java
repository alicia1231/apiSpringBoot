package com.example.interview.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException
            (ResourceNotFoundException resourceNotFoundException)
    {
        ResourceException resourceException = new ResourceException(
                resourceNotFoundException.getMessage(),
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(resourceException, HttpStatus.NOT_FOUND);
    }
}
