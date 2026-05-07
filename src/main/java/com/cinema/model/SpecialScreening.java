package com.cinema.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SpecialScreening extends Movie {
    private String eventName;
    private String guestStars;
    
    @Override
    public void displayDetails() {
        System.out.println("Special Screening: " + eventName + " for " + getTitle());
    }
}
