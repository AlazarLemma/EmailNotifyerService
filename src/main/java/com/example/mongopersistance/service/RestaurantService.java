package com.example.mongopersistance.service;

import com.example.mongopersistance.domain.UserCancledOrder;
import com.example.mongopersistance.repository.UserCancledRepository;
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
public class RestaurantService {

    private UserCancledRepository userCancledRepository;
    private final EmailSenderService emailSenderService;
    public ObjectMapper objectMapper;
    public ModelMapper modelMapper;


    @KafkaListener(topics = "PaymentCancel", groupId = "group_id")
    public String orderCanceled(String userCancel) {
        try {
            UserCancledOrder userCancledOrder =objectMapper.readValue(userCancel, UserCancledOrder.class);
           //
          //  restaurantRepository.save(restaurant1);
            userCancledRepository.save(userCancledOrder);
            emailSenderService.sendEmail(userCancledOrder.getUser().getEmail()
                    ,
                    "Dear"+ userCancledOrder
                            .getUser().getUserName()
                            .toUpperCase(Locale.ROOT)
                            + "Your Order had been canceled ",
                            "\n Order CANCELED" + LocalDateTime.now());

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "Post_Done";
    }
}
