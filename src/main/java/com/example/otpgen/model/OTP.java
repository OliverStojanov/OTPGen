package com.example.otpgen.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;



import java.time.LocalTime;

@Entity
public class OTP {

    @Id
    public Long Id;
    public Long otp;
    public LocalTime timestamp;

    public OTP() {
    }


}
