package com.example.mongopersistance.dto2;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Payment {
    private String cardNumber;
    private String expireMonth;
    private String expireYear;
    private String ccv;
}
