package com.cinema.controller;

import com.cinema.model.Booking;
import com.cinema.model.Movie;
import com.cinema.model.Hall;
import com.cinema.service.BookingService;
import com.cinema.service.MovieService;
import com.cinema.service.HallService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/bookings")
public class BookingController {
    
    @Autowired
    private BookingService bookingService;
    
    @Autowired
    private MovieService movieService;
    
    @Autowired
    private HallService hallService;
    
    @GetMapping("/book/{movieId}")
    public String showBookingPage(@PathVariable Integer movieId, 
                                 @RequestParam(required = false) Integer hallId,
                                 @RequestParam(required = false) String showtime,
                                 Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/user/login";
        }
        
        movieService.getMovieById(movieId).ifPresent(movie -> {
            model.addAttribute("movie", movie);
            model.addAttribute("halls", hallService.getAllHalls());
            
            if (hallId != null) {
                hallService.getHallById(hallId).ifPresent(hall -> model.addAttribute("selectedHall", hall));
            }
            if (showtime != null) {
                model.addAttribute("selectedShowtime", showtime);
            }
        });
        
        return "booking";
    }
    
    @PostMapping("/confirm")
    public String confirmBooking(@ModelAttribute Booking booking, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/user/login";
        }
        
        Integer userId = (Integer) session.getAttribute("userId");
        booking.setUserId(userId);
        
        session.setAttribute("pendingBooking", booking);
        return "redirect:/payment";
    }
    
    @GetMapping("/my-bookings")
    public String myBookings(HttpSession session, Model model) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/user/login";
        }
        model.addAttribute("bookings", bookingService.getMyBookings(userId));
        return "my-bookings";
    }
    @GetMapping("/cancel/{id}")
    public String cancelBooking(@PathVariable Integer id, HttpSession session, RedirectAttributes ra) {
        if (session.getAttribute("user") == null) {
            return "redirect:/user/login";
        }
        
        if (bookingService.cancelBooking(id)) {
            ra.addFlashAttribute("success", "Booking cancelled successfully!");
        } else {
            ra.addFlashAttribute("error", "Cancellation failed!");
        }
        return "redirect:/bookings/my-bookings";
    }
}
