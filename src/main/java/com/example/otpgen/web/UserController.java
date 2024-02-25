package com.example.otpgen.web;

import com.example.otpgen.model.User;
import com.example.otpgen.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {
    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/", "/home" })
    public String getHomePage(@PathVariable(required = false) Long id, Model model){
        if(id!=null) {
            User user = userService.findById(id);
            model.addAttribute("user", user);
        }
        return "home";
    }
//    @GetMapping("/postForm/{id}")
//    public String getPostForm(@PathVariable Long id,Model model ){
//        model.addAttribute("user",userService.findById(id));
//        return "postForm";
//    }




}
