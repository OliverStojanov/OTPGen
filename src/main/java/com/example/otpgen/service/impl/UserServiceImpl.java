package com.example.otpgen.service.impl;

import com.example.otpgen.model.Post;
import com.example.otpgen.model.Role;
import com.example.otpgen.model.User;
import com.example.otpgen.model.exceptions.*;
import com.example.otpgen.repository.UserRepository;
import com.example.otpgen.service.UserService;
import jakarta.mail.MessagingException;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    public final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public User login(String email, String password) {
        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        User user = userRepository.findByEmailEquals(email).orElseThrow(InvalidEmailOrPasswordException::new);
        String hashedPass = user.password;
        if(userRepository.findByEmailEquals(email).isPresent() && passwordEncoder.matches(password,hashedPass)){
            return user;
        }
        throw new InvalidUserCredentialsException();
    }

    @Override
    public User register(String email, String password, String repeatedPassword, String name, String surname, Role role) {
        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            throw new InvalidEmailOrPasswordException();
        }
        if (!password.equals(repeatedPassword)) {
            throw new PasswordsDoNotMatchException();
        }
        if(this.userRepository.findByEmail(email).isPresent()) {
            throw new UsernameAlreadyExistsException(email);
        }
        User user = new User(email, passwordEncoder.encode(password), name, surname, role);
        userRepository.save(user);
        return user;
    }

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public void sendEmail(String toEmail, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("otprojectib@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);

        System.out.println("Mail sent successfully...");
    }
    public void sendEmailFromTemplate(String toEmail, User user, String otp) throws MessagingException {

                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);

                helper.setTo(toEmail);
                helper.setSubject("New Login Attempt");

                Context context = new Context();
                context.setVariable("user", user);
                context.setVariable("otp", otp);

                String htmlContent = templateEngine.process("emailTemplate",context);
                helper.setText(htmlContent, true);
                mailSender.send(message);
    }

    @Override
    public List<Post> findAll(Long id) {
        return findById(id).posts;
    }

    @Override
    public void addNewPost(Post newPost) {
        User user = newPost.getUser();
        userRepository.save(user);
        return;
    }
}
