package com.cinema.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GroupBooking extends Booking {
    private Integer groupSize;
    private String organizationName;
    
    @Override
    public void showReservationDetails() {
        System.out.println("Group Booking: " + organizationName + " Size: " + groupSize);
    }
}
