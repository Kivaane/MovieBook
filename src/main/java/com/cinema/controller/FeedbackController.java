package com.cinema.controller;

import com.cinema.model.Feedback;
import com.cinema.model.User;
import com.cinema.service.FeedbackService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping
    public String showFeedbackPage(Model model) {
        model.addAttribute("feedback", new Feedback());
        return "feedback";
    }

    @PostMapping("/submit")
    public String submitFeedback(@ModelAttribute Feedback feedback, HttpSession session, RedirectAttributes ra) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            feedback.setUserId(user.getUserId());
            feedback.setUserName(user.getName());
        } else {
            feedback.setUserId(0); // Anonymous
            if (feedback.getUserName() == null || feedback.getUserName().isEmpty()) {
                feedback.setUserName("Anonymous");
            }
        }

        feedbackService.submitFeedback(feedback);
        ra.addFlashAttribute("success", "Thank you for your feedback! We value your input.");
        return "redirect:/feedback";
    }
}
