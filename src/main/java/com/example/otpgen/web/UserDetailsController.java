package com.example.otpgen.web;

import com.example.otpgen.model.User;
import com.example.otpgen.model.exceptions.InvalidUserCredentialsException;
import com.example.otpgen.service.PostService;
import com.example.otpgen.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserDetailsController {
    public final UserService userService;
    public final PostService postService;

    public UserDetailsController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("/userDetails/{id}")
    public String userDetails(@PathVariable Long id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("posts", userService.findAll(id));
        return "userDetails";
    }

    @GetMapping("/forum/{id}")
    public String userForum(@PathVariable Long id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("posts", postService.findAll());
        return "forum";
    }
}
