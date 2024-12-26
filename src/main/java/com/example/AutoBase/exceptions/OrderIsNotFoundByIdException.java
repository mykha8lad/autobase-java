package com.example.AutoBase.exceptions;

public class OrderIsNotFoundByIdException extends RuntimeException {
    public OrderIsNotFoundByIdException(String message) {
        super(message);
    }
}
