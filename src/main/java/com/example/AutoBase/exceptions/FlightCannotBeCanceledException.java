package com.example.AutoBase.exceptions;

public class FlightCannotBeCanceledException extends RuntimeException {
    public FlightCannotBeCanceledException(String message) {
        super(message);
    }
}
