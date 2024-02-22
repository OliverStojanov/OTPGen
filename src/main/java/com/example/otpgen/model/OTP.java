package com.example.otpgen.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.time.LocalTime;

@Data
@Entity
@AllArgsConstructor
public class OTP {

    @Id
    public Long Id;
    public String otp;
    public LocalTime timestamp;

    public OTP() {
    }


}
