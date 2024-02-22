package com.example.otpgen;

import com.example.otpgen.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class OtpGenApplication {

//    @Autowired
//    private UserServiceImpl userService;

    public static void main(String[] args) {
        SpringApplication.run(OtpGenApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public  void sendEmail(){
//        userService.sendEmail("oliverstojanov24@yahoo.com",
//                "This is the subject",
//                "This is the body of the email...");
//    }


}
