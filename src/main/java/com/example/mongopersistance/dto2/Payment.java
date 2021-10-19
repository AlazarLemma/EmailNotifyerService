package com.example.mongopersistance.dto2;

import lombok.Data;

@Data
public class Payment {
    private String cardNumber;
    private String expireMonth;
    private String expireYear;
    private String ccv;
}
