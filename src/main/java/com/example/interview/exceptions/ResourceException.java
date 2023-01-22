package com.example.interview.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
public class ResourceException {
    private final String message;
    private final HttpStatus httpStatus;
}
