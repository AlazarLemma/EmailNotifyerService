package com.example.mongopersistance.service;

import com.example.mongopersistance.domain.UserCreationDto;
import com.example.mongopersistance.dto2.UserCreation;
import com.example.mongopersistance.repository.EmailRepository;
import com.example.mongopersistance.repository.UserCreationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Locale;

@Service
@AllArgsConstructor
public class UserService {

    public ObjectMapper objectMapper;
    public UserCreationRepository userCreationRepository;
    public ModelMapper modelMapper;
    private final EmailSenderService emailSenderService;



    @KafkaListener(topics = "UserCreation", groupId = "group_id")
    public String creation(String userCreation) {
        try {
            UserCreation userCreation1 =objectMapper.readValue(userCreation,UserCreation.class);
            System.out.println(userCreation1.getRole());
            userCreationRepository.save(userCreation1);
            emailSenderService.sendEmail(userCreation1.getEmail()
                    ,
                    "Welcome To our Delivery Product Service"+ userCreation1.getUserName().toUpperCase(Locale.ROOT)+
                            "\n YOUR ACCOUNT HAS BEEN CREATED" + LocalDateTime.now()
                            ,
                    userCreation1.getUserName() + " Account Creation " + LocalDateTime.now() );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "Post_Done";
    }
}
