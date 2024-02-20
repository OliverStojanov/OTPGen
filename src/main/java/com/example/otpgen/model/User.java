package com.example.otpgen.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "otpUser")
public class User {

    @Id
    public Long id;
    public String email;
    public String password;
    @OneToOne
    public OTP otp;

    public User() {
    }
}
