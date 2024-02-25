package com.example.otpgen.service;

import com.example.otpgen.model.Post;
import com.example.otpgen.model.Role;
import com.example.otpgen.model.User;

import java.util.List;

public interface UserService {
    public User findById(Long id);
    public User login(String username, String password);

    public User register(String username, String password, String repeatedPassword, String name, String surname, Role role);
    public void sendEmail(String toEmail, String subject, String body);

    public List<Post> findAll(Long id);

    void addNewPost(Post newPost);
}
