package com.example.otpgen.model;

import jakarta.persistence.*;

@Entity
@Table(name = "otpUser")
public class User {

    @Id
    @GeneratedValue
    public Long id;
    public String email;
    public String password;
    public String name;
    public String surname;
    @OneToOne
    public OTP otp;
    public User() {
    }
    public User(String email, String password, String name, String surname) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }
}
