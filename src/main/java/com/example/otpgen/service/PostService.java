package com.example.otpgen.service;

import com.example.otpgen.model.Post;
import com.example.otpgen.model.User;

public interface PostService {
    Post createNewPost(User user, String title, String content);

    Object findAll();

    void delete(Long id);

    Object findById(Long id);
}
