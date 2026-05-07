package com.cinema.model;

import lombok.Data;
import java.io.Serializable;

@Data
public abstract class Venue implements Serializable {
    protected Integer venueId;
    protected String venueName;
    protected Integer totalCapacity;
    
    public abstract void displayVenueInfo();
}
