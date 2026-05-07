package com.cinema.controller;

import com.cinema.model.Movie;
import com.cinema.model.Hall;
import com.cinema.model.Booking;
import com.cinema.service.MovieService;
import com.cinema.service.HallService;
import com.cinema.service.BookingService;
import com.cinema.service.UserService;
import com.cinema.service.PaymentService;
import com.cinema.service.FeedbackService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private HallService hallService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private PaymentService paymentService;
    
    @Autowired
    private FeedbackService feedbackService;

    private boolean isAdmin(HttpSession session) {
        String role = (String) session.getAttribute("userRole");
        return "admin".equalsIgnoreCase(role);
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (!isAdmin(session)) return "redirect:/user/login";
        
        model.addAttribute("movieCount", movieService.getAllMovies().size());
        model.addAttribute("hallCount", hallService.getAllHalls().size());
        model.addAttribute("bookingCount", bookingService.getAllBookings().size());
        model.addAttribute("userCount", userService.getAllUsers().size());
        model.addAttribute("recentBookings", bookingService.getAllBookings());
        return "admin/dashboard";
    }

    // Movie Management
    @GetMapping("/movies")
    public String manageMovies(HttpSession session, Model model) {
        if (!isAdmin(session)) return "redirect:/user/login";
        model.addAttribute("movies", movieService.getAllMovies());
        return "admin/movies";
    }

    @PostMapping("/movies/add")
    public String addMovie(@ModelAttribute Movie movie, HttpSession session, RedirectAttributes ra) {
        if (!isAdmin(session)) return "redirect:/user/login";
        movieService.saveMovie(movie);
        ra.addFlashAttribute("success", "Movie added successfully!");
        return "redirect:/admin/movies";
    }

    @PostMapping("/movies/edit")
    public String editMovie(@ModelAttribute Movie movie, HttpSession session, RedirectAttributes ra) {
        if (!isAdmin(session)) return "redirect:/user/login";
        movieService.updateMovie(movie);
        ra.addFlashAttribute("success", "Movie updated successfully!");
        return "redirect:/admin/movies";
    }

    @GetMapping("/movies/delete/{id}")
    public String deleteMovie(@PathVariable Integer id, HttpSession session, RedirectAttributes ra) {
        if (!isAdmin(session)) return "redirect:/user/login";
        movieService.deleteMovie(id);
        ra.addFlashAttribute("success", "Movie deleted successfully!");
        return "redirect:/admin/movies";
    }

    // Hall Management
    @GetMapping("/halls")
    public String manageHalls(HttpSession session, Model model) {
        if (!isAdmin(session)) return "redirect:/user/login";
        model.addAttribute("halls", hallService.getAllHalls());
        return "admin/halls";
    }

    @PostMapping("/halls/add")
    public String addHall(@ModelAttribute Hall hall, HttpSession session, RedirectAttributes ra) {
        if (!isAdmin(session)) return "redirect:/user/login";
        hallService.saveHall(hall);
        ra.addFlashAttribute("success", "Hall added successfully!");
        return "redirect:/admin/halls";
    }

    @GetMapping("/halls/delete/{id}")
    public String deleteHall(@PathVariable Integer id, HttpSession session, RedirectAttributes ra) {
        if (!isAdmin(session)) return "redirect:/user/login";
        hallService.deleteHall(id);
        ra.addFlashAttribute("success", "Hall deleted successfully!");
        return "redirect:/admin/halls";
    }

    // Booking Management
    @GetMapping("/bookings")
    public String manageBookings(HttpSession session, Model model) {
        if (!isAdmin(session)) return "redirect:/user/login";
        model.addAttribute("bookings", bookingService.getAllBookings());
        return "admin/bookings";
    }
    
    // Payment/Transaction Management
    @GetMapping("/transactions")
    public String manageTransactions(HttpSession session, Model model) {
        if (!isAdmin(session)) return "redirect:/user/login";
        model.addAttribute("payments", paymentService.getAllPayments());
        return "admin/transactions";
    }

    // Feedback Management
    @GetMapping("/feedback")
    public String manageFeedback(HttpSession session, Model model) {
        if (!isAdmin(session)) return "redirect:/user/login";
        model.addAttribute("feedbacks", feedbackService.getAllFeedback());
        return "admin/feedback";
    }
}
