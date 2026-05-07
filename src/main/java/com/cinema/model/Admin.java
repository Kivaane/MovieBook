package com.cinema.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Admin extends User {
    private String adminLevel;
    private String[] permissions;
    
    @Override
    public void displayInfo() {
        System.out.println("Admin: " + getName() + " - Level: " + adminLevel);
    }
}
