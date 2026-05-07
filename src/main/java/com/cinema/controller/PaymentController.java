package com.cinema.controller;

import com.cinema.model.Booking;
import com.cinema.model.Payment;
import com.cinema.service.BookingService;
import com.cinema.service.MovieService;
import com.cinema.service.PaymentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public String showPaymentPage(HttpSession session, Model model) {
        Booking booking = (Booking) session.getAttribute("pendingBooking");
        if (booking == null) {
            return "redirect:/movies";
        }

        movieService.getMovieById(booking.getMovieId()).ifPresent(m -> booking.setMovieTitle(m.getTitle()));

        model.addAttribute("booking", booking);
        return "payment";
    }

    @PostMapping("/process")
    public String processPayment(@ModelAttribute Payment payment, HttpSession session, Model model) {
        Booking booking = (Booking) session.getAttribute("pendingBooking");
        if (booking == null)
            return "redirect:/movies";

        // Save booking first
        Booking confirmedBooking = bookingService.createBooking(booking);

        // Process payment
        payment.setBookingId(confirmedBooking.getBookingId());
        payment.setUserId(confirmedBooking.getUserId());
        payment.setAmount(confirmedBooking.getTotalPrice());
        paymentService.processPayment(payment);

        // Clear session
        session.removeAttribute("pendingBooking");

        model.addAttribute("booking", confirmedBooking);
        return "payment-success";
    }
}
