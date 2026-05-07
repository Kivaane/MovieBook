package com.cinema.model;

import lombok.Data;
import java.io.Serializable;

@Data
public abstract class Transaction implements Serializable {
    protected Integer transactionId;
    protected Double amount;
    protected String status;
    
    public abstract void process();
}
