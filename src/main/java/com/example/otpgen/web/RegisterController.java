package com.example.otpgen.web;

import com.example.otpgen.model.exceptions.InvalidArgumentsException;
import com.example.otpgen.model.exceptions.PasswordsDoNotMatchException;
import com.example.otpgen.model.exceptions.UsernameAlreadyExistsException;
import com.example.otpgen.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {
    public final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String name,
                           @RequestParam String surname,
                           Model model
    ) {
        try{
            this.userService.register(username, password, repeatedPassword, name, surname);
            return "redirect:/login";
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException | UsernameAlreadyExistsException exception) {
            model.addAttribute("error", exception.getMessage());
            return "redirect:/register?error=" + exception.getMessage();
        }
    }
}
