package com.cinema.model;

import lombok.Data;
import java.io.Serializable;

@Data
public abstract class Media implements Serializable {
    protected Integer id;
    protected String title;
    protected String description;
    
    public abstract void displayDetails();
}
