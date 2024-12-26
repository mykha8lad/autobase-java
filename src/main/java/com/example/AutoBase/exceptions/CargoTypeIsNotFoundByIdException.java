package com.example.AutoBase.exceptions;

public class CargoTypeIsNotFoundByIdException extends RuntimeException {
    public CargoTypeIsNotFoundByIdException(String message) {
        super(message);
    }
}
