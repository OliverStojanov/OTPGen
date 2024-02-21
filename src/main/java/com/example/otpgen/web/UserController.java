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
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        // Check if the user is authenticated
//        if (authentication != null && authentication.isAuthenticated()) {
//            // Get the user ID or any other user details
//            //    Long userId = getUserIdFromAuthentication(authentication);
//
//            // Pass the user ID to the view
//            //  model.addAttribute("userId", userId);
//        }
//        //model.addAttribute("user" , userService.findById)
        return "home";
    }

}
