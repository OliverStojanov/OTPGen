package com.example.otpgen.service.impl;

import com.example.otpgen.model.Post;
import com.example.otpgen.model.User;
import com.example.otpgen.repository.PostRepository;
import com.example.otpgen.repository.UserRepository;
import com.example.otpgen.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    public  final PostRepository postRepository;
    public final UserRepository userRepository;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Post createNewPost(User user, String title, String content) {
        Post post = new Post(user, title, content);
        postRepository.save(post);
        return post;
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        User user = post.user;
        user.posts.remove(post);
        userRepository.save(user);
        postRepository.delete(post);
        return;
    }

    @Override
    public Post findById(Long id) {
        return postRepository.findById(id).orElseThrow();
    }
}
