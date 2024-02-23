package com.example.otpgen.model.exceptions;

public class TimeRunOutException extends RuntimeException{
    public TimeRunOutException(){
        super("Code expired try login again");
    }
}
