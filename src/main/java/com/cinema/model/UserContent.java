package com.cinema.model;

import lombok.Data;
import java.io.Serializable;

@Data
public abstract class UserContent implements Serializable {
    protected Integer id;
    protected Integer userId;
    protected String userName;
    protected String date;
    
    public abstract void moderate();
}
