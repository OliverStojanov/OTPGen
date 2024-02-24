package com.example.otpgen.model.exceptions;

public class OtpDoesNotExistException extends RuntimeException{
    public OtpDoesNotExistException(){super("There is no OTP for the user");}
}
