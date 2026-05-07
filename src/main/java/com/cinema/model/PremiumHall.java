package com.cinema.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PremiumHall extends Hall {
    private String conciergeService;
    private String menuOptions;
    
    @Override
    public void displayVenueInfo() {
        System.out.println("Premium Hall: " + getHallName() + " with " + conciergeService);
    }
}
