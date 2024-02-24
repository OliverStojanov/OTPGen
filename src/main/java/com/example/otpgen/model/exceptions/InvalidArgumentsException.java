package com.example.otpgen.model.exceptions;

public class InvalidArgumentsException extends RuntimeException {
    public InvalidArgumentsException() {
        super("Invalid argument.");
    }
}
