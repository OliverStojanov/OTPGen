package com.example.otpgen.config;

import com.example.otpgen.model.User;
import com.example.otpgen.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<User> users;
    public final UserRepository userRepository;

    public DataHolder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init(){
        users = new ArrayList<>();
        userRepository.save(new User("otprojectib@gmail.com", "ibprojectotp", "otp", "project"));
        userRepository.save(new User("admin@gmail.com", "admin", "admin", "a"));
        userRepository.save(new User("user@gmail.com", "user", "user", "u"));
    }

}
