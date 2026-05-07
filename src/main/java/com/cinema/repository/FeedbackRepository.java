package com.cinema.repository;

import com.cinema.model.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class FeedbackRepository {
    
    @Autowired
    private FileHandler fileHandler;
    
    private static final String FILENAME = "feedback.txt";
    
    public Feedback save(Feedback feedback) {
        if (feedback.getFeedbackId() == null) {
            feedback.setFeedbackId(fileHandler.getNextId(FILENAME));
        }
        fileHandler.writeToFile(FILENAME, feedback.toFileString());
        return feedback;
    }
    
    public List<Feedback> findAll() {
        return fileHandler.readFromFile(FILENAME).stream()
                .map(Feedback::fromFileString)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
