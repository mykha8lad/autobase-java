package com.example.AutoBase.exceptions;

public class CityIsNotFoundByIdException extends RuntimeException {
    public CityIsNotFoundByIdException(String message) {
        super(message);
    }
}
