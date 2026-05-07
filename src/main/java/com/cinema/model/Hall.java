package com.cinema.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * OOP CONCEPT: INHERITANCE
 * Hall extends Venue, representing a specific type of screening location.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Hall extends Venue {
    /* ENCAPSULATION: Detailed specifications are private */
    private Integer hallId;
    
    /**
     * OOP CONCEPT: POLYMORPHISM (Method Overriding)
     */
    @Override
    public void displayVenueInfo() {
        System.out.println("Hall: " + hallName + " (" + hallType + ")");
    }
    private String hallName;
    private String hallType; // "Standard", "IMAX", "3D", "4DX"
    private Integer totalSeats;
    private Integer rows;
    private Integer seatsPerRow;
    private Integer premiumSeats;
    private Integer regularSeats;
    private Integer vipSeats;
    private String facilities; // "Dolby Atmos|Recliner Seats"
    private Boolean isActive;
    
    public String toFileString() {
        return hallId + "," + hallName + "," + hallType + "," + totalSeats + "," + 
               rows + "," + seatsPerRow + "," + premiumSeats + "," + regularSeats + "," + 
               vipSeats + "," + facilities + "," + isActive;
    }
    
    public static Hall fromFileString(String line) {
        try {
            String[] parts = line.split(",");
            Hall hall = new Hall();
            hall.setHallId(Integer.parseInt(parts[0]));
            hall.setHallName(parts[1]);
            hall.setHallType(parts[2]);
            hall.setTotalSeats(Integer.parseInt(parts[3]));
            hall.setRows(Integer.parseInt(parts[4]));
            hall.setSeatsPerRow(Integer.parseInt(parts[5]));
            hall.setPremiumSeats(Integer.parseInt(parts[6]));
            hall.setRegularSeats(Integer.parseInt(parts[7]));
            hall.setVipSeats(Integer.parseInt(parts[8]));
            hall.setFacilities(parts[9]);
            hall.setIsActive(Boolean.parseBoolean(parts[10]));
            return hall;
        } catch (Exception e) {
            return null;
        }
    }
    
    public String[] getFacilityArray() {
        return facilities.split("\\|");
    }
}
