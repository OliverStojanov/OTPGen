package com.example.otpgen.web;

import com.example.otpgen.model.Post;
import com.example.otpgen.model.User;
import com.example.otpgen.service.PostService;
import com.example.otpgen.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PostController {

    public final UserService userService;
    public final PostService postService;

    public PostController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }


    @GetMapping("/postForm/{id}")
    public String showPostForm(@PathVariable Long id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "postForm";
    }

    @PostMapping("/postForm/{id}")
    public String createNewPost(@PathVariable Long id, RedirectAttributes redirectAttributes , HttpServletRequest request, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        userService.addNewPost(postService.createNewPost(user, request.getParameter("title"), request.getParameter("content")));
        model.addAttribute("posts", postService.findAll());
        redirectAttributes.addAttribute("id", user.id);
        return "redirect:/forum/{id}";
    }

    @PostMapping("/myPostForm/{id}")
    public String newPost(@PathVariable Long id, RedirectAttributes redirectAttributes, HttpServletRequest request, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        userService.addNewPost(postService.createNewPost(user, request.getParameter("title"), request.getParameter("content")));
        model.addAttribute("posts", userService.findAll(id));
        redirectAttributes.addAttribute("id", user.id);
        return "redirect:/userDetails/{id}";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id,  RedirectAttributes redirectAttributes) {
        Post post = (Post) postService.findById(id);
        redirectAttributes.addAttribute("id", post.user.id);
        this.postService.delete(id);
        return "redirect:/forum/{id}";
    }

    @PostMapping("/deleteMine/{id}")
    public String deleteMyPost(@PathVariable Long id,  RedirectAttributes redirectAttributes) {
        Post post = (Post) postService.findById(id);
        redirectAttributes.addAttribute("id", post.user.id);
        this.postService.delete(id);
        return "redirect:/userDetails/{id}";
    }
}
