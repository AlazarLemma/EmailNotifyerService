package com.example.mongopersistance.dto2;

import lombok.Data;

@Data
public class User {
    private long id;
    private String userName;
    private String email;
    private Address address;
}
