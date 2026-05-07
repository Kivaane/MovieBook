package com.cinema.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * OOP CONCEPT: INHERITANCE
 * Feedback extends UserContent, representing a user-submitted inquiry or suggestion.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Feedback extends UserContent {
    /* ENCAPSULATION: Feedback status and message are private */
    private Integer feedbackId;
    
    /**
     * OOP CONCEPT: POLYMORPHISM (Method Overriding)
     */
    @Override
    public void moderate() {
        System.out.println("Feedback Moderation: Processing '" + subject + "' for status update.");
    }
    private Integer userId;
    private String userName;
    private String feedbackType; // "Service", "Website", "Theater", "General"
    private String subject;
    private String message;
    private String submissionDate;
    private String status; // "pending", "reviewed", "resolved"
    
    public String toFileString() {
        return feedbackId + "," + userId + "," + userName + "," + feedbackType + "," + 
               subject + "," + message + "," + submissionDate + "," + status;
    }
    
    public static Feedback fromFileString(String line) {
        try {
            String[] parts = line.split(",");
            Feedback feedback = new Feedback();
            feedback.setFeedbackId(Integer.parseInt(parts[0]));
            feedback.setUserId(Integer.parseInt(parts[1]));
            feedback.setUserName(parts[2]);
            feedback.setFeedbackType(parts[3]);
            feedback.setSubject(parts[4]);
            feedback.setMessage(parts[5]);
            feedback.setSubmissionDate(parts[6]);
            feedback.setStatus(parts[7]);
            return feedback;
        } catch (Exception e) {
            return null;
        }
    }
}
