package com.example.otpgen.web;

import com.example.otpgen.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/", "/home"})
    public String getHomePage(Model model){
        return "home";
    }

}
