package com.cinema.repository;

import com.cinema.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class ReviewRepository {
    
    @Autowired
    private FileHandler fileHandler;
    
    private static final String FILENAME = "reviews.txt";
    
    public Review save(Review review) {
        if (review.getReviewId() == null) {
            review.setReviewId(fileHandler.getNextId(FILENAME));
        }
        fileHandler.writeToFile(FILENAME, review.toFileString());
        return review;
    }
    
    public List<Review> findByMovieId(Integer movieId) {
        return fileHandler.readFromFile(FILENAME).stream()
                .map(Review::fromFileString)
                .filter(Objects::nonNull)
                .filter(r -> r.getMovieId().equals(movieId))
                .collect(Collectors.toList());
    }
    
    public List<Review> findAll() {
        return fileHandler.readFromFile(FILENAME).stream()
                .map(Review::fromFileString)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
