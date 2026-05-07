package com.cinema.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * OOP CONCEPT: INHERITANCE
 * Payment extends Transaction, representing a financial settlement for a booking.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Payment extends Transaction {
    /* ENCAPSULATION: Sensitive payment details are private */
    /**
     * OOP CONCEPT: POLYMORPHISM (Method Overriding)
     */
    @Override
    public void process() {
        System.out.println("Processing Payment #" + transactionId + " for amount: LKR " + amount);
    }
    private Integer bookingId;
    private Integer userId;
    private String paymentMethod; // "Credit Card", "Debit Card", "PayPal"
    private String transactionDate;
    private String cardLastFour;
    private String paymentGateway;
    
    public String toFileString() {
        return transactionId + "," + bookingId + "," + userId + "," + amount + "," + 
               paymentMethod + "," + transactionDate + "," + status + "," + 
               (cardLastFour != null ? cardLastFour : "null") + "," + paymentGateway;
    }
    
    public static Payment fromFileString(String line) {
        try {
            String[] parts = line.split(",");
            Payment payment = new Payment();
            payment.setTransactionId(Integer.parseInt(parts[0]));
            payment.setBookingId(Integer.parseInt(parts[1]));
            payment.setUserId(Integer.parseInt(parts[2]));
            payment.setAmount(Double.parseDouble(parts[3]));
            payment.setPaymentMethod(parts[4]);
            payment.setTransactionDate(parts[5]);
            payment.setStatus(parts[6]);
            payment.setCardLastFour(parts[7].equals("null") ? null : parts[7]);
            payment.setPaymentGateway(parts[8]);
            return payment;
        } catch (Exception e) {
            return null;
        }
    }
}
