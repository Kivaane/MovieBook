package com.cinema.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * OOP CONCEPT: INHERITANCE
 * User extends Person, inheriting all its attributes and methods.
 * This demonstrates the "IS-A" relationship.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends Person {
    /* Encapsulated fields with private access */
    private Integer userId;
    private String password;
    private String phone;
    private String role; // "customer" or "admin"
    private String registrationDate;
    
    /**
     * OOP CONCEPT: POLYMORPHISM (Method Overriding)
     */
    @Override
    public void displayInfo() {
        System.out.println("User: " + name + " (" + email + ")");
    }
    
    // Data format for file storage
    public String toFileString() {
        return userId + "," + name + "," + email + "," + password + "," + 
               phone + "," + role + "," + registrationDate;
    }
    
    // Parse from file line
    public static User fromFileString(String line) {
        try {
            String[] parts = line.split(",");
            User user = new User();
            user.setUserId(Integer.parseInt(parts[0]));
            user.setName(parts[1]);
            user.setEmail(parts[2]);
            user.setPassword(parts[3]);
            user.setPhone(parts[4]);
            user.setRole(parts[5]);
            user.setRegistrationDate(parts[6]);
            return user;
        } catch (Exception e) {
            return null;
        }
    }
}
