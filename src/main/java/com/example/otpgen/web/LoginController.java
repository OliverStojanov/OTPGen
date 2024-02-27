package com.example.otpgen.web;

import com.example.otpgen.model.OTP;
import com.example.otpgen.model.User;
import com.example.otpgen.model.exceptions.*;
import com.example.otpgen.service.OTPService;
import com.example.otpgen.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    private final OTPService otpService;
    public final UserService userService;
    public LoginController(OTPService otpService, UserService userService) {
        this.otpService = otpService;
        this.userService = userService;
    }
    @GetMapping("/login")
    public String getLoginPage() {
        otpService.purgeInvalidOtp();
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(HttpServletRequest request, RedirectAttributes redirectAttributes, Model model) {
        User user = null;

        try {
            user = userService.login(request.getParameter("username"), request.getParameter("password"));
            model.addAttribute("user",user);
            request.getSession().setAttribute("user", user);
            OTP otp = otpService.createTOTP(user);
                try{
                    userService.sendEmailFromTemplate(request.getParameter("username"),user,otp.otp);
                }
                catch (Exception e){
                    e.getMessage();
                }
            redirectAttributes.addAttribute("id", user.id);
            return "redirect:/verify/{id}";
        } catch (InvalidUserCredentialsException | InvalidArgumentsException | InvalidEmailOrPasswordException exception) {
            model.addAttribute("bodyContent", "login");
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            return "redirect:/login?error=" + exception.getMessage();
        }
    }

    @GetMapping("/verify/{id}")
    public String getVerifyPage(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "otpVerification";
    }

    @PostMapping("/verify/{id}")
    public String verifyLogin(HttpServletRequest request, Model model, @PathVariable Long id){
        try {
            User user = userService.findById(id);
            User checkedOtp = otpService.checkOtp(request.getParameter("otp"),user);
            model.addAttribute("user", user);
            request.getSession().setAttribute("user", user);
            //TODO: redirect na forum
            return "redirect:/forum/{id}";
        }catch (InvalidUserCredentialsException | TimeRunOutException | OTPDoesNotMatchException | OtpDoesNotExistException exception){
            model.addAttribute("error", exception.getMessage());
            return "redirect:/verify/{id}?error=" + exception.getMessage();
        }
    }

    @GetMapping("/resend/{id}")
    public String resend(@PathVariable Long id, HttpServletRequest request, RedirectAttributes redirectAttributes, Model model) {
            User user = userService.findById(id);

        try {
            model.addAttribute("user",user);
            request.getSession().setAttribute("user", user);
            OTP otp = otpService.createTOTP(user);
            try{
                userService.sendEmailFromTemplate(user.email,user,otp.otp);
            }
            catch (Exception e){
                e.getMessage();
            }
            redirectAttributes.addAttribute("id", user.id);
            return "redirect:/verify/{id}";
        } catch (InvalidUserCredentialsException | InvalidArgumentsException | InvalidEmailOrPasswordException exception) {
            model.addAttribute("bodyContent", "login");
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            return "redirect:/verify/{id}?error=" + exception.getMessage();
        }
    }
}
