package com.cinema.service;

import com.cinema.model.Payment;
import com.cinema.repository.PaymentRepository;
import com.cinema.util.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaymentService {
    
    @Autowired
    private PaymentRepository paymentRepository;
    
    public Payment processPayment(Payment payment) {
        payment.setTransactionDate(DateFormatter.getCurrentDateTime());
        payment.setStatus("completed");
        payment.setPaymentGateway("CineGate Premium");
        return paymentRepository.save(payment);
    }
    
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}
