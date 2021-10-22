package com.example.mongopersistance.dto2;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Address {
    private String zipCode;
    private String city;
    private String hoseNumber;
    private String streetName;
    private double latitude;
    private double longitude;
}
