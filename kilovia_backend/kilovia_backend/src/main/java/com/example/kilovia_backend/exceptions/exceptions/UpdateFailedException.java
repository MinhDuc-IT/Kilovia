package com.example.kilovia_backend.exceptions.exceptions;

public class UpdateFailedException extends RuntimeException {
    public UpdateFailedException(String message) {
        super(message);
    }
}
