package com.example.mongopersistance.dto2;

import lombok.Data;

@Data
public class PaymentToDoStatus {

    private PaymentToDo paymentToDo;
    private PaymentStatus paymentStatus;
}
