package com.example.mongopersistance.controller;


import com.example.mongopersistance.domain.Email;
import com.example.mongopersistance.dto2.PaymentStatus;
import com.example.mongopersistance.dto2.PaymentToDo;
import com.example.mongopersistance.dto2.PaymentToDoStatus;
import com.example.mongopersistance.dto2.UserCreation;
import com.example.mongopersistance.repository.EmailRepository;
import com.example.mongopersistance.service.EmailSenderService;
import com.example.mongopersistance.service.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class EmailController {

    KafkaTemplate<String, Object> kafkaTemplate;

    EmailService emailService;


    private static final String TOPICSuccess="PaymentSuccess";
    private static final String TOPICEmail="EmailAllPaid";
    private static final String TOPICUser="UserCreation";
    //private static final String TOPICFailure="PaymentFailureTopic";


    @PostMapping("/user")
    public String publish(@RequestBody PaymentToDoStatus paymentToDoStatus){
            kafkaTemplate.send(TOPICSuccess,paymentToDoStatus);
            return "paymentToDoStatus";
    }
    @PostMapping("/createUser")
    public String userCreation(@RequestBody UserCreation userCreation){
        kafkaTemplate.send(TOPICUser,userCreation);
        return "user-created";
    }


    @GetMapping()
    public List<Email> getAllPaymentStatus(){
          return emailService.getAll();
    }
    @GetMapping("/paymentstatus/paid")
    public List<Email> getAllPaidPayment(){
        return emailService.findByPaymentStatusPaid();
    }
    @GetMapping("/paymentstatus/failed")
    public List<Email> getAllFailedPayment(){
        return emailService.findByPaymentStatusFailed();
    }

}
