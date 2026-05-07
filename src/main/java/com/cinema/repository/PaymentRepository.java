package com.cinema.repository;

import com.cinema.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class PaymentRepository {
    
    @Autowired
    private FileHandler fileHandler;
    
    private static final String FILENAME = "payments.txt";
    
    public Payment save(Payment payment) {
        if (payment.getTransactionId() == null) {
            payment.setTransactionId(fileHandler.getNextId(FILENAME));
        }
        fileHandler.writeToFile(FILENAME, payment.toFileString());
        return payment;
    }
    
    public Optional<Payment> findById(Integer id) {
        String line = fileHandler.findById(FILENAME, id);
        return line != null ? Optional.ofNullable(Payment.fromFileString(line)) : Optional.empty();
    }
    
    public List<Payment> findAll() {
        return fileHandler.readFromFile(FILENAME).stream()
                .map(Payment::fromFileString)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
