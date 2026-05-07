package com.cinema.controller;

import com.cinema.model.Seat;
import com.cinema.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/seats")
public class SeatRestController {
    
    @Autowired
    private SeatService seatService;
    
    @GetMapping("/booked")
    public List<String> getBookedSeats(@RequestParam Integer movieId, 
                                     @RequestParam Integer hallId, 
                                     @RequestParam String showtime) {
        return seatService.getBookedSeats(movieId, hallId, showtime).stream()
                .map(Seat::getSeatNumber)
                .collect(Collectors.toList());
    }
}
