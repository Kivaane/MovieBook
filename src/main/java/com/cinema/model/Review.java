package com.cinema.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * OOP CONCEPT: INHERITANCE
 * Review extends UserContent, representing a user-generated critique of a movie.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Review extends UserContent {
    /* ENCAPSULATION: Review content is private and managed via getters/setters */
    private Integer reviewId;
    
    /**
     * OOP CONCEPT: POLYMORPHISM (Method Overriding)
     */
    @Override
    public void moderate() {
        System.out.println("Review Moderation: Screening content for Review ID " + reviewId);
    }
    private Integer userId;
    private String userName;
    private Integer movieId;
    private Double rating; // 1.0 to 5.0
    private String reviewText;
    private String reviewDate;
    private Integer helpfulCount;
    private Boolean isVerifiedBooking;
    
    public String toFileString() {
        return reviewId + "," + userId + "," + userName + "," + movieId + "," + 
               rating + "," + reviewText + "," + reviewDate + "," + helpfulCount + "," + 
               isVerifiedBooking;
    }
    
    public static Review fromFileString(String line) {
        try {
            String[] parts = line.split(",");
            Review review = new Review();
            review.setReviewId(Integer.parseInt(parts[0]));
            review.setUserId(Integer.parseInt(parts[1]));
            review.setUserName(parts[2]);
            review.setMovieId(Integer.parseInt(parts[3]));
            review.setRating(Double.parseDouble(parts[4]));
            review.setReviewText(parts[5]);
            review.setReviewDate(parts[6]);
            review.setHelpfulCount(Integer.parseInt(parts[7]));
            review.setIsVerifiedBooking(Boolean.parseBoolean(parts[8]));
            return review;
        } catch (Exception e) {
            return null;
        }
    }
}
