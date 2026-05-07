package com.cinema.model;

import lombok.Data;
import java.io.Serializable;

/**
 * OOP CONCEPT: ABSTRACTION & ENCAPSULATION
 * Person is an abstract class representing common attributes of all people in the system.
 * It cannot be instantiated directly, demonstrating abstraction.
 * Fields are protected to allow inheritance while maintaining encapsulation.
 */
@Data
public abstract class Person implements Serializable {
    protected String name;
    protected String email;
    
    /**
     * OOP CONCEPT: POLYMORPHISM
     * Abstract method to be overridden by subclasses.
     */
    public abstract void displayInfo();
}
