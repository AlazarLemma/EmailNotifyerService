package com.example.mongopersistance.controller;


import com.example.mongopersistance.dto2.PaymentToDo;
import com.example.mongopersistance.dto2.PaymentToDoStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PaymentController {

    KafkaTemplate<String, PaymentToDoStatus> kafkaTemplate;


    private static final String TOPICSuccess="PaymentSuccessTopic";
    private static final String TOPICFailure="PaymentFailureTopic";


    @PostMapping("/user")
    public String publish(@RequestBody PaymentToDoStatus paymentToDoStatus){
            kafkaTemplate.send(TOPICSuccess,paymentToDoStatus);
            return "paymentToDoStatus";
    }
}
