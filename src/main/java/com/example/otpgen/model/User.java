package com.example.otpgen.model;

import jakarta.persistence.*;

import java.util.List;

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
    @Enumerated(value = EnumType.STRING)
    public Role role;
    @OneToOne
    public OTP otp;
    @OneToMany(mappedBy = "user")
    public List<Post> posts;
    public User() {
    }
    public User(String email, String password, String name, String surname, Role role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role=role;
    }
}
