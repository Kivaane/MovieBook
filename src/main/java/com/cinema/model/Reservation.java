package com.cinema.model;

import lombok.Data;
import java.io.Serializable;

@Data
public abstract class Reservation implements Serializable {
    protected Integer reservationId;
    protected Integer customerId;
    protected String reservationDate;
    
    public abstract void showReservationDetails();
}
