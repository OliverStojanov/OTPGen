package com.example.otpgen.service;

import com.example.otpgen.model.User;

import java.util.Optional;

public interface UserService {
    public User findById(Long id);
    public User login(String username, String password);

    User register(String username, String password, String repeatedPassword, String name, String surname);
    public void sendEmail(String toEmail, String subject, String body);
}
