package com.cinema.repository;

import com.cinema.model.Hall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class HallRepository {
    
    @Autowired
    private FileHandler fileHandler;
    
    private static final String FILENAME = "halls.txt";
    
    public Hall save(Hall hall) {
        if (hall.getHallId() == null) {
            hall.setHallId(fileHandler.getNextId(FILENAME));
        }
        fileHandler.writeToFile(FILENAME, hall.toFileString());
        return hall;
    }
    
    public Optional<Hall> findById(Integer id) {
        String line = fileHandler.findById(FILENAME, id);
        return line != null ? Optional.ofNullable(Hall.fromFileString(line)) : Optional.empty();
    }
    
    public List<Hall> findAll() {
        return fileHandler.readFromFile(FILENAME).stream()
                .map(Hall::fromFileString)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
    
    public boolean update(Hall hall) {
        int lineNumber = fileHandler.getLineNumberById(FILENAME, hall.getHallId());
        if (lineNumber >= 0) {
            return fileHandler.updateLine(FILENAME, lineNumber, hall.toFileString());
        }
        return false;
    }
    public boolean deleteById(Integer id) {
        int lineNumber = fileHandler.getLineNumberById(FILENAME, id);
        if (lineNumber >= 0) {
            return fileHandler.deleteLine(FILENAME, lineNumber);
        }
        return false;
    }
}
