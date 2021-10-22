package com.example.mongopersistance.dto2;


import lombok.Data;

import java.util.List;

@Data
public class Order {
    private Long id;
    //private Long orderId;
    private User user;
    private List<Food> foods;
    private Restaurant restaurant;
    private Payment payment;
    private Dasher dasher;
}
