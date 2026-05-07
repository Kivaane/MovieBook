package com.cinema.service;

import com.cinema.model.Seat;
import com.cinema.repository.FileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatService {
    
    @Autowired
    private FileHandler fileHandler;
    
    private static final String FILENAME = "seats.txt";
    
    public List<Seat> getBookedSeats(Integer movieId, Integer hallId, String showtime) {
        return fileHandler.readFromFile(FILENAME).stream()
                .map(Seat::fromFileString)
                .filter(s -> s != null && s.getMovieId().equals(movieId) && 
                            s.getHallId().equals(hallId) && 
                            s.getShowtime().equalsIgnoreCase(showtime) && 
                            s.getStatus().equalsIgnoreCase("booked"))
                .collect(Collectors.toList());
    }
    
    public void markSeatsAsBooked(Integer movieId, Integer hallId, String showtime, String seats, Integer userId) {
        String[] seatArray = seats.split("-");
        for (String seatNum : seatArray) {
            Seat seat = new Seat(hallId, movieId, showtime, seatNum, "Regular", "booked", userId);
            fileHandler.writeToFile(FILENAME, seat.toFileString());
        }
    }
    public void cancelSeats(Integer movieId, Integer hallId, String showtime, String seats) {
        String[] seatArray = seats.split("-");
        for (String seatNum : seatArray) {
            // In a simple file implementation, we can just add a "cancelled" record
            // and filter it out in getBookedSeats
            Seat seat = new Seat(hallId, movieId, showtime, seatNum, "Regular", "available", 0);
            fileHandler.writeToFile(FILENAME, seat.toFileString());
        }
    }
}
