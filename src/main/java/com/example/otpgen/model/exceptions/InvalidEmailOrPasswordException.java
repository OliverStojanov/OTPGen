package com.example.otpgen.model.exceptions;

public class InvalidEmailOrPasswordException extends RuntimeException{
    public InvalidEmailOrPasswordException(){
        super("Invalid email or password. Try again to login.");
    }
}
