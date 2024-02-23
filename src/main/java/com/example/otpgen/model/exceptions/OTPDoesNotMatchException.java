package com.example.otpgen.model.exceptions;

public class OTPDoesNotMatchException extends RuntimeException{

    public OTPDoesNotMatchException(){
        super("The code you have entered is not correct");
    }
}
