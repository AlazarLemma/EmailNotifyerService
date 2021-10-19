package com.example.mongopersistance.domain;

import com.example.mongopersistance.dto2.PaymentToDo;
import lombok.Data;

@Data
public class Email {
    //private PaymentToDo paymentToDo;
    private String status;
    private EmailStatus emailStatus;
}
