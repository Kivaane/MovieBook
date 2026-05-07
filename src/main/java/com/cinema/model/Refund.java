package com.cinema.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Refund extends Transaction {
    private Integer refundId;
    private String reason;
    private String refundDate;
    
    @Override
    public void process() {
        System.out.println("Processing refund of: " + amount + " due to: " + reason);
    }
}
