package com.cinema.service;

import com.cinema.model.Review;
import com.cinema.repository.ReviewRepository;
import com.cinema.util.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReviewService {
    
    @Autowired
    private ReviewRepository reviewRepository;
    
    public Review addReview(Review review) {
        review.setReviewDate(DateFormatter.getCurrentDate());
        review.setHelpfulCount(0);
        return reviewRepository.save(review);
    }
    
    public List<Review> getReviewsByMovie(Integer movieId) {
        return reviewRepository.findByMovieId(movieId);
    }
    
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
}
