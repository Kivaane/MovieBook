package com.cinema.repository;

import com.cinema.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class BookingRepository {
    
    @Autowired
    private FileHandler fileHandler;
    
    private static final String FILENAME = "bookings.txt";
    
    public Booking save(Booking booking) {
        if (booking.getBookingId() == null) {
            booking.setBookingId(fileHandler.getNextId(FILENAME));
        }
        fileHandler.writeToFile(FILENAME, booking.toFileString());
        return booking;
    }
    
    public Optional<Booking> findById(Integer id) {
        String line = fileHandler.findById(FILENAME, id);
        return line != null ? Optional.ofNullable(Booking.fromFileString(line)) : Optional.empty();
    }
    
    public List<Booking> findAll() {
        return fileHandler.readFromFile(FILENAME).stream()
                .map(Booking::fromFileString)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
    
    public List<Booking> findByUserId(Integer userId) {
        return findAll().stream()
                .filter(b -> b.getUserId().equals(userId))
                .collect(Collectors.toList());
    }
    
    public boolean update(Booking booking) {
        int lineNumber = fileHandler.getLineNumberById(FILENAME, booking.getBookingId());
        if (lineNumber >= 0) {
            return fileHandler.updateLine(FILENAME, lineNumber, booking.toFileString());
        }
        return false;
    }
}
