package com.cinema.service;

import com.cinema.model.Booking;
import com.cinema.model.Movie;
import com.cinema.model.User;
import com.cinema.repository.BookingRepository;
import com.cinema.repository.MovieRepository;
import com.cinema.repository.UserRepository;
import com.cinema.util.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for managing movie bookings and seat reservations.
 * Demonstrates business logic implementation including double-booking prevention.
 */
@Service
public class BookingService {
    
    @Autowired
    private BookingRepository bookingRepository;
    
    @Autowired
    private MovieRepository movieRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private SeatService seatService;
    
    public Booking createBooking(Booking booking) {
        // Double-booking prevention: Check if any selected seat is already booked
        String[] seats = booking.getSeats().split("-");
        List<String> bookedSeats = seatService.getBookedSeats(booking.getMovieId(), 
                                                           booking.getHallId(), 
                                                           booking.getShowtime())
                                   .stream().map(com.cinema.model.Seat::getSeatNumber)
                                   .collect(Collectors.toList());
        
        for (String seat : seats) {
            if (bookedSeats.contains(seat)) {
                throw new RuntimeException("Seat " + seat + " is already booked by another customer!");
            }
        }

        booking.setBookingDate(DateFormatter.getCurrentDateTime());
        booking.setStatus("confirmed");
        Booking savedBooking = bookingRepository.save(booking);
        
        // Mark seats as booked
        seatService.markSeatsAsBooked(booking.getMovieId(), booking.getHallId(), 
                                    booking.getShowtime(), booking.getSeats(), 
                                    booking.getUserId());
        
        return savedBooking;
    }
    
    public List<Booking> getMyBookings(Integer userId) {
        List<Booking> bookings = bookingRepository.findByUserId(userId);
        // Enrich with movie and user details
        return bookings.stream().map(b -> {
            movieRepository.findById(b.getMovieId()).ifPresent(m -> b.setMovieTitle(m.getTitle()));
            userRepository.findById(b.getUserId()).ifPresent(u -> b.setUserName(u.getName()));
            return b;
        }).collect(Collectors.toList());
    }
    
    public Optional<Booking> getBookingById(Integer id) {
        return bookingRepository.findById(id);
    }
    
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
    
    public boolean cancelBooking(Integer bookingId) {
        Optional<Booking> bookingOpt = bookingRepository.findById(bookingId);
        if (bookingOpt.isPresent()) {
            Booking booking = bookingOpt.get();
            booking.setStatus("cancelled");
            bookingRepository.update(booking);
            
            // Mark seats as available (remove from seats.txt or update status)
            // In our current SeatService, we just append to seats.txt. 
            // For cancellation, we should ideally remove those lines or add a "cancelled" entry.
            // Let's add a "cancelled" entry in seats.txt which our getBookedSeats will filter out.
            seatService.cancelSeats(booking.getMovieId(), booking.getHallId(), 
                                  booking.getShowtime(), booking.getSeats());
            return true;
        }
        return false;
    }
}
