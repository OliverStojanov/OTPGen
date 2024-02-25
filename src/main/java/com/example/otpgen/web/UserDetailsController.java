package com.example.otpgen.web;

import com.example.otpgen.model.User;
import com.example.otpgen.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserDetailsController {
    public final UserService userService;

    public UserDetailsController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/userDetails/{id}")
    public String userDetails(@PathVariable Long id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "userDetails";
    }
}
