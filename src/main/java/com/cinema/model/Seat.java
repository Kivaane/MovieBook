package com.cinema.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seat implements Serializable {
    private Integer hallId;
    private Integer movieId;
    private String showtime;
    private String seatNumber;
    private String seatType; // "Regular", "Premium", "VIP"
    private String status; // "available", "booked"
    private Integer bookedBy; // userId
    
    public String toFileString() {
        return hallId + "," + movieId + "," + showtime + "," + seatNumber + "," + 
               seatType + "," + status + "," + (bookedBy != null ? bookedBy : "null");
    }
    
    public static Seat fromFileString(String line) {
        try {
            String[] parts = line.split(",");
            Seat seat = new Seat();
            seat.setHallId(Integer.parseInt(parts[0]));
            seat.setMovieId(Integer.parseInt(parts[1]));
            seat.setShowtime(parts[2]);
            seat.setSeatNumber(parts[3]);
            seat.setSeatType(parts[4]);
            seat.setStatus(parts[5]);
            seat.setBookedBy(parts[6].equals("null") ? null : Integer.parseInt(parts[6]));
            return seat;
        } catch (Exception e) {
            return null;
        }
    }
}
