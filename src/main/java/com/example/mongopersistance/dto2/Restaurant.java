package com.example.mongopersistance.dto2;

import lombok.Data;

@Data
public class Restaurant {
    private String id;
    private String email;
    private String restaurantName;
    private Address address;
}
