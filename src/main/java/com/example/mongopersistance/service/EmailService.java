package com.example.mongopersistance.service;


import com.example.mongopersistance.domain.Email;
import com.example.mongopersistance.domain.EmailStatus;
import com.example.mongopersistance.dto2.PaymentStatus;
import com.example.mongopersistance.dto2.PaymentToDoStatus;
import com.example.mongopersistance.repository.EmailRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmailService {

    public ObjectMapper objectMapper;
    public EmailRepository emailRepository;
    public ModelMapper modelMapper;
    private final EmailSenderService emailSenderService;




    public List<Email> getAll()
    {
        return emailRepository
                .findAll();
    }

    public List<Email> findByPaymentStatusPaid(){
        return emailRepository.findByPaymentStatus("PAID");
    }

    public List<Email> findByPaymentStatusFailed(){
        return emailRepository.findByPaymentStatus("FAILED");
    }

    @KafkaListener(topics = "PaymentSuccessTopic", groupId = "group_id")
    public String post(String paymentDtoStatus) {
        System.out.println("success user");
        try {
           PaymentToDoStatus paymentToDoStatus1 =objectMapper.readValue(paymentDtoStatus,PaymentToDoStatus.class);
            Email email = modelMapper.map(paymentToDoStatus1, Email.class);
            email.setEmailStatus(EmailStatus.SENT);
            email.setUserName(paymentToDoStatus1
                    .getPaymentToDo()
                    .getOrder()
                    .getUser()
                    .getUserName());
            emailRepository.save(email);
            emailSenderService.sendEmail(paymentToDoStatus1
                            .getPaymentToDo()
                            .getOrder()
                            .getUser()
                            .getEmail(),
                    "Our Delighted Customer "+ paymentToDoStatus1.getPaymentToDo().getOrder().getUser().getUserName().toUpperCase(Locale.ROOT)+
                            "\n your Order number that made at " + LocalDateTime.now()
                            +" Successfully accepted with total of ",
                    email.getPaymentStatus()+ " Payment " + LocalDateTime.now() );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "Post_Done";
    }
//    @KafkaListener(topics = "PaymentFailureTopic", groupId = "group_id")
//    public String postFailure(String user) {
//        System.out.println("fail User");
//        try {
//            User user1=objectMapper.readValue(user,User.class);
//            UserDTO userDTO = modelMapper.map(user1, UserDTO.class);
//            userDTO.setStatus(EmailStatus.FAILED);
//            emailSenderService.sendEmail(userDTO.getEmail(),
//                    "Our Delighted Customer "+ userDTO.getName().toUpperCase(Locale.ROOT)+
//                            "\n your Order that made at " + LocalDateTime.now()
//                            +userDTO.getStatus(),
//                    userDTO.getStatus()+ " Payment EmailStatus" +LocalDateTime.now());
//            userRepository.save(userDTO);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        return "Post_Done";
//    }
}

