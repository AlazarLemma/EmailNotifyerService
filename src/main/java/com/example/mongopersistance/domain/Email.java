package com.example.mongopersistance.domain;

import com.example.mongopersistance.dto2.PaymentStatus;
import com.example.mongopersistance.dto2.PaymentToDo;
import lombok.Data;

@Data
public class Email {
    //private PaymentToDo paymentToDo;
    private PaymentStatus paymentStatus;
    private EmailStatus emailStatus;
    private String UserName;

}
