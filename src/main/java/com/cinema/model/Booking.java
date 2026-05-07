package com.cinema.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * OOP CONCEPT: INHERITANCE
 * Booking extends Reservation, representing a customer movie booking.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Booking extends Reservation {
    /* ENCAPSULATION: Booking details are private and validated through services */
    private Integer bookingId;
    
    /**
     * OOP CONCEPT: POLYMORPHISM (Method Overriding)
     */
    @Override
    public void showReservationDetails() {
        System.out.println("Booking #" + bookingId + " for Movie ID: " + movieId);
    }
    private Integer userId;
    private Integer movieId;
    private Integer hallId;
    private String showtime;
    private String seats; // "A1-A2-A3"
    private Double totalPrice;
    private String bookingDate;
    private String status; // "confirmed", "cancelled"
    private Integer transactionId;
    
    // For display purposes
    private String movieTitle;
    private String userName;
    private String hallName;
    
    public String toFileString() {
        return bookingId + "," + userId + "," + movieId + "," + hallId + "," + 
               showtime + "," + seats + "," + totalPrice + "," + bookingDate + "," + 
               status + "," + transactionId;
    }
    
    public static Booking fromFileString(String line) {
        try {
            String[] parts = line.split(",");
            Booking booking = new Booking();
            booking.setBookingId(Integer.parseInt(parts[0]));
            booking.setUserId(Integer.parseInt(parts[1]));
            booking.setMovieId(Integer.parseInt(parts[2]));
            booking.setHallId(Integer.parseInt(parts[3]));
            booking.setShowtime(parts[4]);
            booking.setSeats(parts[5]);
            booking.setTotalPrice(Double.parseDouble(parts[6]));
            booking.setBookingDate(parts[7]);
            booking.setStatus(parts[8]);
            booking.setTransactionId(parts.length > 9 && !parts[9].equals("null") ? Integer.parseInt(parts[9]) : null);
            return booking;
        } catch (Exception e) {
            return null;
        }
    }
    
    public String[] getSeatArray() {
        return seats.split("-");
    }
    
    public int getSeatCount() {
        return getSeatArray().length;
    }
}
