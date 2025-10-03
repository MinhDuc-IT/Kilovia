package com.example.kilovia_backend.exceptions.exceptions;

public class GradeNotFoundException extends RuntimeException {
    public GradeNotFoundException(String message) {
        super(message);
    }
}
