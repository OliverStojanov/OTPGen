package com.example.otpgen.web;

import com.example.otpgen.model.User;
import com.example.otpgen.model.exceptions.InvalidArgumentsException;
import com.example.otpgen.model.exceptions.InvalidUserCredentialsException;
import com.example.otpgen.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    public final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(HttpServletRequest request, Model model) {
        User user = null;

        try {
            user = userService.login(request.getParameter("username"), request.getParameter("password"));
            request.getSession().setAttribute("user", user);
            return "redirect:/";
        } catch (InvalidUserCredentialsException | InvalidArgumentsException exception) {
            model.addAttribute("bodyContent", "login");
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            return "redirect:/login?error=" + exception.getMessage();
        }
    }
}
