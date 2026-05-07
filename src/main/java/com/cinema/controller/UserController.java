package com.cinema.controller;

import com.cinema.model.User;
import com.cinema.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
    
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, 
                              RedirectAttributes redirectAttributes) {
        try {
            userService.registerUser(user);
            redirectAttributes.addFlashAttribute("success", 
                "Registration successful! Please login.");
            return "redirect:/user/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/user/register";
        }
    }
    
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
    
    @PostMapping("/login")
    public String loginUser(@RequestParam String email, 
                           @RequestParam String password,
                           HttpSession session,
                           RedirectAttributes redirectAttributes) {
        Optional<User> user = userService.loginUser(email, password);
        if (user.isPresent()) {
            session.setAttribute("user", user.get());
            session.setAttribute("userId", user.get().getUserId());
            session.setAttribute("userName", user.get().getName());
            session.setAttribute("userRole", user.get().getRole());
            return "redirect:/movies";
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid credentials!");
            return "redirect:/user/login";
        }
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
    
    @GetMapping("/profile")
    public String showProfile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        model.addAttribute("user", user);
        return "profile";
    }
    
    @PostMapping("/update")
    public String updateProfile(@ModelAttribute User user,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {
        if (userService.updateUser(user)) {
            session.setAttribute("user", user);
            redirectAttributes.addFlashAttribute("success", "Profile updated!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Update failed!");
        }
        return "redirect:/user/profile";
    }
}
