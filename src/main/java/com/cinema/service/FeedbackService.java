package com.cinema.service;

import com.cinema.model.Feedback;
import com.cinema.repository.FeedbackRepository;
import com.cinema.util.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FeedbackService {
    
    @Autowired
    private FeedbackRepository feedbackRepository;
    
    public Feedback submitFeedback(Feedback feedback) {
        feedback.setSubmissionDate(DateFormatter.getCurrentDate());
        feedback.setStatus("pending");
        return feedbackRepository.save(feedback);
    }
    
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }
}
