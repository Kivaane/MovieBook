package com.cinema.controller;

import com.cinema.model.Review;
import com.cinema.model.User;
import com.cinema.service.ReviewService;
import com.cinema.service.BookingService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private BookingService bookingService;

    @PostMapping("/add")
    public String addReview(@ModelAttribute Review review, HttpSession session, RedirectAttributes ra) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            ra.addFlashAttribute("error", "You must be logged in to post a review.");
            return "redirect:/user/login";
        }

        review.setUserId(user.getUserId());
        review.setUserName(user.getName());
        
        // Check if verified booking
        boolean hasBooked = bookingService.getMyBookings(user.getUserId()).stream()
                .anyMatch(b -> b.getMovieId().equals(review.getMovieId()) && "confirmed".equalsIgnoreCase(b.getStatus()));
        review.setIsVerifiedBooking(hasBooked);

        reviewService.addReview(review);
        ra.addFlashAttribute("success", "Review submitted successfully!");
        return "redirect:/movies/" + review.getMovieId();
    }
}
