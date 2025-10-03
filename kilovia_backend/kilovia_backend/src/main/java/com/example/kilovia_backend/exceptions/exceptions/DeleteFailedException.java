package com.example.kilovia_backend.exceptions.exceptions;

public class DeleteFailedException extends RuntimeException {
    public DeleteFailedException(String message) {
        super(message);
    }
}
